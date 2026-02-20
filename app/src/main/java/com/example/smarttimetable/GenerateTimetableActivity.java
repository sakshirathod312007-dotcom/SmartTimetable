package com.example.smarttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class GenerateTimetableActivity extends AppCompatActivity {

    TextView tvBranch, tvSemester;
    MaterialButton btnGenerate, btnView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_timetable);

        tvBranch = findViewById(R.id.tvBranch);
        tvSemester = findViewById(R.id.tvSemester);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnView = findViewById(R.id.btnView);
        progressBar = findViewById(R.id.progressBar);

        // Receive data from SelectSchemeActivity
        String branch = getIntent().getStringExtra("branch");
        String semester = getIntent().getStringExtra("semester");

        tvBranch.setText("Branch: " + branch);
        tvSemester.setText("Semester: " + semester);

        btnGenerate.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);
            btnGenerate.setEnabled(false);

            // Fake generation delay (backend later add karenge)
            new Handler().postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                btnView.setVisibility(View.VISIBLE);
            }, 2000);

        });

        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(GenerateTimetableActivity.this,
                    ViewTimetableActivity.class);
            startActivity(intent);
        });

    }
}
