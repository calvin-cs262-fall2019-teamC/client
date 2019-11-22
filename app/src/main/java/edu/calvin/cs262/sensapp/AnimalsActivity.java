package edu.calvin.cs262.sensapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This class has not yet been finished
 * The idea is that we wish to have an activity that shows
 * pictures of different animals, which you can pet/touch
 * and they will make animal-appropriate sounds
 */
public class AnimalsActivity extends AppCompatActivity {

    /**
     * onCreate establishes the activity
     * setContentView assigns this activity to the "activity_animals" layout
      * @param savedInstanceState The Bundle of data to initialize
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
    }
}
