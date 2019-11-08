package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.Button;

public class MusicButtonView extends Button {
    private Context my_context;
    private MediaPlayer mediaPlayer;

    public MusicButtonView(Context context) {
        super(context);
        my_context = context;
    }

    public MusicButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        my_context = context;
    }

    public MusicButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        my_context = context;
    }

    public void makeMusicButton(int drawableID, int audioId, String label) {
        mediaPlayer = MediaPlayer.create(my_context, audioId);
        mediaPlayer.setLooping(true);
        this.setCompoundDrawablesWithIntrinsicBounds(0, drawableID, 0, 0);
        this.setText(label);
    }

    public void playPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    public void stopAudio() {
        mediaPlayer.stop();
    }
}
