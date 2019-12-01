package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * A subclass of {@link Button} that has a top drawable, a text label,
 * and a {@link MediaPlayer} for playing its sound on a loop
 */
public class MusicButtonView extends Button {

    /**
     * Create MusicButtonView
     *
     * @param context Current Context
     */
    public MusicButtonView(Context context) {
        super(context);
    }

    /**
     * Create MusicButtonView
     *
     * @param context Current Context
     * @param attrs AttributeSet to initialize
     */
    public MusicButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Create MusicButtonView
     *
     * @param context Current Context
     * @param attrs AttributeSet to initialize
     * @param defStyleAttr int of whether to initialize style attributes
     */
    public MusicButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Convert dp units to px
     *
     * @param dp int dp value to convert
     * @return int px units for given dp
     */
    public float dp_to_px(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale;
    }

    /**
     * Create a MusicButton with given parameters
     * @param drawableID int ID of drawable image
     * @param stringID String of text label
     */
    public void makeMusicButton(int drawableID, int stringID, boolean isPlaying) {
        Drawable drawable = getResources().getDrawable(drawableID);
        int dim = (int) dp_to_px(200);
        drawable.setBounds(0, 0, dim, dim);
        this.setCompoundDrawables(null, drawable, null, null);
        this.setText(stringID);
        setPlayPause(isPlaying);
    }

    /**
     * Change the drawable's opacity: translucent if we are paused, opaque if we are playing
     */
    public void setPlayPause(boolean isPlaying) {
        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawable = drawables[1];

        if (isPlaying) {
            drawable.setAlpha(255);
        } else {
            drawable.setAlpha(100);
        }
    }

}
