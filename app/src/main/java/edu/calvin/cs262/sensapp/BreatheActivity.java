package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Random;

public class BreatheActivity extends AppCompatActivity {

    //create mediaPlayer for the audio
    public MediaPlayer musicPlayer;

    //create list for audio
    public int[] audioList;

    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * @param savedInstanceState The Bundle of information which initializes the breathe activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        //initialize the videoView and its mediaController
        VideoView simpleVideoView = findViewById(R.id.videoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.breathe_video_trees01));

        MediaController mediaController = new MediaController(this);
        simpleVideoView.setMediaController(mediaController);

        //initialize list of audio files (from raw)
        audioList = new int[]{R.raw.breathe_audio_waves, R.raw.breathe_audio_zymbel};

        //initialize the musicPlayer
        chooseAudio();

        //start video and music
        simpleVideoView.start();
        musicPlayer.start();

        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            //when the current audio is finished, start playing a new audio
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicPlayer.reset();
                chooseAudio();
                musicPlayer.start();
            }
        });
    }

    public void chooseAudio(){
        //choose next audio to play

        //TODO: get different audio? make customizable/let user remember combinations they liked?
        //TODO: make audio not choose same clip consecutively
        //TODO: make audio resume rather than stop onPause?

        Random random = new Random();
        int randInt = random.nextInt(audioList.length);

        musicPlayer = MediaPlayer.create(this, audioList[randInt]);
//        musicPlayer = MediaPlayer.create(this, R.raw.breathe_audio_zymbel);
    }

    public void onPause() {
        //stop the music if focus is lost
        super.onPause();
        if (musicPlayer.isPlaying())
            musicPlayer.stop();
    }

}
