package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This class is the main/home screen of the activity.
 * The user can either start a new project or load a previous one.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This class will operate the main activity in the app.
     * It puts the title of the app and the app icon in the action bar.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Music Magi");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
    }

    /**
     * This function is called if the user taps the create new project button.
     * @param view
     */
    public void createNew(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        intent.putExtra("notes", "");
        startActivity(intent);
    }

    /**
     * This function is called if the user taps the load previous project button.
     * @param view
     */
    public void loadExisting(View view) {
        Intent intent = new Intent(this, LoadFile.class);
        startActivity(intent);
    }
}
