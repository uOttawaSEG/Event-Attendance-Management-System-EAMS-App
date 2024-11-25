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

public class InitialPage extends AppCompatActivity {

    private Button loginButton;
    private Button buttonCreateOrganizer;
    private Button buttonCreateAttendee;
    private Button buttonExit;
    private EditText usernameET;
    private EditText loginPasswordET;

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
        usernameET = findViewById(R.id.usernameET);
        loginPasswordET = findViewById(R.id.loginPasswordET);

    }

    private void initializeEventListeners() {
        loginButton.setOnClickListener(v -> {
            // Verifying that username is registered
            if (MainActivity.emailExists(usernameET.getText().toString())) {
                User user = MainActivity.getUserFromEmail(usernameET.getText().toString());
                if (user.getRegistrationStatus().equals("rejected")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Registration rejected:( You can contact the admin at 4234242 ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else if ( user.getRegistrationStatus().equals("pending")){
                    Toast pendingToast= Toast.makeText(getApplicationContext(),"Your registration request is still pending! :p ", Toast.LENGTH_LONG);
                    pendingToast.setGravity(Gravity.CENTER, 0, 0);
                    pendingToast.show();
                }
                else{
                    if (user.getAccountPassword().equals(loginPasswordET.getText().toString())) {
                        Intent intent;
                        if (user.getUserType().equals("Administrator")) {
                            intent = new Intent(InitialPage.this, AdministratorWelcomePage.class);
                            Bundle b = new Bundle();
                            b.putString("userID", user.getUserID());
                            intent.putExtras(b);
                            startActivity(intent);

                        }
                        else if (user.getUserType().equals("Organizer")) {
                            intent = new Intent(InitialPage.this, OrganizerWelcomePage.class);
                            Bundle b = new Bundle();
                            b.putString("userID", user.getUserID());
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                        else if (user.getUserType().equals("Attendee")) {
                            intent = new Intent(InitialPage.this, AttendeeWelcomePage.class);
                            Bundle b = new Bundle();
                            b.putString("userID", user.getUserID());
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Email or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else {
                Toast.makeText(getApplicationContext(), "Email/username is not registered", Toast.LENGTH_SHORT).show();
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