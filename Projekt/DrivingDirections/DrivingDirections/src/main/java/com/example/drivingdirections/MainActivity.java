package com.example.drivingdirections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class MainActivity extends Activity implements View.OnClickListener {

    Button bMyLocation, bDrivingDirections, bManageMyPlaces, bExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bMyLocation = (Button) findViewById(R.id.main_my_location);
        bDrivingDirections = (Button) findViewById(R.id.main_driving_directions);
        bManageMyPlaces = (Button) findViewById(R.id.main_manage_my_places);
        bExit = (Button) findViewById(R.id.main_exit);

        bMyLocation.setOnClickListener(this);
        bDrivingDirections.setOnClickListener(this);
        bManageMyPlaces.setOnClickListener(this);
        bExit.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()) {
            case R.id.main_my_location:
                i = new Intent(getApplicationContext(),MyLocationActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                //setContentView(R.layout.activity_my_location);
                break;
            case R.id.main_driving_directions:
                i = new Intent(getApplicationContext(),DrivingDirectionsActivity.class);
                startActivity(i);
                //setContentView(R.layout.activity_driving_directions);
                break;

            case R.id.main_manage_my_places:
                i = new Intent(getApplicationContext(),ManageMyPlacesActivity.class);
                startActivity(i);
                //setContentView(R.layout.activity_manage_my_places);
                break;
            case R.id.main_exit:
                finish();
                break;

        }
    }
}
