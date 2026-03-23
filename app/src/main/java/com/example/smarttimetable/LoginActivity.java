package com.example.smarttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {



            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // ✅ Empty check
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Username: only alphabets
            if(!username.matches("[a-zA-Z]+")){
                Toast.makeText(this,"Username must contain only letters",Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Password: at least 6 chars
            if(password.length() < 6){
                Toast.makeText(this,"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                return;
            }

// Password must contain at least one letter AND one number
            if(!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")){
                Toast.makeText(this,"Password must contain letters & numbers",Toast.LENGTH_SHORT).show();
                return;
            }



            // ✅ Login success (no fixed username now)
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);



        });
    }
}