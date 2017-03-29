package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Note> noteList = new ArrayList<>();
    private Note note = new Note();
    private String[] noteValues = {"A3" /*0*/, "A3♯"/*1*/,
            "B3♭"/*2*/, "B3"/*3*/, "C4"/*4*/, "C4♯"/*5*/,
            "D4♭"/*6*/, "D4"/*7*/, "D4♯"/*8*/, "E4♭"/*9*/,
            "E4"/*10*/, "F4"/*11*/, "F4♯"/*12*/, "G4♭"/*13*/,
            "G4"/*14*/, "G4♯"/*15*/, "A4♭"/*16*/, "A4"/*17*/,
            "A4♯"/*18*/, "B4♭"/*19*/, "B4"/*20*/, "C5"/*21*/,
            "C5♯"/*22*/, "D5♭"/*23*/, "D5"/*24*/, "D5♯"/*25*/,
            "E5♭"/*26*/, "E5"/*27*/, "F5"/*28*/, "F5♯"/*29*/,
            "G5♭"/*30*/, "G5"/*31*/, "G5♯"/*32*/, "A5♭"/*33*/,
            "A5"/*34*/, "Rest"/*35*/};
    private String[] noteLengths = {"Sixteenth Note" /*0*/,
            "Eighth Note" /*1*/,
            "Dotted Eighth" /*2*/, "Quarter Note" /*3*/,
            "Dotted Quarter" /*4*/, "Half Note" /*5*/};

    private final int sampleRate = 8000;
    private final int numSamples = sampleRate;
    private final double sample[] = new double[numSamples];
    private double freqOfTone = note.getNoteFrequency(); // hz
    private final byte generatedSnd[] = new byte[4 * numSamples];
    private double length = note.getNoteDuration();
    private int displayIterator;
    private int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle("Editor");

        Intent intent = getIntent();
        String json = intent.getStringExtra("notes");
        if(json.length() > 0) {

            //Toast.makeText(this,"Hello World",Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();

            NoteListContainer noteListContainer = gson.fromJson(json, NoteListContainer.class);
            for(int i = 0; i < noteListContainer.getNoteList().size(); i++) {
                noteList.add(noteListContainer.getNoteList().get(i));
            }
            display();
        }

        Spinner dropdown = (Spinner) findViewById(R.id.select_note);
        ArrayAdapter<String> noteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, noteValues);
        noteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(noteAdapter);
        dropdown.setOnItemSelectedListener(this);

        Spinner dropdown2 = (Spinner) findViewById(R.id.select_length);
        ArrayAdapter<String> lengthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, noteLengths);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(lengthAdapter);
        dropdown2.setOnItemSelectedListener(this);

        displayIterator = 0;

        if (noteList.size() > 0)
            display();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater inflater = getMenuInflater();

        // Inflate the menu; this adds items to the action bar if it is present
        inflater.inflate(R.menu.menu2, menu2);

        MenuItem saveFull = menu2.findItem(R.id.fullScreenEdit);
        MenuItem editFull = menu2.findItem(R.id.saveEdit);

        return super.onCreateOptionsMenu(menu2);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.fullScreenEdit:
                fullScreen();
                //Toast.makeText(this, "Look at the music", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.saveEdit:
                save();
                //Toast.makeText(this, "Save the music", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String tempValue = parent.getItemAtPosition(pos).toString();

        if (tempValue.length() < 5) {
            selectNote(pos, tempValue);
            //Toast.makeText(this,"Value: " + tempValue,Toast.LENGTH_SHORT).show();
        }
        else {
            selectLength(pos);
            //Toast.makeText(this, "Length: " + tempValue, Toast.LENGTH_SHORT).show();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void selectNote(int value, String name) {
        //dropdown list with notes and rests
        note.setNoteValue(value);
        note.setNoteName(name);
    }

    public void selectLength(int length) {
        //dropdown list with length of note
        note.setNoteLength(length);
    }

    public void addNote(View view) {
        //adds note to noteList
        Note newNote = new Note();
        newNote.setNoteValue(note.getNoteValue());
        newNote.setNoteName(note.getNoteName());
        newNote.setNoteLength(note.getNoteLength());
        noteList.add(newNote);
        //Toast.makeText(this,"Note added " + note.getNoteLength(),Toast.LENGTH_SHORT).show();
        display();
    }

    public void display() {
        //update display with x number of notes

        num = 0;

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.noteLayout);

        int noteX = 0;
        int noteY = 50;
        int textX = 30;
        int textY = 150;

        if(noteList.size() > 8) {
            num = noteList.size() - 8;
            layout.removeAllViewsInLayout();
        }

        layout.removeAllViewsInLayout();
        while (num < noteList.size()) {

                ImageView image = new ImageView(this);
                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
                image.setX(noteX);
                image.setY(noteY);
                noteX += 125;

                TextView text = new TextView(this);
                text.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
                text.setX(textX);
                text.setY(textY);

            if (noteList.get(num).getNoteValue() != 35) {

                text.setText(noteList.get(num).getNoteName());

                //determine type of note to display
                switch (noteList.get(num).getNoteLength()) {
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
            }
            /*else {
                text.setText(" ");

                //determine type of note to display
                switch (noteList.get(num).getNoteLength()) {
                    case 0:
                        image.setImageResource(R.drawable.sixteenth_rest);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.eighth_rest);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.dotted_eighth_rest);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.quarter_rest);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.dotted_quarter_rest);
                        break;
                    case 5:
                        image.setImageResource(R.drawable.half_rest);
                        break;
                    default:
                        image.setImageResource(R.drawable.whole_note);
                        break;
                }
            }*/

            textX += 125;

            // Adds the view to the layout
            layout.addView(image);
            layout.addView(text);
            num++;
        }
    }

    public void delete(View view) {
        //delete the last note on noteList
        if(noteList.size() != 0) {
            noteList.remove(noteList.size() - 1);
            //Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        }
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.noteLayout);
        layout.removeAllViewsInLayout();
        display();
    }

    public void save() {
        //change view to Save activity
        NoteListContainer noteListContainer = new NoteListContainer(noteList);
        Gson gson = new Gson();
        String json = gson.toJson(noteListContainer);
        Log.d("save", "save was called");

        Intent intent = new Intent(this, SaveFile.class);
        intent.putExtra("notes", json);
        startActivity(intent);
    }

    public void fullScreen() {
        //change view to FullScreen activity
        NoteListContainer noteListContainer = new NoteListContainer(noteList);
        Gson gson = new Gson();
        String json = gson.toJson(noteListContainer);
        Log.d("fullScreen", "fullScreen was called");

        Intent intent = new Intent(this, FullScreenActivity.class);
        intent.putExtra("notes", json);
        startActivity(intent);
    }

    //Playing the music!!!

    public void playNotes(View view) throws InterruptedException {
        int num = 0;

        Log.e("playNotes", "before if statement");

        while(num < noteList.size()) {

            Log.e("playNotes", "first line of while loop");

            freqOfTone = noteList.get(num).getNoteFrequency();
            Log.e("playNotes", "just set frequency");
            length = noteList.get(num).getNoteDuration();
            Log.e("playNotes", "just set duration");

            // only play non-rests
            if(noteList.get(num).getNoteValue() != 35) {
                genTone();
                Log.e("playNotes", "just generated the tone");
                playSound();
                Log.e("playNotes", "just played the sounds");
            }
            else {
                Thread.sleep((int) (2000 / length));
            }

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

