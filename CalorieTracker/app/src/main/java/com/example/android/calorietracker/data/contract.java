package com.example.android.calorietracker.data;

import android.provider.BaseColumns;

/**
 * Created by ROHAN on 13-07-2017.
 */

public final class contract {

private contract(){}

    public static final class entry implements BaseColumns{

       public static final String table_name="calories";
        public static final String _ID=BaseColumns._ID;
        public static final String FOOD="food_name";
        public static final String CALORIE="calories";
    }

}
