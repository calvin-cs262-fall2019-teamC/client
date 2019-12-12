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

public class AnimalsTutorial extends AppCompatActivity {
    private Context context;
    private AnimalsTutorialPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_tutorial);

        context = getApplicationContext();

        viewPager = findViewById(R.id.tutorial_pager);
        adapter = new AnimalsTutorialPagerAdapter(getSupportFragmentManager(), 2, context);
        viewPager.setAdapter(adapter);
    }

    /**
     * Launches the animals activity when the button is pressed.
     *
     * @param view The current View object (the "Done" button).
     */
    public void launchAnimalsActivity(View view) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("animals_tutorial", false);
        editor.apply();

        Log.d("AnimalsTutorial", "activity launching AnimalsActivity");
        Intent intent = new Intent(context, AnimalsActivity.class);
        startActivity(intent);
    }

}