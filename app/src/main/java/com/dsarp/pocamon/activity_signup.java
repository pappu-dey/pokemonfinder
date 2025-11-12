package com.dsarp.pocamon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class activity_signup extends AppCompatActivity {
    EditText username, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        DatabaseHelper db = new DatabaseHelper(this);

        register.setOnClickListener(v -> {
            String usern = username.getText().toString().trim();
            String passw = password.getText().toString().trim();

            if (usern.isEmpty() || passw.isEmpty()) {
                Toast.makeText(activity_signup.this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
            } else {
                boolean success = db.registerUser(usern, passw);

                if (success) {
                    Toast.makeText(activity_signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_signup.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(activity_signup.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}