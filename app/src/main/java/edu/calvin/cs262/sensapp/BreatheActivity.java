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

    //create companion history list to not allow the same audio file to be played twice
    public int[] audioRecordList;

    //count how full audioRecordList is
    public int audioRecordCount = 0;

    public int previousAudio;

    //create list for videos
    public int[] videoList;

    //create companion history list to not allow the same video file to be played twice
    public int[] videoRecordList;

    //count how full videoRecordList is
    public int videoRecordCount = 0;

    //keep track of previous video
    public int previousVideo;

    //initializes a videoView
    public VideoView simpleVideoView;

    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * video/audio lists must have more than 1 video for this to work
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

        //initialize audioRecordList - remembers which audios have played already
        audioRecordList = new int[audioList.length];
        for(int i=0; i<audioList.length; i++){
            audioRecordList[i] = 0;
        }

        //initialize list of video files (from raw)
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
        for(int i=0; i<videoList.length; i++){
            videoRecordList[i] = 0;
        }

        //initialize the videoView and its mediaController
        simpleVideoView = findViewById(R.id.videoView);
        chooseVideo();

        //initialize mediaController (unused)
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

    /**
     * Chooses next audio from the list randomly
     * (will not play same audio twice until all audios have played or activity resets)
     * (does this by putting a 1 into another 'companion' list whenever the corresponding song is played)
     *
     * no parameters
     */
    public void chooseAudio(){

        //if all audios have been played, reset the companion list
        //(audioRecordCount counts number of times a 1 has been added to the companion list)
        if(audioRecordCount == audioRecordList.length){
            for(int i=0; i<audioList.length; i++){
                audioRecordList[i] = 0;
            }
        audioRecordCount = 0;
        }

        //choose next audio to play

        //(random.nextInt(xxx) limits to length of audioList)
        Random random = new Random();
        int randIndex = random.nextInt(audioList.length);

        //between playthroughs, could play same song consecutively; make sure it doesn't
        while(previousAudio == audioList[randIndex]){
            randIndex = random.nextInt(audioList.length);
        }

        //get okay from companion array (check if audio has been played already)
        while(audioRecordList[randIndex] == 1){

                if(randIndex != audioRecordList.length - 1) {
                    randIndex += 1;
                } else {randIndex = 0;}

            //could also just do this here below, but is O(n^2) :o veryBad
            //randInt = random.nextInt(audioList.length);
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
                audioPlayer.reset();
                chooseAudio();
                audioPlayer.start();
            }
        });
    }

    /**
     * Chooses next video from the list randomly, in the same fashion as the audio
     *
     * no parameters
     */
    public void chooseVideo(){

        //if all videos have been played, reset the companion list
        //(videoRecordCount counts number of times a 1 has been added to the companion list)
        if(videoRecordCount == videoRecordList.length){
            for(int i=0; i<videoList.length; i++){
                videoRecordList[i] = 0;
            }
            videoRecordCount = 0;
        }

        //choose next video to play

        //(random.nextInt(xxx) limits to length of videoList)
        Random random = new Random();
        int randIndex = random.nextInt(videoList.length);

        //between playthroughs, could play same video consecutively; make sure it doesn't
        while(previousVideo == videoList[randIndex]){
            randIndex = random.nextInt(videoList.length);
        }

        //get okay from companion array (check if video has been played already)
        while(videoRecordList[randIndex] == 1){

                if(randIndex != videoRecordList.length - 1) {
                    randIndex += 1;
                } else {randIndex = 0;}

            //could also just do this here below, but is O(n^2) :o veryBad
            //randInt = random.nextInt(videoList.length);
        }

        //set the selected video and update lastVideo
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoList[randIndex]));
        previousVideo = videoList[randIndex];

        //make sure current video doesn't get played again
        videoRecordList[randIndex] = 1;
        videoRecordCount += 1;

        //when the current audio is finished, start playing a new audio
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
