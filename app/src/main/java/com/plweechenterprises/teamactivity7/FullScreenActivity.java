package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FullScreenActivity extends AppCompatActivity {

    private List<Note> noteList = new ArrayList<>();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        getSupportActionBar().setTitle("Music Magi");

        Intent intent = getIntent();
        String json = intent.getStringExtra("notes");

        // if(json.length() > 0) {
        if(1 > 0) {
            Log.d("List of notes", "Deserializing the note list");
            Toast.makeText(this, "Deserializing the note list", Toast.LENGTH_SHORT).show();

                Gson gson = new Gson();

                NoteListContainer noteListContainer = gson.fromJson(json, NoteListContainer.class);

                //for(int i = 0; i < noteListContainer.getNoteList().size(); i++) {
                    // noteList.add(noteListContainer.getNoteList().get(i));
                //}

            }
        }

    /**
     *
     */
    // display list of music
    // currently the display is a scrollable view and may need to be changed
    public void displayFull() {
        for (int i = 0; i <= noteList.size(); i++) {
            // Display note

            i++;
        }
    }

    /**
     *
     */
    // start playing the all of the music in the current file
    public void playFull() {
        //for (int i = 0; i <= noteList.size(); i++) {
        for (int i = 0; i <= 5; i++)
        {
            // play note
            Toast.makeText(this, "We are looping!!", Toast.LENGTH_SHORT).show();
            i++;
        }
    }

    /**
     *
     * @param view
     */
    // change view to Save File screen
    public void save(View view) {
        Intent intent = new Intent(this, SaveFile.class);
        startActivity(intent);
    }

    /**
     *
     * @param view
     */
    // Change view to MusicEditor screen
    public void edit(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        intent.putExtra("notes", "");
        startActivity(intent);
    }
}
