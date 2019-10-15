package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 *
 * Modeled after https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/.
 */
public class FavoritesFragment extends Fragment {
    // idea to store context: https://stackoverflow.com/questions/17917968/get-context-in-non-activity-class
    private Context context = null;

    /**
     * Create a new FavoritesFragment instance initializing the context for use in creating Intents.
     *
     * @param ctxt The Context of the MainActivity.
     * @return The new Fragment.
     */
    public static FavoritesFragment newInstance(Context ctxt) {
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.context = ctxt;
        return fragment;
    }

    /**
     * When created initialize data.
     *
     * @param savedInstanceState The Bundle of data to initialize.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * When created place the Favorites Layout in the MainActivity.
     *
     * @param inflater The LayoutInflater to place the Layout in the MainActivity.
     * @param container The ViewGroup in which to place the Layout.
     * @param savedInstanceState The Bundle of data to initialize.
     * @return The root View of the activity_main.xml.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    /**
     * Launches the breathing activity when the button is pressed.
     *
     * @param view The current View object (the breathe activity button).
     */
    public void launchBreatheActivity(View view) {
        Intent intent = new Intent(context, BreatheActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the bubbles activity when the button is pressed.
     *
     * @param view The current View object (the bubbles activity button).
     */
    public void launchBubblesActivity(View view) {
        Intent intent = new Intent(context, BubblesActivity.class);
        startActivity(intent);
    }
}
