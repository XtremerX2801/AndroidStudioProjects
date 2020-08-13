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

public class ReportYearFragment extends Fragment {
    ImageButton year_exit;
    EditText year_enter_year;
    Button year_enter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_year_setting, container, false);

        year_exit = view.findViewById(R.id.year_exiting);
        year_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.year_exiting){
                    returnViewReport();
                }
            }
        });

        year_enter_year = view.findViewById(R.id.year_enter_year);
        year_enter_year.setInputType(InputType.TYPE_CLASS_NUMBER);

        year_enter = view.findViewById(R.id.year_entering);

        year_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.year_entering){
                    String year = year_enter_year.getText().toString();
                    returnViewReportWithDate(year);
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
        intent.putExtra("set_year", date);
        getActivity().startActivity(intent);
    }
}
