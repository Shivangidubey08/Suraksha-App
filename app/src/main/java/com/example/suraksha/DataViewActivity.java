package com.example.suraksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suraksha.data.dbHelper;
import com.example.suraksha.data.contractClass;

public class DataViewActivity extends AppCompatActivity {
    private dbHelper mDbHelper;
    private TextView name;
    private TextView age;
    private TextView adhar;
    private TextView contactP;
    private TextView contactN;
    private TextView address;
    private TextView bloodgroup;
    private TextView diabetes;
    private TextView policy;
    private Uri fileUri=null;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_view);
        mDbHelper = new dbHelper(this);
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        adhar = (TextView) findViewById(R.id.adhar_number);
        contactP = (TextView) findViewById(R.id.contactPname);
        contactN = (TextView) findViewById(R.id.contactPno);
        address = (TextView) findViewById(R.id.address);
        bloodgroup = (TextView) findViewById(R.id.bloodgroup);
        diabetes = (TextView) findViewById(R.id.diabetes);
        policy = (TextView) findViewById(R.id.policynumber);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(DataViewActivity.this, EditorActivity.class));
        return true;
    }
    @Override
    public void onBackPressed(){
        finishAffinity();
        finish();

    }
    private void displayDatabaseInfo() {
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
                contractClass.UserEntry.COLUMN_POLICY_NIUMBER,
                contractClass.UserEntry.COLUMN_MEDICAL_FILE_URL
        };

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
        int medicalHistoryColumnIndex = cursor.getColumnIndex(contractClass.UserEntry.COLUMN_MEDICAL_FILE_URL);
        cursor.moveToNext();
        try {name.setText(cursor.getString(nameColumnIndex));
            age.setText(cursor.getString(ageColumnIndex));
            adhar.setText(cursor.getString(adharColumnIndex));
            contactP.setText(cursor.getString(personColumnIndex));
            contactN.setText(cursor.getString(numberColumnIndex));
            address.setText(cursor.getString(addressColumnIndex));
            bloodgroup.setText(cursor.getString(bloodgroupColumnIndex));
            diabetes.setText(cursor.getString(diabetesColumnIndex));
            policy.setText(cursor.getString(policyColumnIndex));
            fileUri=Uri.parse(cursor.getString(medicalHistoryColumnIndex));
            filePath=cursor.getString(medicalHistoryColumnIndex);

        } finally {

            cursor.close();

        }
    }
    public void seeMedicalHistory(View view){
        Log.i("nevla", filePath);
        if(filePath=="NA"){
            Toast.makeText(this, "No Medical history added", Toast.LENGTH_SHORT).show();}
        else{
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri,"application/pdf");
        startActivity(Intent.createChooser(intent, "Open folder"));}
    }

}
