package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MusicButtonView extends Button {
    private MediaPlayer mediaPlayer;

    public interface OnClick {
        void onClick(MusicButtonView button);
    }

    public MusicButtonView(Context context) {
        super(context);
    }

    public MusicButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void makeMusicButton(int drawableID, int audioId, String label, Context context) {
        mediaPlayer = MediaPlayer.create(context, audioId);
        mediaPlayer.setLooping(true);
//        this.setBackgroundResource(drawableID);
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
