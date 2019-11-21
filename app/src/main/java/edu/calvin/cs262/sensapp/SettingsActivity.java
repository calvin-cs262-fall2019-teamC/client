package edu.calvin.cs262.sensapp;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Activity to display all settings of app.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * Initialize the Settings Fragment in the Settings Activity.
     *
     * @param savedInstanceState The saved Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * The Settings Fragment to display the necessary settings.
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {

        /**
         * Initialize the Settings Activity with the root preferences.
         *
         * @param savedInstanceState The saved Bundle.
         * @param rootKey            A String of where to set Settings.
         */
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}