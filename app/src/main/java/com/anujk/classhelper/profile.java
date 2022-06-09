package com.anujk.classhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profile extends Fragment {

    View myView;
    int id[] = {0, R.id.name, R.id.regid, R.id.branch, R.id.year, R.id.section, R.id.birthdate};
    TextView tv[] = new TextView[7];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.profile, container, false);
        String data[] = Main2Activity.data;
        initialize();
        for (int k = 1; k < 7; k++)
            tv[k].setText(data[k]);
        return myView;
    }

    private void initialize() {
        for (int i = 1; i <= 6; i++) {
            tv[i] = myView.findViewById(id[i]);
        }
    }
}
