package edu.calvin.cs262.sensapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int NumFaces;
    private Context contex;

    public PagerAdapter(FragmentManager fm, int NumFaces, Context app_context) {
        super(fm);
        this.NumFaces = NumFaces;
        contex = app_context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ButtonFragment();
            case 1:
                return new SwitchFragment();
            case 2:
                return new JoystickFragment();
            default:
                throw new RuntimeException("ERROR: unknown face visited in fidget cube.");
        }
    }

    @Override
    public int getCount() {
        return NumFaces;
    }
}
