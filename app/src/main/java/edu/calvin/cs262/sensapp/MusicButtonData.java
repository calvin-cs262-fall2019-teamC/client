package edu.calvin.cs262.sensapp;

/**
 * A container class to hold the id of the drawable, id of the audio file, and String label for
 * a {@link MusicButtonView}
 */

public class MusicButtonData {
    private int drawableID;
    private int audioID;
    private String label;

    public MusicButtonData() {
        drawableID = 0;
        audioID = 0;
        label = "";
    }

    public MusicButtonData(int draw, int audio, String lbl) {
        drawableID = draw;
        audioID = audio;
        label = lbl;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public int getAudioID() {
        return audioID;
    }

    public String getLabel() {
        return label;
    }
}
