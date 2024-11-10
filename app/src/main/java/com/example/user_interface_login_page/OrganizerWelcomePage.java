package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrganizerWelcomePage extends AppCompatActivity {
    private Button buttonCreateEvent;
    private Button buttonUpcomingEvents;
    private Button buttonPastEvents;
    private Organizer organizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_organizer_welcome_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String userID = b.getString("userID");
        organizer = (Organizer) MainActivity.getUserFromID(userID);

        initViews();
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        this.buttonCreateEvent.setOnClickListener( v -> {
            Intent intent = new Intent(OrganizerWelcomePage.this, EventRegistrationPage.class);
            Bundle b = new Bundle();
            b.putString("userID", organizer.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        this.buttonUpcomingEvents.setOnClickListener( v -> {
            Intent intent = new Intent(OrganizerWelcomePage.this,UpcomingEventsPage.class);
            Bundle b = new Bundle();
            b.putString("userID", organizer.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        this.buttonPastEvents.setOnClickListener( v -> {
            Intent intent = new Intent (OrganizerWelcomePage.this,PastEventsPage.class);
            Bundle b = new Bundle();
            b.putString("userID", organizer.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    private void initViews() {
        this.buttonCreateEvent = findViewById(R.id.buttonCreateEvents);
        this.buttonUpcomingEvents = findViewById(R.id.buttonUpcomingEvents);
        this.buttonPastEvents = findViewById(R.id.buttonPastEvents);
    }
}