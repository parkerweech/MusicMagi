package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
    private Note note = new Note();

    int maxNotes = 0;
    int count = 0;

    private final int sampleRate = 8000;
    private final int numSamples = sampleRate;
    private final double sample[] = new double[numSamples];
    private double freqOfTone = note.getNoteFrequency(); // hz
    private final byte generatedSnd[] = new byte[4 * numSamples];
    private double length = note.getNoteDuration();

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        Intent intent = getIntent();
        String json = intent.getStringExtra("notes");

        if(json.length() > 0) {
            Log.d("List of notes", "Deserializing the note list");
            // Toast.makeText(this, "Deserializing the note list", Toast.LENGTH_SHORT).show();

            Gson gson = new Gson();

            NoteListContainer noteListContainer = gson.fromJson(json, NoteListContainer.class);

            for(int i = 0; i < noteListContainer.getNoteList().size(); i++) {
                noteList.add(noteListContainer.getNoteList().get(i));
            }
        }

        if (noteList.size() > 48)
            maxNotes = 48;
        else
            maxNotes = noteList.size();

        displayFull();

        if (noteList.size() <= maxNotes) {
            Button button = (Button) findViewById(R.id.scroll);
            button.setVisibility(View.GONE);
        }

        Button button2 = (Button) findViewById(R.id.firstPage);
        button2.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        // Inflate the menu; this adds items to the action bar if it is present
        inflater.inflate(R.menu.menu, menu);

        MenuItem resetButton = menu.findItem(R.id.playFull);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.playFull:
                try {
                    playNotes();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(this, "Playing the music", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.saveFull:
                save();
                //Toast.makeText(this, "Saving the music", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.editFull:
                edit();
                //Toast.makeText(this, "Time to edit the music", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This function will display the music from the music list.
     */
    public void displayFull(){

        //initial location for first note
        int noteX = 0;
        int noteY = 50;
        int textX = 30;
        int textY = 150;

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.linearThing);

        for(; count < maxNotes; count++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
            image.setX(noteX);
            image.setY(noteY);
            noteX += 125;

            TextView text = new TextView(this);
            text.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
            text.setX(textX);
            text.setY(textY);
            text.setText(noteList.get(count).getNoteName());
            textX += 125;

            //determine type of note to display
            switch (noteList.get(count).getNoteLength()) {
                case 0:
                    image.setImageResource(R.drawable.sixteenth_image);
                    break;
                case 1:
                    image.setImageResource(R.drawable.eighth_note);
                    break;
                case 2:
                    image.setImageResource(R.drawable.dotted_eighth_note);
                    break;
                case 3:
                    image.setImageResource(R.drawable.quarter_note);
                    break;
                case 4:
                    image.setImageResource(R.drawable.dotted_quarter_note);
                    break;
                case 5:
                    image.setImageResource(R.drawable.half_note);
                    break;
                default:
                    image.setImageResource(R.drawable.whole_note);
                    break;
            }

            //start a new line
            if ((count + 1) % 8 == 0) {
                noteY += 200;
                noteX = 0;
                textY += 200;
                textX = 30;
            }

            // Adds the view to the layout
            layout.addView(image);
            layout.addView(text);
        }
    }


    public void scroll(View view){
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.linearThing);
            layout.removeAllViewsInLayout();

            if (noteList.size() > 48 + maxNotes)
                maxNotes += 48;
            else
                maxNotes = noteList.size();

            displayFull();

        if (noteList.size() <= maxNotes) {
            Button button = (Button) findViewById(R.id.scroll);
            button.setVisibility(View.GONE);
        }

        Button button2 = (Button) findViewById(R.id.firstPage);
        button2.setVisibility(View.VISIBLE);
    }

    public void scrollBack(View view){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.linearThing);
        layout.removeAllViewsInLayout();

        maxNotes = 48;
        count = 0;

        displayFull();

        Button button = (Button) findViewById(R.id.scroll);
        button.setVisibility(View.VISIBLE);

        Button button2 = (Button) findViewById(R.id.firstPage);
        button2.setVisibility(View.GONE);
    }


    /**
     * This function will save the current list of music to the user's desired location.
     */
    // change view to Save File screen
    public void save() {
        NoteListContainer noteListContainer = new NoteListContainer(noteList);
        Gson gson = new Gson();
        String json = gson.toJson(noteListContainer);
        Log.d("fullScreen", "fullScreen was called");

        Intent intent = new Intent(this, SaveFile.class);
        intent.putExtra("notes", json);
        startActivity(intent);
    }

    /**
     * This function will send you to the edit screen to make changes to the music list.
     */
    // Change view to MusicEditor screen
    public void edit() {
        NoteListContainer noteListContainer = new NoteListContainer(noteList);
        Gson gson = new Gson();
        String json = gson.toJson(noteListContainer);
        Log.d("fullScreen", "fullScreen was called");

        Intent intent = new Intent(this, MusicEditor.class);
        intent.putExtra("notes", json);
        startActivity(intent);
    }

    /**
     * This function will play the full list of music from the full screen
     */
    // start playing all of the music in the current file
    public void playNotes() throws InterruptedException {
        int num = 0;

        Log.e("playNotes", "before if statement");

        while(num < noteList.size()) {

            Log.e("playNotes", "first line of while loop");
            freqOfTone = noteList.get(num).getNoteFrequency();
            Log.e("playNotes", "just set frequency");
            length = noteList.get(num).getNoteDuration();
            Log.e("playNotes", "just set duration");

            genTone();
            Log.e("playNotes", "just generated the tone");
            playSound();
            Log.e("playNotes", "just played the sounds");

            Log.e("playNotes", "just slept");


            num++;
        }
    }

    void genTone(){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    void playSound() throws InterruptedException {
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, (int) (generatedSnd.length / length));
        audioTrack.play();
        Thread.sleep((int) (2000 / length));
        audioTrack.release();
    }
}
