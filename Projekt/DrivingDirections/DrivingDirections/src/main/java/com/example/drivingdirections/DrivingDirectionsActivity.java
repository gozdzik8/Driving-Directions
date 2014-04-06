package com.example.drivingdirections;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DrivingDirectionsActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    Button search;
    Button del_history;
    EditText from;
    EditText to;
    ListView hListView;
    ArrayAdapter hArrayAdapter;
    ArrayList hArrayList = new ArrayList();
    int number;
    static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_directions);

        from = (EditText) findViewById(R.id.from);
        to = (EditText) findViewById(R.id.to);

        search = (Button) findViewById(R.id.search);
        del_history = (Button) findViewById(R.id.del_my_search_history);

        search.setOnClickListener(this);
        del_history.setOnClickListener(this);

        hListView = (ListView) findViewById(R.id.my_search_history);
        hArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                hArrayList);

        // Set the ListView to use the ArrayAdapter
        hListView.setAdapter(hArrayAdapter);
        hListView.setOnItemClickListener(this);

        LoadPreferences();


    }

    private void LoadPreferences() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        String numStr = data.getString("Number", "0");
        number = Integer.parseInt(numStr);
        int nr = number;
        while( nr != 0){
            String dataSet = data.getString("Destination"+nr, "Not available");
            hArrayAdapter.add(dataSet);

            nr--;
        }

        hArrayAdapter.notifyDataSetChanged();

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
        getMenuInflater().inflate(R.menu.driving_directions, menu);
        return true;
    }


    @Override
    public void onClick(View v) {

        // Also add that value to the list shown in the ListView
        switch (v.getId()) {
            case R.id.search:
                if((!from.getText().toString().matches("")) && (!to.getText().toString().matches("")) ) {
                    String destination = "From: " + from.getText().toString() + " To: " + to.getText().toString();
                    hArrayList.add(0, destination);
                    hArrayAdapter.notifyDataSetChanged();
                    number++;
                    SavePreferences("Destination" + number, destination);
                    SavePreferences("Number", Integer.toString(number));
                }
                break;
            case R.id.del_my_search_history:
                hArrayList.removeAll(hArrayList);
                hArrayAdapter.notifyDataSetChanged();
                SavePreferences("Number", "0");
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO get directions using listview values

    }
}
