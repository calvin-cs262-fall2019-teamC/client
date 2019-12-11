package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

/**
 * A tutorial explaining how to use Sounds
 */
public class MusicTutorial extends AppCompatActivity {
    private Context context;
    private MusicTutorialPagerAdapter adapter;
    private ViewPager viewPager;

    /**
     * Create the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        context = getApplicationContext();

        viewPager = findViewById(R.id.tutorial_pager);
        adapter = new MusicTutorialPagerAdapter(getSupportFragmentManager(), 6, context);
        viewPager.setAdapter(adapter);
    }

    /**
     * Launches the music activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchActivity(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("sounds_tutorial", false);
        editor.apply();

        Log.d("MusicTutorial", "activity launching MusicActivity");
        Intent intent = new Intent(context, MusicActivity.class);
        startActivity(intent);
    }

}
