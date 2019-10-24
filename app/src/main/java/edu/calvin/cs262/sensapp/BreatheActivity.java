package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;
import java.util.Random;

public class BreatheActivity extends AppCompatActivity {

    //create mediaPlayer for the audio
    public MediaPlayer audioPlayer;

    //create list for audio
    public int[] audioList;

    //keep track of previous audio
    public int previousAudio;

    //create list for videos
    public int[] videoList;

    //keep track of previous video
    public int previousVideo;

    //initializes a videoView
    public VideoView simpleVideoView;

    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * @param savedInstanceState The Bundle of information which initializes the breathe activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);



        //initialize list of audio files (from raw)
        audioList = new int[]{R.raw.breathe_audio_waves_short,
                              R.raw.breathe_audio_zymbel_short,
                              R.raw.breathe_audio_wind_short
                              };

        //initialize list of video files (from raw)
        videoList = new int[]{R.raw.breathe_video_trees01,
                              R.raw.breathe_video_ducks06,
                              R.raw.breathe_video_cars04
                              };

        //initialize the videoView and its mediaController
        simpleVideoView = findViewById(R.id.videoView);
        chooseVideo();
        //MediaController mediaController = new MediaController(this);
        //simpleVideoView.setMediaController(mediaController);


        //initialize the musicPlayer
        chooseAudio();

        //initialize the videoPlayer
        chooseVideo();

        //start video and music
        simpleVideoView.start();
        audioPlayer.start();
    }

    public void chooseAudio(){

        //TODO: get different audio? make customizable/let user remember combinations they liked?
        //TODO: make an onResume instead of just stopping onPause?

        //choose next audio to play...
        Random random = new Random();
        int randInt = random.nextInt(audioList.length);

        //...with no <U>consecutive</U> replays of one audio
        while(randInt == previousAudio){
            randInt = random.nextInt(audioList.length);
        }

        //set the selected audio and update previousAudio tracker
        audioPlayer = MediaPlayer.create(this, audioList[randInt]);
        previousAudio = randInt;

        //when the current audio is finished, start playing a new audio
        audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                audioPlayer.reset();
                chooseAudio();
                audioPlayer.start();
            }
        });
    }

    public void chooseVideo(){
        //choose next video to play...
        Random random = new Random();
        int randInt = random.nextInt(videoList.length);

        //...with no <U>consecutive</U> replays of one video
        while(randInt == previousVideo){
            randInt = random.nextInt(videoList.length);
        }
        //simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.breathe_video_trees01));
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoList[randInt]));
        previousVideo = randInt;

        //when the current video is finished, start playing a new video
        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                chooseVideo();
                simpleVideoView.start();
            }
        });
    }

    public void onPause() {
        //stop the music if focus is lost
        super.onPause();
        if (audioPlayer.isPlaying())
            audioPlayer.stop();
    }

}
