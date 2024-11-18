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

public class RequestedAndAcceptedEvents extends AppCompatActivity {

    private ArrayAdapter<Event> requestedAndAcceptedEventsAdapter;
    private TextView eventNameViewAttendee;
    private TextView eventDateViewAttendee;
    private TextView eventTimeViewAttendee;
    private TextView eventLocationViewAttendee;
    private TextView organizerNameViewAttendee;
    private TextView eventDescriptionViewAttendee;
    private ListView eventsListViewAttendee;
    private Button backButtonAttendee;
    private Event selectedEvent;
    private Button unregisterButtonAttendee;
    Attendee attendee;
    ArrayList<Event> attendeeEventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_requested_and_accepted_events);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String userID = b.getString("userID");
        attendee = (Attendee) MainActivity.getUserFromID(userID);

        attendeeEventsList = new ArrayList<Event>();
        for (int i=0; i<attendee.getEventIDs().size();i++){
            Event eventToAdd = MainActivity.getEventFromID(attendee.getEventIDs().get(i));
            if (eventToAdd.getEventStartTimeMillis() > System.currentTimeMillis()) {
                attendeeEventsList.add(eventToAdd);
            }
        }

        initViews();
        initializeEventListeners();
        initializeRequestedEventsAdapter();
    }

    private void initializeRequestedEventsAdapter() {
        requestedAndAcceptedEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, attendeeEventsList);
        eventsListViewAttendee.setAdapter(requestedAndAcceptedEventsAdapter);

        eventsListViewAttendee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEvent = attendeeEventsList.get(position);

                eventNameViewAttendee.setText("Event Title: " + selectedEvent.getEventTitle());
                eventDescriptionViewAttendee.setText("Desc: " + selectedEvent.getDescription());

                String eventDate = (new Date(selectedEvent.getEventDateMillis())).toString();
                String[] tempArray = eventDate.split(" ");
                eventDate = tempArray[0]+" "+tempArray[1]+" "+tempArray[2]+" "+tempArray[5];
                eventDateViewAttendee.setText("Date: " + eventDate);


                String startEventDate = (new Date(selectedEvent.getEventStartTimeMillis())).toString();
                String endEventDate = (new Date(selectedEvent.getEventEndTimeMillis()).toString());
                tempArray = startEventDate.split(" ");
                startEventDate = tempArray[3].substring(0,5)+" "+tempArray[4];
                tempArray = endEventDate.split(" ");
                endEventDate = tempArray[3].substring(0,5)+" "+tempArray[4];
                eventTimeViewAttendee.setText("From: " + startEventDate + " to: " + endEventDate);

                eventLocationViewAttendee.setText("Address: " + selectedEvent.getEventAddress());
                User eventOrganizer = MainActivity.getUserFromID(selectedEvent.getOrganizerID());
                organizerNameViewAttendee.setText("Organizer: " + eventOrganizer.getFirstName() + " " + eventOrganizer.getLastName());
            }
        });
    }

    private void initializeEventListeners() {
        backButtonAttendee.setOnClickListener(v -> {
            Intent intent = new Intent(RequestedAndAcceptedEvents.this, AttendeeWelcomePage.class);
            Bundle b = new Bundle();
            b.putString("userID", attendee.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        unregisterButtonAttendee.setOnClickListener(v -> {
            if (selectedEvent != null){
                Long currentDate = new Date().getTime();
                Long eventStartTime = selectedEvent.getEventStartTimeMillis();
                if (eventStartTime - currentDate >= 86400 * 1000) {
                    MainActivity.deleteEvent(selectedEvent.getEventID());
                    Toast.makeText(this,"You unregistered from the event!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RequestedAndAcceptedEvents.this, RequestedAndAcceptedEvents.class);
                    Bundle b = new Bundle();
                    b.putString("userID", attendee.getUserID());
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"It is too late to unregister from the selected event!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"Please select an event to unregister from!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        eventsListViewAttendee = findViewById(R.id.eventsListViewAttendee);
        eventNameViewAttendee = findViewById(R.id.eventNameTextAttendee);
        eventDateViewAttendee = findViewById(R.id.eventDateTextAttendee);
        eventTimeViewAttendee = findViewById(R.id.eventTimeTextAttendee);
        eventLocationViewAttendee = findViewById(R.id.eventLocationTextAttendee);
        backButtonAttendee = findViewById(R.id.backToMainButtonAttendee);
        organizerNameViewAttendee = findViewById(R.id.organizerNameTextAttendee);
        eventDescriptionViewAttendee = findViewById(R.id.eventDescriptionTextAttendee);
        unregisterButtonAttendee = findViewById(R.id.unregisterAttendee);
    }
}