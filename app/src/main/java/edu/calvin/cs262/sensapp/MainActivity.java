package edu.calvin.cs262.sensapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The Main Activity of the app which renders the activities, history, and favorites fragments.
 */
public class MainActivity extends AppCompatActivity {
    private SensappRoomDatabase mSensappRoomDatabase;
    protected BottomNavigationView navigation_bar;
    public static final String EXTRA_MESSAGE =
            "edu.calvin.cs262.sensapp.extra.MESSAGE";
    // idea to store context: https://stackoverflow.com/questions/17917968/
    //                            get-context-in-non-activity-class
    private Context context = this;

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
                            case R.id.navigation_main:
                                selected_fragment = new MainFragment();
                                break;
                            case R.id.navigation_favorites:
                                selected_fragment = new FavoritesFragment();
                                break;
                            case R.id.navigation_history:
                                selected_fragment = new HistoryFragment();
                                break;
                        }

                        // switch to selected Fragment
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        assert selected_fragment != null;
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

        // Get the database
        mSensappRoomDatabase = SensappRoomDatabase.getDatabase(this);
    }

    /**
     * Setup the top menu bar.
     *
     * @param menu The Menu to inflate.
     * @return true.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Handle selection from options menu. Likely only for settings.
     *
     * @param item The options MenuItem selected.
     * @return A true boolean if terminated as expected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this,
                        SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Launches the breathing activity when the button is pressed.
     *
     * @param view The current View object (the breathe activity button).
     */
    public void launchBreatheActivity(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean videos_tutorial = prefs.getBoolean("videos_tutorial", true);
        Intent intent;
        if (videos_tutorial) {
            intent = new Intent(this, VideosTutorial.class);
        }
        else {
            intent = new Intent(this, BreatheActivity.class);
        }
        startActivity(intent);
    }

    /**
     * Launches the bubbles activity when the button is pressed.
     *
     * @param view The current View object (the bubbles activity button).
     */
    public void launchBubblesActivity(View view) {
        Intent intent = new Intent(this, BubblesActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the stories activity when the button is pressed.
     *
     * @param view The current View object (the stories activity button).
     */
    public void launchStoriesActivity(View view) {
        Intent intent = new Intent(this, StoriesActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the animals activity when the button is pressed.
     *
     * @param view The current View object (the animals activity button).
     */
    public void launchAnimalsActivity(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean animals_tutorial = prefs.getBoolean("animals_tutorial", true);
        Intent intent;
        if (animals_tutorial) {
            intent = new Intent(this, AnimalsTutorial.class);
        }
        else {
            intent = new Intent(this, AnimalsActivity.class);
        }
        startActivity(intent);
    }

    /**
     * Launches the music activity when the button is pressed.
     *
     * @param view The current View object (the music activity button).
     */
    public void launchMusicActivity(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean videos_tutorial = prefs.getBoolean("sounds_tutorial", true);
        Intent intent;
        if (videos_tutorial) {
            intent = new Intent(this, MusicTutorial.class);
        }
        else {
            intent = new Intent(this, MusicActivity.class);
        }
        startActivity(intent);
    }

    /**
     * Launches the fidget cube activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchFidgetCubeActivity(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean videos_tutorial = prefs.getBoolean("fidget_tutorial", true);
        Intent intent;
        if (videos_tutorial) {
            intent = new Intent(this, FidgetCubeTutorial.class);
        }
        else {
            intent = new Intent(this, FidgetCubeActivity.class);
        }
        startActivity(intent);
    }

    /**
     * Launches the information page activity when the info button is pressed.
     *
     * @param view The current View object (the info button).
     */
    public void launchInformation(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        String message = view.getTag().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * Records the current activity as favorited and updates the heart icon
     *
     * @param view The current View object (the favorite heart button, must be an ImageButton)
     */
    public void favoriteActivity(View view) {

        // Cast clicked View to ImageButton
        final ImageButton button = (ImageButton) view;

        // Initialize the necessary Drawables
        final Drawable heartBorder = context.getResources().getDrawable(R.drawable.ic_favorite_border);
        final Drawable heartFull = context.getResources().getDrawable(R.drawable.ic_favorite_pink);

        // Insert and get data using Database Async way
        // From https://stackoverflow.com/questions/44167111/
        //          android-room-simple-select-query-cannot-access-database-on-the-main-thread
        AsyncTask.execute(new Runnable() {
            /**
             * Insert or remove the selected Activity from Favorites
             */
            @Override
            public void run() {
                // Get the ID of the Activity selected
                int id = SensappRoomDatabase.getDatabase(context).activityDao()
                        .getActivityIdByName(button.getTag().toString());

                if (SensappRoomDatabase.getDatabase(context)
                        .favoriteDao().getFavoriteById(id) == null) {
                    // Add Activity to Favorites
                    SensappRoomDatabase.getDatabase(context).favoriteDao().insert(new Favorite(id));
                    button.setImageDrawable(heartFull);
                } else {
                    // Remove Activity to Favorites
                    SensappRoomDatabase.getDatabase(context).favoriteDao().deleteFavorite(
                            SensappRoomDatabase.getDatabase(context)
                                    .favoriteDao().getFavoriteById(id));
                    button.setImageDrawable(heartBorder);
                }
            }
        });
    }

    /**
     * Launches the Activity associated with the clicked ImageButton's Tag
     *
     * @param view The View clicked (an Activity ImageButton)
     */
    public void launcherHandler(View view) {
        // Idea from https://stackoverflow.com/questions/40205847/
        //               change-the-onclick-method-of-a-button-programmatically
        String tag = (String) view.getTag();
        if (tag.equals(getString(R.string.activity_one_title))) {
            launchBreatheActivity(view);
        } else if (tag.equals(getString(R.string.activity_two_title))) {
            launchFidgetCubeActivity(view);
        } else if (tag.equals(getString(R.string.activity_three_title))) {
            launchInformation(view);  // Coming soon
        } else if (tag.equals(getString(R.string.activity_four_title))) {
            launchInformation(view);  // Coming soon
        } else if (tag.equals(getString(R.string.activity_five_title))) {
            launchAnimalsActivity(view);
        } else if (tag.equals(getString(R.string.activity_six_title))) {
            launchMusicActivity(view);
        }
    }
}
