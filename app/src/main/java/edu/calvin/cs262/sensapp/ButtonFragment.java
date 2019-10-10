package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ButtonFragment extends Fragment implements View.OnClickListener {
    // idea to store context: https://stackoverflow.com/questions/17917968/get-context-in-non-activity-class
    private Context context = null;
    public MediaPlayer buttonSoundPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        buttonSoundPlayer = MediaPlayer.create(context, R.raw.button_click_short);
    }

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
     * onClick for the buttons so we can play sound when button is clicked.
     * @param view
     */
    @Override
    public void onClick(View view) {
        buttonSoundPlayer.start();
        if (buttonSoundPlayer.isPlaying()) {
            Log.d("ButtonFragment", "button playing!");
        }
    }
}
