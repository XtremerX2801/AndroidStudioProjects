package app.advance.hcmut.cse.smartgardensystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DeviceTab extends AppCompatActivity implements View.OnClickListener {
    Button device_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_tab);

        device_list = findViewById(R.id.textView2);
        device_list.setOnClickListener(this);

        selectFragment(0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textView2){
            selectFragment(0);
        }
    }

    Fragment fragment = null;
    String fragmentTag = "";

    public void selectFragment(int position) {
        Class fragmentClass = null;
        switch (position) {
            case 0:
                fragmentClass = DeviceFragment.class;
                fragmentTag = "ActionFragment";
                break;
            default:
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();

            Bundle bundle = new Bundle();

            bundle.putString("fragmentTag", fragmentTag);

            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
