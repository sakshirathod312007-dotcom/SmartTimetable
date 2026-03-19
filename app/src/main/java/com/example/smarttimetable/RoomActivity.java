package com.example.smarttimetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomActivity extends AppCompatActivity {

    EditText etRoom;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);



        etRoom = findViewById(R.id.etRoom);
        btnSave = findViewById(R.id.btnSaveRoom);

        DBHelper db = new DBHelper(this);

        btnSave.setOnClickListener(v -> {

            db.insertRoom(etRoom.getText().toString());

            Toast.makeText(this,"Room Saved",Toast.LENGTH_SHORT).show();
        });
    }
}