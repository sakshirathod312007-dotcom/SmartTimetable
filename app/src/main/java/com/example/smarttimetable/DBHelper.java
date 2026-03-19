package com.example.smarttimetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "SmartTimetable.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE faculty(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,subject TEXT,branch TEXT)");

        db.execSQL("CREATE TABLE room(id INTEGER PRIMARY KEY AUTOINCREMENT,room_no TEXT)");

        db.execSQL("CREATE TABLE timetable(id INTEGER PRIMARY KEY AUTOINCREMENT,day TEXT,period TEXT,subject TEXT,faculty TEXT,room TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS faculty");
        db.execSQL("DROP TABLE IF EXISTS room");
        db.execSQL("DROP TABLE IF EXISTS timetable");
        onCreate(db);
    }

    // ---------- INSERT FUNCTIONS ----------

    public void insertFaculty(String name,String subject,String branch){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("subject",subject);
        cv.put("branch",branch);
        db.insert("faculty",null,cv);
    }

    public void insertRoom(String room){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("room_no",room);
        db.insert("room",null,cv);
    }

    public void insertTimetable(String day,String period,String subject,String faculty,String room){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("day",day);
        cv.put("period",period);
        cv.put("subject",subject);
        cv.put("faculty",faculty);
        cv.put("room",room);
        db.insert("timetable",null,cv);
    }

    public Cursor getTimetable(){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM timetable",null);
    }
}