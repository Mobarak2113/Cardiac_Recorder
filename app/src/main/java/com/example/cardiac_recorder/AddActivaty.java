package com.example.cardiac_recorder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


/**
 * this CreateRecord activity creates a record and
 * put it or update it into the sqlite database
 */
public class AddActivaty extends AppCompatActivity {
    EditText dateInput, timeInput, systolicInput, diastolicInput, pulseInput, commentInput;
    Button add_button;
    MyDatabaseHelper myDatabaseHelper;

    /**
     * this onCreate method will execute when CreateRecord
     * activity is created and this method execute data
     * insertion and update in database using MyDatabaseHelper Class;
     *
     * @param savedInstanceState As for activity onCreate takes a Bundle parameter
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activaty);
        dateInput = findViewById(R.id.date);
        timeInput = findViewById(R.id.time);
        systolicInput = findViewById(R.id.systolic);
        diastolicInput = findViewById(R.id.diastolic);
        pulseInput = findViewById(R.id.pulse);
        commentInput = findViewById(R.id.comment);
        add_button = findViewById(R.id.add_button2);
        myDatabaseHelper = new MyDatabaseHelper(AddActivaty.this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();
        String date_value = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        dateInput.setText(date_value);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String time_value = simpleDateFormat.format(calendar.getTime());
        timeInput.setText(time_value);




        add_button.setOnClickListener(new View.OnClickListener() {
                /**
                 * this method will be used for data insertion
                 * in database using MyDatabaseHelper Class
                 * @param v
                 * take a View type parameter to get user input
                 */
                @Override
                public void onClick(View v) {


                    String systol = systolicInput.getText().toString();
                    String diastol = diastolicInput.getText().toString();
                    String pulse = pulseInput.getText().toString();
                    String comments = commentInput.getText().toString();

                    systolicInput.setText("");
                    diastolicInput.setText("");
                    pulseInput.setText("");
                    commentInput.setText("");


                    if (TextUtils.isEmpty(systol)) {
                        systolicInput.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(diastol)) {
                        diastolicInput.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(comments)) {
                        commentInput.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(pulse)) {
                        pulseInput.setError("Required");
                        return;
                    }

                    long id = myDatabaseHelper.insertData(systol, diastol, pulse, date_value, time_value, comments);

                    if (id == -1) {
                        Toast.makeText(AddActivaty.this, "Failled", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddActivaty.this, "Data added Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddActivaty.this, MainActivity.class));
                        finish();
                    }
                }


            });



    }
}
