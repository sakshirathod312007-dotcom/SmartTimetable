package com.example.smarttimetable;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.smarttimetable.R;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        findViewById(R.id.btnGenerate).setOnClickListener(v ->
                startActivity(new Intent(this, GenerateTimetableActivity.class)));

        findViewById(R.id.btnView).setOnClickListener(v ->
                startActivity(new Intent(this, ViewTimetableActivity.class)));

        findViewById(R.id.btnFaculty).setOnClickListener(v ->
                startActivity(new Intent(this, FacultyActivity.class)));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}