package edu.calvin.cs262.sensapp;

import java.util.ArrayList;
import java.util.List;

/**
 * A Factory class for returning a list of {@link MusicButtonView}s for a given category
 * from {@link MusicCategoryFragment}
 */

public class MusicButtonFactory {
    private static MusicButtonFactory INSTANCE;
    private final MusicButtonData catPurr = new MusicButtonData(R.drawable.cat, R.raw.cat_purring, "Purr");
    private final MusicButtonData leafCrunch = new MusicButtonData(R.drawable.leaves_square, R.raw.footsteps_on_dry_leaves, "Leaves");
    private final MusicButtonData forest = new MusicButtonData(R.drawable.forest, R.raw.forest, "Forest");
    private final MusicButtonData rain = new MusicButtonData(R.drawable.rain, R.raw.rain_heavy_2_rural, "Rain");
    private final MusicButtonData river = new MusicButtonData(R.drawable.river, R.raw.small_river_1_slow_close, "River");
    private final MusicButtonData birds = new MusicButtonData(R.drawable.bird, R.raw.spring_birds_new_jersey, "Birds");

    public static synchronized MusicButtonFactory getInstance() {
        if (INSTANCE == null) {
            return new MusicButtonFactory();
        }
        return INSTANCE;
    }

    private MusicButtonFactory() {

    }

    public List<MusicButtonData> getMusicButtonData(String category) {
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
}
