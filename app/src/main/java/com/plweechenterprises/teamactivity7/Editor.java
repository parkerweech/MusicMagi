package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Editor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Note> noteList = new ArrayList<>();
    private Note note = new Note();
    private String[] noteValues = {"A1", "A1♯", "B1♭", "B1", "C1", "C1♯", "D1♭", "D1", "D1♯", "E1♭", "E1", "F1", "F1♯", "G1♭",
            "G1", "G1♯", "A2♭", "A2", "A2♯", "B2♭", "B2", "C2", "C2♯", "D2♭", "D2", "D2♯", "E2♭", "E2", "F2", "F2♯", "G2♭",
            "G2", "G2♯", "A3♭", "A3"};
    private String[] noteLengths = {"Eighth Note", "Dotted Eighth", "Quarter Note", "Dotted Quarter", "Half Note", "Dotted Half", "Whole Note"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle("Music Magi");

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
        noteList.add(note);
        Toast.makeText(this,"Note added " + note.getNoteName(),Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, SaveFile.class);
        startActivity(intent);
    }

    public void playMini(View view) {
        //play the music that is currently being edited
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }

    public void fullScreen(View view) {
        //change view to FullScreen activity
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }

}

