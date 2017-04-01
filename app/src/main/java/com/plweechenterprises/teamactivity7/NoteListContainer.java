package com.plweechenterprises.teamactivity7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/1/2017.
 */

    /**
     * This function will create the container to hold the list of notes.
     */
    public class NoteListContainer {
    public NoteListContainer(List<Note> noteList) {
        for(int i = 0; i < noteList.size(); i++) {
            this.noteList.add(noteList.get(i));
        }
    }

    /**
     * This function will get the note to put in the container.
     * @return
     */
    public List<Note> getNoteList() {
        return noteList;
    }

    /**
     * This function will set the note in the container.
     * @param noteList
     */
    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    private List<Note> noteList = new ArrayList<>();
}
