package com.example.smarttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.List;

public class GenerateTimetableActivity extends AppCompatActivity {

    Spinner spinnerBranch, spinnerSem;
    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_timetable);

        // Bind views
        spinnerBranch = findViewById(R.id.spinnerBranch);
        spinnerSem = findViewById(R.id.spinnerSem);
        btnGenerate = findViewById(R.id.btnGenerate);

        // Data
        String[] branches = {"AIML"};
        String[] sems = {"1","2","3","4","5","6"};

        // Adapters
        spinnerBranch.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, branches));

        spinnerSem.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, sems));

        // Button Click
        btnGenerate.setOnClickListener(v -> {

            String sem = spinnerSem.getSelectedItem().toString();
            String branch = spinnerBranch.getSelectedItem().toString();

            DBHelper db = new DBHelper(this);
            db.deleteTimetable(sem, branch);

            // ✅ Subject class use ho raha hai
            List<Subject> subjects = SubjectManager.getSubjects(sem);

            if (subjects.isEmpty()) {
                Toast.makeText(this, "No subjects found!", Toast.LENGTH_SHORT).show();
                return;
            }

            TimetableGenerator generator = new TimetableGenerator();
            String[][] timetable = generator.generate(subjects);

            String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
            String[] times = {
                    "10:15-11:15",
                    "11:15-12:15",
                    "12:15-1:00",
                    "1:00-2:00",
                    "2:00-3:00",
                    "3:00-5:15"
            };

            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){

                    db.insertTimetable(
                            days[i],
                            times[j],
                            timetable[i][j],
                            "",
                            "",
                            sem,
                            branch
                    );
                }
            }

            Toast.makeText(this,"Timetable Generated",Toast.LENGTH_SHORT).show();

            // Next screen
            Intent intent = new Intent(this, ViewTimetableActivity.class);
            intent.putExtra("sem", sem);
            intent.putExtra("branch", branch);
            startActivity(intent);
        });
    }
}