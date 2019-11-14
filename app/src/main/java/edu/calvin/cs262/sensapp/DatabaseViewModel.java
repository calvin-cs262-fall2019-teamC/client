package edu.calvin.cs262.sensapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * DatabaseViewModel class to hold/process database data for display by the UI.
 */
public class DatabaseViewModel extends AndroidViewModel {

    private FavoriteRepository mFavoriteRepository;
    private LiveData<List<Favorite>> mAllFavorites;

    /**
     * Setup a FavoritesRepository and get all Favorites from it.
     *
     * @param application This Application.
     */
    public DatabaseViewModel (Application application) {
        super(application);
        mFavoriteRepository = new FavoriteRepository(application);
        mAllFavorites = mFavoriteRepository.getAllFavorites();
    }

    /**
     * Gets all Favorites in the DB.
     *
     * @return LiveData List of all Favorites in the database.
     */
    public LiveData<List<Favorite>> getAllFavorites() { return mAllFavorites; }

    /**
     * Inserts a Favorite into the DB.
     *
     * @param favorite Favorite to be inserted.
     */
    public void insertFavorites(Favorite favorite) { mFavoriteRepository.insert(favorite); }

    /**
     * Deletes all Favorites in the DB.
     */
    public void deleteAllFavorites() {mFavoriteRepository.deleteAll();}

    /**
     * Delete a Favorite from the DB.
     *
     * @param favorite Favorite to be deleted.
     */
    public void deleteFavorite(Favorite favorite) {mFavoriteRepository.deleteFavorite(favorite);}
}
