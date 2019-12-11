package edu.calvin.cs262.sensapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

/**
 * The Breathe activity plays calming audio over a video and will tell the user to breathe.
 */
public class BreatheActivity extends AppCompatActivity {

    //create mediaPlayer to play the audio files
    public MediaPlayer audioPlayer;
    //create list to store audio files
    public int[] audioList;
    //create record list to ensure the same audio file isn't played twice
    public int[] audioRecordList;
    //keep track of how full audioRecordList is
    public int audioRecordCount = 0;
    //remember which audio played last
    public int previousAudio;

    //create VideoView to play the videos
    public VideoView simpleVideoView;
    //create list to store the videos
    public int[] videoList;
    //create record list to ensure the same video isn't played twice
    public int[] videoRecordList;
    //keep track of how full videoRecordList is
    public int videoRecordCount = 0;
    //remember which video played last
    public int previousVideo;

    //for creating History records once Activity is used for 5 or more seconds
    private HistoryManager hist_manager;


    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * NOTE: video/audio lists must have more than 1 video, or else they won't play anything.

     *
     * @param savedInstanceState The Bundle of information which initializes the breathe activity.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hist_manager = new HistoryManager(getString(R.string.activity_one_title),
                getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        //initialize list of audio files (get audio files from raw)
        audioList = new int[]{R.raw.breathe_audio_oceanwaves,
                R.raw.breathe_audio_zymbel,
                R.raw.breathe_audio_wind,
                R.raw.breathe_audio_crickets,
                R.raw.breathe_audio_rainforest,
                R.raw.breathe_audio_waterfall
        };

        //initialize audioRecordList - remembers which audios have played already
        audioRecordList = new int[audioList.length];
        for (int i = 0; i < audioList.length; i++) {
            audioRecordList[i] = 0;
        }

        //initialize list of video files (get videos from raw)
        videoList = new int[]{R.raw.breathe_video_ducks04,
                R.raw.breathe_video_ducks05,
                R.raw.breathe_video_ducks06,
                R.raw.breathe_video_ducks07,
                R.raw.breathe_video_trees03,
                R.raw.breathe_video_trees04,
                R.raw.breathe_video_trees06,
                R.raw.breathe_video_flowers01
        };

        //initialize videoRecordList - remembers which videos have played already
        videoRecordList = new int[videoList.length];
        for (int i = 0; i < videoList.length; i++) {
            videoRecordList[i] = 0;
        }

        //initialize the videoView
        simpleVideoView = findViewById(R.id.videoView);
        chooseVideo();

        //initialize the musicPlayer
        chooseAudio();

        //start video and music
        simpleVideoView.start();
        audioPlayer.start();
    }

    /**
     * Chooses next audio from the list randomly
     * (will not play same audio twice until all audios have played or activity resets)
     */
    public void chooseAudio() {


        //if all audios have been played, reset the record list and counter
        //(audioRecordCount counts number of times a 1 has been added to the record list)
        if(audioRecordCount == audioRecordList.length){
            for(int i=0; i<audioList.length; i++){

                audioRecordList[i] = 0;
            }
            audioRecordCount = 0;
        }

        //choose next audio to play:

        //(random.nextInt(xxx) limits to length of audioList)
        Random random = new Random();
        int randIndex = random.nextInt(audioList.length);

        //between play throughs, could play same song consecutively; make sure it doesn't
        while (previousAudio == audioList[randIndex]) {
            randIndex = random.nextInt(audioList.length);
        }


        //get okay from record list (check if audio has been played already)
        while(audioRecordList[randIndex] == 1){

                if(randIndex != audioRecordList.length - 1) {
                    randIndex += 1;
                } else {randIndex = 0;}

        }

        //set the selected audio and update lastAudio
        audioPlayer = MediaPlayer.create(this, audioList[randIndex]);
        previousAudio = audioList[randIndex];

        //make sure current video doesn't get played again
        audioRecordList[randIndex] = 1;
        audioRecordCount += 1;

        //when the current audio is finished, start playing a new audio
        audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                audioPlayer.release();
                chooseAudio();
                audioPlayer.start();
            }
        });
    }

    /**
     * Chooses next video from the list randomly, in the same fashion as the audio
     */
    public void chooseVideo() {


        //if all videos have been played, reset the record list and counter
        //(videoRecordCount counts number of times a 1 has been added to the record list)
        if(videoRecordCount == videoRecordList.length){
            for(int i=0; i<videoList.length; i++){

                videoRecordList[i] = 0;
            }
            videoRecordCount = 0;
        }

        //choose next video to play:

        //(random.nextInt(xxx) limits to length of videoList)
        Random random = new Random();
        int randIndex = random.nextInt(videoList.length);

        //between play throughs, could play same video consecutively; make sure it doesn't
        while (previousVideo == videoList[randIndex]) {
            randIndex = random.nextInt(videoList.length);
        }


        //get okay from record array (check if video has been played already)
        while(videoRecordList[randIndex] == 1){

                if(randIndex != videoRecordList.length - 1) {
                    randIndex += 1;
                } else {randIndex = 0;}

        }

        //set the selected video and update lastVideo
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoList[randIndex]));
        previousVideo = videoList[randIndex];

        //make sure current video doesn't get played again
        videoRecordList[randIndex] = 1;
        videoRecordCount += 1;

        //when the current video is finished, start playing a new video
        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                chooseVideo();
                simpleVideoView.start();
            }
        });
    }

    /**
     * When app is exited or focus is lost somehow, onPause() is called.
     * When this happens, the music should stop.
     * Creates a History record of this activity if it was open for 5 or more seconds
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPause() {
        super.onPause();
        hist_manager.createRecord();
        if (audioPlayer.isPlaying())
            audioPlayer.stop();
    }

}
