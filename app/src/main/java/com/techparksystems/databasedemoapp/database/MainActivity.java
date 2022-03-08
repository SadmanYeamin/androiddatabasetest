package com.techparksystems.databasedemoapp.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.techparksystems.databasedemoapp.database.DatabaseHelper;
import com.techparksystems.databasedemoapp.database.DatabaseManager;
import com.techparksystems.databasedemoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DatabaseManager databaseManager;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        databaseManager = new DatabaseManager(this);
        try {
            databaseManager.open();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        buttonclicklisteners();



    }

    private void buttonclicklisteners() {
        binding.btnInsert.setOnClickListener(view -> {
            databaseManager.insert(binding.etUserName.getText().toString(),binding.etuserpassword.getText().toString());
        });

        binding.btnUpdate.setOnClickListener(view -> {
            databaseManager.update(Long.parseLong(binding.etuserid.getText().toString()),binding.etUserName.getText().toString(),binding.etuserpassword.getText().toString());
        });

        binding.btnFetch.setOnClickListener(view -> {
            Cursor cursor = databaseManager.fetch();
            if(cursor.moveToFirst()){
                do{

                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_ID));
                    @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                    @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD));


                    Log.i("Database_tag","I have read ID: "+id+"Username: "+username+"password:"+password);

                }
                while (cursor.moveToNext());
            }
        });
        binding.btnDelete.setOnClickListener(view -> {
            databaseManager.delete(Long.parseLong(binding.etuserid.getText().toString()));
        });

    }
}