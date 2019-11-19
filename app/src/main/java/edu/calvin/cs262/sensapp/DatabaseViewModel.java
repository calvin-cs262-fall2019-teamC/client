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
    private ActivityRepository mActivityRepository;
    private LiveData<List<Activity>> mAllActivities;
    private HistoryRepository mHistoryRepository;
    private LiveData<List<History>> mAllHistories;

    /**
     * Setup a FavoritesRepository and get all Favorites from it.
     *
     * @param application This Application.
     */
    public DatabaseViewModel (Application application) {
        super(application);
        mFavoriteRepository = new FavoriteRepository(application);
        mAllFavorites = mFavoriteRepository.getAllFavorites();
        mActivityRepository = new ActivityRepository(application);
        mAllActivities = mActivityRepository.getAllActivities();
        mHistoryRepository = new HistoryRepository(application);
        mAllHistories = mHistoryRepository.getAllHistories();
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

    /**
     * Gets all Activities in the DB.
     *
     * @return LiveData List of all Activities in the database.
     */
    public LiveData<List<Activity>> getAllActivities() { return mAllActivities; }

    /**
     * Inserts an Activity into the DB.
     *
     * @param favorite Activity to be inserted.
     */
    public void insertActivities(Activity favorite) { mActivityRepository.insert(favorite); }

    /**
     * Deletes all Activities in the DB.
     */
    public void deleteAllActivities() {mActivityRepository.deleteAll();}

    /**
     * Delete an Activity from the DB.
     *
     * @param favorite Activity to be deleted.
     */
    public void deleteActivity(Activity favorite) {mActivityRepository.deleteActivity(favorite);}

    /**
     * Gets all Activities in the DB.
     *
     * @return LiveData List of all Activities in the database.
     */
    public LiveData<List<History>> getAllHistories() { return mAllHistories; }

    /**
     * Inserts a History into the DB.
     *
     * @param favorite History to be inserted.
     */
    public void insertHistories(History favorite) { mHistoryRepository.insert(favorite); }

    /**
     * Deletes all Histories in the DB.
     */
    public void deleteAllHistories() {mHistoryRepository.deleteAll();}

    /**
     * Delete a History from the DB.
     *
     * @param favorite History to be deleted.
     */
    public void deleteHistory(History favorite) {mHistoryRepository.deleteHistory(favorite);}
}
