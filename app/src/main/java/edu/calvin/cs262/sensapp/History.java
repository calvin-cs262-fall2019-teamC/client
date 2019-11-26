package edu.calvin.cs262.sensapp;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

/**
 * History table from database
 */
@Entity(tableName = "history_table")
public class History {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ForeignKey(entity = Activity.class, parentColumns = "id", childColumns = "activityId")
    @ColumnInfo(name = "activityId")
    private int mActivityId;

    @ColumnInfo(name = "timeStart")
    private String mTimeStart;

    @ColumnInfo(name = "timeEnd")
    private String mTimeEnd;

    @Nullable
    @ColumnInfo(name = "satisfaction")
    private int mSatisfaction;

    /**
     * Constructor for History Table
     *
     * @param id The id primary key field (auto generated)
     * @param activityId The activityId foreign key field
     * @param timeStart The timeStart field (timestamp String like "2007-12-03T10:18:30")
     * @param timeEnd The timeEnd field (timestamp String like "2007-12-03T10:18:30")
     * @param satisfaction The satisfaction field (int 1-5 or NULL)
     */
    public History(int id, int activityId, String timeStart,
                   String timeEnd, @Nullable int satisfaction) {
        this.mId = id;
        this.mActivityId = activityId;
        this.mTimeStart = timeStart;
        this.mTimeEnd = timeEnd;
        this.mSatisfaction = satisfaction;
    }

    /**
     * Getters for Column values
     */
    public int getId() {return this.mId;}
    public int getActivityId(){return this.mActivityId;}
    public String getTimeStart(){return this.mTimeStart;}
    public String getTimeEnd(){return this.mTimeEnd;}
    public int getSatisfaction(){return this.mSatisfaction;}
}
