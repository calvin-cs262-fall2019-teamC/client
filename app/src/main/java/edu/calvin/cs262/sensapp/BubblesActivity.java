package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BubblesActivity extends AppCompatActivity {

    public int bubbleCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubbles);
    }

    //TODO Initialize a function that counts how many bubbles are on the screen at any given time
    int countBubbles(){
        
    }

    //TODO This function will create bubbles, one bubble at a time, an will randomize trajectory
    void createBubbles(){

    }

    //TODO This function will display a popped bubble image when a bubble is clicked, and it will reduce the count of bubbles by one, prompting another bubble to be created
    void onClick() {

    }
}
