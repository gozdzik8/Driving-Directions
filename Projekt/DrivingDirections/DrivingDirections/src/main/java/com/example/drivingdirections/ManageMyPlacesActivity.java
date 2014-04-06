package com.example.drivingdirections;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ManageMyPlacesActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button add_new_place;
    EditText new_place;
    ListView pListView;
    ArrayAdapter pArrayAdapter;
    ArrayList pArrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_places);
        new_place = (EditText) findViewById(R.id.new_place);

        add_new_place = (Button) findViewById(R.id.add_new_place);
        add_new_place.setOnClickListener(this);

        pListView = (ListView) findViewById(R.id.my_places);
        pArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                pArrayList);

        // Set the ListView to use the ArrayAdapter
        pListView.setAdapter(pArrayAdapter);
        pListView.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_my_places, menu);
        return true;
    }


    @Override
    public void onClick(View v) {

        pArrayList.add(0,  new_place.getText().toString() );
        pArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
