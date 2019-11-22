package edu.calvin.cs262.sensapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A {@link Fragment} subclass that holds a {@link JoystickView}
 */

public class JoystickFragment extends Fragment {

    /**
     * Create JoystickFragment
     *
     * @param savedInstanceState Bundle to initialize
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Setup the Fragment with the Joystick
     *
     * @param inflater LayoutInflater to setup the Fragment
     * @param container ViewGroup in which to display
     * @param savedInstanceState Bundle to initialize
     * @return The View after inflation
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_joystick, container, false);
    }
}
