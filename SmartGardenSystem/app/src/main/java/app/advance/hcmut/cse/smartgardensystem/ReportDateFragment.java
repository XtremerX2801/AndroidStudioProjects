package app.advance.hcmut.cse.smartgardensystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ReportDateFragment extends Fragment {
    ImageButton date_exit;
    EditText enter_day, enter_month, enter_year;
    Button date_enter;
    String date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_date_setting, container, false);

        date_exit = view.findViewById(R.id.date_exiting);
        date_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.date_exiting){
                    returnViewReport();
                }
            }
        });
        enter_day = view.findViewById(R.id.date_enter_day);
//        enter_day.setInputType(InputType.TYPE_CLASS_NUMBER);

        enter_month = view.findViewById(R.id.date_enter_month);
//        enter_month.setInputType(InputType.TYPE_CLASS_NUMBER);

        enter_year = view.findViewById(R.id.date_enter_year);
//        enter_year.setInputType(InputType.TYPE_CLASS_NUMBER);

        date_enter = view.findViewById(R.id.date_entering);

        date_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.date_entering){
                    String day = enter_day.getText().toString();
                    String month = enter_month.getText().toString();
                    String year = enter_year.getText().toString();
                    date = day + "-" + month + "-" + year;
                    returnViewReportWithDate(date);
                }
            }
        });

        return view;
    }

    public void returnViewReport(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), ViewReport.class);
        getActivity().startActivity(intent);
    }

    public void returnViewReportWithDate(String date){
        Intent intent = new Intent();
        intent.setClass(getActivity(), ViewReport.class);
        intent.putExtra("set_date", date);
        getActivity().startActivity(intent);
    }
}