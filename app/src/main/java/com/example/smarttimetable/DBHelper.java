package com.example.smarttimetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "TimetableDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Faculty Table
        db.execSQL("CREATE TABLE faculty(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "subject TEXT," +
                "branch TEXT," +
                "maxLectures TEXT)");
        // Room Table
        db.execSQL("CREATE TABLE room(id INTEGER PRIMARY KEY AUTOINCREMENT, room_no TEXT)");

        // Timetable Table
        db.execSQL("CREATE TABLE timetable(id INTEGER PRIMARY KEY AUTOINCREMENT, day TEXT, time TEXT, subject TEXT, faculty TEXT, room TEXT, sem TEXT, branch TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // ---------------- FACULTY ----------------

    public boolean insertFaculty(String name, String subject, String branch, String maxLectures){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("subject", subject);
        cv.put("branch", branch);
        cv.put("maxLectures", maxLectures);
        long res = db.insert("faculty", null, cv);
        return res != -1;
    }


    public Cursor getFaculty() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM faculty", null);
    }

    public void deleteAllFaculty() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM faculty");
    }

    // ---------------- TIMETABLE ----------------

    public void deleteTimetable(String sem, String branch) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("timetable", "sem=? AND branch=?", new String[]{sem, branch});
    }

    public void insertTimetable(String day, String time, String subject, String faculty, String room, String sem, String branch) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("day", day);
        cv.put("time", time);
        cv.put("subject", subject);
        cv.put("faculty", faculty);
        cv.put("room", room);
        cv.put("sem", sem);
        cv.put("branch", branch);

        db.insert("timetable", null, cv);
    }

    public Cursor getTimetable(String sem, String branch) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM timetable WHERE sem=? AND branch=?",
                new String[]{sem, branch});
    }
    public void updateTimetable(String day, String time, String subject, String sem, String branch){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("subject", subject);

        db.update("timetable",
                cv,
                "day=? AND time=? AND sem=? AND branch=?",
                new String[]{day, time, sem, branch});
    }

}