package edu.calvin.cs262.sensapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 *
 * Modeled after https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/.
 */
public class FavoritesFragment extends Fragment {

    private DatabaseViewModel mDatabaseViewModel;

    /**
     * When created initialize favorites data from database.
     *
     * @param savedInstanceState The Bundle of data to initialize.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        // https://stackoverflow.com/questions/6495898/findviewbyid-in-fragment?page=1&tab=votes#tab-top
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerview);
        final FavoritesListAdapter adapter = new FavoritesListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        mDatabaseViewModel.getAllFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(@Nullable final List<Favorites> favorites) {
                // Update the cached copy of the players in the adapter.
                adapter.setFavorites(favorites);
            }
        });
    }

    /**
     * When created place the Favorites Layout in the MainActivity.
     *
     * @param inflater The LayoutInflater to place the Layout in the MainActivity.
     * @param container The ViewGroup in which to place the Layout.
     * @param savedInstanceState The Bundle of data to initialize.
     * @return The root View of the activity_main.xml.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }
}
