package arduino.android.bku.videoviewassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_Action, btn_Comedy, btn_Scifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Action = findViewById(R.id.btn_Action);
        btn_Comedy = findViewById(R.id.btn_Comedy);
        btn_Scifi = findViewById(R.id.btn_Scifi);

        btn_Action.setOnClickListener(this);
        btn_Comedy.setOnClickListener(this);
        btn_Scifi.setOnClickListener(this);

        selectFragment(0);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Action){
            selectFragment(0);
            btn_Action.setBackgroundColor(R.color.colorPrimary);
            btn_Comedy.setBackgroundColor(Color.TRANSPARENT);
            btn_Scifi.setBackgroundColor(Color.TRANSPARENT);
        }
        if (view.getId() == R.id.btn_Comedy){
            selectFragment(1);
            btn_Comedy.setBackgroundColor(R.color.colorPrimary);
            btn_Action.setBackgroundColor(Color.TRANSPARENT);
            btn_Scifi.setBackgroundColor(Color.TRANSPARENT);
        }
        if (view.getId() == R.id.btn_Scifi){
            selectFragment(2);
            btn_Scifi.setBackgroundColor(R.color.colorPrimary);
            btn_Action.setBackgroundColor(Color.TRANSPARENT);
            btn_Comedy.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    Fragment fragment = null;
    String fragmentTag = "";

    public void selectFragment(int position) {
        Class fragmentClass = null;
        switch (position) {
            case 0:
                fragmentClass = ActionFragment.class;
                fragmentTag = "ActionFragment";
                break;
            case 1:
                fragmentClass = ComedyFragment.class;
                fragmentTag = "ComedyFragment";
                break;
            case 2:
                fragmentClass = ScifiFragment.class;
                fragmentTag = "ScifiFragment";
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
