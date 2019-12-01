package edu.calvin.cs262.sensapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Factory class for returning a list of {@link MusicButtonView}s for a given category
 * from {@link MusicCategoryFragment}
 */
public class MusicButtonFactory {
    private static MusicButtonFactory INSTANCE;
    private static final MusicButtonData catPurr = new MusicButtonData(R.drawable.cat, R.raw.cat_purring, R.string.purr, false);
    private static final MusicButtonData leafCrunch = new MusicButtonData(R.drawable.leaves_square, R.raw.footsteps_on_dry_leaves, R.string.leaves, false);
    private static final MusicButtonData forest = new MusicButtonData(R.drawable.forest, R.raw.forest, R.string.forest, false);
    private static final MusicButtonData rain = new MusicButtonData(R.drawable.rain, R.raw.rain_heavy_2_rural, R.string.rain, false);
    private static final MusicButtonData river = new MusicButtonData(R.drawable.river, R.raw.small_river_1_slow_close, R.string.river, false);
    private static final MusicButtonData birds = new MusicButtonData(R.drawable.bird, R.raw.spring_birds_new_jersey, R.string.birds, false);
    private final ArrayList<MusicButtonData> dataList = new ArrayList<MusicButtonData>();

    /**
     * Create (if needed) a MusicButtonFactory and return current instance
     */
    public static synchronized MusicButtonFactory getInstance() {
        if (INSTANCE == null) {
            return new MusicButtonFactory();
        }
        return INSTANCE;
    }

    /**
     * Create MusicButtonFragment (blank)
     */
    private MusicButtonFactory() {
        dataList.add(catPurr);
        dataList.add(leafCrunch);
        dataList.add(forest);
        dataList.add(rain);
        dataList.add(river);
        dataList.add(birds);
    }

    /**
     * Get the MusicButtonData for a given category to display
     *
     * @param category String of category name
     * @return List of MusicButtonData
     */
    public static List<MusicButtonData> getMusicButtonData(String category) {
        List<MusicButtonData> list = new ArrayList<MusicButtonData>();
        switch (category) {
            case "Animal":
                list.add(catPurr);
                list.add(birds);
                break;
            case "Nature":
                list.add(forest);
                list.add(leafCrunch);
                break;
            case "Water":
                list.add(rain);
                list.add(river);
                break;
            default:
                throw new RuntimeException("ERROR: UNKNOWN MUSIC CATEGORY: " + category);
        }

        return list;
    }
    
    public ArrayList<MusicButtonData> getDataList() {
        return dataList;
    }
}
