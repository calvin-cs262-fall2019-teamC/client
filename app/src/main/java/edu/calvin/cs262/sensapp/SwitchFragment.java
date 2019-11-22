package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

/**
 * A {@link Fragment} subclass that contains a switch. When clicked,
 * the switch animates (from off to on or on to off) and makes a sound.
 * If the setting is enabled, the device also vibrates.
 */

public class SwitchFragment extends Fragment implements View.OnClickListener {
    public MediaPlayer switchUpPlayer;
    public MediaPlayer switchDownPlayer;
    AnimationDrawable SwitchAnimation;
    Vibrator vibrator;
    private boolean switchOn = false;
    private ImageView switchImage;
    private Context context = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_switch, container, false);

        switchImage = view.findViewById(R.id.switch_image);
        switchImage.setBackgroundResource(R.drawable.switch_animation_on);
        switchImage.setOnClickListener(this);
        SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
        switchUpPlayer = MediaPlayer.create(context, R.raw.switch_up);
        switchDownPlayer = MediaPlayer.create(context, R.raw.switch_down);

        return view;
    }

    /**
     * onClick for the switch so we can play the sound, vibrate, and do the animation
     *
     * @param view The View clicked
     */
    @Override
    public void onClick(View view) {
        SwitchAnimation.start();
        switchOn = !switchOn;

        vibrator = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);

        // https://stackoverflow.com/questions/2614719/how-do-i-get-the-sharedpreferences-from-a-preferenceactivity-in-android
        // Vibrate only if the setting is true
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean vibrate = prefs.getBoolean("vibrate", true);

        if (switchOn) {
            if (vibrate) {
                vibrator.vibrate(200);
            }
            switchDownPlayer.start();
            switchImage.setBackgroundResource(R.drawable.switch_animation_off);
            SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
        } else {
            if (vibrate) {
                vibrator.vibrate(400);
            }
            switchUpPlayer.start();
            switchImage.setBackgroundResource(R.drawable.switch_animation_on);
            SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
        }
    }
}
