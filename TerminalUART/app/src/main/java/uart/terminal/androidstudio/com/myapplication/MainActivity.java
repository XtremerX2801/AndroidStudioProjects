package uart.terminal.androidstudio.com.myapplication;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;



//https://github.com/rcties/PrinterPlusCOMM
//https://github.com/mik3y/usb-serial-for-android
public class MainActivity extends AppCompatActivity  implements SerialInputOutputManager.Listener {
    private static final String ACTION_USB_PERMISSION = "com.android.recipes.USB_PERMISSION";

    TextView txtOut;
    DrawerLayout mDrawerLayout;
    NavigationView settings_drawer;
    EditText TextInput;
    Button dataEnter;
//    Button params;


    UsbSerialPort port;


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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        TextInput = findViewById(R.id.txt_send_data);
        txtOut = findViewById(R.id.txtOut);
        dataEnter = findViewById(R.id.btn_send_data);
        txtOut.setMovementMethod(new ScrollingMovementMethod());

        dataEnter.setOnClickListener(view -> {
            txtOut.append("\n" + TextInput.getText());
            try {
                port.write(TextInput.getText().toString().getBytes(), 1000);
            } catch (Exception e) {
                txtOut.append("\n Message send failed");
            }
            TextInput.getText().clear();
        });

        mDrawerLayout = findViewById(R.id.drawer_layout);
        settings_drawer = findViewById(R.id.settings_drawer);
        settings_drawer.setClickable(false);

