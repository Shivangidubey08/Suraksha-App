package com.example.suraksha.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.suraksha.data.contractClass.UserEntry;

import com.example.suraksha.data.contractClass;
import com.example.suraksha.data.contractClass.UserEntry;
import com.example.suraksha.data.dbHelper;
public class dbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = dbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "userinfo.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link dbHelper}.
     *
     * @param context of the app
     */
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + UserEntry.TABLE_NAME + " ("
                + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
                + UserEntry.COLUMN_AGE + " TEXT, "
                + UserEntry.COLUMN_ADHAR + " INTEGER NOT NULL, "
                + UserEntry.COLUMN_CONTACT_PERSON + " TEXT, "
                + UserEntry.COLUMN_CONTACT_NUMBER + " TEXT, "
                + UserEntry.COLUMN_ADDRESS + " TEXT, "
                + UserEntry.COLUMN_BLOODGROUP + " TEXT, "
                + UserEntry.COLUMN_DIABETES + " TEXT, "
                + UserEntry.COLUMN_POLICY_NIUMBER + " TEXT, "
                + UserEntry.COLUMN_MEDICAL_FILE_URL + " TEXT);";
        String SQL_INSERT_DUMMY =  "INSERT INTO " + UserEntry.TABLE_NAME + " ("
                + UserEntry._ID + ", "
                + UserEntry.COLUMN_USER_NAME + ", "
                + UserEntry.COLUMN_AGE + ", "
                + UserEntry.COLUMN_ADHAR + ", "
                + UserEntry.COLUMN_CONTACT_PERSON + ", "
                + UserEntry.COLUMN_CONTACT_NUMBER + ", "
                + UserEntry.COLUMN_ADDRESS + ", "
                + UserEntry.COLUMN_BLOODGROUP + ", "
                + UserEntry.COLUMN_DIABETES + ", "
                + UserEntry.COLUMN_POLICY_NIUMBER + ", "
                + UserEntry.COLUMN_MEDICAL_FILE_URL + ")\n"+"VALUES ("
                + "1, "
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\","
                + "\"NA\");";
        // Execute the SQL statement
        Log.i("nevla",SQL_CREATE_PETS_TABLE);
        Log.i("nevla",SQL_INSERT_DUMMY);
        db.execSQL(SQL_CREATE_PETS_TABLE);
        db.execSQL(SQL_INSERT_DUMMY);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}



