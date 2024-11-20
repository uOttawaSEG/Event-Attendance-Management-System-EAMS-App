package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AttendeeWelcomePage extends AppCompatActivity {

    Button searchForEvents;
    Button registrationRequests;
    Button signOutAttendee;

    Attendee attendee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attendee_welcome_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String userID = b.getString("userID");
        attendee = (Attendee) MainActivity.getUserFromID(userID);

        initViews();
        setOnClickListeners();
    }

    private void initViews() {
        searchForEvents = findViewById(R.id.buttonSearchForEvents);
        registrationRequests = findViewById(R.id.buttonRegistrationRequests);
        signOutAttendee = findViewById(R.id.buttonSignOutAttendee);
    }

    private void setOnClickListeners() {
        searchForEvents.setOnClickListener( v -> {
            Intent intent = new Intent (AttendeeWelcomePage.this,ViewEventsAttendee.class);
            Bundle b = new Bundle();
            b.putString("userID", attendee.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        registrationRequests.setOnClickListener( v -> {
            Intent intent = new Intent (AttendeeWelcomePage.this, RequestedAndAcceptedEvents.class);
            Bundle b = new Bundle();
            b.putString("userID", attendee.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        signOutAttendee.setOnClickListener( v -> {
            Intent intent = new Intent (AttendeeWelcomePage.this, InitialPage.class);
            startActivity(intent);
        });
    }
}