package com.example.smarttimetable;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class FacultyActivity extends AppCompatActivity {

    EditText etName, etSubject, etBranch;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        etName = findViewById(R.id.etName);
        etSubject = findViewById(R.id.etSubject);
        etBranch = findViewById(R.id.etBranch);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            DBHelper db = new DBHelper(this);

            db.insertFaculty(
                    etName.getText().toString(),
                    etSubject.getText().toString(),
                    etBranch.getText().toString()
            );

            Toast.makeText(this,"Faculty Saved",Toast.LENGTH_SHORT).show();
        });
    }
}