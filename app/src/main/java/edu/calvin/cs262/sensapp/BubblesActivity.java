package edu.calvin.cs262.sensapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Bubbles activity displays bubbles on the screen allowing the user to pop them.
 * Unfinished
 */
public class BubblesActivity extends AppCompatActivity {

    /**
     * Create the BubbleActivity
     *
     * @param savedInstanceState A Bundle of info to initialize
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubbles);

        createBubble();
    }

    /**
     * Create a Bubble on the screen.
     */
    public void createBubble() {

    }


}
