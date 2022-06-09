package com.anujk.classhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ThirdFragment extends Fragment implements View.OnClickListener {

    View myView;
    EditText subjects[] = new EditText[12];
    Spinner grades[] = new Spinner[12];
    TextView tv;
    Button calc;
    int edittxtId[] = {0, R.id.editText, R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5, R.id.editText6, R.id.editText7
            , R.id.editText8, R.id.editText9, R.id.editText10};
    int spinnerId[] = {0, R.id.spinner, R.id.spinner1, R.id.spinner2, R.id.spinner3, R.id.spinner4, R.id.spinner5, R.id.spinner6, R.id.spinner7
            , R.id.spinner8, R.id.spinner9, R.id.spinner10};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.third_layout, container, false);
        initialize();
        return myView;
    }

    private void initialize() {
        for (int i = 1; i <= 11; i++) {
            subjects[i] = (EditText) myView.findViewById(edittxtId[i]);
            grades[i] = (Spinner) myView.findViewById(spinnerId[i]);
        }
        tv = (TextView) myView.findViewById(R.id.gpa);
        calc = (Button) myView.findViewById(R.id.calculate);
    }

    private void calculate() {
        int sum = 0, credits = 0;
        int c = 0, gp = 0;
        for (int i = 1; i <= 11; i++) {
            int num = Integer.parseInt(subjects[i].getText().toString());
            if (num > 0) {
                credits += num;
                String ch = grades[i].getSelectedItem().toString();
                switch (ch) {
                    case "O":
                        gp = 10;
                        break;
                    case "A+":
                        gp = 9;
                        break;
                    case "A":
                        gp = 8;
                        break;
                    case "B+":
                        gp = 7;
                        break;
                    case "B":
                        gp = 6;
                        break;
                    case "C":
                        gp = 5;
                        break;
                    case "F":
                        gp = 0;
                        break;
                }
                sum = sum + (num * gp);
            }
        }
        double gpa = sum / credits;
        String g = String.valueOf(gpa);
        tv.setText(g);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculate) {
            calculate();
        }
    }
}
