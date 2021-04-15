package com.example.suraksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.suraksha.data.contractClass;
import com.example.suraksha.data.contractClass.UserEntry;
import com.example.suraksha.data.dbHelper;
import com.example.suraksha.DataViewActivity;
public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mAgeEditText;
    private EditText mAdharEditText;
    private EditText mContactPersonEditText;
    private EditText mContactNumberEditText;
    private EditText mAddressEditText;
    private EditText mBloodgroupEditText;
    private EditText mDiabetesEditText;
    private EditText mPolicyEditText;
    private Uri mCurrentPetUri;




    private dbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();
        mNameEditText = (EditText) findViewById(R.id.edit_user_name);
        mAgeEditText = (EditText) findViewById(R.id.edit_user_age);
        mAdharEditText = (EditText) findViewById(R.id.edit_user_adhar);
        mContactPersonEditText = (EditText) findViewById(R.id.edit_contact_person);
        mContactNumberEditText = (EditText) findViewById(R.id.edit_contact_number);
        mAddressEditText = (EditText) findViewById(R.id.edit_user_address);
        mBloodgroupEditText = (EditText) findViewById(R.id.edit_user_bloodgroup);
        mDiabetesEditText = (EditText) findViewById(R.id.edit_user_diabetes);
        mPolicyEditText = (EditText) findViewById(R.id.edit_policy_number);

        mDbHelper = new dbHelper(this);
    }
    @Override
    protected void onStart() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                contractClass.UserEntry._ID,
                contractClass.UserEntry.COLUMN_USER_NAME,
                contractClass.UserEntry.COLUMN_AGE,
                contractClass.UserEntry.COLUMN_ADHAR,
                contractClass.UserEntry.COLUMN_CONTACT_PERSON,
                contractClass.UserEntry.COLUMN_CONTACT_NUMBER,
                contractClass.UserEntry.COLUMN_ADDRESS,
                contractClass.UserEntry.COLUMN_BLOODGROUP,
                contractClass.UserEntry.COLUMN_DIABETES,
                contractClass.UserEntry.COLUMN_POLICY_NIUMBER };

        Cursor cursor = db.query(
                contractClass.UserEntry.TABLE_NAME, projection, null, null, null, null, null);
        int idColumnIndex = cursor.getColumnIndex(contractClass.UserEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_USER_NAME);
        int ageColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_AGE);
        int adharColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_ADHAR);
        int personColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_CONTACT_PERSON);
        int numberColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_CONTACT_NUMBER);
        int addressColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_ADDRESS);
        int bloodgroupColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_BLOODGROUP);
        int diabetesColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_DIABETES);
        int policyColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_POLICY_NIUMBER);
        cursor.moveToNext();
        try {mNameEditText.setText(cursor.getString(nameColumnIndex));
            mAgeEditText.setText(cursor.getString(ageColumnIndex));
            mAdharEditText.setText(cursor.getString(adharColumnIndex));
            mContactPersonEditText.setText(cursor.getString(personColumnIndex));
            mContactNumberEditText.setText(cursor.getString(numberColumnIndex));
            mAddressEditText.setText(cursor.getString(addressColumnIndex));
            mBloodgroupEditText.setText(cursor.getString(bloodgroupColumnIndex));
            mDiabetesEditText.setText(cursor.getString(diabetesColumnIndex));
            mPolicyEditText.setText(cursor.getString(policyColumnIndex));

        } finally {

            cursor.close();
        }

        super.onStart();
    }

    public void et(View view) {
         String name = mNameEditText.getText().toString().trim();
         String age = mAgeEditText.getText().toString().trim();
         String adhar =  mAdharEditText.getText().toString().trim();
         String contactP =  mContactPersonEditText.getText().toString().trim();
         String contactN =  mContactNumberEditText.getText().toString().trim();
         String address =  mAddressEditText.getText().toString().trim();
         String bloodgroup =  mBloodgroupEditText.getText().toString().trim();
         String diabetes =  mDiabetesEditText.getText().toString().trim();
         String policyNumber = mPolicyEditText.getText().toString().trim();
        dbHelper dbHelper = new dbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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


        long newRowId = db.insert(UserEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {

            Toast.makeText(this, "Error with saving data", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(EditorActivity.this, DataViewActivity.class));
            Toast.makeText(this, "Saved successfully" + newRowId, Toast.LENGTH_SHORT).show();
        }

    }
    public void insertPet(View view) {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String name = mNameEditText.getText().toString().trim();
        String age = mAgeEditText.getText().toString().trim();
        String adhar =  mAdharEditText.getText().toString().trim();
        String contactP =  mContactPersonEditText.getText().toString().trim();
        String contactN =  mContactNumberEditText.getText().toString().trim();
        String address =  mAddressEditText.getText().toString().trim();
        String bloodgroup =  mBloodgroupEditText.getText().toString().trim();
        String diabetes =  mDiabetesEditText.getText().toString().trim();
        String policyNumber = mPolicyEditText.getText().toString().trim();

        // Check if this is supposed to be a new pet
        // and check if all the fields in the editor are blank
        if (mCurrentPetUri == null &&
                TextUtils.isEmpty(name) && TextUtils.isEmpty(age) &&
                TextUtils.isEmpty(adhar) &&   TextUtils.isEmpty(contactP)
                && TextUtils.isEmpty(contactN)  && TextUtils.isEmpty(address)
                && TextUtils.isEmpty(bloodgroup)  && TextUtils.isEmpty(diabetes)
                && TextUtils.isEmpty(policyNumber)
    ) {
            // Since no fields were modified, we can return early without creating a new pet.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }

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


            mCurrentPetUri = ContentUris.withAppendedId(UserEntry.CONTENT_URI, 1);
               int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                Log.i("happend","noupdate");
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, "Error with saving data", Toast.LENGTH_SHORT).show();
            } else {Log.i("happend","update");
                // Otherwise, the update was successful and we can display a toast.

                startActivity(new Intent(EditorActivity.this, DataViewActivity.class));
                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();

        }
    }

}
