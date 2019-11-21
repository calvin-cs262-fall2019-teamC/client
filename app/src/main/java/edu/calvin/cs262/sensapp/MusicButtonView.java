package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * A subclass of {@link Button} that has a top drawable, a text label,
 * and a {@link MediaPlayer} for playing its sound on a loop
 */

public class MusicButtonView extends Button {
    private MediaPlayer mediaPlayer;
    public MusicButtonView(Context context) {
        super(context);
    }

    public MusicButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MusicButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float dp_to_px(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale;
    }

    public void makeMusicButton(int drawableID, int audioId, String label, Context context) {
        mediaPlayer = MediaPlayer.create(context, audioId);
        mediaPlayer.setLooping(true);
        Drawable drawable = getResources().getDrawable(drawableID);
        int dim = (int) dp_to_px(200);
        drawable.setBounds(0, 0, dim, dim);
        drawable.setAlpha(100);
        this.setCompoundDrawables(null, drawable, null, null);
        this.setText(label);
    }

    /**
     * If the audio isn't playing, play the audio and make the drawable more opaque
     * Else, pause the audio and make the drawable less opaque
     */
    public void playPause() {
        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawable = drawables[1];
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            drawable.setAlpha(100);
        } else {
            mediaPlayer.start();
            drawable.setAlpha(255);
        }
    }

    public void stopAudio() {
        mediaPlayer.stop();
    }
}