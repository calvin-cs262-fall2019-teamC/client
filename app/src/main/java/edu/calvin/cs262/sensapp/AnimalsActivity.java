package edu.calvin.cs262.sensapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This class has not yet been finished
 * The idea is that we wish to have an activity that shows
 * pictures of different animals, which you can pet/touch
 * and they will make animal-appropriate sounds
 */
public class AnimalsActivity extends AppCompatActivity {

//    //create mediaPlayer to play the sound files
//    public MediaPlayer soundPlayer;
//
//    //remember which button got clicked last
//    public String lastClicked;

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

//    /**
//     * called when user presses horse button; plays "neigh" sound
//     *
//     * @param view
//     */
//    public void horseSound(View view){
//        if(lastClicked != "horse") {
//            soundPlayer.reset();
//            soundPlayer = MediaPlayer.create(this, R.raw.animals_horseneigh);
//        }
//
//        soundPlayer.start();
//        lastClicked = "horse";
//    }
}
