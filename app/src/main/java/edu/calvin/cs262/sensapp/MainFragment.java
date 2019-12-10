package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * Modeled after https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/.
 */
public class MainFragment extends Fragment {
    // idea to store context: https://stackoverflow.com/questions/17917968/
    //                            get-context-in-non-activity-class
    private Context context = null;

    /**
     * When created initialize data, including getting the context of the current activity.
     *
     * @param savedInstanceState The Bundle of data to initialize.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    /**
     * When created place the Main Layout in the MainActivity (initialize Favorite buttons).
     *
     * @param inflater           The LayoutInflater to place the Layout in the MainActivity.
     * @param container          The ViewGroup in which to place the Layout.
     * @param savedInstanceState The Bundle of data to initialize.
     * @return The root View of the activity_main.xml.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view
        View returnView = inflater.inflate(R.layout.fragment_main, container, false);

        // Initialize the necessary Drawables and ImageButtons
        final Drawable heartBorder = context.getResources().getDrawable(R.drawable.ic_favorite_border);
        final Drawable heartFull = context.getResources().getDrawable(R.drawable.ic_favorite_pink);
        final ImageButton activityOneFavorite = returnView.findViewById(R.id.activity_one_favorite);
        final ImageButton activityTwoFavorite = returnView.findViewById(R.id.activity_two_favorite);
        final ImageButton activityThreeFavorite = returnView.findViewById(R.id.activity_three_favorite);
        final ImageButton activityFourFavorite = returnView.findViewById(R.id.activity_four_favorite);
        final ImageButton activityFiveFavorite = returnView.findViewById(R.id.activity_five_favorite);
        final ImageButton activitySixFavorite = returnView.findViewById(R.id.activity_six_favorite);

        // Default to empty heart
        activityOneFavorite.setImageDrawable(heartBorder);
        activityTwoFavorite.setImageDrawable(heartBorder);
        activityThreeFavorite.setImageDrawable(heartBorder);
        activityFourFavorite.setImageDrawable(heartBorder);
        activityFiveFavorite.setImageDrawable(heartBorder);
        activitySixFavorite.setImageDrawable(heartBorder);

        // Update to full heart if a Favorite of the activity exists
        SensappRoomDatabase.getDatabase(context)
                .favoriteDao().getAllFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(@Nullable final List<Favorite> favorites) {
                if (favorites != null) {
                    // https://www.geeksforgeeks.org/for-each-loop-in-java/
                    for (Favorite favorite : favorites) {
                        Log.d("Favorite", String.valueOf(favorite.getActivityId()));
                        switch (favorite.getActivityId()) {
                            case 1:
                                activityOneFavorite.setImageDrawable(heartFull);
                                break;
                            case 2:
                                activityTwoFavorite.setImageDrawable(heartFull);
                                break;
                            case 3:
                                activityThreeFavorite.setImageDrawable(heartFull);
                                break;
                            case 4:
                                activityFourFavorite.setImageDrawable(heartFull);
                                break;
                            case 5:
                                activityFiveFavorite.setImageDrawable(heartFull);
                                break;
                            case 6:
                                activitySixFavorite.setImageDrawable(heartFull);
                                break;
                            default:
                                throw new RuntimeException("ERROR: unknown favorited activity.");
                        }
                    }
                }
            }
        });

        return returnView;
    }
}
