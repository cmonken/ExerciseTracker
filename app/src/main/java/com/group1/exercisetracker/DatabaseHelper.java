package com.group1.exercisetracker;

/**
 * Modified code adapted from SQLite tutorials
 * from www.vogella.com/tutorials/AndroidSQLite/article.html,
 * www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
 * and www.codeofaninja.com/2013/02/android-sqlite-tutorial.html
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper  extends SQLiteOpenHelper{

    // Database name
    private static final String DATABASE_NAME = "workout.db";

    // Database version number
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_NAME = "exercise";

    // Column names
    public static final String KEY_ID = "_id";
    public static final String COLUMN_EXERCISE = "exerciseName";
    public static final String COLUMN_WEEKDAY = "dayName";
    public static final String COLUMN_REPETITIONS = "numReps";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_SUMMARY = "summary";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_COMPLETED_TIME = "time";

    // Table creation statement
    private static final String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EXERCISE + " TEXT, " + COLUMN_WEEKDAY + " TEXT, "
            + COLUMN_REPETITIONS + " INTEGER, " + COLUMN_NOTES + " TEXT, "+ COLUMN_SUMMARY + " TEXT, " + COLUMN_DATE + " TEXT, "
            + COLUMN_COMPLETED_TIME + " TEXT );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);

        onCreate(db);
    }

}