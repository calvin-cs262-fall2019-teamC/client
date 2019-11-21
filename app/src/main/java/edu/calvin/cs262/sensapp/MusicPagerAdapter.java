package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * A PagerAdapter for {@link MusicCategoryFragment}, so we can navigate to different pages of music categories
 */
public class MusicPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumTabs;
    private Context context;

    public MusicPagerAdapter(FragmentManager fm, int NumOfTabs, Context app_context) {
        super(fm);
        this.mNumTabs = NumOfTabs;
        context = app_context;
    }

    /**
     * set up the various tabs for music categories
     * @param position indicates which tab to open
     * @return the Fragment that contains the music buttons, labels, etc.
     * @author cmd16
     */
    @NonNull
    @Override
    public MusicCategoryFragment getItem(int position) {
        // use a bundle of data so we can tell the fragment which sound buttons to display
        Bundle fragment_data = new Bundle();
        MusicCategoryFragment tabOfSounds = new MusicCategoryFragment();
        switch (position) {
            case 0:
                fragment_data.putString("Sound_category", context.getString(R.string.animal_sounds_label));
                break;
            case 1:
                fragment_data.putString("Sound_category", context.getString(R.string.nature_sounds_label));
                break;
            case 2:
                fragment_data.putString("Sound_category", context.getString(R.string.water_sounds_label));
                break;
            default:
                throw new RuntimeException("ERROR: unknown tab clicked in Music Activity: " + position);
        }
        tabOfSounds.setArguments(fragment_data);
        return tabOfSounds;
    }

    @Override
    public int getCount() {
        return mNumTabs;
    }
}
