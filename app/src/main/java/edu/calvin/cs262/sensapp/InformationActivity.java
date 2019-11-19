package edu.calvin.cs262.sensapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    /**
     * Loads in the appropriate information about the activity whose info button was pressed.
     *
     * @param savedInstanceState Bundle of saved info to initialize.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView info = findViewById(R.id.information);

        // determine which activity to display info about and set the text accordingly
        // uses getResources().getString(id) because id is an int by default
        // idea from https://stackoverflow.com/questions/1983548/eclipse-java-values-in-r-string-return-int
        if (message.equals(getResources().getString(R.string.activity_one_title))) {
            info.setText(R.string.activity_one_info);
        } else if (message.equals(getResources().getString(R.string.activity_two_title))) {
            info.setText(R.string.activity_two_info);
        } else if (message.equals(getResources().getString(R.string.activity_three_title))) {
            info.setText(R.string.activity_three_info);
        } else if (message.equals(getResources().getString(R.string.activity_four_title))) {
            info.setText(R.string.activity_four_info);
        } else if (message.equals(getResources().getString(R.string.activity_five_title))) {
            info.setText(R.string.activity_five_info);
        } else if (message.equals(getResources().getString(R.string.activity_six_title))) {
            info.setText(R.string.activity_six_info);
        } else {
            info.setText(R.string.info_error);
        }
    }
}
