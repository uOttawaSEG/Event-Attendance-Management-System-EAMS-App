package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class RequestRegistrationToEventAttendee extends AppCompatActivity {

    TextView eventName;
    TextView eventDateTextView;
    TextView eventTime;
    TextView eventLocation;
    TextView organizerName;
    TextView eventDescription;

    Button backToWelcomeButton;
    Button registerToEventButton;

    Attendee attendee;

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_registration_to_event_attendee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String eventID = b.getString("eventID");
        String attendeeID = b.getString("userID");

        event = (Event) MainActivity.getEventFromID(eventID);
        attendee = (Attendee) MainActivity.getUserFromID(attendeeID);

        initViewsAndFields(event);
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        backToWelcomeButton.setOnClickListener( v -> {
            Intent intent = new Intent(RequestRegistrationToEventAttendee.this, ViewEventsAttendee.class);
            Bundle b = new Bundle();
            b.putString("userID", attendee.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        registerToEventButton.setOnClickListener( v -> {
            event.addPendingAttendeeID(attendee.getUserID());
            //Intent back to search page afterwards
        });
    }

    private void initViewsAndFields(Event event) {
        eventName = findViewById(R.id.eventNameTextAttendeeRegistration);
        eventName.setText(event.getEventTitle());

        eventDateTextView = findViewById(R.id.eventDateTextAttendeeRegistration);
        String eventDate = (new Date(event.getEventDateMillis())).toString();
        String[] tempArray = eventDate.split(" ");
        eventDate = tempArray[0]+" "+tempArray[1]+" "+tempArray[2]+" "+tempArray[5];
        eventDateTextView.setText(eventDate);


        eventTime = findViewById(R.id.eventTimeTextAttendeeRegistration);
        String startEventDate = (new Date(event.getEventStartTimeMillis())).toString();
        String endEventDate = (new Date(event.getEventEndTimeMillis()).toString());
        tempArray = startEventDate.split(" ");
        startEventDate = tempArray[3].substring(0,5)+" "+tempArray[4];
        tempArray = endEventDate.split(" ");
        endEventDate = tempArray[3].substring(0,5)+" "+tempArray[4];
        eventTime.setText("From: " + startEventDate + " to: " + endEventDate);


        eventLocation = findViewById(R.id.eventLocationTextAttendeeRegistration);
        eventLocation.setText(event.getEventAddress());

        organizerName = findViewById(R.id.organizerNameTextAttendeeRegistration);
        Organizer organizer = (Organizer) MainActivity.getUserFromID(event.getOrganizerID());
        organizerName.setText(organizer.toString());

        eventDescription = findViewById(R.id.eventDescriptionTextAttendeeRegistration);
        eventDescription.setText(event.getDescription());

        backToWelcomeButton = findViewById(R.id.backToMainButtonAttendeeEventRegistration);
        registerToEventButton = findViewById(R.id.registerToEventAttendee);


    }
}