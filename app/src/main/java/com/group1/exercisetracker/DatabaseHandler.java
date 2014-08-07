package com.group1.exercisetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends DatabaseHelper {

    public DatabaseHandler(Context context) {
        super(context);
    }

    public boolean createExercise(ObjectExercise objectExercise) {

        ContentValues values = new ContentValues();
        values.put("exerciseName", objectExercise.getExerciseName());
        values.put("dayName", objectExercise.getDayName());
        values.put("numReps", objectExercise.getNumReps());
        values.put("summary", objectExercise.getSummary());
        values.put("notes", objectExercise.getNotes());
        values.put("date", objectExercise.getDate());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("exercise", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public List<ObjectExercise> readList(String dName) {

        List<ObjectExercise> exerciseList = new ArrayList<ObjectExercise>();

        String sql = "SELECT * FROM exercise WHERE dayName = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[] { dName });

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
                String exerciseName = cursor.getString(cursor.getColumnIndex("exerciseName"));
                String dayName = cursor.getString(cursor.getColumnIndex("dayName"));
                Integer numReps = cursor.getInt(cursor.getColumnIndex("numReps"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String notes = cursor.getString(cursor.getColumnIndex("notes"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                ObjectExercise objectExercise = new ObjectExercise();
                objectExercise.setId(id);
                objectExercise.setExerciseName(exerciseName);
                objectExercise.setDayName(dayName);
                objectExercise.setNumReps(numReps);
                objectExercise.setSummary(summary);
                objectExercise.setNotes(notes);
                objectExercise.setDate(date);

                exerciseList.add(objectExercise);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return exerciseList;
    }

    public ObjectExercise readSingleExercise(int exerciseID) {

        ObjectExercise objectExercise = null;

        String sql = "SELECT * FROM exercise WHERE _id = ?";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(sql, new String[] { "" + exerciseID });

        if (c.moveToFirst()) {
            int id = Integer.parseInt(c.getString(c.getColumnIndex("_id")));
            String eName = c.getString(c.getColumnIndex("exerciseName"));
            String dName = c.getString(c.getColumnIndex("dayName"));
            int nReps = Integer.parseInt(c.getString(c.getColumnIndex("numReps")));
            String summary = c.getString(c.getColumnIndex("summary"));
            String notes = c.getString(c.getColumnIndex("notes"));
            String date = c.getString(c.getColumnIndex("date"));

            objectExercise = new ObjectExercise();
            objectExercise.setId(id);
            objectExercise.setExerciseName(eName);
            objectExercise.setDayName(dName);
            objectExercise.setNumReps(nReps);
            objectExercise.setSummary(summary);
            objectExercise.setNotes(notes);
            objectExercise.setDate(date);
        }
        c.close();
        db.close();

        return objectExercise;
    }

    public boolean updateExercise(ObjectExercise objectExercise) {

        ContentValues values = new ContentValues();

        values.put("exerciseName", objectExercise.getExerciseName());
        values.put("dayName", objectExercise.getDayName());
        values.put("numReps", objectExercise.getNumReps());
        values.put("summary", objectExercise.getSummary());
        values.put("notes", objectExercise.getNotes());
        values.put("date", objectExercise.getDate());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectExercise.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("exerciseName", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public boolean deleteExercise(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        boolean deleteSuccessful = db.delete("exercise", "id = ?", new String[] { id} ) > 0;
        db.close();

        return deleteSuccessful;
    }

}