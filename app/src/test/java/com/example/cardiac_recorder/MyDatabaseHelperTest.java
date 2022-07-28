package com.example.cardiac_recorder;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


/**
 * DatabaseUnitTest class for testing on Database insertion, update or delete
 */
@RunWith(RobolectricTestRunner.class)
public class MyDatabaseHelperTest {
    /**
     * checks if a record is added successfully on database
     */
    @Test
    public void testAdd() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(RuntimeEnvironment.application);

        String systol = "95";
        String diastol = "75";
        String pulse = "68";
        String comments = "sdf";
        String date_value = "22-Jul-2022";
        String time_value = "6:41 Pm";


        Long id = myDB.insertData(systol, diastol, pulse, date_value, time_value, comments);

        assertTrue(myDB.checkIfDataExists(id));

        myDB.close();
    }

    /**
     * checks if a record is deleted successfully on database
     */
    @Test
    public void testdelete() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(RuntimeEnvironment.application);

        String systol = "95";
        String diastol = "75";
        String pulse = "68";
        String comments = "sdf";
        String date_value = "22-jun-2022";
        String time_value = "10:43";


        Long id = myDB.insertData(systol, diastol, pulse, date_value, time_value, comments);

        myDB.delete(Long.toString(id));

        assertFalse(myDB.checkIfDataExists(id));

        myDB.close();
    }

    /**
     * checks if update on database is successful
     */
    @Test
    public void testUpdate() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(RuntimeEnvironment.application);

        String systol = "95";
        String diastol = "75";
        String pulse = "68";
        String comments = "sdf";
        String date_value = "26-jul-2022";
        String time_value = "6:59 PM";


        Long id = myDB.insertData(systol, diastol, pulse, date_value, time_value, comments);

        String systol1 = "85";
        String diastol1 = "65";
        String pulse1 = "58";
        String comments1 = "sitting";
        String date_value1 = "26-jun-2022";
        String time_value1 = "8:00PM";
        myDB.UpdateData(Long.toString(id), systol1, diastol1, pulse1, date_value1, time_value1, comments1);

        assertFalse(myDB.checkDataBaseContent(Long.toString(id), systol1, diastol1, pulse1, date_value1, time_value1, comments1));

        myDB.close();
    }
}