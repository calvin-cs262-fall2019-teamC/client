package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class BreatheActivity extends AppCompatActivity {

    //create mediaPlayer
    public MediaPlayer musicPlayer;

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
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.breathe_video_trees01));

        MediaController mediaController = new MediaController(this);
        simpleVideoView.setMediaController(mediaController);


        //initialize the musicPlayer


        //TODO: <initialize list of videos here, pick a random one

        //set mediaPlayer to breathe_music_lightWaves
        //make this random later
        musicPlayer = musicPlayer.create(this, R.raw.breathe_music_waves);

//        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {

                //musicPlayer = musicPlayer.create(this, R.raw.breathe_music_waves);
//            }
//        });

        //start video and run sound
        simpleVideoView.start();
        musicPlayer.start();
    }

    public void onPause(){
        //stop the music if focus is lost

        super.onPause();
        if(musicPlayer.isPlaying())
            musicPlayer.stop();
    }
}
