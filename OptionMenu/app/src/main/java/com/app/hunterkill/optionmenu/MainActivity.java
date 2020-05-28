package com.app.hunterkill.optionmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.add("Option 1");
        MenuItem menuItem2 = menu.add("Option 2");
        MenuItem menuItem3 = menu.add("Option 3");

        // show in the action bar
        menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        // show in the action bar with condition
        menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;

    }
}

