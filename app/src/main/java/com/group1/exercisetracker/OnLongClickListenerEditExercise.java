package com.group1.exercisetracker;

/**
 *  Some code used adapted from SQLite Tutorials located at
 *  http://www.vogella.com/tutorials/AndroidSQLite/article.html
 *  www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
 *  and www.codeofaninja.com/2013/02/android-sqlite-tutorial.html
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class OnLongClickListenerEditExercise implements View.OnLongClickListener {

    private String[] values;
    private String dayClicked;
    Context context;
    String id;


    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();


        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Exercise")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new DatabaseHandler(context).deleteExercise(id);

                            if (deleteSuccessful){
                                Toast.makeText(context, "Exercise was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete exercise.", Toast.LENGTH_SHORT).show();
                            }

//                            ((DayActivity) context).readRecords();

                        }
                        dialog.dismiss();

                    }
                }).show();

        return false;
    }

    public void editRecord(final int exerciseId) {

        final DatabaseHandler databaseHandler = new DatabaseHandler(context);
        ObjectExercise objectExercise = databaseHandler.readSingleExercise(exerciseId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.exercise_input_form, null, false);

        final EditText editTextExerciseName = (EditText) formElementsView.findViewById(R.id.editTextExerciseName);
        final NumberPicker numberPickerNumReps = (NumberPicker) formElementsView.findViewById(R.id.npNumReps);
        final EditText editTextNotes = (EditText) formElementsView.findViewById(R.id.editTextNotes);
        final String editSummary;

        editTextExerciseName.setText(objectExercise.getExerciseName());
        numberPickerNumReps.setValue((int) objectExercise.getNumReps());
        editTextNotes.setText(objectExercise.getNotes());
        editSummary = objectExercise.getNotes();

        //NumberPicker setup
        values = new String[5];
        for(int i = 0; i < values.length; i++){
            values[i] = Integer.toString(i + 1);
        }
        numberPickerNumReps.setMinValue(1);
        numberPickerNumReps.setMaxValue(5);
        numberPickerNumReps.setDisplayedValues(values);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Exercise")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ObjectExercise objectExercise = new ObjectExercise();
                                objectExercise.setId(exerciseId);
                                objectExercise.setExerciseName(editTextExerciseName.getText().toString());
                                objectExercise.setNumReps(numberPickerNumReps.getValue());
                                objectExercise.setNotes(editTextNotes.getText().toString());
                                objectExercise.setSummary(editSummary);

                                boolean updateSuccessful = databaseHandler.updateExercise(objectExercise);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Exercise was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update exercise.", Toast.LENGTH_SHORT).show();
                                }
                                ((DayActivity) context).readRecords(objectExercise.getDayName());

                                dialog.cancel();

                            }

                        }).show();
    }

}