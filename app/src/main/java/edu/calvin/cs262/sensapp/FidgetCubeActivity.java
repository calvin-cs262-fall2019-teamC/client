package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FidgetCubeActivity extends AppCompatActivity {
    AnimationDrawable SwitchAnimation;
    private boolean switchOn = false;
    private ImageView switchImage;
    public MediaPlayer buttonSoundPlayer;
    public MediaPlayer switchUpPlayer;
    public MediaPlayer switchDownPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidget_cube);

        switchImage = findViewById(R.id.switch_image);
        switchImage.setBackgroundResource(R.drawable.switch_animation_on);
        SwitchAnimation = (AnimationDrawable) switchImage.getBackground();

        buttonSoundPlayer = MediaPlayer.create(this, R.raw.button_click_short);
        switchUpPlayer = MediaPlayer.create(this, R.raw.switch_up);
        switchDownPlayer = MediaPlayer.create(this, R.raw.switch_down);
    }

    public void playButtonClick(View view) {
        buttonSoundPlayer.start();
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
    public void launchJoystickActivity(View view) {
        Intent intent = new Intent(this, JoystickActivity.class);
        startActivity(intent);
    }
}
