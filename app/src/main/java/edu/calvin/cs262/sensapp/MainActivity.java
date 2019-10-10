package edu.calvin.cs262.sensapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // idea to store context: https://stackoverflow.com/questions/17917968/get-context-in-non-activity-class
    private Context context = this;
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
        // https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/
        navigation_bar = findViewById(R.id.navigation_bar);
        navigation_bar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    /**
                     * When a navigation bar item is selected, switch to the corresponding Fragment.
                     *
                     * @param item The selected MenuItem from the navigation bar.
                     * @return true
                     */
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        // select the proper fragment
                        Fragment selected_fragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_favorites:
                                selected_fragment = new FavoritesFragment();
                                break;
                            case R.id.navigation_main:
                                selected_fragment = new MainFragment();
                                break;
                            case R.id.navigation_history:
                                selected_fragment = new HistoryFragment();
                                break;
                        }

                        // switch to selected Fragment
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selected_fragment);
                        transaction.commit();
                        return true;
                    }
            }
        );

        // setup the main Fragment upon starting app (one time setup)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new MainFragment());
        transaction.commit();
    }

    /**
     * Launches the breathing activity when the button is pressed.
     *
     * @param view The current View object (the breathe activity button).
     */
    public void launchBreatheActivity(View view) {
        Log.d("MainActivity", "activity launching BreatheActivity :)");
        Intent intent = new Intent(this, BreatheActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the fidget cube activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchFidgetCubeActivity(View view) {
        Log.d("MainActivity", "activity launching FidgetCubeActivity");
        Intent intent = new Intent(this, FidgetCubeActivity.class);
        startActivity(intent);
    }
}