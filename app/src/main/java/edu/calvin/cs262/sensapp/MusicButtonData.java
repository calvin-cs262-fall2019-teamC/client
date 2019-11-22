package edu.calvin.cs262.sensapp;

/**
 * A container class to hold the id of the drawable, id of the audio file, and String label for
 * a {@link MusicButtonView}
 */
public class MusicButtonData {
    private int drawableID;
    private int audioID;
    private String label;

    /**
     * Default constructor for MusicButtonData
     */
    public MusicButtonData() {
        drawableID = 0;
        audioID = 0;
        label = "";
    }

    /**
     * Explicit constructor for MusicButtonData
     *
     * @param draw int of drawableID
     * @param audio int of audioID
     * @param lbl String of label text
     */
    public MusicButtonData(int draw, int audio, String lbl) {
        drawableID = draw;
        audioID = audio;
        label = lbl;
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
    public String getLabel() {
        return label;
    }
}
