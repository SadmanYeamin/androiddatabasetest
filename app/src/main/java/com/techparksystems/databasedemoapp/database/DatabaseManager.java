package com.techparksystems.databasedemoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public void insert (String username,String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_NAME,username);
        contentValues.put(DatabaseHelper.USER_PASSWORD,password);
        sqLiteDatabase.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);
    }

    public Cursor fetch(){
        String[] columns = new String[]{DatabaseHelper.USER_ID,DatabaseHelper.USER_NAME,DatabaseHelper.USER_PASSWORD};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.DATABASE_TABLE,columns,null,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update (long id,String username,String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_NAME,username);
        contentValues.put(DatabaseHelper.USER_PASSWORD,password);
        int ret = sqLiteDatabase.update(DatabaseHelper.DATABASE_TABLE,contentValues,DatabaseHelper.USER_ID+"="+id,null);
        return ret;
    }

    public void delete(long id){
        sqLiteDatabase.delete(DatabaseHelper.DATABASE_TABLE,DatabaseHelper.USER_ID+"="+id,null);
    }
}
