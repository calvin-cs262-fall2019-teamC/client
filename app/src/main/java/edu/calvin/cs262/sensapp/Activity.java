package edu.calvin.cs262.sensapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Activity table from database
 */
@Entity(tableName = "activity_table")
public class Activity {

    // Columns in Activity Table
    // Help for autogen from https://stackoverflow.com/questions/44109700/how-to-make-primary-key-as-autoincrement-for-room-persistence-lib
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    /**
     * Constructor for Activity Table (id is auto generated)
     *
     * @param id The id field
     * @param name The name field (String)
     */
    public Activity(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    /**
     * Getters for Column values
     */
    public int getId(){return this.mId;}
    public String getName(){return this.mName;}
}

