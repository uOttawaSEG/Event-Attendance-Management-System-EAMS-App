package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.List;

public class UpcomingEvents extends AppCompatActivity {



    private ArrayAdapter<Event> upcomingEventsAdapter;
    private TextView eventNameView;
    private TextView eventDateView;
    private TextView eventTimeView;
    private TextView eventLocationView;
    private TextView organizerNameView;
    private TextView eventDescriptionView;
    private ListView eventsListView;
    private Button backButton;
    private Button attendeesViewButton;
    private Event selectedEvent;
    Organizer organizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upcoming_events);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        initializeEventListeners();

        upcomingEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.upcomingEventList);
        eventsListView.setAdapter(upcomingEventsAdapter);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEvent = MainActivity.upcomingEventList.get(position);

                eventNameView.setText("Event Title: " + selectedEvent.getEventTitle());
                eventDescriptionView.setText("Desc.: " + selectedEvent.getDescription());
                eventDateView.setText("Date: " + selectedEvent.getEventDateMillis());
                eventTimeView.setText("From: " + selectedEvent.getEventStartTimeMillis() + "till: " + selectedEvent.getEventEndTimeMillis());
                eventLocationView.setText("Address: " + selectedEvent.getEventAddress());
                organizerNameView.setText("Organizer: " + selectedEvent.getOrganizerID());

            }
    private void initializeEventListeners() {
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent (UpcomingEvents.this,OrganizerWelcomePage.class);
            startActivity(intent);
        });
        attendeesViewButton.setOnClickListener(v -> {
            Intent intent = new Intent (UpcomingEvents.this,...);
            startActivity(intent);
        });

    }

    private void initViews() {
        eventsListView = findViewById(R.id.eventsListView);
        eventNameView = findViewById(R.id.eventNameText);
        eventDateView = findViewById(R.id.eventDateText);
        eventTimeView = findViewById(R.id.eventTimeText);
        eventLocationView = findViewById(R.id.eventLocationText);
        backButton = findViewById(R.id.backToMainButton);
        attendeesViewButton = findViewById(R.id.viewAttendeesButton);
        organizerNameView = findViewById(R.id.organizerNameText);
        eventDescriptionView = findViewById(R.id.eventDescriptionText);
        attendeesViewButton = findViewById(R.id.viewAttendeesButton);
    }


}