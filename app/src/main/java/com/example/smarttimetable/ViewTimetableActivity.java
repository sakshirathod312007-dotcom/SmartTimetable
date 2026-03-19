package com.example.smarttimetable;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTimetableActivity extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        tableLayout = findViewById(R.id.tableLayout);

        DBHelper db = new DBHelper(this);
        Cursor cursor = db.getTimetable();

        while(cursor.moveToNext()){

            TableRow row = new TableRow(this);

            for(int i=1;i<=5;i++){
                TextView tv = new TextView(this);
                tv.setText(cursor.getString(i));
                tv.setPadding(10,10,10,10);
                row.addView(tv);
            }

            tableLayout.addView(row);
        }
    }
}