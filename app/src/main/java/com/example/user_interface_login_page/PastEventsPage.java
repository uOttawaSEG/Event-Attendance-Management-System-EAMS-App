package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;

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
    private Button pastAttendees;
    Organizer organizer;
    ArrayList<Event> organizerEventsList;

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

        organizerEventsList = new ArrayList<Event>();
        for (int i=0; i<organizer.getEventIDs().size();i++){
            Event eventToAdd = MainActivity.getEventFromID(organizer.getEventIDs().get(i));
            if (eventToAdd.getEventStartTimeMillis() <= System.currentTimeMillis()) {
                organizerEventsList.add(eventToAdd);
            }
        }

        initViews();
        initializeEventListeners();
        initializePastEventListeners();
    }

    private void initializePastEventListeners() {

        pastEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, organizerEventsList);
        pastEventsListView.setAdapter(pastEventsAdapter);

        pastEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPastEvent = organizerEventsList.get(position);

                pastEventNameView.setText("Event Title: " + selectedPastEvent.getEventTitle());
                pastEventDescriptionView.setText("Desc: " + selectedPastEvent.getDescription());

                String eventDate = (new Date(selectedPastEvent.getEventDateMillis())).toString();
                String[] tempArray = eventDate.split(" ");
                eventDate = tempArray[0]+" "+tempArray[1]+" "+tempArray[2]+" "+tempArray[5];
                pastEventDateView.setText("Date: " + eventDate);

                String startEventDate = (new Date(selectedPastEvent.getEventStartTimeMillis())).toString();
                String endEventDate = (new Date(selectedPastEvent.getEventEndTimeMillis()).toString());
                tempArray = startEventDate.split(" ");
                startEventDate = tempArray[3]+" "+tempArray[4];
                tempArray = endEventDate.split(" ");
                endEventDate = tempArray[3]+" "+tempArray[4];
                pastEventTimeView.setText("From: " + startEventDate + " to: " + endEventDate);

                pastEventLocationView.setText("Address: " + selectedPastEvent.getEventAddress());
                User eventOrganizer = MainActivity.getUserFromID(selectedPastEvent.getOrganizerID());
                pastOrganizerNameView.setText("Organizer: " + eventOrganizer.getFirstName() + " " + eventOrganizer.getLastName());
            }
        });

        deletePastEvent.setOnClickListener(v -> {
            if (selectedPastEvent != null){
                MainActivity.deleteEvent(selectedPastEvent.getEventID());
                Toast.makeText(this,"Event deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PastEventsPage.this, PastEventsPage.class);
                Bundle b = new Bundle();
                b.putString("userID", organizer.getUserID());
                intent.putExtras(b);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Please select an event to delete!", Toast.LENGTH_SHORT).show();
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
        pastEventTimeView = findViewById(R.id.eventTimeText);
        pastEventsListView = findViewById(R.id.eventsListView);
        deletePastEvent = findViewById(R.id.buttonDeletePast);
        pastAttendees = findViewById(R.id.buttonPastAttendees);

    }

    private void initializeEventListeners() {
        pastBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(PastEventsPage.this, OrganizerWelcomePage.class);
            Bundle b = new Bundle();
            b.putString("userID", organizer.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        pastAttendees.setOnClickListener(v -> {
            if (selectedPastEvent != null) {
                Intent intent = new Intent(PastEventsPage.this, PastEventsPage.class);
                Bundle b = new Bundle();
                b.putString("eventID", selectedPastEvent.getEventID());
                intent.putExtras(b);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Please select an event!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}