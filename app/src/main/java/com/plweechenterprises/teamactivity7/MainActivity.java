package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Music Magi");
    }

    public void CreateNew(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        startActivity(intent);
    }

    public void LoadExisting(View view) {
        Intent intent = new Intent(this,LoadFile.class);
        startActivity(intent);
    }
}
