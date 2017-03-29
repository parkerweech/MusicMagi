package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LoadFile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<String> filenames = new ArrayList<>();
    //private String[] filenames = new String[50];
    String filename;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_file);
        getSupportActionBar().setTitle("Load Project");

        SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> allEntries = myPrefs.getAll();

        int i = 0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            filenames.add(entry.getKey());
            //filenames[i] = entry.getKey();
            Log.d("Filenames in onCreate"," Filename is: " + filenames.get(i));
            i++;
        }

        Spinner dropdown = (Spinner) findViewById(R.id.select_filename);
        ArrayAdapter<String> filenameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filenames);
        filenameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(filenameAdapter);
        dropdown.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu_load) {
        MenuInflater inflater = getMenuInflater();

        // Inflate the menu; this adds items to the action bar if it is present
        inflater.inflate(R.menu.menu_load, menu_load);

        MenuItem homeScreen = menu_load.findItem(R.id.homeScreen);

        return super.onCreateOptionsMenu(menu_load);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.homeScreen:
                homeScreen();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void homeScreen() {
        //change view to homeScreen activity
        Log.d("homeScreen", "homeScreen was called");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param parent
     * @param view
     * @param pos
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        filename = parent.getItemAtPosition(pos).toString();
    }

    /**
     *
     * @param parent
     */
    public void onNothingSelected(AdapterView<?> parent) {

        // Android Studio needed this for the spinner
        return;
    }

    /**
     *
     * @return
     */
    String openFile() {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String json = mPrefs.getString(filename, "");
        return json;
    }

    /**
     *
     * @param view
     */
    public void load(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        String json = openFile();
        intent.putExtra("notes", json);
        startActivity(intent);
    }

}
