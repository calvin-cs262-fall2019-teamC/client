package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.time.LocalDateTime;

/**
 * An Activity for a virtual fidget cube with buttons, a switch, and a joystick
 */
public class FidgetCubeActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] face_names = {"buttons", "switch", "joystick"};
    private Context context;
    private FidgetCubePagerAdapter adapter;
    private ViewPager viewPager;
    private ImageButton nextButton;
    private ImageButton previousButton;

    // For creating History records once Activity is used for 5 or more seconds
    private HistoryManager hist_manager;

    /**
     * Create FidgetCubeActivity setting up the buttons and pager.
     *
     * @param savedInstanceState Bundle to initialize
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hist_manager = new HistoryManager(getString(R.string.activity_two_title),
                getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidget_cube);

        context = getApplicationContext();

        viewPager = findViewById(R.id.fidget_cube_pager);
        adapter = new FidgetCubePagerAdapter(getSupportFragmentManager(), face_names.length, context);
        viewPager.setAdapter(adapter);

        nextButton = findViewById(R.id.ButtonRight);
        nextButton.bringToFront();
        nextButton.setOnClickListener(this);
        previousButton = findViewById(R.id.ButtonLeft);
        previousButton.bringToFront();
        previousButton.setOnClickListener(this);
    }

    /**
     * Navigate to the next face when the right button is clicked, or the previous face when the left button is clicked
     *
     * @param view The View clicked
     */
    @Override
    public void onClick(View view) {
        int idx = viewPager.getCurrentItem();
        if (view == nextButton) {
            viewPager.setCurrentItem((idx + 1) % face_names.length);
        } else if (view == previousButton) {
            // Add length to prevent negative indexes (because % can return a negative number)
            viewPager.setCurrentItem((idx - 1 + face_names.length) % face_names.length);
        }
    }

    /**
     * Creates a History record of this activity if it was open for 5 or more seconds
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        hist_manager.createRecord();
    }

    /**
     * Restart the timer when Activity is restarted
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        hist_manager.startTime = LocalDateTime.now();
    }
}
