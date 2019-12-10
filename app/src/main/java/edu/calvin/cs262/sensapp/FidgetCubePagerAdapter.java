package edu.calvin.cs262.sensapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * FidgetCubePagerAdapter is a FragmentStatePagerAdapter for a number of faces of a cube to be paged through
 */
public class FidgetCubePagerAdapter extends FragmentStatePagerAdapter {
    private int NumFaces;
    private Context context;

    /**
     * Construct a PagerAdapter
     *
     * @param fm FragmentManager
     * @param NumFaces int of number of faces on cube
     * @param app_context Current Context
     */
    public FidgetCubePagerAdapter(FragmentManager fm, int NumFaces, Context app_context) {
        super(fm);
        this.NumFaces = NumFaces;
        context = app_context;
    }

    /**
     * Get the Fragment at a position
     *
     * @param position int position of the needed Fragment
     * @return needed Fragment
     */
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

    /**
     * Return the number of cube faces
     */
    @Override
    public int getCount() {
        return NumFaces;
    }
}
