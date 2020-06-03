package app.advance.hcmut.cse.smartgardensystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuFragment extends Fragment implements View.OnClickListener{
    ImageButton viewDevice;
    TextView txtViewDevice;
    ImageButton addDevice;
    TextView txtAddDevice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu1, container, false);

        viewDevice = view.findViewById(R.id.btn_menu_view_device);
        viewDevice.setOnClickListener(this);
        txtViewDevice = view.findViewById(R.id.txt_menu_view_device);
        txtViewDevice.setOnClickListener(this);
        addDevice = view.findViewById(R.id.btn_menu_add_device);
        addDevice.setOnClickListener(this);
        txtAddDevice = view.findViewById(R.id.txt_menu_add_new_advice);
        txtAddDevice.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if ((view.getId() == R.id.btn_menu_view_device) ||
                (view.getId() == R.id.txt_menu_view_device))
        {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DeviceTab.class);
            getActivity().startActivity(intent);
        }

        if ((view.getId() == R.id.btn_menu_add_device) ||
                (view.getId() == R.id.txt_menu_add_new_advice))
        {
            Intent intent = new Intent();
            intent.setClass(getActivity(), RegisterDevice.class);
            getActivity().startActivity(intent);
        }
    }
}