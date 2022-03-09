package com.techparksystems.databasedemoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME="My_Company.db";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE="USERS";
    static final String USER_ID="_ID";
    static final String USER_NAME="user_name";
    static final String USER_PASSWORD="password";
//    private static final String CREATE_DB_QUERY="CREATE TABLE "+DATABASE_TABLE+"("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+"TEXT NOT NULL,"+USER_PASSWORD+"TEXT NOT NULL);";


    public DatabaseHelper(@Nullable  Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_DB_QUERY);
        String query = "create table " + DATABASE_TABLE + "("+USER_ID+" integer primary key,"+USER_NAME+" text,"+USER_PASSWORD+" text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);

        String query = "drop table if exists " + DATABASE_TABLE + "";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }
}
