package com.example.android.calorietracker;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.android.calorietracker.data.contract;
import com.example.android.calorietracker.data.contract.entry;
import com.example.android.calorietracker.data.helper;

public class MainActivity extends AppCompatActivity {
    private helper mdbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SQL_activity.class);
                startActivity(intent);
            }
        });

        mdbhelper = new helper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        cursor();
    }

    private void cursor() {
        SQLiteDatabase db = mdbhelper.getReadableDatabase();
        String[] projection = {
                entry._ID,
                entry.FOOD,
                entry.CALORIE
        };
        Cursor cursor = db.query(entry.table_name, projection, null, null, null, null, null);
        TextView displayView = (TextView) findViewById(R.id.textview);
        try {
            displayView.setText("The  table contain " + cursor.getCount() + "food.\n\n");
            displayView.append(contract.entry._ID + "-" +
                    contract.entry.FOOD + "-" +
                    contract.entry.CALORIE + "-" + "\n");
            int idColumnIndex = cursor.getColumnIndex(contract.entry._ID);
            int nameColumnIndex = cursor.getColumnIndex(contract.entry.FOOD);
            int calColumnIndex = cursor.getColumnIndex(contract.entry.CALORIE);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentcal = cursor.getString(calColumnIndex);

                displayView.append(("\n" + currentID + " - " +" - " + currentName + currentcal));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    private void insert() {
        SQLiteDatabase db = mdbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(contract.entry.FOOD, "Toto");
        values.put(contract.entry.CALORIE, 7);
        long newRowId = db.insert(contract.entry.table_name, null, values);
    }
}

