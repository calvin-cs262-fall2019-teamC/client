package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class FidgetCubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidget_cube);

        // setup the main Fragment upon starting app (one time setup)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new SwitchFragment());
        transaction.commit();
    }

    /**
     * Launches the joystick activity when the button is pressed.
     *
     * @param view The current View object (the fidget cube activity button).
     */
    public void launchJoystickFragment(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new JoystickFragment());
        transaction.commit();
    }

    public void launchButtonFragment(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new ButtonFragment());
        transaction.commit();
    }
}
