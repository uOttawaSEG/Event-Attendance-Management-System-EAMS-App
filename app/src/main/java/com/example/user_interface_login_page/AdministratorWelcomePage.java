package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdministratorWelcomePage extends AppCompatActivity {
    private Button pendingButton;
    private Button rejectedButton;
    private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_administrator_welcome_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        initializeEventListener();
    }


    private void initializeViews(){
        pendingButton = findViewById(R.id.buttonPendingRegistrations);
        rejectedButton = findViewById(R.id.buttonRejectedRegistrations);
        logoutButton = findViewById(R.id.buttonSignOut);

    }

    private void initializeEventListener(){

        pendingButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdministratorWelcomePage.this, AdminInboxActivity.class);
            startActivity(intent);
        });

        rejectedButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdministratorWelcomePage.this, .class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdministratorWelcomePage.this, InitialPage.class);
            startActivity(intent);
        });


    }
}