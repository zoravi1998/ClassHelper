package com.anujk.classhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String data[]=new String[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        collectData();
        Toast.makeText(this,"!!! WELCOME "+data[1]+"!!!",Toast.LENGTH_LONG).show();
    }


    private void collectData() {
        String portal=getIntent().getExtras().get("portal").toString();
        String regid=getIntent().getExtras().get("username").toString();
        BackgroundWorker bw=new BackgroundWorker(this);
        String result= "";
        try {
            result=bw.execute(portal,"read",regid).get();
            JSONObject jo=new JSONObject(result);
            int a=jo.getInt("success");
            if(a==1){
                JSONArray details= jo.getJSONArray("details");
                for(int i=0;i<details.length();i++){
                    JSONObject details1=details.getJSONObject(i);
                    data[1]=details1.getString("name");
                    data[2]=details1.getString("reg_id");
                    data[3]=details1.getString("branch");
                    data[4]=details1.getString("year");
                    data[5]=details1.getString("section");
                    data[6]=details1.getString("birth_date");
                }
            }
            else{
                Toast.makeText(this,"No data fetched",Toast.LENGTH_LONG);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            FragmentManager fragment=getSupportFragmentManager();
        if (id == R.id.FirstLayout) {
            fragment.beginTransaction().replace(R.id.content_frame,new timetable()).commit();
        } else if (id == R.id.SecondLayout) {
            fragment.beginTransaction().replace(R.id.content_frame, new Notes()).commit();
        } else if (id == R.id.ThirdLayout) {
            fragment.beginTransaction().replace(R.id.content_frame, new ThirdFragment()).commit();
        } else if (id == R.id.profile) {
            fragment.beginTransaction().replace(R.id.content_frame, new profile()).commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
