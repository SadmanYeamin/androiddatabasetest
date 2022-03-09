package com.techparksystems.databasedemoapp.database;

public class Student {
    private int id;
    private String studentName;

    Student() {
    }
    Student(int id, String studentName) {
        this.id = id;
        this.studentName = studentName;
    }
    void setID(int id) {
        this.id = id;
    }
    int getID() {
        return this.id;
    }
    void setStudentName(String studentname) {
        this.studentName = studentname;
    }
    String getStudentName() {
        return this.studentName;
    }
}