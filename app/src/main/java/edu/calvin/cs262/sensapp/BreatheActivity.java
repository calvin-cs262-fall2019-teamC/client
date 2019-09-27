package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class BreatheActivity extends AppCompatActivity {

    /**
     * Creates the breathe activity in which the user will be guided to breathe deeply.
     *
     * @param savedInstanceState The Bundle of information which initializes the breathe activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        //initialize videoView
        VideoView simpleVideoView = (VideoView) findViewById(R.id.videoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.trees01));

        //create mediaController
        MediaController mediaController = new MediaController(this);
        simpleVideoView.setMediaController(mediaController);

        //start the video
        simpleVideoView.start();
    }
}
