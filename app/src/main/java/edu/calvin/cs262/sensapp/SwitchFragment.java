package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SwitchFragment extends Fragment implements View.OnClickListener {
    private boolean switchOn = false;
    AnimationDrawable SwitchAnimation;
    private ImageView switchImage;
    public MediaPlayer switchUpPlayer;
    public MediaPlayer switchDownPlayer;
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
     * onClick for the switch so we can play the sound and do the animation
     * @param view
     */
    @Override
    public void onClick(View view) {
        SwitchAnimation.start();
        switchOn = ! switchOn;
        if (switchOn) {
            switchDownPlayer.start();
            switchImage.setBackgroundResource(R.drawable.switch_animation_off);
            SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
        }
        else {
            switchUpPlayer.start();
            switchImage.setBackgroundResource(R.drawable.switch_animation_on);
            SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
        }
    }
}