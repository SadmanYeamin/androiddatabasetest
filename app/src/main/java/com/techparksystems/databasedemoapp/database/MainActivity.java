package com.techparksystems.databasedemoapp.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.techparksystems.databasedemoapp.R;
import com.techparksystems.databasedemoapp.database.DatabaseHelper;
import com.techparksystems.databasedemoapp.database.DatabaseManager;
import com.techparksystems.databasedemoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHandler;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.result.setMovementMethod(new ScrollingMovementMethod());
        dbHandler= new DbHelper(this);

    }

    public void loadStudents(View view) {
        binding.result.setText(dbHandler.loadHandler());
        binding.studentId.setText("");
        binding.studentName.setText("");
    }

    public void addStudent (View view) {
        if(!binding.studentId.getText().toString().isEmpty() && !binding.studentName.getText().toString().isEmpty()) {
            int id = Integer.parseInt(binding.studentId.getText().toString());
            String name = binding.studentName.getText().toString();
            Student student = new Student(id, name);
            long insertId=dbHandler.addHandler(student);
            if(insertId==-1){
                binding.result.setText("Record already exists");
            }
            else{
                binding.studentId.setText("");
                binding.studentName.setText("");
                binding.result.setText("Record added");
            }
        }
        else{
            binding.result.setText("Please fill correct id and name");
        }
    }

    public void updateStudent(View view) {
        if( !binding.studentId.getText().toString().isEmpty() && !binding.studentName.getText().toString().isEmpty()) {
            boolean result = dbHandler.updateHandler(Integer.parseInt(
                    binding.studentId.getText().toString()), binding.studentName.getText().toString());
            if (result) {
                binding.studentId.setText("");
                binding.studentName.setText("");
                binding.result.setText("Record Updated");
            } else {
                binding.result.setText("No Record Found");
            }
        }
        else{
            binding.result.setText("Please fill correct id and name");
        }
    }

    public void deleteStudent(View view) {
        if(!binding.studentId.getText().toString().isEmpty()) {
            boolean result = dbHandler.deleteHandler(Integer.parseInt(
                    binding.studentId.getText().toString()));
            if (result) {
                binding.studentId.setText("");
                binding.studentName.setText("");
                binding.result.setText("Record Deleted");
            } else {
                binding.result.setText("No Record Found");
            }
        } else{
            binding.result.setText("Please fill correct id");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHandler.close();
    }
}