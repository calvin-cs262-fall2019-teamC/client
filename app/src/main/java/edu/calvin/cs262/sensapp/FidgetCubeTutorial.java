package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FidgetCubeTutorial extends AppCompatActivity {
    private Context context;
    private FidgetTutorialPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        context = getApplicationContext();

        viewPager = findViewById(R.id.tutorial_pager);
        adapter = new FidgetTutorialPagerAdapter(getSupportFragmentManager(), 6, context);
        viewPager.setAdapter(adapter);
    }

    /**
     * Launches the fidget cube activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchActivity(View view) {
        Log.d("FidgetCubeTutorial", "activity launching FidgetCubeActivity");
        Intent intent = new Intent(context, FidgetCubeActivity.class);
        startActivity(intent);
    }

}
