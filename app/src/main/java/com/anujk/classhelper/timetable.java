package com.anujk.classhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class timetable extends Fragment implements View.OnClickListener {

    View myView;
    ImageView iv;
    Button reload;
    String url, section;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.timetable, container, false);
        iv = (ImageView) myView.findViewById(R.id.timetable);
        Main2Activity ob = new Main2Activity();
        section = ob.data[5].toUpperCase();
        url = "http://tristichic-tactic.000webhostapp.com/uploads/" + section + ".jpg";
        Picasso.get().load(url).into(iv);
        reload = (Button) myView.findViewById(R.id.refresh);
        reload.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == reload.getId()) {
            url = "http://tristichic-tactic.000webhostapp.com/uploads/" + section + ".jpg";
            Picasso.get().load(url).into(iv);
        }
    }
}
