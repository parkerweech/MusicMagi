package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
    }

    // display list of music
    // currently the display is a text view and may need to be changed
    public void displayFull() {

    }

    // start playing the all of the music in the file
    public void playFull() {

    }

    // change view to Save File screen
    public void save(View view) {
        Intent intent = new Intent(this, SaveFile.class);
        startActivity(intent);
    }

    // Change view to Editor screen
    public void edit(View view) {
        Intent intent = new Intent(this, Editor.class);
        startActivity(intent);
    }
}
