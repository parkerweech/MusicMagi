package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SaveFile extends AppCompatActivity {

    String fileName;
    String noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);
        getSupportActionBar().setTitle("Music Magi");

        Intent intent = getIntent();
        noteList = intent.getStringExtra("notes");
    }

    void inputFileName() {

    }

    void saveFile() {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("FileName", noteList);
        prefsEditor.commit();
    }

    public void save(View view) {
        saveFile();

        Intent intent = new Intent(this, MusicEditor.class);
        startActivity(intent);
    }

}
