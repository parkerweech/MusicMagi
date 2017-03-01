package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SaveFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);
        getSupportActionBar().setTitle("Music Magi");
    }

    String fileName;

    void inputFileName() {

    }

    void saveFile() {

    }

    public void save(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        startActivity(intent);
    }

}
