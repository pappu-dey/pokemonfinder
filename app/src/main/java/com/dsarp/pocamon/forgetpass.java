package com.dsarp.pocamon;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class forgetpass extends AppCompatActivity {
    // Declare EditText fields for username and new password input
    EditText username, newpass;
    // Declare button for resetting the password
    Button rsetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display for the activity
        EdgeToEdge.enable(this);
        // Set the layout for the activity
        setContentView(R.layout.activity_forgetpass);

        // Initialize UI components by finding their views by ID
        username = findViewById(R.id.username);
        newpass = findViewById(R.id.newpass);
        rsetpassword = findViewById(R.id.resetpass);

        // Initialize the DatabaseHelper helper with the current context
        DatabaseHelper db = new DatabaseHelper(this);

        // Set an OnClickListener for the reset password button
        rsetpassword.setOnClickListener(v -> {
            // Get the entered username and new password, trimming any extra spaces
            String usern = username.getText().toString().trim();
            String newPassword = newpass.getText().toString().trim();

            // Check if either the username or new password fields are empty
            if (usern.isEmpty() || newPassword.isEmpty()) {
                // Show a toast message if any field is empty
                Toast.makeText(forgetpass.this, "Enter Username and New Password", Toast.LENGTH_SHORT).show();
            } else {
                // Attempt to reset the password using the DatabaseHelper helper
                boolean success = db.
                        updatePassword(usern, newPassword);

                // Check if the password reset was successful
                if (success) {
                    // Show a toast message for successful password reset
                    Toast.makeText(forgetpass.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                    // Navigate back to the MainActivity
                    startActivity(new Intent(forgetpass.this, MainActivity.class));
                    // Close the current forget password activity
                    finish();
                } else {
                    // Show a toast message if the user does not exist
                    Toast.makeText(forgetpass.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}