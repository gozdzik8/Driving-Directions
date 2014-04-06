package com.example.drivingdirections;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    Button del_my_places;
    EditText name_new_place;
    EditText address_new_place;
    ListView pListView;
    ArrayAdapter pArrayAdapter;
    ArrayList pArrayList = new ArrayList();
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_places);
        name_new_place = (EditText) findViewById(R.id.name_new_place);
        address_new_place = (EditText) findViewById(R.id.address_new_place);

        add_new_place = (Button) findViewById(R.id.add_new_place);
        del_my_places = (Button) findViewById(R.id.del_my_places);
        add_new_place.setOnClickListener(this);
        del_my_places.setOnClickListener(this);

        pListView = (ListView) findViewById(R.id.my_places);
        pArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                pArrayList);

        // Set the ListView to use the ArrayAdapter
        pListView.setAdapter(pArrayAdapter);
        pListView.setOnItemClickListener(this);

        LoadPreferences();
    }

    private void LoadPreferences() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        String numStr = data.getString("DestinationNumber", "0");
        number = Integer.parseInt(numStr);
        int nr = number;


        while( nr != 0){
            String dataSet = data.getString("DestinationName"+nr, "Not available");
            pArrayAdapter.add(dataSet);

            nr--;
        }

        pArrayAdapter.notifyDataSetChanged();

    }

    protected void SavePreferences(String key, String value) {
        // TODO Auto-generated method stub

        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(key, value);
        editor.commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_my_places, menu);
        return true;
    }


    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.add_new_place:
                String name = name_new_place.getText().toString();
                String address = address_new_place.getText().toString();
                if((!name.matches("")) && (!address.matches("")) ) {
                    pArrayList.add(0,name );
                    pArrayAdapter.notifyDataSetChanged();
                    number++;
                    SavePreferences("DestinationAddress" + number, address);
                    SavePreferences("DestinationName" + number, name);
                    SavePreferences("DestinationNumber", Integer.toString(number));
                }
                break;
            case R.id.del_my_places:
                pArrayList.removeAll(pArrayList);
                pArrayAdapter.notifyDataSetChanged();
                SavePreferences("DestinationNumber", "0");
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
