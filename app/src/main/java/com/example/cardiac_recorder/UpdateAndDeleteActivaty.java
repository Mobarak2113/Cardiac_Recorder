package com.example.cardiac_recorder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class UpdateAndDeleteActivaty extends AppCompatActivity {

    EditText dateInput, timeInput, systolicInput, diastolicInput, pulseInput, commentInput;
    Button update_button,delete_button;
    String Dataid,Systolic,Diastolic,Pulse,Date,Time,Comment;

    /**
     * activity is created and this method execute data
     * update and delete in database using MyDatabaseHelper Class;
     * @param savedInstanceState
     * As for activity onCreate takes a Bundle parameter
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_activaty);
        dateInput = findViewById(R.id.date3);
        timeInput = findViewById(R.id.time3);
        systolicInput = findViewById(R.id.systolic3);
        diastolicInput = findViewById(R.id.diastolic3);
        pulseInput = findViewById(R.id.pulse3);
        commentInput = findViewById(R.id.comment3);
        update_button = findViewById(R.id.update_Button3);
        delete_button = findViewById(R.id.delete_Button);
        getandsetIntentData();
        ActionBar ab = getSupportActionBar() ;
        if(ab!=null)
        {
            ab.setTitle("Back");
        }

        /**
         * this method will update the data
         * in database using MyDatabaseHelper Class;
         */

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateAndDeleteActivaty.this);

                 Date = dateInput.getText().toString();
                 Time = timeInput.getText().toString();
                 Systolic = systolicInput.getText().toString();
                Diastolic = diastolicInput.getText().toString();
                 Pulse = pulseInput.getText().toString();
                 Comment = commentInput.getText().toString();

                myDB.UpdateData(Dataid,Systolic,Diastolic,Pulse,Date,Time,Comment);
            }

        });

        /**
         * this method will delete the data
         * in database using MyDatabaseHelper Class;
         */

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    /**
     * this method will gett data from main activity recyclerview item
     * and this data will be show in updateanddelete activaty page input formate for further update or deletion
     */

    void getandsetIntentData(){
        if(getIntent().hasExtra("dataid") && getIntent().hasExtra("systolic") && getIntent().hasExtra("diastolic") &&
                getIntent().hasExtra("pulse") && getIntent().hasExtra("date") && getIntent().hasExtra("time") &&
                getIntent().hasExtra("comment") ){
            //Getting Data
            Dataid = getIntent().getStringExtra("dataid");
            Systolic = getIntent().getStringExtra("systolic");
            Diastolic = getIntent().getStringExtra("diastolic");
            Pulse = getIntent().getStringExtra("pulse");
            Date = getIntent().getStringExtra("date");
            Time = getIntent().getStringExtra("time");
            Comment = getIntent().getStringExtra("comment");

            //Setting Data
            systolicInput.setText(Systolic);
            diastolicInput.setText(Diastolic);
            pulseInput.setText(Pulse);
            commentInput.setText(Comment);

        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * this confirmdialog method will show before deletion for confirmation
     */



    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " +Dataid+ "?");
        builder.setMessage("Are you want to delete"+Dataid+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateAndDeleteActivaty.this);
                myDB.delete(Dataid);
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}