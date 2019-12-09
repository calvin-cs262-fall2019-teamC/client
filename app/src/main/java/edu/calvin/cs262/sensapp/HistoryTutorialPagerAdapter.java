package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * HistoryTutorialPagerAdapter is a FragmentStatePagerAdapter for the pages of the History tutorial
 */
public class HistoryTutorialPagerAdapter extends FragmentStatePagerAdapter {
    private int numPages;
    private Context context;

    /**
     * Construct a PagerAdapter
     *
     * @param fm FragmentManager
     * @param numPages int of number pages in the tutorial
     * @param app_context Current Context
     */
    public HistoryTutorialPagerAdapter(FragmentManager fm, int numPages, Context app_context) {
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
                fragment_data.putInt("textID", R.string.history_tutorial1);
                fragment_data.putInt("drawableID", R.drawable.history_pic);
                break;
            case 1:
                fragment_data.putInt("textID", R.string.history_tutorial2);
                fragment_data.putInt("drawableID", R.drawable.rating_selection);
                break;
            case 2:
                fragment_data.putInt("textID", R.string.history_tutorial3);
                fragment_data.putInt("drawableID", R.drawable.rating_reselection);
                break;
            default:
                throw new RuntimeException("ERROR: unknown page visited in history tutorial.");
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
