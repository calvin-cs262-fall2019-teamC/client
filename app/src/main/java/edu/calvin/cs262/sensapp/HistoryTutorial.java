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
 * A tutorial explaining how to use History
 */
public class HistoryTutorial extends AppCompatActivity {
    private Context context;
    private HistoryTutorialPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        context = getApplicationContext();

        viewPager = findViewById(R.id.tutorial_pager);
        adapter = new HistoryTutorialPagerAdapter(getSupportFragmentManager(), 3, context);
        viewPager.setAdapter(adapter);
    }

    /**
     * Launches the main activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchActivity(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("history_tutorial", false);
        editor.apply();

        Log.d("HistoryTutorial", "activity launching HistoryFragment");
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("tab", R.id.navigation_history);
        startActivity(intent);
    }

}
