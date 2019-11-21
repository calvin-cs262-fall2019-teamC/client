package edu.calvin.cs262.sensapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository to access the Favorite Table through DAO
 */
public class FavoriteRepository {

    private FavoriteDao mFavoriteDao;
    private LiveData<List<Favorite>> mAllFavorites;

    /**
     * Create this Repository by getting a database and all Favorites
     *
     * @param application Current Application
     */
    FavoriteRepository(Application application) {
        SensappRoomDatabase db = SensappRoomDatabase.getDatabase(application);
        mFavoriteDao = db.favoriteDao();
        mAllFavorites = mFavoriteDao.getAllFavorites();
    }

    /**
     * Return all Favorites in DB
     *
     * @return LiveData List of all Favorites
     */
    LiveData<List<Favorite>> getAllFavorites() {
        return mAllFavorites;
    }

    /**
     * Spawn AsyncTask to delete all Favorites
     */
    public void deleteAll()  {
        new deleteAllFavoritesAsyncTask(mFavoriteDao).execute();
    }

    /**
     * Spawn AsyncTask to insert one Favorite
     *
     * @param favorite Favorite to be inserted
     */
    public void insert (Favorite favorite) {
        new insertAsyncTask(mFavoriteDao).execute(favorite);
    }

    /**
     * Spawn AsyncTask to delete one Favorite
     *
     * @param favorite Favorite to be deleted
     */
    public void deleteFavorite(Favorite favorite)  {
        new deleteFavoriteAsyncTask(mFavoriteDao).execute(favorite);
    }

    /**
     * AsyncTask to insert new Favorites
     */
    private static class insertAsyncTask extends AsyncTask<Favorite, Void, Void> {

        private FavoriteDao mAsyncTaskDao;

        insertAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Favorite... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all Favorites
     */
    private static class deleteAllFavoritesAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteDao mAsyncTaskDao;

        deleteAllFavoritesAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * AsyncTask to delete a single Favorite
     */
    private static class deleteFavoriteAsyncTask extends AsyncTask<Favorite, Void, Void> {
        private FavoriteDao mAsyncTaskDao;

        deleteFavoriteAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Favorite... params) {
            mAsyncTaskDao.deleteFavorite(params[0]);
            return null;
        }
    }
}