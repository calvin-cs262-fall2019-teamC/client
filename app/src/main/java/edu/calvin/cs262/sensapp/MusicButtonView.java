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
    public void makeMusicButton(int drawableID, int stringID) {
        Drawable drawable = getResources().getDrawable(drawableID);
        int dim = (int) dp_to_px(200);
        drawable.setBounds(0, 0, dim, dim);
        drawable.setAlpha(100);
        this.setCompoundDrawables(null, drawable, null, null);
        this.setText(stringID);
    }

    /**
     * Make the drawable translucent to show we aren't playing
     */
    public void setPause() {
        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawable = drawables[1];
        drawable.setAlpha(100);
    }

    /**
     * Make the drawable opaque to show we are playing
     */
    public void setPlay() {
        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawable = drawables[1];
        drawable.setAlpha(255);
    }

}
