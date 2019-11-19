package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * Modeled after https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/.
 */
public class MainFragment extends Fragment {
    // idea to store context: https://stackoverflow.com/questions/17917968/get-context-in-non-activity-class
    private Context context = null;

    /**
     * When created initialize data, including getting the context of the current activity.
     *
     * @param savedInstanceState The Bundle of data to initialize.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    /**
     * When created place the Main Layout in the MainActivity.
     *
     * @param inflater           The LayoutInflater to place the Layout in the MainActivity.
     * @param container          The ViewGroup in which to place the Layout.
     * @param savedInstanceState The Bundle of data to initialize.
     * @return The root View of the activity_main.xml.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
