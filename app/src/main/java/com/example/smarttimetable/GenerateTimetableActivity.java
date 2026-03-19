package com.example.smarttimetable;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class GenerateTimetableActivity extends AppCompatActivity {

    Spinner spinnerBranch, spinnerSem;
    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_timetable);

        spinnerBranch = findViewById(R.id.spinnerBranch);
        spinnerSem = findViewById(R.id.spinnerSem);
        btnGenerate = findViewById(R.id.btnGenerate);

        // ---------- Spinner Data ----------

        String[] branches = {"CO","IT","ME","CE","EE"};
        String[] sems = {"1","2","3","4","5","6"};

        ArrayAdapter<String> branchAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        branches);

        ArrayAdapter<String> semAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        sems);

        spinnerBranch.setAdapter(branchAdapter);
        spinnerSem.setAdapter(semAdapter);

        // ---------- Generate Button ----------

        btnGenerate.setOnClickListener(v -> {

            String branch = spinnerBranch.getSelectedItem().toString();
            String sem = spinnerSem.getSelectedItem().toString();

            DBHelper db = new DBHelper(this);

            String[] days = {"Mon","Tue","Wed","Thu","Fri"};
            String[] subjects = {"DBMS","Java","CN","Python","AI","Maths"};
            String[] faculty = {"Asha","Patil","Kale","Joshi","Mehta","Rao"};
            String[] rooms = {"101","102","103","Lab1","Lab2","104"};

            for(int i=0;i<5;i++){
                for(int j=0;j<6;j++){

                    db.insertTimetable(
                            days[i],
                            "P"+(j+1),
                            subjects[j],
                            faculty[j],
                            rooms[j]
                    );
                }
            }

            Toast.makeText(this,
                    "Timetable Generated for " + branch + " Sem " + sem,
                    Toast.LENGTH_LONG).show();
        });
    }
}