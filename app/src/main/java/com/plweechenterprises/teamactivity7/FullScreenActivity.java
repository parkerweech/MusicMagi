package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends AppCompatActivity {

    // private List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        getSupportActionBar().setTitle("Music Magi");

        /*
        Log.d("List of notes", "Deserializing the note list");
        // Toast.makeText(this, "Deserializing the note list", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        String json = intent.getStringExtra("notes");
        if(json.length() > 0) {

            Toast.makeText(this,"Hello World",Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();

            NoteListContainer noteListContainer = gson.fromJson(json, NoteListContainer.class);
            for(int i = 0; i < noteListContainer.getNoteList().size(); i++) {
                noteList.add(noteListContainer.getNoteList().get(i));
            }
        }
        */
    }

    // display list of music
    // currently the display is a scrollable view and may need to be changed
    public void displayFull() {
        /*
        for (int i = 0; i <= noteList.size(); i++) {
            // Display note

            i++;
        }
        */
    }

    // start playing the all of the music in the current file
    public void playFull() {
        /*
        for (int i = 0; i <= noteList.size(); i++) {
            // play note

            i++;
        }
        */
    }

    // change view to Save File screen
    public void save(View view) {
        Intent intent = new Intent(this, SaveFile.class);
        startActivity(intent);
    }

    // Change view to MusicEditor screen
    public void edit(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        intent.putExtra("notes", "");
        startActivity(intent);
    }
}
