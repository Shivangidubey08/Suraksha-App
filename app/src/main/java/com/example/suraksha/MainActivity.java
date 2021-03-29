package com.example.suraksha;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.suraksha.data.contractClass.UserEntry;
import com.example.suraksha.data.dbHelper;
public class MainActivity extends AppCompatActivity {


    /** EditText field to enter the pet's name */
    private EditText mNameEditText;

    /** EditText field to enter the pet's breed */
    private EditText mAgeEditText;

    /** EditText field to enter the pet's weight */
    private EditText mAdharEditText;
    private EditText mContactPersonEditText;
    private EditText mContactNumberEditText;
    private EditText mAddressEditText;
    private EditText mBloodgroupEditText;
    private EditText mDiabetesEditText;
    private EditText mPolicyEditText;

    /** EditText field to enter the pet's gender */
    private Spinner mGenderSpinner;

    /**
     * Gender of the pet. The possible valid values are in the PetContract.java file:
     * {@link UserEntry#GENDER_UNKNOWN}, {@link UserEntry#GENDER_MALE}, or
     * {@link UserEntry#GENDER_FEMALE}.
     */
    private dbHelper mDbHelper;
    private int mGender = UserEntry.GENDER_UNKNOWN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameEditText = (EditText) findViewById(R.id.edit_user_name);
        mAgeEditText = (EditText) findViewById(R.id.edit_user_age);
        mAdharEditText = (EditText) findViewById(R.id.edit_user_adhar);
        mContactPersonEditText = (EditText) findViewById(R.id.edit_contact_person);
        mContactNumberEditText = (EditText) findViewById(R.id.edit_contact_number);
        mAddressEditText = (EditText) findViewById(R.id.edit_user_address);
        mBloodgroupEditText = (EditText) findViewById(R.id.edit_user_bloodgroup);
        mDiabetesEditText = (EditText) findViewById(R.id.edit_user_diabetes);
        mPolicyEditText = (EditText) findViewById(R.id.edit_policy_number);
        //mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

     //   setupSpinner();
        mDbHelper = new dbHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = UserEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = UserEntry.GENDER_FEMALE;
                    } else {
                        mGender = UserEntry.GENDER_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = UserEntry.GENDER_UNKNOWN;
            }
        });

    }
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_AGE,
                UserEntry.COLUMN_ADHAR,
                UserEntry.COLUMN_CONTACT_PERSON,
                UserEntry.COLUMN_CONTACT_NUMBER,
                UserEntry.COLUMN_ADDRESS,
                UserEntry.COLUMN_BLOODGROUP,
                UserEntry.COLUMN_DIABETES,
                UserEntry.COLUMN_POLICY_NIUMBER };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                UserEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.result);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(UserEntry._ID + " - " +
                    UserEntry.COLUMN_USER_NAME + " - " +
                    UserEntry.COLUMN_AGE + " - " +
                    UserEntry.COLUMN_ADHAR + " - " +
                    UserEntry.COLUMN_CONTACT_PERSON + " - " +
                    UserEntry.COLUMN_CONTACT_NUMBER + " - " +
                    UserEntry.COLUMN_ADDRESS + " - " +
                    UserEntry.COLUMN_BLOODGROUP + " - " +
                    UserEntry.COLUMN_DIABETES + " - " +
                    UserEntry.COLUMN_POLICY_NIUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(UserEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_NAME);
            int ageColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_AGE);
            int adharColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_ADHAR);
            int personColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_CONTACT_PERSON);
            int numberColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_CONTACT_NUMBER);
            int addressColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_ADDRESS);
            int bloodgroupColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_BLOODGROUP);
            int diabetesColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_DIABETES);
            int policyColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_POLICY_NIUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentage = cursor.getString(ageColumnIndex);
                String currentadhar = cursor.getString(adharColumnIndex);
                String currentperson = cursor.getString(personColumnIndex);
                String currentnumber = cursor.getString(numberColumnIndex);
                String currentaddress = cursor.getString(addressColumnIndex);
                String currentbloodgroup = cursor.getString(bloodgroupColumnIndex);
                String currentdiabetes = cursor.getString(diabetesColumnIndex);
                String currentpolicy = cursor.getString(policyColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentage + " - " +
                        currentadhar + " - " +
                        currentperson + " - " +
                        currentnumber + " - " +
                        currentaddress + " - " +
                        currentbloodgroup + " - " +
                        currentdiabetes + " - " +
                        currentpolicy));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    public void insertPet(View view) {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
    /*    String nameString = mNameEditText.getText().toString().trim();
        String breedString = mBreedEditText.getText().toString().trim();
        String weightString = mWeightEditText.getText().toString().trim();
        int weight = Integer.parseInt(weightString);*/
       // String nameString = mNameEditText.getText().toString().trim();
       // String breedString = mBreedEditText.getText().toString().trim();
      //  String weightString = mWeightEditText.getText().toString().trim();
     //   int weight = Integer.parseInt(weightString);
         String name = mNameEditText.getText().toString().trim();
         String age = mAgeEditText.getText().toString().trim();
         String adhar =  mAdharEditText.getText().toString().trim();
         String contactP =  mContactPersonEditText.getText().toString().trim();
         String contactN =  mContactNumberEditText.getText().toString().trim();
         String address =  mAddressEditText.getText().toString().trim();
         String bloodgroup =  mBloodgroupEditText.getText().toString().trim();
         String diabetes =  mDiabetesEditText.getText().toString().trim();
         String policyNumber = mPolicyEditText.getText().toString().trim();
        // Create database helper
        dbHelper dbHelper = new dbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_USER_NAME, name);
        values.put(UserEntry.COLUMN_AGE, age);
        values.put(UserEntry.COLUMN_ADHAR, adhar);
        values.put(UserEntry.COLUMN_CONTACT_PERSON, contactP);
        values.put(UserEntry.COLUMN_CONTACT_NUMBER, contactN);
        values.put(UserEntry.COLUMN_ADDRESS, address);
        values.put(UserEntry.COLUMN_BLOODGROUP, bloodgroup);
        values.put(UserEntry.COLUMN_DIABETES, diabetes);
        values.put(UserEntry.COLUMN_POLICY_NIUMBER, policyNumber);


        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(UserEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
        displayDatabaseInfo();
    }
 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertPet();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}




/**
 * Allows user to create a new pet or edit an existing one.
 */
