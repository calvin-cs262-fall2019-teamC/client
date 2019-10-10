package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FidgetCubeActivity extends AppCompatActivity {
    private Context context = this;
    AnimationDrawable SwitchAnimation;
    private boolean switchOn = false;
    private ImageView switchImage;
    public MediaPlayer switchUpPlayer;
    public MediaPlayer switchDownPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidget_cube);

//        switchImage = findViewById(R.id.switch_image);
//        switchImage.setBackgroundResource(R.drawable.switch_animation_on);
//        SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
//        switchUpPlayer = MediaPlayer.create(this, R.raw.switch_up);
//        switchDownPlayer = MediaPlayer.create(this, R.raw.switch_down);


        // setup the main Fragment upon starting app (one time setup)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new FidgetCubeFragment());
        transaction.commit();
    }

    public void switchClick(View view) {
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

    /**
     * Launches the joystick activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchJoystickFragment(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new JoystickFragment());
        transaction.commit();
    }

    public void launchButtonFragment(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ButtonFragment.newInstance(context));
        transaction.commit();
    }
}
