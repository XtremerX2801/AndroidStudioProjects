package app.advance.hcmut.cse.smartgardensystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Notification extends AppCompatActivity implements View.OnClickListener{
    ImageButton noti;
    ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_notication);

        noti = findViewById(R.id.notification);
        noti.setOnClickListener(this);

        menu = findViewById(R.id.imageButtonMenu);
        menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.notification){
            goToNotificationDevice();
        }
        if (view.getId() == R.id.imageButtonMenu){
            menu();
        }
    }

    public void goToNotificationDevice(){
        Intent intent = new Intent(this, NotificationDevice.class);
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
            fragmentManager.beginTransaction().replace(R.id.existing_notification, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
