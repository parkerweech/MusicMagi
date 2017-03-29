package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 *
 */
public class SaveFile extends AppCompatActivity {

    String filename;
    String noteList;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);
        getSupportActionBar().setTitle("Save Project");

        Intent intent = getIntent();
        noteList = intent.getStringExtra("notes");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu_save) {
        MenuInflater inflater = getMenuInflater();

        // Inflate the menu; this adds items to the action bar if it is present
        inflater.inflate(R.menu.menu_save, menu_save);

        MenuItem homeScreen = menu_save.findItem(R.id.homeScreen);

        return super.onCreateOptionsMenu(menu_save);
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
     */
    void inputFileName() {
        EditText editText = (EditText) findViewById(R.id.editFileName);
        filename = editText.getText().toString();
    }

    /**
     *
     */
    void saveFile() {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(filename, noteList);
        prefsEditor.commit();
    }

    /**
     *
     * @param view
     */
    public void save(View view) {
        inputFileName();
        Log.d("Filename in save method", " Filename is: " + filename);
        saveFile();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
