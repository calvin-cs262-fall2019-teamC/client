package edu.calvin.cs262.sensapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data accessor for the Favorite Table
 */
@Dao
public interface FavoriteDao {

    // Insert a Favorite obj
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favorite favorite);

    // Delete all Favorites
    @Query("DELETE FROM favorite_table")
    void deleteAll();

    // Get all Favorites ordered by ID
    @Query("SELECT * from favorite_table ORDER BY activityId ASC")
    LiveData<List<Favorite>> getAllFavorites();

    // Get one Favorite
    @Query("SELECT * from favorite_table LIMIT 1")
    Favorite[] getAnyFavorite();

    // Get one Favorite based on a given activityID
    @Query("SELECT * FROM favorite_table WHERE activityId = :id LIMIT 1")
    Favorite getFavoriteById(int id);
  
    // Delete a Favorite
    @Delete
    void deleteFavorite(Favorite favorite);

    /*
    // Get all Activities which a are referenced by Favorites
    @Query("SELECT * FROM activity_table INNER JOIN favorite_table ON " +
            "activity_table.id = favorite_table.activityId")
    List<Activity> getFavoritedActivites();
     */
}
