package edu.calvin.cs262.sensapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    protected BottomNavigationView navigation_bar;

    /**
     * Creates the main activity from which other activities will be selected.
     * Sets up the bottom navigation bar.
     *
     * @param savedInstanceState The Bundle of information which initializes the main activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bottom nav bar, help mainly from:
        // https://medium.com/@hitherejoe/exploring-the-android-design-support-library-bottom-navigation-drawer-548de699e8e0
        // TODO: Implement each page individually
        navigation_bar = findViewById(R.id.navigation_bar);
        navigation_bar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_favorites:
                                findViewById(R.id.scroll_window).setVisibility(View.VISIBLE);
                            case R.id.navigation_main:
                                findViewById(R.id.scroll_window).setVisibility(View.VISIBLE);
                            case R.id.navigation_history:
                                findViewById(R.id.scroll_window).setVisibility(View.VISIBLE);
                        }
                        return true;
                    }
        });
    }

    /**
     * Launches the breathing activity when the button is pressed.
     *
     * @param view The current View object (the breathe activity button).
     */
    public void launchBreatheActivity(View view) {
        Intent intent = new Intent(this, BreatheActivity.class);
        startActivity(intent);
    }
}