        ArrayAdapter<CharSequence> baudrates = ArrayAdapter.createFromResource(MainActivity.this, R.array.baudrate_array, android.R.layout.simple_spinner_item);
        Spinner spinner_baudrate = (Spinner) settings_drawer.getMenu().findItem(R.id.baudrate).getActionView();
        spinner_baudrate.setAdapter(baudrates);
        spinner_baudrate.setSelection(5);
        spinner_baudrate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner_baudrate.getSelectedItemPosition() == 0) {
                    Values.baudrate = 2400;
                } else if (spinner_baudrate.getSelectedItemPosition() == 1) {
                    Values.baudrate = 9600;
                } else if (spinner_baudrate.getSelectedItemPosition() == 2) {
                    Values.baudrate = 19200;
                } else if (spinner_baudrate.getSelectedItemPosition() == 3) {
                    Values.baudrate = 38400;
                } else if (spinner_baudrate.getSelectedItemPosition() == 4) {
                    Values.baudrate = 57600;
                } else if (spinner_baudrate.getSelectedItemPosition() == 5) {
                    Values.baudrate = 115200;
                }
                try {
                    port.setParameters(Values.baudrate, Values.dataBits, Values.stopBits, Values.parity);
//                    Toast.makeText(MainActivity.this, "Set baudrate to " + Values.baudrate, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    txtOut.append("\n error setting parameter.");
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> dataBits = ArrayAdapter.createFromResource(MainActivity.this, R.array.dataBits_array, android.R.layout.simple_spinner_item);
        Spinner spinner_dataBits = (Spinner) settings_drawer.getMenu().findItem(R.id.dataBits).getActionView();
        spinner_dataBits.setAdapter(dataBits);
        spinner_dataBits.setSelection(3);
        spinner_dataBits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner_dataBits.getSelectedItemPosition() == 0) {
                    Values.dataBits = UsbSerialPort.DATABITS_5;
                } else if (spinner_dataBits.getSelectedItemPosition() == 1) {
                    Values.dataBits = UsbSerialPort.DATABITS_6;
                } else if (spinner_dataBits.getSelectedItemPosition() == 2) {
                    Values.dataBits = UsbSerialPort.DATABITS_7;
                } else if (spinner_dataBits.getSelectedItemPosition() == 3) {
                    Values.dataBits = UsbSerialPort.DATABITS_8;
                }
                try {
                    port.setParameters(Values.baudrate, Values.dataBits, Values.stopBits, Values.parity);
//                    Toast.makeText(MainActivity.this, "Set data bits to " + Values.dataBits, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    txtOut.append("\n error setting parameter.");
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> parity = ArrayAdapter.createFromResource(MainActivity.this, R.array.parity_array, android.R.layout.simple_spinner_item);
        Spinner spinner_parity = (Spinner) settings_drawer.getMenu().findItem(R.id.parity).getActionView();
        spinner_parity.setAdapter(parity);
        spinner_parity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner_parity.getSelectedItemPosition() == 1) {
                    Values.parity = UsbSerialPort.PARITY_ODD;
                } else if (spinner_parity.getSelectedItemPosition() == 0) {
                    Values.parity = UsbSerialPort.PARITY_NONE;
                } else if (spinner_parity.getSelectedItemPosition() == 2) {
                    Values.parity = UsbSerialPort.PARITY_EVEN;
                }
                try {
                    port.setParameters(Values.baudrate, Values.dataBits, Values.stopBits, Values.parity);
//                    Toast.makeText(MainActivity.this, "Set parity " + Values.parity, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    txtOut.append("\n error setting parameter.");
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> stopBits = ArrayAdapter.createFromResource(MainActivity.this, R.array.stopbits_array, android.R.layout.simple_spinner_item);
        Spinner spinner_stopBits = (Spinner) settings_drawer.getMenu().findItem(R.id.stopBits).getActionView();
        spinner_stopBits.setAdapter(stopBits);
        //stopBits.getPosition("1");
        spinner_stopBits.setSelection(0);
        spinner_stopBits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner_stopBits.getSelectedItemPosition() == 0) {
                    Values.stopBits = UsbSerialPort.STOPBITS_1;
                } else if (spinner_stopBits.getSelectedItemPosition() == 1) {
                    Values.stopBits = UsbSerialPort.STOPBITS_2;
                } else if (spinner_stopBits.getSelectedItemPosition() == 2) {
                    Values.stopBits = UsbSerialPort.STOPBITS_1_5;
                }
                try {
                    port.setParameters(Values.baudrate, Values.dataBits, Values.stopBits, Values.parity);
//                    Toast.makeText(MainActivity.this, "Set stop bits to " + Values.stopBits, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    txtOut.append("\n error setting parameter.");
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            Log.d("UART", "UART is not available");
            txtOut.setText("UART is not available");
        } else {
            Log.d("UART", "UART is available");
            txtOut.setText("UART is available" + "\n");

            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {
                manager.requestPermission(driver.getDevice(), PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));
            } else {
                // Most devices have just one port (port 0)
                port = driver.getPorts().get(0);
                try {
                    port.open(connection);
                    port.setParameters(Values.baudrate, Values.dataBits, Values.stopBits, Values.parity);

                    SerialInputOutputManager usbIoManager = new SerialInputOutputManager(port, this);
                    Executors.newSingleThreadExecutor().submit(usbIoManager);
                } catch (Exception e) {
                    txtOut.setText("Message send failed");
                }

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(settings_drawer);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            port.close();
        } catch (Exception e) {
        }
    }

    String device_data;
    @Override
    public void onNewData(byte[] data) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String receivedData = new String(data);
                device_data += receivedData;
                txtOut.append(receivedData + "\n");
                if (device_data.contains("!")){
                    String[] value = device_data.split(",", 5);
                    String temperature = value[1];
                    String light = value[2].substring(0, value[2].length() - 1);
                    sendDataToThingSpeak(temperature, light);
                    device_data = "";
                }
            }
        });
    }

    @Override
    public void onRunError(Exception e) {

    }

    private void sendDataToThingSpeak(String temp, String light){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        String apiURL = "https://api.thingspeak.com/update?api_key=M2KSJMKX1JR48H00&field1=" + temp + "&field2=" + light;
        Request request = builder.url(apiURL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) { }

            @Override
            public void onResponse(Response response) throws IOException { }
        });
    }
}
