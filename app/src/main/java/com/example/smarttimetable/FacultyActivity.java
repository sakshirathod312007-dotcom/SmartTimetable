package com.example.smarttimetable;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class FacultyActivity extends AppCompatActivity {

    TextInputEditText etName, etSubject, etBranch, etMaxLectures;
    MaterialButton btnSave, btnShow;
    TextView tvData;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        // Bind Views
        etName = findViewById(R.id.etName);
        etSubject = findViewById(R.id.etSubject);
        etBranch = findViewById(R.id.etBranch);
        etMaxLectures = findViewById(R.id.etMaxLectures);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        tvData = findViewById(R.id.tvData);

        db = new DBHelper(this);

        // SAVE
        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String subject = etSubject.getText().toString().trim();
            String branch = etBranch.getText().toString().trim();
            String maxLectures = etMaxLectures.getText().toString().trim();

            if(name.isEmpty()){
                Toast.makeText(FacultyActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.insertFaculty(name, subject, branch, maxLectures);

            if(result){
                Toast.makeText(FacultyActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FacultyActivity.this, "Error in saving data", Toast.LENGTH_SHORT).show();
            }
        });
        // SHOW
        btnShow.setOnClickListener(v -> {

            Cursor cursor = db.getFaculty();

            if(cursor.getCount() == 0){
                tvData.setText("No Data Found");
                return;
            }

            StringBuilder data = new StringBuilder();

            while(cursor.moveToNext()) {

                // Safe access for 'name'
                int nameIndex = cursor.getColumnIndex("name");
                String name = nameIndex != -1 ? cursor.getString(nameIndex) : "";

                // Safe access for optional columns
                int subjectIndex = cursor.getColumnIndex("subject");
                String subject = subjectIndex != -1 ? cursor.getString(subjectIndex) : "";

                int branchIndex = cursor.getColumnIndex("branch");
                String branch = branchIndex != -1 ? cursor.getString(branchIndex) : "";

                int maxLecturesIndex = cursor.getColumnIndex("maxLectures");
                String maxLectures = maxLecturesIndex != -1 ? cursor.getString(maxLecturesIndex) : "";

                // Build display string
                data.append("Name: ").append(name).append("\n");
                if(!subject.isEmpty()) data.append("Subject: ").append(subject).append("\n");
                if(!branch.isEmpty()) data.append("Branch: ").append(branch).append("\n");
                if(!maxLectures.isEmpty()) data.append("Max Lectures: ").append(maxLectures).append("\n\n");
            }

            tvData.setText(data.toString());
        });
    }
}