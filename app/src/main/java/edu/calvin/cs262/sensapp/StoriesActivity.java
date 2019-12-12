package edu.calvin.cs262.sensapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

/**
 * This is another class that is unfinished and unimplemented
 * When done, this class will create an activity in which
 * the user can listen to fairytales and other stories
 * there may also be a visual component
 */

public class StoriesActivity extends AppCompatActivity {

    // For creating History records once Activity is used for 5 or more seconds
    private HistoryManager hist_manager;

    /**
     * When created, inititalize data
     * @param savedInstanceState The Bundle of data to initialize.
     * Assign "activity_stories" layout
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hist_manager = new HistoryManager(getString(R.string.activity_four_title),
                getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
    }

    /**
     * Creates a History record of this activity if it was open for 5 or more seconds
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        hist_manager.createRecord();
    }

    /**
     * Restart the timer when Activity is restarted
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        hist_manager.startTime = LocalDateTime.now();
    }
}
