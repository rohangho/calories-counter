package com.example.android.calorietracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by ROHAN on 13-07-2017.
 */

public class helper extends SQLiteOpenHelper {
    private static final String databasE_name = "cal.db";
    private static final int database_version = 1;
    public helper(Context context) {
        super(context, databasE_name, null, database_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE = "CREATE TABLE " + contract.entry.table_name + "("
                + contract.entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + contract.entry.FOOD + " TEXT NOT NULL, "
                + contract.entry.CALORIE + " INTEGER NOT NULL DEFAULT 0); ";

        Log.i("statement: ", TABLE);
        db.execSQL(TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
