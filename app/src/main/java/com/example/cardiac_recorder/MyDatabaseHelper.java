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
        contentValues.put(COLUMN_DIASTOLIC, diastol);
        contentValues.put(COLUMN_PULSE,pulse);
        contentValues.put(COLUMN_DATE,date_value);
        contentValues.put(COLUMN_TIME,time_value);
        contentValues.put(COLUMN_COMMENTS,comments);
        long id= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return id;
    }

    /**
     * check on a particular id if data exists or not
     * on sqlite database
     * @param id where to check for data on sqlite database
     * @return
     * true if data exists or false or no existence of data on
     * that id
     */
    public boolean checkIfDataExists(Long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where " + COLUMN_ID + " = " + Long.toString(id);
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * read all data
     * on sqlite database
     * true if data exists or false or no existence of data on
     * that id
     * @return cursor
     */

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

    /**
     * Update record into sqlite database
     * @param systol
     * systolic data
     * @param diastol
     * disatolic data
     * @param pulse pulse of user
     * @param date_value on which data record is inserted
     * @param time_value on which time record is inserted
     * @param comments comment on each record
     */

    void UpdateData(String row_id,String systol, String diastol, String pulse,String date_value, String time_value, String comments){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SYSTOLIC,systol);
        contentValues.put(COLUMN_DIASTOLIC,diastol);
        contentValues.put(COLUMN_PULSE,pulse);
        contentValues.put(COLUMN_DATE,date_value);
        contentValues.put(COLUMN_TIME,time_value);
        contentValues.put(COLUMN_COMMENTS,comments);
        long result = db.update(TABLE_NAME,contentValues, "_id=?", new String[]{row_id});
        if(result==-1)
        {
            Toast.makeText(context,"Failed To Updated",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"SuccessfullY Updated",Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * @param row_id Primary key of cardiac record table.
     * Will delete the tuple if condition meet
     */

    void delete(String row_id){
        SQLiteDatabase db =  this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if(result==-1)
        {
            Toast.makeText(context,"Failed To Delete",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"SuccessfullY Deleted",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Will delete all tuple
     */

    void deleteall(){
        SQLiteDatabase db =  this.getWritableDatabase();
        db.execSQL("Delete from " +TABLE_NAME);

    }


    /**
     * compare if record on that id on database is equal
     * or not to the parameterized record
     * @param id
     * id for each record
     * @param sys
     * systolic data
     * @param dias
     * disatolic data
     * @param date on which data record is inserted
     * @param time on which time record is inserted
     * @param comments comment on each record
     * @return
     * true if record on that id on database is equal,
     * false if not equal
     */
    public boolean checkDataBaseContent(String id, String sys, String dias, String pulse,String date, String time, String comments) {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        String[] columns = {MyDatabaseHelper.COLUMN_SYSTOLIC, MyDatabaseHelper.COLUMN_DIASTOLIC, MyDatabaseHelper.COLUMN_PULSE,MyDatabaseHelper.COLUMN_DATE, MyDatabaseHelper.COLUMN_TIME, MyDatabaseHelper.COLUMN_COMMENTS};
        Cursor cursor = sqLiteDatabase.query(MyDatabaseHelper.TABLE_NAME, columns, MyDatabaseHelper.COLUMN_ID+" = '"+id+"'", null, null, null, null);
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_SYSTOLIC);
            int index2 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_DIASTOLIC);
            int index3 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PULSE);
            int index4 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_DATE);
            int index5 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TIME);
            int index6 = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_COMMENTS);

            String sys1 = cursor.getString(index1);
            String dia1 = cursor.getString(index2);
            String pulse1 = cursor.getString(index3);
            String date1 = cursor.getString(index4);
            String time1 = cursor.getString(index5);
            String comm1 = cursor.getString(index6);

            if (sys != sys1 || dias != dia1 || pulse != pulse1 ||  date != date1 || time != time1 || comments != comm1) {
                cursor.close();
                return false;
            }
        }
        cursor.close();
        return true;
    }


}



