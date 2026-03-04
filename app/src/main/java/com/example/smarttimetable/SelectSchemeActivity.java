package com.example.smarttimetable;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectSchemeActivity extends AppCompatActivity {

    Spinner spinnerBranch, spinnerSemester;
    EditText etSubjectName;
    Button btnAddSubject, btnSaveScheme;
    ListView listSubjects;

    ArrayList<String> subjects = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_scheme);

        spinnerBranch = findViewById(R.id.spinnerBranch);
        spinnerSemester = findViewById(R.id.spinnerSemester);
        etSubjectName = findViewById(R.id.etSubjectName);
        btnAddSubject = findViewById(R.id.btnAddSubject);
        btnSaveScheme = findViewById(R.id.btnSaveScheme);
        listSubjects = findViewById(R.id.listSubjects);

        String[] branches = {"AIML", "CO", "IT", "ME"};
        String[] semesters = {"1", "2", "3", "4", "5", "6"};

        spinnerBranch.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, branches));

        spinnerSemester.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, semesters));

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, subjects);

        listSubjects.setAdapter(adapter);

        btnAddSubject.setOnClickListener(v -> {
            String subject = etSubjectName.getText().toString().trim();
            if (!subject.isEmpty()) {
                subjects.add(subject);
                adapter.notifyDataSetChanged();
                etSubjectName.setText("");
            }
        });

        btnSaveScheme.setOnClickListener(v ->
                Toast.makeText(this, "Scheme Saved!", Toast.LENGTH_SHORT).show());
    }
}