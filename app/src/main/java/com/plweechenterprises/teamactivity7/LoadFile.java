package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoadFile extends AppCompatActivity {

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

    void openFile() {

    }

    public void load(View view) {
        Intent intent = new Intent(this,MusicEditor.class);
        startActivity(intent);
    }

}
