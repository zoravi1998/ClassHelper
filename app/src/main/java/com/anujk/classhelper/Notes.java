package com.anujk.classhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Notes extends Fragment implements AdapterView.OnItemClickListener {

    View myView;
    WebView wv;
    String myItems[]={"MECH","CSE","ECE","EEE","Automotive","CIVIL"};;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView=inflater.inflate(R.layout.notes,container,false);
        populateListView();


        return myView;

    }
    public void populateListView(){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, myItems);
        ListView list =myView.findViewById(R.id.listview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast t = Toast.makeText(getActivity(),myItems[position],Toast.LENGTH_SHORT);
        t.show();
        Intent intent=new Intent(getActivity(),webdrive.class);
        intent.putExtra("branch",myItems[position]);
        startActivity(intent);


    }
}
