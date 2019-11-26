package edu.calvin.cs262.sensapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data accessor for the History Table
 */
@Dao
public interface HistoryDao {

    // Insert a History obj
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(History history);

    // Delete all Activities
    @Query("DELETE FROM history_table")
    void deleteAll();

    // Get all Activities ordered by ID
    @Query("SELECT * from history_table ORDER BY timeStart ASC")
    LiveData<List<History>> getAllHistories();

    // Get one History
    @Query("SELECT * from history_table LIMIT 1")
    History[] getAnyHistory();

    // Update the satisfaction rating of a History record based on its id
    @Query("UPDATE history_table SET satisfaction = :rating WHERE id = :id")
    void updateRating(int id, int rating);

    // Delete a History
    @Delete
    void deleteHistory(History history);
}
