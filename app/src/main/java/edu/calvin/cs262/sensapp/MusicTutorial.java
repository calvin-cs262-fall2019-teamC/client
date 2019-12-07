package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MusicTutorial extends AppCompatActivity {
    private Context context;
    private MusicTutorialPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_tutorial);

        context = getApplicationContext();

        viewPager = findViewById(R.id.fidget_cube_tutorial_pager);
        adapter = new MusicTutorialPagerAdapter(getSupportFragmentManager(), 6, context);
        viewPager.setAdapter(adapter);
    }

    /**
     * Launches the music activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchMusicActivity(View view) {
        Log.d("MusicTutorial", "activity launching MusicActivity");
        Intent intent = new Intent(context, MusicActivity.class);
        startActivity(intent);
    }

}
