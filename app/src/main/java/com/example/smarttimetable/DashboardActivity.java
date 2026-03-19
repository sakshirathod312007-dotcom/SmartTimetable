package com.example.smarttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnGenerate;
    Button btnUpload;
    Button btnView;
    Button btnFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Button IDs
        btnGenerate = findViewById(R.id.btnGenerate);
        btnUpload = findViewById(R.id.btnUpload);
        btnView = findViewById(R.id.btnView);
        btnFaculty = findViewById(R.id.btnFaculty);

        // Generate Timetable
        btnGenerate.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, GenerateTimetableActivity.class);
            startActivity(intent);
        });

        // Upload Scheme
        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, SelectSchemeActivity.class);
            startActivity(intent);
        });

        // View Timetable
        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ViewTimetableActivity.class);
            startActivity(intent);
        });

        // Faculty Management
        btnFaculty.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, FacultyActivity.class);
            startActivity(intent);
        });

    }
}