package app.advance.hcmut.cse.smartgardensystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ReportMonthFragment extends Fragment {
    ImageButton month_exit;
    EditText month_enter_month, month_enter_year;
    Button month_enter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_month_setting, container, false);

        month_exit = view.findViewById(R.id.month_exiting);
        month_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.month_exiting){
                    returnViewReport();
                }
            }
        });

        month_enter_month = view.findViewById(R.id.month_enter_month);
        month_enter_month.setInputType(InputType.TYPE_CLASS_NUMBER);

        month_enter_year = view.findViewById(R.id.month_enter_year);
        month_enter_year.setInputType(InputType.TYPE_CLASS_NUMBER);

        month_enter = view.findViewById(R.id.month_entering);

        month_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.month_entering){
                    String month = month_enter_month.getText().toString();
                    String year = month_enter_year.getText().toString();
                    String date = month + "-" + year;
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
        intent.putExtra("set_month", date);
        getActivity().startActivity(intent);
    }
}
