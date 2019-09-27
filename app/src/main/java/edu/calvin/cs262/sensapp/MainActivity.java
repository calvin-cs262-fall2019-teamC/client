package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * Creates the main activity from which other activities will be selected.
     *
     * @param savedInstanceState The Bundle of information which initializes the main activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launches the breathing activity when the button is pressed.
     *
     * @param view The current View object (the breathe activity button).
     */
    public void launchBreatheActivity(View view) {
        Intent intent = new Intent(this, BreatheActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the fidget cube activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchFidgetCubeActivity(View view) {
        Intent intent = new Intent(this, FidgetCubeActivity.class);
        startActivity(intent);
    }
}
