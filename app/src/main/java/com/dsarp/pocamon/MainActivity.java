package com.dsarp.pocamon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare EditText fields for username and password input
    EditText entusername, entpassword;
    // Declare buttons for sign-in and sign-up actions
    Button signinbtn, signupbtn;
    // Declare DatabaseHelper helper instance
    DatabaseHelper db;
    TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display for the activity
        EdgeToEdge.enable(this);
        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Initialize UI components by finding their views by ID
        entusername = findViewById(R.id.username);
        entpassword = findViewById(R.id.password);
        signinbtn=findViewById(R.id.login);
        signupbtn = findViewById(R.id.signup);
        forgetpass = findViewById(R.id.forgetpass);

        // Initialize DatabaseHelper helper with the current context
        db = new DatabaseHelper(this);

        // Set an OnClickListener for the sign-in button
        signinbtn.setOnClickListener(v -> {
            // Get the entered username and password, trimming any extra spaces
            String user = entusername.getText().toString().trim();
            String pass = entpassword.getText().toString().trim();

            // Check if either the username or password fields are empty
            if (user.isEmpty() || pass.isEmpty()) {
                // Show a toast message if any field is empty
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Check if the user credentials are valid using the DatabaseHelper helper
                boolean validUser = db.checkUser(user, pass);
                if (validUser) {
                    // Show a toast message for successful login
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Create an intent to start the PokemonFinder activity
                    Intent intent = new Intent(MainActivity.this, PokemonFinder.class);
                    // Pass the username to the next activity
                    intent.putExtra("username", user);
                    // Start the PokemonFinder activity
                    startActivity(intent);
                    // Close the current login activity
                    finish();
                } else {
                    // Show a toast message for invalid credentials
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set an OnClickListener for the sign-up button
        signupbtn.setOnClickListener(v -> {
            // Create an intent to start the sign-up activity
            Intent intent = new Intent(MainActivity.this, activity_signup.class);
            // Start the sign-up activity
            startActivity(intent);
        });
        forgetpass.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,forgetpass.class);
            startActivity(intent);
        });
    }
}
