package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Editor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<Note> noteList;
    Note note;
    String[] noteValues = {"A1", "B1", "C1", "D1", "E1", "F1", "G1", "A2", "B2", "C2", "D2", "E2", "F2", "G2", "A3"};
    String[] noteLengths = {"Eighth Note", "Dotted Eighth", "Quarter Note", "Dotted Quarter", "Half Note", "Dotted Half", "Whole Note"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

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

        if (tempValue.length() < 3) {
            selectNote(pos);
            Toast.makeText(this,"Value: " + tempValue,Toast.LENGTH_SHORT).show();
        }
        else {
            selectLength(pos);
            Toast.makeText(this, "Length: " + tempValue, Toast.LENGTH_SHORT).show();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void selectNote(int value) {
        //dropdown list with notes and rests
        //note.setNoteValue(value);
    }

    public void selectLength(int length) {
        //dropdown list with length of note
        //note.setNoteLength(length);
    }

    public void addNote() {
        //adds note to noteList
        noteList.add(note);
    }

    public void display() {
        //update display with x number of notes
    }

    public void delete() {
        //delete the last note on noteList
    }

    public void save() {
        //change view to Save activity
        Intent intent = new Intent(this, SaveFile.class);
        startActivity(intent);
    }

    public void playMini(View view) {
        //change view to FullScreen activity and play the music
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }

    public void fullScreen(View view) {
        //change view to FullScreen activity
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }

}

