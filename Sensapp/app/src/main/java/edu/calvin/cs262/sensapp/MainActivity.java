package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchFidgetCube(View view) {
        Log.d(LOG_TAG, "Fidget cube started!");
        Intent intent = new Intent(this, FidgetCube.class);
        startActivity(intent);
    }
}
