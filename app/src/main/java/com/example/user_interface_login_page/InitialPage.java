package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InitialPage extends AppCompatActivity {

    private Button loginButton;
    private Button buttonCreateOrganizer;
    private Button buttonCreateAttendee;

    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_initial_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        initializeEventListeners();
    }

    private void initializeViews() {
        //Initialize views
        loginButton = findViewById(R.id.loginButton);
        buttonCreateOrganizer = findViewById(R.id.buttonCreateOrganizer);
        buttonCreateAttendee = findViewById(R.id.buttonCreateAttendee);
        buttonExit = findViewById(R.id.buttonExit);

    }

    private void initializeEventListeners() {
        loginButton.setOnClickListener(v -> {
            boolean verification = true;
            /*
            * Don't forget to verify if Inputted username and password are appropriate before doing intent
            */
            if (verification) {
                Intent intent = new Intent(InitialPage.this, IntoAppPage.class);
                startActivity(intent);
            }
            else {
                return;
                //handle false username/password, with toast
            }
        });

        buttonCreateOrganizer.setOnClickListener(v -> {
            Intent intent = new Intent(InitialPage.this, OrganiserRegisterPage.class);
            startActivity(intent);
        });

        buttonCreateAttendee.setOnClickListener(v -> {
            Intent intent = new Intent(InitialPage.this, AttendeeRegisterPage.class);
            startActivity(intent);
        });

        buttonExit.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}