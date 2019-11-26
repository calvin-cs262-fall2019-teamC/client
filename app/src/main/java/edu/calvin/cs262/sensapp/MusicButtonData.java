package edu.calvin.cs262.sensapp;

/**
 * A container class to hold the id of the drawable, id of the audio file, and String stringID for
 * a {@link MusicButtonView}
 */
public class MusicButtonData {
    private int drawableID;
    private int audioID;
    private int stringID;

    /**
     * Default constructor for MusicButtonData
     */
    public MusicButtonData() {
        drawableID = 0;
        audioID = 0;
        stringID = 0;
    }

    /**
     * Explicit constructor for MusicButtonData
     *
     * @param draw int of drawableID
     * @param audio int of audioID
     * @param lbl int of string resource
     */
    public MusicButtonData(int draw, int audio, int lbl) {
        drawableID = draw;
        audioID = audio;
        stringID = lbl;
    }

    /**
     * Getters for instance variables
     */
    public int getDrawableID() {
        return drawableID;
    }
    public int getAudioID() {
        return audioID;
    }
    public int getStringID() {
        return stringID;
    }
}
