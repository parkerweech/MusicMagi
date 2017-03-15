package com.plweechenterprises.teamactivity7;

import android.graphics.Bitmap;

/**
 * Created by admin on 2/22/2017.
 */

public class Note {

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) { this.noteName = noteName; }

    public int getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(int noteValue) {
        this.noteValue = noteValue;

        if (noteValue == 0)
            noteFrequency = 55;
        else if (noteValue == 1 || noteValue == 2)
            noteFrequency = 58.27;
        else if (noteValue == 3)
            noteFrequency = 61.74;
        else if (noteValue == 4)
            noteFrequency = 65.41;
        else if (noteValue == 5 || noteValue == 6)
            noteFrequency = 69.30;
        else if (noteValue == 7)
            noteFrequency = 73.42;
        else if (noteValue == 8 || noteValue == 9)
            noteFrequency = 77.78;
        else if (noteValue == 10)
            noteFrequency = 82.41;
        else if (noteValue == 11)
            noteFrequency = 87.31;
        else if (noteValue == 12 || noteValue == 13)
            noteFrequency = 92.50;
        else if (noteValue == 14)
            noteFrequency = 98;
        else if (noteValue == 15 || noteValue == 16)
            noteFrequency = 103.83;
        else if (noteValue == 17)
            noteFrequency = 110;
        else if (noteValue == 18 || noteValue == 19)
            noteFrequency = 116.54;
        else if (noteValue == 20)
            noteFrequency = 123.47;
        else if (noteValue == 21)
            noteFrequency = 130.81;
        else if (noteValue == 22 || noteValue == 23)
            noteFrequency = 138.59;
        else if (noteValue == 24)
            noteFrequency = 146.83;
        else if (noteValue == 25 || noteValue == 26)
            noteFrequency = 155.56;
        else if (noteValue == 27)
            noteFrequency = 164.81;
        else if (noteValue == 28)
            noteFrequency = 174.61;
        else if (noteValue == 29 || noteValue == 30)
            noteFrequency = 185;
        else if (noteValue == 31)
            noteFrequency = 196;
        else if (noteValue == 32 || noteValue == 33)
            noteFrequency = 207.65;
        else if (noteValue == 34)
            noteFrequency = 220;

        switch (noteLength) {
            case 0:
                noteDuration = 16;          // 2/16 = .125 seconds
                break;
            case 1:
                noteDuration = 8;           // 2/8 = .25 seconds
                break;
            case 2:
                noteDuration = 2/.375;      // 2/(2/.375) = .375 seconds
                break;
            case 3:
                noteDuration = 4;           // 2/4 = .5 seconds
                break;
            case 4:
                noteDuration = 2/.75;       // 2/(2/.75) = .75 seconds
                break;
            case 5:
                noteDuration = 2;           // 2/2 = 1 seconds
                break;
            case 6:
                noteDuration = 4/3;         // 2/(4/3) = 1.5 seconds
                break;
            case 7:
                noteDuration = 1;           // 2/1 = 2 seconds
                break;
            default:
                break;
        }
    }

    public int getNoteLength() {
        return noteLength;
    }

    public void setNoteLength(int noteLength) {
        this.noteLength = noteLength;
    }

    private String noteName;
    private int noteValue;
    private int noteLength;

    public double getNoteFrequency() {
        return noteFrequency;
    }

    public double getNoteDuration() {
        return noteDuration;
    }

    private double noteFrequency;
    private double noteDuration;
}
