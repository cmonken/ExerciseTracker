package com.group1.exercisetracker;

/**
 *  Some code used adapted from SQLite Tutorials located at
 *  http://www.vogella.com/tutorials/AndroidSQLite/article.html
 *  www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
 *  and www.codeofaninja.com/2013/02/android-sqlite-tutorial.html
 */

public class ObjectExercise {

    private Integer id;
    private String exerciseName;
    private String dayName;
    private Integer numReps;
    private String notes;
    private String summary;
    private String date;
    private String time;

    // constructor
    public ObjectExercise(){
        // blank constructor
    }

    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setExerciseName(String eName) {
        this.exerciseName = eName;
    }

    public void setDayName(String dName) {
        this.dayName = dName;
    }

    public void setNumReps(Integer nReps) {
        this.numReps = nReps;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSummary(String notes) {
        this.summary = summary;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // getters
    public Integer getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getDayName() {
        return dayName;
    }

    public Integer getNumReps() {
        return numReps;
    }

    public String getNotes() {
        return notes;
    }

    public String getSummary() {
        return summary;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return "Exercise: " + exerciseName + ", # Reps:  " + numReps + ", Notes: " + notes;
    }
}