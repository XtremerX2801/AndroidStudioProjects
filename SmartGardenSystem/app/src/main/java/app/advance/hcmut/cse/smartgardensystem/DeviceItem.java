package app.advance.hcmut.cse.smartgardensystem;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class DeviceItem extends AppCompatActivity implements View.OnClickListener{
    Button btn_return;
    ImageButton home;
    ImageButton menu1;

    private static final String TAG = "DeviceItem";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_item);

        btn_return = findViewById(R.id.item_returnButton);
        btn_return.setOnClickListener(this);

        home = findViewById(R.id.btn_item_home);
        home.setOnClickListener(this);

        menu1 = findViewById(R.id.btn_menu2);
        menu1.setOnClickListener(this);

        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client = new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883", clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        String topic = "foo/bar";
        String payload = "the payload";
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            message.setRetained(true);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(){

    }

    @Override
    public void onClick(View view) {
        if((view.getId() == R.id.item_returnButton) ||
                (view.getId() == R.id.btn_item_home)){
            deviceTab_return();
        }
        if (view.getId() == R.id.btn_menu2){
            menu();
        }
    }

    public void deviceTab_return() {
        Intent intent = new Intent(this, DeviceTab.class);
        startActivity(intent);
    }

    Fragment fragment = null;
    String fragmentTag = "";
    Class fragmentClass = null;
    int menuState;

    public void menu() {
        menuState += 1;
        if (menuState == 1) {
            fragmentClass = MenuFragment.class;
            fragmentTag = "MenuFragment";
        } else {
            menuState = 0;
            fragmentClass = FragmentNothing.class;
            fragmentTag = "NothingFragment";
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();

            Bundle bundle = new Bundle();

            bundle.putString("fragmentTag", fragmentTag);

            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.device_item, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
