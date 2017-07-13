package com.example.android.calorietracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.calorietracker.data.contract;
import com.example.android.calorietracker.data.helper;

public class SQL_activity extends AppCompatActivity {

    private EditText foodname;
    private EditText calories_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_activity);

        foodname=(EditText)findViewById(R.id.foodname);
        calories_in=(EditText)findViewById(R.id.calorie);
    }

    private void insert(){
        String namestring=foodname.getText().toString().trim();
        String calstring=calories_in.getText().toString().trim();
        int c=Integer.parseInt(calstring);

        helper mDbHelper = new helper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(contract.entry.FOOD, namestring);
        values.put(contract.entry.CALORIE,c);
        long newRowId = db.insert(contract.entry.table_name, null, values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving ", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean save(View view){
        insert();
        finish();
        return true;
    }
}
