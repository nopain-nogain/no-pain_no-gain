package com.nopain_nogain.npng;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nopain_nogain.npng.dbtables.ExerciseTable;

import java.util.ArrayList;

public class CreateExercise extends AppCompatActivity{

    private DBHelper db;
    ListView listView;
    ArrayAdapter<ExerciseTable> adapter = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        listView = findViewById(R.id.editingExerciseList);
        db =  new DBHelper(this);

        ArrayList<ExerciseTable> table = db.getAllExercise();

        adapter = new ArrayAdapter<>(this,
                R.layout.item_exercise_train,R.id.nameExercise, table);

        listView.setAdapter(adapter);


    }

    public void onClickAddExercise(View v){
        Intent intent = new Intent(getApplicationContext(),AddExerciseActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteItem(View v){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateExercise.this);
        alertDialog.setTitle("Are you sure?");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);


        alertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // empty
            }
        } );

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int positionView = listView.getPositionForView(v);
                Toast.makeText(getApplicationContext(), "Exercise was deleted", Toast.LENGTH_LONG).show();
                long idExercise = adapter.getItem(positionView).getId();
                // TODO: Need add delete row table approach and repeat depends this exercise
                db.deleteExercise(idExercise);
                recreate();
            }
        } );

        alertDialog.show();
    }
}


