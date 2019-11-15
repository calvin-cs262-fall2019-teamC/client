package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

public class Bubble extends ImageButton implements View.OnClickListener{
    AnimationDrawable PopAnimation;

    public Bubble(Context context) {
        super(context);
        makeBubble();
    }

    public Bubble(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeBubble();
    }

    public Bubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        makeBubble();
    }

    public void makeBubble() {
//        Bubble bubble = findViewById(R.id.bubble);
//        bubble.setImageResource(R.drawable.bubble_pop_frame_01);
        this.setBackgroundResource(R.drawable.bubble_pop_animation);
        PopAnimation = (AnimationDrawable) this.getBackground();
        PopAnimation.start();
    }

    @Override
    public void onClick(View view) {

    }

}

