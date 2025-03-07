package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrganiserRegisterPage extends AppCompatActivity {
    private Button backButton;
    private Button registerButton;
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText phoneNumberET;
    private EditText addressET;
    private EditText organizationNameET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_organiser_register_page);
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
        backButton = findViewById(R.id.backButton);
        registerButton = findViewById(R.id.registerButton);
        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        phoneNumberET = findViewById(R.id.phoneNumberET);
        addressET = findViewById(R.id.addressET);
        organizationNameET = findViewById(R.id.organizationName);
    }

    private void initializeEventListeners() {
       backButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrganiserRegisterPage.this, InitialPage.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(v -> {
            try {
                if (MainActivity.emailExists(emailET.getText().toString())) {
                    throw new IllegalArgumentException("Email already exists");
                }

                Organizer organizer = new Organizer(firstNameET.getText().toString(),
                        lastNameET.getText().toString(),
                        emailET.getText().toString(),
                        passwordET.getText().toString(),
                        phoneNumberET.getText().toString(),
                        addressET.getText().toString(),
                        organizationNameET.getText().toString());

                // Adding organizer to database
                MainActivity.addUser(organizer);

                Intent intent = new Intent(OrganiserRegisterPage.this, InitialPage.class);
                Bundle b = new Bundle();
                b.putString("userID", organizer.getUserID());
                intent.putExtras(b);
                Toast registrationToast= Toast.makeText(getApplicationContext(),"Your registration request is now being processed!", Toast.LENGTH_LONG);
                registrationToast.setGravity(Gravity.CENTER, 0, 0);
                registrationToast.show();
                startActivity(intent);
            }
            catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}