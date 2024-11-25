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

public class UpcomingEventsPage extends AppCompatActivity {
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
    private Button deleteButton;
    Organizer organizer;
    ArrayList<Event> organizerEventsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upcoming_events_page);
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
            if (eventToAdd.getEventStartTimeMillis() > System.currentTimeMillis()) {
                organizerEventsList.add(eventToAdd);
            }
        }

        initViews();
        initializeEventListeners();
        initializeUpcomingEventsAdapter();
    }
    private void initializeUpcomingEventsAdapter() {
        upcomingEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, organizerEventsList);
        eventsListView.setAdapter(upcomingEventsAdapter);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEvent = organizerEventsList.get(position);

                eventNameView.setText("Event Title: " + selectedEvent.getEventTitle());
                eventDescriptionView.setText("Desc: " + selectedEvent.getDescription());

                String eventDate = (new Date(selectedEvent.getEventDateMillis())).toString();
                String[] tempArray = eventDate.split(" ");
                eventDate = tempArray[0]+" "+tempArray[1]+" "+tempArray[2]+" "+tempArray[5];
                eventDateView.setText("Date: " + eventDate);


                String startEventDate = (new Date(selectedEvent.getEventStartTimeMillis())).toString();
                String endEventDate = (new Date(selectedEvent.getEventEndTimeMillis()).toString());
                tempArray = startEventDate.split(" ");
                startEventDate = tempArray[3].substring(0,5)+" "+tempArray[4];
                tempArray = endEventDate.split(" ");
                endEventDate = tempArray[3].substring(0,5)+" "+tempArray[4];
                eventTimeView.setText("From: " + startEventDate + " to: " + endEventDate);

                eventLocationView.setText("Address: " + selectedEvent.getEventAddress());
                User eventOrganizer = MainActivity.getUserFromID(selectedEvent.getOrganizerID());
                organizerNameView.setText("Organizer: " + eventOrganizer.getFirstName() + " " + eventOrganizer.getLastName());
            }
        });
    }

    private void initializeEventListeners() {
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(UpcomingEventsPage.this, OrganizerWelcomePage.class);
            Bundle b = new Bundle();
            b.putString("userID", organizer.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        attendeesViewButton.setOnClickListener(v -> {
            if(selectedEvent!= null) {

                if (selectedEvent.isAutoRegistration()) {
                    Toast.makeText(this, "This event has auto registration!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(UpcomingEventsPage.this, EventAttendees.class);
                    Bundle b = new Bundle();
                    b.putString("eventID", selectedEvent.getEventID());
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }else{
                Toast.makeText(this,"Please select an event", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            if (selectedEvent != null){
                if (selectedEvent.getAcceptedAttendeeIDs().isEmpty()) {
                    MainActivity.deleteEvent(selectedEvent.getEventID());
                    Toast.makeText(this, "Event deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpcomingEventsPage.this, UpcomingEventsPage.class);
                    Bundle b = new Bundle();
                    b.putString("userID", organizer.getUserID());
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "This event has " + selectedEvent.getAcceptedAttendeeIDs().size() + " registered attendee(s). Cannot delete this event.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"Please select an event to delete", Toast.LENGTH_SHORT).show();
            }
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
        deleteButton = findViewById(R.id.buttonDelete);
    }
}
