package app.advance.hcmut.cse.smartgardensystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DeviceSetting extends AppCompatActivity {
    Button btn_return;
    Button btn_summit;
    ImageButton menu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_device_properties);

        btn_summit = findViewById(R.id.btn_summit);
        btn_summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_summit){
                    summitChangeSetting();
                }
            }
        });

        btn_return = findViewById(R.id.btn_settingReturn);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_settingReturn){
                    returnDeviceTab();
                }
            }
        });

        menu = findViewById(R.id.btn_menuSetting);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
    }

    public void returnDeviceTab(){
        Intent intent = new Intent(this, DeviceTab.class);
        startActivity(intent);
    }

    public void summitChangeSetting(){
        Intent intent = new Intent(this, DeviceSettingMessage.class);
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
            fragmentManager.beginTransaction().replace(R.id.setting_layout, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}