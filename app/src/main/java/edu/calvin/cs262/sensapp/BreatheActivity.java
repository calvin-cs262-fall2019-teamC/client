package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BreatheActivity extends AppCompatActivity {

    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * @param savedInstanceState The Bundle of information which initializes the breathe activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);
    }
}
