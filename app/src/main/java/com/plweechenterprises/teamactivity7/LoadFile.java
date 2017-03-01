package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

public class LoadFile extends AppCompatActivity {

    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_file);
        getSupportActionBar().setTitle("Music Magi");
    }

    String fileName;

    String getFileName() {

        return "fileName goes here";
    }

    String openFile() {
        String json = mPrefs.getString("MyObject", "");
        return json;
    }

    public void load(View view) {
        Intent intent = new Intent(this,MusicEditor.class);
        String json = openFile();
        intent.putExtra("notes", json);
        startActivity(intent);
    }

}
