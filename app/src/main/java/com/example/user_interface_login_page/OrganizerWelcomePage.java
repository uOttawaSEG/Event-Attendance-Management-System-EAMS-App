package com.example.user_interface_login_page;

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

        initViews();
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        this.buttonCreateEvent.setOnClickListener( v -> {
            //Create intent for event creation page
        });

        this.buttonUpcomingEvents.setOnClickListener( v -> {
            //Create intent for upcoming event page
        });

        this.buttonPastEvents.setOnClickListener( v -> {
            //Create intent for past event page
        });
    }

    private void initViews() {
        this.buttonCreateEvent = findViewById(R.id.buttonCreateEvents);
        this.buttonUpcomingEvents = findViewById(R.id.buttonUpcomingEvents);
        this.buttonPastEvents = findViewById(R.id.buttonPastEvents);
    }
}