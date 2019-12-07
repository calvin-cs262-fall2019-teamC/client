package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class VideosTutorial extends AppCompatActivity {
    private Context context;
    private VideosTutorialPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_tutorial);

        context = getApplicationContext();

        viewPager = findViewById(R.id.tutorial_pager);
        adapter = new VideosTutorialPagerAdapter(getSupportFragmentManager(), 3, context);
        viewPager.setAdapter(adapter);
    }

    /**
     * Launches the videos activity when the button is pressed.
     *
     * @param view The current View object (the "Done" button).
     */
    public void launchVideosActivity(View view) {
        Log.d("VideosTutorial", "activity launching VideosActivity");
        Intent intent = new Intent(context, BreatheActivity.class);
        startActivity(intent);
    }

}
