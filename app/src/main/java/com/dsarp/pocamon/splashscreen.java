package com.dsarp.pocamon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Set splash screen layout
        setContentView(R.layout.activity_splashscreen);

        // Delay and move to MainActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splashscreen.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close splash screen so it can't be returned to
        }, SPLASH_TIME_OUT);
    }
}
