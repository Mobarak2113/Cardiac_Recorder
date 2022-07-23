package com.example.cardiac_recorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * this is a helper class which extends SQLiteOpenHelper class
 * this helper class has some method to do some operation with
 * sqlite database
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="cardiac_recorder.db";
    private static final String TABLE_NAME="cardiac_recorder";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_SYSTOLIC="systolic";
    private static final String COLUMN_DIASTOLIC="diastolic";
    private static final String COLUMN_PULSE="pulse";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_TIME="time";
    private static final String COLUMN_COMMENTS="comments";
    private static final int DATABASE_VERSION= 1;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_SYSTOLIC+" varchar,"+COLUMN_DIASTOLIC+" varchar,"+COLUMN_PULSE+" varchar,"+COLUMN_DATE+" varchar,"+COLUMN_TIME+" varchar,"+COLUMN_COMMENTS+" varchar(255));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;



    private Context context;

    /**
     * initialize MyDatabaseHelper with context
     * @param context
     * initialize context
     */
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context= context;
    }

    /**
     * onCreate of MyDatabaseHelper class this method will be
     * executed and connect system with sqlite database
     * @param db
     * this uses SQLiteDatabase type as parameter
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);

        }catch(Exception e)
        {
            Toast.makeText(context,"Failed : "+e,Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * this method will do some update on database table
     * @param db
     * this is a SQLiteDatabase type parameter
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try
        {
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * insert record into sqlite database
     * @param systol
     * systolic data
     * @param diastol
     * disatolic data
     * blood_pressure_status will be showed
     * @param pulse pulse of user
     * @param date_value on which data record is inserted
     * @param time_value on which time record is inserted
     * @param comments comment on each record
     * @return
     * return the id of where this record data is inserted
     * in database
     */
    public long insertData(String systol, String diastol, String pulse,String date_value, String time_value, String comments) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SYSTOLIC,systol);
        contentValues.put(COLUMN_DIASTOLIC,diastol);
        contentValues.put(COLUMN_PULSE,pulse);
        contentValues.put(COLUMN_DATE,"Date: "+date_value);
        contentValues.put(COLUMN_TIME,"Time: "+time_value);
        contentValues.put(COLUMN_COMMENTS,"Comments: "+comments);
        long id= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return id;
    }

    Cursor readAllData(){
        String query = "SELECT * FROM "  +TABLE_NAME;
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = null;

        if(db!=null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }


}



