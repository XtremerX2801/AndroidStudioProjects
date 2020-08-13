package com.example.myapplication;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hunterkill.terminaluart.util.HexDump;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import androidx.fragment.app.Fragment;

/**
 * Created by hunterkill on 20/05/2020.
 */

public class TerminalFragment extends Fragment implements SerialInputOutputManager.Listener {
    private static final String ACTION_USB_PERMISSION = "com.android.recipes.USB_PERMISSION";
    private enum Connected { False, Pending, True }
    private int deviceId, portNum, baudRate;
    private String newline = "\n";
    private TextView txtReceiveText;

    private SerialInputOutputManager usbIoManager;
    private Handler mainLooper = new Handler(Looper.getMainLooper());

    private UsbSerialPort usbSerialPort;


    // check connection
    private Connected connected = Connected.False;

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        // meo dung
        deviceId = 1212;
        portNum = 233231;
        baudRate = 1434324;
        startMQTT();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terminal, container, false);
        txtReceiveText = view.findViewById(R.id.receive_text);
        txtReceiveText.setMovementMethod(ScrollingMovementMethod.getInstance()); // allow scrolling view


        //action for sent data
//        final TextView sendText = view.findViewById(R.id.send_text);
//        final ImageButton  btnSend = view.findViewById(R.id.btn_send_message);



        UsbManager manager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            Log.d("UART", "UART is not available");
            connected = Connected.False;

        }else {
            Log.d("UART", "UART is available");
            connected = Connected.True;
            txtReceiveText.append("Connected\n");

            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {
                manager.requestPermission(driver.getDevice(), PendingIntent.getBroadcast(getActivity(), 0, new Intent(ACTION_USB_PERMISSION), 0));
            } else {
                // Most devices have just one port (port 0)
                usbSerialPort = driver.getPorts().get(0);

                try {
                    usbSerialPort.open(connection);

                    usbSerialPort.setParameters(SerialValue.baudrate, SerialValue.dataBits, SerialValue.stopBits, SerialValue.parity);
                    // doi cho understand bug ; fucking stupid
                    SerialInputOutputManager usbIoManager = new SerialInputOutputManager(usbSerialPort, this);
                    Executors.newSingleThreadExecutor().submit(usbIoManager);


                } catch (Exception e) {

                }

            }
        }


        return  view;
    }
//    // send to phone
    private void sendMessage(String str) {
        if(connected != Connected.True) {
            Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            SpannableStringBuilder spn = new SpannableStringBuilder(str+'\n');
            txtReceiveText.append(spn);
            byte[] data = (str + newline).getBytes();
            usbSerialPort.write(data, 2000);
            System.out.println(str);

        } catch (Exception e) {
            System.out.println("Send Error");
            Toast.makeText(getActivity(), "Send Error", Toast.LENGTH_SHORT).show();
        }
    }

    private  boolean isDigit(String str) {
        boolean numeric = true;

        try {
            Double num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        return numeric;
    }

    private void processingValue(String str) {
        String[] arrOfStr = str.split(",", 5);
        String tempValue = arrOfStr[1];
        String lightValue =  arrOfStr[2].substring(0, arrOfStr[2].length() - 1);

        if (isDigit(tempValue) && isDigit(lightValue)) {
            System.out.println(tempValue);
            System.out.println(lightValue);

            sendDataToThingSpeak(tempValue, lightValue);
        }


    }

    private String storeMessage = "";
    private void receive(byte[] data) {
        String str = new String(data);
        storeMessage += str;

        if (str.contains("!")) {
            txtReceiveText.append(getCurrentTimeStamp() + " : ");
            txtReceiveText.append(storeMessage);
            txtReceiveText.append(newline);
            processingValue(storeMessage);
            storeMessage = "";

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            usbSerialPort.close();
        }catch (Exception e) {}
    }


    @Override
    public void onNewData(byte[] data) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                receive(data);
            }
        });

    }


    @Override
    public void onRunError(Exception e) {

    }

    MQTTHelper mqttHelper;

    private void startMQTT() {
        mqttHelper = new MQTTHelper(getActivity().getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.e("Mqtt-check", topic);
                Log.e("Mqtt-check", mqttMessage.toString());
               sendMessage(mqttMessage.toString());

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

        });

    }

    private void sendDataToThingSpeak(String temp, String light){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        String apiURL = "https://api.thingspeak.com/update?api_key=G6WED96LA7KLQQ2J&field1=" + temp + "&field2=" + light;
        Request request = builder.url(apiURL).build();
        System.out.println("send done");

        publishNewSignal1(temp);
        System.out.println("call temp");
//        publishNewSignal2(light);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }


    private void publishNewSignal1(String signal){
        MqttMessage message = new MqttMessage();
        message.setPayload(signal.getBytes());
        try {
            mqttHelper.mqttAndroidClient.publish(mqttHelper.subscriptionTopic1, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
//
//    private void publishNewSignal2(String signal){
//        MqttMessage message = new MqttMessage();
//        message.setPayload(signal.getBytes());
//        try {
//            mqttHelper.mqttAndroidClient.publish(mqttHelper.subscriptionTopic2, message);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }



}
//    cd D:\IDE\android-sdk\platform-tools

//    adb connect  192.168.56.104
