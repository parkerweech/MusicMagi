package com.plweechenterprises.teamactivity7;

import android.content.Context;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.midi.MidiInputPort;
import android.media.midi.MidiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Note> noteList = new ArrayList<>();
    private Note note = new Note();
    private String[] noteValues = {"A1" /*0*/, "A1♯"/*1*/,
            "B1♭"/*2*/, "B1"/*3*/, "C1"/*4*/, "C1♯"/*5*/,
            "D1♭"/*6*/, "D1"/*7*/, "D1♯"/*8*/, "E1♭"/*9*/,
            "E1"/*10*/, "F1"/*11*/, "F1♯"/*12*/, "G1♭"/*13*/,
            "G1"/*14*/, "G1♯"/*15*/, "A2♭"/*16*/, "A2"/*17*/,
            "A2♯"/*18*/, "B2♭"/*19*/, "B2"/*20*/, "C2"/*21*/,
            "C2♯"/*22*/, "D2♭"/*23*/, "D2"/*24*/, "D2♯"/*25*/,
            "E2♭"/*26*/, "E2"/*27*/, "F2"/*28*/, "F2♯"/*29*/,
            "G2♭"/*30*/, "G2"/*31*/, "G2♯"/*32*/, "A3♭"/*33*/,
            "A3"/*34*/};
    private String[] noteLengths = {"Sixteenth Note" /*0*/,
            "Eighth Note" /*1*/,
            "Dotted Eighth" /*2*/, "Quarter Note" /*3*/,
            "Dotted Quarter" /*4*/, "Half Note" /*5*/,
            "Dotted Half" /*6*/, "Whole Note" /*7*/};

    private final int sampleRate = 8000;
    private final int numSamples = sampleRate;
    private final double sample[] = new double[numSamples];
    private double freqOfTone = note.getNoteFrequency(); // hz
    private final byte generatedSnd[] = new byte[4 * numSamples];
    private double length = note.getNoteDuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle("Music Magi");

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

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String tempValue = parent.getItemAtPosition(pos).toString();

        if (tempValue.length() < 4) {
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
        Toast.makeText(this,"Note added " + note.getNoteLength(),Toast.LENGTH_SHORT).show();
        display();
    }

    public void display() {
        //update display with x number of notes
        int num;
        String someNotes = new String();

       if(noteList.size() > 8)
            num = noteList.size() - 8;
        else
            num = 0;

        while(num < noteList.size())
        {
            someNotes += noteList.get(num++).getNoteName();
            someNotes += " ";
        }

        Toast.makeText(this,"noteList: " + someNotes,Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        //delete the last note on noteList
        if(noteList.size() != 0) {
            noteList.remove(noteList.size() - 1);
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        }
        display();
    }

    public void save(View view) {
        //change view to Save activity
        NoteListContainer noteListContainer = new NoteListContainer(noteList);
        Gson gson = new Gson();
        String json = gson.toJson(noteListContainer);

        Intent intent = new Intent(this, SaveFile.class);
        intent.putExtra("notes", json);
        startActivity(intent);
    }

    public void fullScreen(View view) {
        //change view to FullScreen activity
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }



    //Playing the music!!!

    public void playNotes(View view) throws InterruptedException {
        int num;

        if(noteList.size() > 8)
            num = noteList.size() - 8;
        else
            num = 0;

        while(num < noteList.size()) {
            freqOfTone = noteList.get(num).getNoteFrequency();
            length = noteList.get(num).getNoteDuration();

            genTone();
            playSound();
            Thread.sleep((int) (2000 / length));

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

    void playSound(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, (int) (generatedSnd.length / length));
        audioTrack.play();
    }

}

