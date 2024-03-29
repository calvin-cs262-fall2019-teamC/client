package edu.calvin.cs262.sensapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * Modeled after https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/.
 */
public class FavoritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseViewModel mDatabaseViewModel;

    /**
     * Create the FavoritesFragment.
     *
     * @param savedInstanceState The Bundle of data to initialize.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * When created populate and place the Favorites Layout in the MainActivity.
     *
     * @param inflater           The LayoutInflater to place the Layout in the MainActivity.
     * @param container          The ViewGroup in which to place the Layout.
     * @param savedInstanceState The Bundle of data to initialize.
     * @return The root View of the activity_main.xml.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Populate the RecyclerView with Favorites
        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        // https://stackoverflow.com/questions/6495898/
        //     findviewbyid-in-fragment?page=1&tab=votes#tab-top
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final FavoritesListAdapter adapter = new FavoritesListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        mDatabaseViewModel.getAllActivities().observe(this, new Observer<List<Activity>>() {
            @Override
            public void onChanged(@Nullable final List<Activity> activities) {
                // Update the cached copy of the activities in the adapter.
                adapter.setActivities(activities);
            }
        });
        mDatabaseViewModel.getAllFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(@Nullable final List<Favorite> favorites) {
                // Update the cached copy of the favorites in the adapter.
                adapter.setFavorites(favorites);
            }
        });

        return view;
    }
}
