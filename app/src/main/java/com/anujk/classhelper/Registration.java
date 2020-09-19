package com.anujk.classhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class Registration extends AppCompatActivity {

    EditText name,reg_id,password,branch,section,year,bdate;
    Bundle b;String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent intent=getIntent();
        b=intent.getExtras();
        type=(String)b.get("type");
        Toast t= Toast.makeText(this,type,Toast.LENGTH_LONG);
        t.show();

            name = (EditText) findViewById(R.id.input_name);
            reg_id = (EditText) findViewById(R.id.register_id);
            password = (EditText) findViewById(R.id.input_password);
            branch = (EditText) findViewById(R.id.input_branch);
            section = (EditText) findViewById(R.id.input_section);
            year = (EditText) findViewById(R.id.input_year);
            bdate = (EditText) findViewById(R.id.input_bdate);


    }

    public void onReg(View view){
        String name,reg_id,password,branch,section,year,bdate,type1,result="";
        name=this.name.getText().toString();
        reg_id= this.reg_id.getText().toString();
        password= this.password.getText().toString();
        branch= this.branch.getText().toString();
        section= this.section.getText().toString();
        year= this.year.getText().toString();
        bdate= this.bdate.getText().toString();
        type1="register";
        BackgroundWorker bw=new BackgroundWorker(this);
        try {
            result= bw.execute(type,type1,name,reg_id,password,branch,year,section,bdate).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(result.equals("insert Successfull")){
            Toast.makeText(getApplicationContext(),"REGISTERED SUCCESSFULLY",Toast.LENGTH_LONG);
        }

    }
}
