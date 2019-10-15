package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class BreatheActivity extends AppCompatActivity {

    //create mediaPlayer
    public MediaPlayer mediaPlayer;

    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * @param savedInstanceState The Bundle of information which initializes the breathe activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        //initialize videoView and its mediaController
        VideoView simpleVideoView = findViewById(R.id.videoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.trees01));

        MediaController mediaController = new MediaController(this);
        simpleVideoView.setMediaController(mediaController);

        //set mediaPlayer to light_waves
        mediaPlayer = mediaPlayer.create(this, R.raw.light_waves);

        //start video and run sound
        simpleVideoView.start();

        mediaPlayer.start();
    }


    //figure out how to access protected mediaPlayer
    //ry logging to see if we get into this function
    @Override
    public void onBackPressed(){
        Log.v("test_onPressed","made it into the function!");
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }
}
