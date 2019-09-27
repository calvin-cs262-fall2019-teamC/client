package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FidgetCubeActivity extends AppCompatActivity {
    AnimationDrawable SwitchAnimation;
    private boolean switchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidget_cube);

        ImageView switchImage = findViewById(R.id.switch_image);
        switchImage.setBackgroundResource(R.drawable.switch_animation_on);
        SwitchAnimation = (AnimationDrawable) switchImage.getBackground();

        switchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchAnimation.start();
                switchOn = ! switchOn;
                ImageView switchImage = findViewById(R.id.switch_image);
                if (switchOn) {
                    switchImage.setBackgroundResource(R.drawable.switch_animation_off);
                    SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
                }
                else {
                    switchImage.setBackgroundResource(R.drawable.switch_animation_on);
                    SwitchAnimation = (AnimationDrawable) switchImage.getBackground();
                }
            }
        });
    }
}
