package com.group1.exercisetracker;

/**
 * Some code used adapted from SQLite Tutorials located at
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
 * and www.codeofaninja.com/2013/02/android-sqlite-tutorial.html
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class OnClickListenerCreateExercise implements View.OnClickListener {

    private String[] values;
    private String dayClicked;

    @Override
    public void onClick(View view) {

        final Context context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View formElementsView = inflater.inflate(R.layout.exercise_input_form, null, false);

        final EditText editExerciseName = (EditText) formElementsView.findViewById(R.id.editTextExerciseName);
        final NumberPicker editRepetitions = (NumberPicker) formElementsView.findViewById(R.id.npNumReps);
        final EditText editNotes = (EditText) formElementsView.findViewById(R.id.editTextNotes);

        //NumberPicker setup
        values = new String[5];
        for(int i = 0; i < values.length; i++){
            values[i] = Integer.toString(i + 1);
        }
        editRepetitions.setMinValue(1);
        editRepetitions.setMaxValue(5);
        editRepetitions.setDisplayedValues(values);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Exercise")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {

                                String eName = editExerciseName.getText().toString();
                                String dNsme = dayClicked;
                                Integer nReps = editRepetitions.getValue();
                                String notes = editNotes.getText().toString();

                                ObjectExercise objectExercise = new ObjectExercise();
                                objectExercise.setExerciseName(eName);
                                objectExercise.setDayName(dNsme);
                                objectExercise.setNumReps(nReps);
                                objectExercise.setNotes(notes);

                                boolean createSuccessful = new DatabaseHandler(context).createExercise(objectExercise);

                                //if(createSuccessful){
                                if(createSuccessful){
                                    Toast.makeText(context, "Exercise was saved.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to save exercise.", Toast.LENGTH_SHORT).show();
                                }
                                ((DayActivity) context).readRecords(dayClicked);

                                dialog.cancel();
                            }
                        }
                ).show();
    }

    public OnClickListenerCreateExercise(String dayClicked){
        this.dayClicked = dayClicked;
    }
}