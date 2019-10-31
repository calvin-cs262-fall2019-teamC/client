package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class MusicButtonView extends Button {
    private Context my_context;
    private MediaPlayer mediaPlayer;

    public MusicButtonView(Context context) {
        super(context);
        makeMusicButton(context);
    }

    public MusicButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeMusicButton(context);
    }

    public MusicButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        makeMusicButton(context);
    }

    private void makeMusicButton(Context context) {
        my_context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.forest);
        mediaPlayer.setLooping(true);
    }

    public void playPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }
}
