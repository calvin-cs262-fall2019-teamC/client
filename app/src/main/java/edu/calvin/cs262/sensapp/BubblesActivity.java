package edu.calvin.cs262.sensapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Bubbles activity displays bubbles on the screen allowing the user to pop them.
 * Unfinished
 */
public class BubblesActivity extends AppCompatActivity {

    // For creating History records once Activity is used for 5 or more seconds
    private HistoryManager hist_manager;

    /**
     * Create the BubbleActivity
     *
     * @param savedInstanceState A Bundle of info to initialize
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hist_manager = new HistoryManager(getString(R.string.activity_three_title),
                getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubbles);

        createBubble();
    }

    /**
     * Create a Bubble on the screen.
     */
    public void createBubble() {

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
}
