package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A {@link Fragment} subclass that holds 5 buttons ({@link FloatingActionButton})
 * that can be clicked, triggering a sound (and, if enabled, vibration).
 */
public class ButtonFragment extends Fragment implements View.OnClickListener {
    public MediaPlayer buttonSoundPlayer;
    Vibrator vibrator;
    private Context context = null;

    /**
     * Create the Fragment.
     *
     * @param savedInstanceState Bundle to initialize
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        buttonSoundPlayer = MediaPlayer.create(context, R.raw.button_click_short);
    }

    /**
     * Setup the Button Views
     *
     * @param inflater LayoutInflater to setup Layout
     * @param container ViewGroup to display Views in
     * @param savedInstanceState Bundle to initialize
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        int[] buttonLabelArray = new int[]{R.id.circlebutton_topleft, R.id.circlebutton_topright,
                R.id.circlebutton_center, R.id.circlebutton_bottomleft, R.id.circlebutton_bottomright};
        FloatingActionButton button;
        for (int i = 0; i < buttonLabelArray.length; i++) {
            button = view.findViewById(buttonLabelArray[i]);
            button.setOnClickListener(this);
        }
        return view;
    }

    /**
     * onClick for the buttons so we can play sound and vibrate when button is clicked.
     *
     * @param view The View clicked
     */
    @Override
    public void onClick(View view) {

        buttonSoundPlayer.start();

        // https://stackoverflow.com/questions/2614719/how-do-i-get-the-sharedpreferences-from-a-preferenceactivity-in-android
        // Vibrate if the setting is true
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean vibrate = prefs.getBoolean("vibrate", true);
        if (vibrate) {
            vibrator = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
            assert vibrator != null;
            vibrator.vibrate(300);
        }
    }
}
