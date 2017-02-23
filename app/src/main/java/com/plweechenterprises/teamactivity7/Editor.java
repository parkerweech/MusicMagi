package com.plweechenterprises.teamactivity7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class Editor extends AppCompatActivity {

    List<Note> noteList;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
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

    public void play(View view) {
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
