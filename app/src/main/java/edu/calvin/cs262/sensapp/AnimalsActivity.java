package edu.calvin.cs262.sensapp;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This class has not yet been finished
 * The idea is that we wish to have an activity that shows
 * pictures of different animals, which you can pet/touch
 * and they will make animal-appropriate sounds
 */
public class AnimalsActivity extends AppCompatActivity {

    //create mediaPlayer to play the sound files
    public MediaPlayer soundPlayer;

    // For creating History records once Activity is used for 5 or more seconds
    private HistoryManager hist_manager;

    //remember last sound played
    public String lastSound;

    public boolean isPlaying;

    /**
     * onCreate establishes the activity
     * setContentView assigns this activity to the "activity_animals" layout
      * @param savedInstanceState The Bundle of data to initialize
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hist_manager = new HistoryManager(getString(R.string.activity_five_title),
                getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
    }

    /**
     * called when user presses horse button; plays "neigh" sound
     *
     * @param view
     */
    public void horseSound(View view){
        if(isPlaying != true){
            isPlaying = true;
            soundPlayer = MediaPlayer.create(this, R.raw.animals_horse_neigh);
            soundPlayer.start();

            soundPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    soundPlayer.release();
                    isPlaying = false;
                }
            });
        }
    }

    public void gooseSound(View view){
        if(isPlaying != true){
            isPlaying = true;
            soundPlayer = MediaPlayer.create(this, R.raw.animals_goose_honk);
            soundPlayer.start();

            soundPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    soundPlayer.release();
                    isPlaying = false;
                }
            });
        }
    }

    public void catSound(View view){
        if(isPlaying != true){
            isPlaying = true;
            soundPlayer = MediaPlayer.create(this, R.raw.animals_cat_meow);
            soundPlayer.start();

            soundPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    soundPlayer.release();
                    isPlaying = false;
                }
            });
        }
    }

    public void chimpSound(View view){
        if(isPlaying != true){
            isPlaying = true;
            soundPlayer = MediaPlayer.create(this, R.raw.animals_chimp_screech);
            soundPlayer.start();

            soundPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    soundPlayer.release();
                    isPlaying = false;
                }
            });
        }
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
