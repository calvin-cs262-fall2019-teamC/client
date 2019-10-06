package edu.calvin.cs262.sensapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryActivity extends AppCompatActivity {
    // idea to store context: https://stackoverflow.com/questions/17917968/get-context-in-non-activity-class
    private Context context = this;
    protected BottomNavigationView navigation_bar;

    /**
     * Creates the favorites activity from which favorite activities will be selected.
     * Sets up the bottom navigation bar.
     *
     * @param savedInstanceState The Bundle of information which initializes this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // bottom nav bar, help mainly from:
        // https://medium.com/@hitherejoe/exploring-the-android-design-support-library-bottom-navigation-drawer-548de699e8e0
        navigation_bar = findViewById(R.id.navigation_bar);
        navigation_bar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_favorites:
                                startActivity(new Intent(context, FavoritesActivity.class));
                                break;
                            case R.id.navigation_main:
                                startActivity(new Intent(context, MainActivity.class));
                                break;
                            case R.id.navigation_history:
                                break;
                        }
                        return true;
                    }
                });
    }
}
