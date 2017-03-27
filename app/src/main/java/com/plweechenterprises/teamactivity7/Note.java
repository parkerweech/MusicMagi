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
            noteFrequency = 220;
        else if (noteValue == 1 || noteValue == 2)
            noteFrequency = 233.082;
        else if (noteValue == 3)
            noteFrequency = 246.942;
        else if (noteValue == 4)
            noteFrequency = 261.626;
        else if (noteValue == 5 || noteValue == 6)
            noteFrequency = 277.183;
        else if (noteValue == 7)
            noteFrequency = 293.665;
        else if (noteValue == 8 || noteValue == 9)
            noteFrequency = 311.127;
        else if (noteValue == 10)
            noteFrequency = 329.628;
        else if (noteValue == 11)
            noteFrequency = 349.228;
        else if (noteValue == 12 || noteValue == 13)
            noteFrequency = 369.994;
        else if (noteValue == 14)
            noteFrequency = 391.995;
        else if (noteValue == 15 || noteValue == 16)
            noteFrequency = 415.305;
        else if (noteValue == 17)
            noteFrequency = 440;
        else if (noteValue == 18 || noteValue == 19)
            noteFrequency = 466.164;
        else if (noteValue == 20)
            noteFrequency = 493.883;
        else if (noteValue == 21)
            noteFrequency = 523.251;
        else if (noteValue == 22 || noteValue == 23)
            noteFrequency = 554.365;
        else if (noteValue == 24)
            noteFrequency = 587.330;
        else if (noteValue == 25 || noteValue == 26)
            noteFrequency = 622.254;
        else if (noteValue == 27)
            noteFrequency = 659.255;
        else if (noteValue == 28)
            noteFrequency = 698.456;
        else if (noteValue == 29 || noteValue == 30)
            noteFrequency = 739.989;
        else if (noteValue == 31)
            noteFrequency = 783.991;
        else if (noteValue == 32 || noteValue == 33)
            noteFrequency = 830.609;
        else if (noteValue == 34)
            noteFrequency = 880;
        else if (noteValue == 35)
            noteFrequency = 0;
    }

    public int getNoteLength() {
        return noteLength;
    }

    public void setNoteLength(int noteLength) {

        this.noteLength = noteLength;

        switch (noteLength) {
            case 0:
                noteDuration = 16;          // 2000/16 = 125
                break;
            case 1:
                noteDuration = 8;           // 2000/8 = 250
                break;
            case 2:
                noteDuration = 2 / .375;      // 2000/(2/.375) = 375
                break;
            case 3:
                noteDuration = 4;           // 2000/4 = 500
                break;
            case 4:
                noteDuration = 2 / .75;       // 2000/(2/.75) = 750
                break;
            case 5:
                noteDuration = 2;           // 2000/2 = 1000
                break;
            case 6:
                noteDuration = 4 / 3;         // 2000/(4/3) = 1500
                break;
            case 7:
                noteDuration = 1;           // 2000/1 = 2000
                break;
            default:
                break;
        }
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
