package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * PagerAdapter is a FragmentStatePagerAdapter for a number of faces of a cube to be paged through
 */
public class VideosTutorialPagerAdapter extends FragmentStatePagerAdapter {
    private int numPages;
    private Context context;

    /**
     * Construct a PagerAdapter
     *
     * @param fm FragmentManager
     * @param numPages int of number of faces on cube
     * @param app_context Current Context
     */
    public VideosTutorialPagerAdapter(FragmentManager fm, int numPages, Context app_context) {
        super(fm);
        this.numPages = numPages;
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
        Bundle fragment_data = new Bundle();
        TutorialItem tutorialItem = new TutorialItem();
        switch (position) {
            case 0:
                Tutorial1 tutorial1 = new Tutorial1();
                fragment_data.putInt("activity_name_id", R.string.activity_one_title);
                tutorial1.setArguments(fragment_data);
                return tutorial1;
            case 1:
                fragment_data.putInt("textID", R.string.activity_one_info);
                break;
            case 2:
                return new TutorialToHistory();
            default:
                throw new RuntimeException("ERROR: unknown page visited in sounds tutorial.");
        }
        tutorialItem.setArguments(fragment_data);
        return tutorialItem;
    }

    /**
     * Return the number of tutorial pages
     */
    @Override
    public int getCount() {
        return numPages;
    }
}
