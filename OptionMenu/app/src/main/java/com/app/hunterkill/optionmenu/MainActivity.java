package com.app.hunterkill.optionmenu;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements SerialInputOutputManager.Listener{

    private static final String ACTION_USB_PERMISSION = "com.android.recipes.USB_PERMISSION";

    TextView txtOut;
    EditText sendData;
    UsbSerialPort port;
    Button dataEnter;

//    private Thread readThread;
//    private boolean mRunning;
//    private UsbHidDevice device = null;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOut = findViewById(R.id.txtOut);
        sendData = findViewById(R.id.txt_send_data);
        dataEnter = findViewById(R.id.btn_send_data);

//        String getSentData = sendData.getText().toString();
        sendData.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    txtOut.append("\n" + sendData.getText());
                    try {   /* push TextInput to UART */
                        port.write(sendData.getText().toString().getBytes(), 1000);
                    } catch (Exception e) {
                        txtOut.append("\n Message send failed");
                    }
                    //scroll to lastest text
                    sendData.getText().clear();
                    return true;
                }
                return false;
            }
        });

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            Log.d("UART", "UART is not available");
            txtOut.setText("UART is not available");
        }else {
            Log.d("UART", "UART is available");
            txtOut.setText("UART is available");

            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {
                manager.requestPermission(driver.getDevice(), PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));
            } else {
                // Most devices have just one port (port 0)
                port = driver.getPorts().get(0);
                try {
                    port.open(connection);
                    port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
                    SerialInputOutputManager usbIoManager = new SerialInputOutputManager(port, this);
                    Executors.newSingleThreadExecutor().submit(usbIoManager);

                } catch (Exception e) {
                    txtOut.setText("Sending a message is fail");
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.add("Option 1");
        MenuItem menuItem2 = menu.add("Option 2");
        MenuItem menuItem3 = menu.add("Option 3");
        MenuItem menuItem4 = menu.add("Option 4");

        menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menuItem3.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menuItem4.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            port.close();
        }catch (Exception e) {}
    }

    @Override
    public void onNewData(final byte[] data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String receivedData = new String(data);
                txtOut.append("\n\t" + receivedData);
            }
        });
    }

    @Override
    public void onRunError(Exception e) {

    }
}

