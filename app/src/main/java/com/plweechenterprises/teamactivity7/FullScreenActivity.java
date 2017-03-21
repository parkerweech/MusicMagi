package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will operate the full screen activity in the app.
 * It will take the users inputted musical notes, display them,
 *  and if desired it will play the song.
 */
public class FullScreenActivity extends AppCompatActivity {

    private List<Note> noteList = new ArrayList<>();

    /**
     * This will function will initialize the activity when the
     *  activity is started for the first time.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        getSupportActionBar().setTitle("Music Magi");

        Intent intent = getIntent();
        String json = intent.getStringExtra("notes");

        if(json.length() > 0) {
            Log.d("List of notes", "Deserializing the note list");
            Toast.makeText(this, "Deserializing the note list", Toast.LENGTH_SHORT).show();

            Gson gson = new Gson();

            NoteListContainer noteListContainer = gson.fromJson(json, NoteListContainer.class);

            for(int i = 0; i < noteListContainer.getNoteList().size(); i++) {
                noteList.add(noteListContainer.getNoteList().get(i));
            }
        }
        displayFull();
    }

    /**
     * This function will display the music from the music list.
     */
    // currently the display is a scrollable view and may need to be changed
    public void displayFull() {

        //initial location for first note
        int noteX = 0;
        int noteY = 50;

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_full_screen);

        for(int i = 1; i < 25; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(100,100));
            image.setX(noteX);
            image.setY(noteY);
            noteX += 125;

            //determine type of note to display
            if (i % 2 == 0) {
                image.setImageResource(R.drawable.eighth_note);
            }
            else {
                image.setImageResource(R.drawable.half_note);
            }

            //start a new line
            if (i % 8 == 0){
                noteY += 200;
                noteX = 0;
            }

            // Adds the view to the layout
            layout.addView(image);
        }
    }

    /**
     * This function will play the full list of music from the full screen
     */
    // start playing all of the music in the current file
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
     * This function will save the current list of music to the user's desired location.
     * @param view
     */
    // change view to Save File screen
    public void save(View view) {
        Intent intent = new Intent(this, SaveFile.class);
        startActivity(intent);
    }

    /**
     * This function will send you to the edit screen to make changes to the music list.
     * @param view
     */
    // Change view to MusicEditor screen
    public void edit(View view) {
        Intent intent = new Intent(this, MusicEditor.class);
        intent.putExtra("notes", "");
        startActivity(intent);
    }
}
