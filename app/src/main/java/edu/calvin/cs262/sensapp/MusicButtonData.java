package edu.calvin.cs262.sensapp;

public class MusicButtonData {
    private int drawableID;
    private int audioID;
    private String label;

    public MusicButtonData() {
        drawableID = 0;
        audioID = 0;
        label = "";
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

    public void setDrawableID(int id) {
        drawableID = id;
    }

    public void setAudioID(int id) {
        audioID = id;
    }

    public void setLabel(String lbl) {
        label = lbl;
    }
}
