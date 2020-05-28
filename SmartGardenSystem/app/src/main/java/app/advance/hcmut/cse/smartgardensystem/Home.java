package app.advance.hcmut.cse.smartgardensystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends Activity implements View.OnClickListener{
    ImageButton viewDevice;
    TextView txtViewDevice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        viewDevice = findViewById(R.id.btn_view_device);
        viewDevice.setOnClickListener(this);
        txtViewDevice = findViewById(R.id.txt_view_device);
        txtViewDevice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if ((view.getId() == R.id.btn_view_device) ||
        (view.getId() == R.id.txt_view_device)){
            Intent intent = new Intent(this, DeviceTab.class);
            startActivity(intent);
        }
    }
}
