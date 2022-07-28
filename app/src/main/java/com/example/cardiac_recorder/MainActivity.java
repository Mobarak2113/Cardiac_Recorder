package com.example.cardiac_recorder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * this is MainActivity class of CardiacRecorder application
 * Recycle view and input data will be show in this
 * MainActivity class
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button1;

    MyDatabaseHelper myDB;
    ArrayList<String> data_id,date,time,systolic,diastolic,pulse,comment;
    CustomAdapter customAdapter;

    /**
     * this method  will show measurement data in recyle view and navigate to add activati if add button is clickec
     * @param savedInstanceState
     * takes a Bundle type parameter
     */
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

        customAdapter = new CustomAdapter(MainActivity.this, this,data_id, systolic, diastolic, pulse,date, time,  comment);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    /**
     * this method  is for refreashing main activity after adding ,update or delete
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            recreate();
        }
    }
    /**
     * this method  will fetch data from sqlite database and strore in recylerview using custom adaptor
     */
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
    /**
     * this method  for showing menu bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * this method  is for confirming before all delete data
     */
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you want to delete All Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteall();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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