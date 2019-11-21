package edu.calvin.cs262.sensapp;

import android.graphics.drawable.Drawable;

/**
 * A container class to hold the id of the resources for a History item
 */

public class HistoryData {
    private String title;
    private Drawable drawable;
    private int rating;
    private String duration;

    public HistoryData() {
        title = "";
        drawable = null;
        rating = 0;
        duration = "";
    }

    public HistoryData(String ttl, Drawable draw, int rate, String dur) {
        title = ttl;
        drawable = draw;
        rating = rate;
        duration = dur;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public int getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }
}
