package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        getSupportActionBar().setTitle("Music Magi");

        Intent intent = getIntent();
        noteList = intent.getStringExtra("notes");
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
