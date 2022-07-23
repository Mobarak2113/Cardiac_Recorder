package com.example.cardiac_recorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button1;

    MyDatabaseHelper myDB;
    ArrayList<String> data_id,date,time,systolic,diastolic,pulse,comment;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        add_button1 =  findViewById(R.id.add_button1);
        add_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( MainActivity.this, AddActivaty.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(MainActivity.this);
        data_id = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        systolic = new ArrayList<>();
        diastolic = new ArrayList<>();
        pulse = new ArrayList<>();
        comment = new ArrayList<>();

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this, data_id, systolic, diastolic, pulse,date, time,  comment);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                data_id.add(cursor.getString(0));
                systolic.add(cursor.getString(1));
                diastolic.add(cursor.getString(2));
                pulse.add(cursor.getString(3));
                date.add(cursor.getString(4));
                time.add(cursor.getString(5));
                comment.add(cursor.getString(6));
            }
        }
    }
}