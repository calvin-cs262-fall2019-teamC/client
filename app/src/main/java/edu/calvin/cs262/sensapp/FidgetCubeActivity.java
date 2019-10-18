package edu.calvin.cs262.sensapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class FidgetCubeActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] face_names = {"buttons", "switch", "joystick"};
    private int face_idx;
    private Context context;
    private PagerAdapter adapter;
    private ViewPager viewPager;
    private ImageButton nextButton;
    private ImageButton previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidget_cube);

        context = getApplicationContext();

        face_idx = 0;

        viewPager = findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), face_names.length, context);
        viewPager.setAdapter(adapter);

        nextButton = findViewById(R.id.ButtonRight);
        nextButton.setOnClickListener(this);
        previousButton = findViewById(R.id.ButtonLeft);
        previousButton.setOnClickListener(this);
    }

    /**
     * Navigate to the next face when the right button is clicked, or the previous face when the left button is clicked
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view == nextButton) {
            face_idx = (face_idx + 1) % face_names.length;
        }
        else if (view == previousButton) {
            face_idx = (face_idx - 1) % face_names.length;
        }
        Log.d("FidgetCube", Integer.toString(face_idx));
        viewPager.setCurrentItem(face_idx);
    }
}
