package edu.calvin.cs262.sensapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is another class that is unfinished and unimplemented
 * When done, this class will create an activity in which
 * the user can listen to fairytales and other stories
 * there may also be a visual component
 */

public class StoriesActivity extends AppCompatActivity {

    /**
     * When created, inititalize data
     * @param savedInstanceState The Bundle of data to initialize.
     * Assign "activity_stories" layout
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
    }
}
