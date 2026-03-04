package com.example.smarttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class GenerateTimetableActivity extends AppCompatActivity {

    Spinner spinnerBranch, spinnerSemester;
    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_timetable);

        spinnerBranch = findViewById(R.id.spinnerBranch);
        spinnerSemester = findViewById(R.id.spinnerSemester);
        btnGenerate = findViewById(R.id.btnGenerateTimetable);

        String[] branches = {"AIML", "CO", "IT", "ME"};
        String[] semesters = {"1", "2", "3", "4", "5", "6"};

        spinnerBranch.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, branches));

        spinnerSemester.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, semesters));

        btnGenerate.setOnClickListener(v ->
                startActivity(new Intent(this, ViewTimetableActivity.class)));
    }
}