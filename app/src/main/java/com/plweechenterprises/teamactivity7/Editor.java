package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class Editor extends AppCompatActivity {

    List<Note> noteList;
    Note note;
    String[] noteValues = {"A1","B1","C1","D1","E1","F1","G1","A2","B2","C2","D2","E2","F2","G2","A3"};
    String[] noteLengths = {"Eighth Note","Dotted Eighth","Quarter Note","Dotted Quarter","Half Note","Dotted Half","Whole Note"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Spinner dropdown = (Spinner)findViewById(R.id.select_note);
        ArrayAdapter<String> noteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, noteValues);
        dropdown.setAdapter(noteAdapter);

        Spinner dropdown2 = (Spinner)findViewById(R.id.select_length);
        ArrayAdapter<String> lengthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, noteLengths);
        dropdown2.setAdapter(lengthAdapter);

    }

    public void selectNote() {
        //dropdown list with notes and rests
    }

    public void selectLength() {
        //dropdown list with length of note
    }

    public void addNote() {
        //adds note to noteList
    }

    public void display() {
        //update display with x number of notes
    }

    public void delete() {
        //delete the last note on noteList
    }

    public void save() {
        //change view to Save activity
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
