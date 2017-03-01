package com.plweechenterprises.teamactivity7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/1/2017.
 */

public class NoteListContainer {
    public NoteListContainer(List<Note> noteList) {
        this.noteList = noteList;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    private List<Note> noteList = new ArrayList<>();
}
