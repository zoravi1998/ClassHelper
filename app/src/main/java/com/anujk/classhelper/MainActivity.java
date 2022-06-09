package com.anujk.classhelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnClickListener {

    Button login, register, student, teacher;
    EditText uid, password;
    public String portal = "";
    int MY_PERMISSIONS_REQUEST_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        {
            uid = (EditText) (findViewById(R.id.reg_id));
            password = (EditText) findViewById(R.id.pass);
            login = (Button) findViewById(R.id.btn_login);
            register = (Button) findViewById(R.id.btn_reg);
            student = (Button) findViewById(R.id.btn_stu);
            teacher = (Button) findViewById(R.id.btn_teacher);
            login.setOnClickListener(this);
            register.setOnClickListener(this);

        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Toast t = Toast.makeText(this, "FILE STORAGE GRANTED", Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void studentClick(View v) {

        portal = "student";

    }

    public void teacherClick(View v) {
        portal = "teacher";
    }

    @Override
    public void onClick(View v) {
        if (portal.equals("student")) {
            if (v.getId() == R.id.btn_login) {
                Intent intent = new Intent(this, Main2Activity.class);
                BackgroundWorker bw = new BackgroundWorker(this);
                String uname, pass, type, result = "", type1 = portal;
                uname = uid.getText().toString();
                pass = password.getText().toString();
                type = "login";
                try {
                    result = bw.execute(type1, type, uname, pass).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result.equals("login success")) {
                    intent.putExtra("portal", portal);
                    intent.putExtra("username", uname);
                    startActivity(intent);
                }

            }
            if (v.getId() == R.id.btn_reg) {
                Intent intent = new Intent(this, Registration.class);
                intent.putExtra("type", portal);
                startActivity(intent);
            }
        } else if (portal.equals("teacher")) {
            if (v.getId() == R.id.btn_login) {
                Intent intent = new Intent(this, TeacherActivity.class);
                BackgroundWorker bw = new BackgroundWorker(this);
                String uname, pass, type, result = "", type1 = "";
                uname = uid.getText().toString();
                pass = password.getText().toString();
                type = "login";
                try {
                    result = bw.execute(type1, type, uname, pass).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result.equals("login success")) {
                    startActivity(intent);
                }

            }
            if (v.getId() == R.id.btn_reg) {
                Intent intent = new Intent(this, Registration.class);
                intent.putExtra("type", portal);
                startActivity(intent);
            }
        } else {
            Toast t = Toast.makeText(this, "NO PORTAL SELECTED", Toast.LENGTH_SHORT);
            t.show();
        }

    }
}

