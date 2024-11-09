package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PastEventsPage extends AppCompatActivity {

    private ArrayAdapter<Event> pastEventsAdapter;
    private TextView pastEventNameView;
    private TextView pastEventDateView;
    private TextView pastEventTimeView;
    private TextView pastEventLocationView;
    private TextView pastOrganizerNameView;
    private TextView pastEventDescriptionView;
    private ListView pastEventsListView;
    private Button pastBackButton;
    private Event selectedPastEvent;
    private Button deletePastEvent;
    Organizer organizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_past_events_page);
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
        initializeEventListeners();
        initializePastEventListeners();
        MainActivity.updateEventsLists();
    }

    private void initializePastEventListeners() {

        pastEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.pastEventList);
        pastEventsListView.setAdapter(pastEventsAdapter);

        pastEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPastEvent = MainActivity.pastEventList.get(position);

                pastEventNameView.setText("Event Title: " + selectedPastEvent.getEventTitle());
                pastEventDescriptionView.setText("Desc.: " + selectedPastEvent.getDescription());
                pastEventDateView.setText("Date: " + selectedPastEvent.getEventDateMillis());
                pastEventTimeView.setText("From: " + selectedPastEvent.getEventStartTimeMillis() + " till: " + selectedPastEvent.getEventEndTimeMillis());
                pastEventLocationView.setText("Address: " + selectedPastEvent.getEventAddress());
                pastOrganizerNameView.setText("Organizer: " + selectedPastEvent.getOrganizerID());
            }
        });
    }

    private void initViews() {
        pastEventNameView = findViewById(R.id.eventNameText);
        pastEventDateView = findViewById(R.id.eventDateText);
        pastEventDescriptionView = findViewById(R.id.eventDescriptionText);
        pastEventLocationView = findViewById(R.id.eventLocationText);
        pastOrganizerNameView = findViewById(R.id.organizerNameText);
        pastBackButton = findViewById(R.id.backToMainButton);
        pastAttendeesViewButton = findViewById(R.id.viewAttendeesButton);
        pastEventTimeView = findViewById(R.id.eventTimeText);
        pastEventsListView = findViewById(R.id.eventsListView);
        deletePastEvent = findViewById(R.id.buttonDeletePast);

    }

    private void initializeEventListeners() {
        pastBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(PastEventsPage.this, OrganizerWelcomePage.class);
            Bundle b = new Bundle();
            b.putString("userID", organizer.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });
    }

}