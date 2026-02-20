package com.example.smarttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SelectSchemeActivity extends AppCompatActivity {

    Spinner spinnerBranch, spinnerSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scheme);

        spinnerBranch = findViewById(R.id.spinnerBranch);
        spinnerSemester = findViewById(R.id.spinnerSemester);
        Button btnGenerate = findViewById(R.id.btnGenerateTimetable);
        btnGenerate.setOnClickListener(v -> {
            Intent intent = new Intent(SelectSchemeActivity.this,
                    GenerateTimetableActivity.class);
            startActivity(intent);
        });

        String[] branches = {"AIML", "CSE", "IT", "ENTC"};
        String[] semesters = {"1", "2", "3", "4", "5", "6"};

        ArrayAdapter<String> branchAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        branches);

        spinnerBranch.setAdapter(branchAdapter);

        ArrayAdapter<String> semesterAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        semesters);

        spinnerSemester.setAdapter(semesterAdapter);
    }
}
