package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ViewEventsAttendee extends AppCompatActivity {
    private ArrayAdapter<Event> upcomingEventsAdapter;
    private EditText searchBar;
    private Button backButton;
    private Button goToEventButton;
    private ListView eventsListView;
    private ArrayList<Event> attendeeEventsList;
    private ArrayList<Event> filteredEventsList;
    private Attendee attendee;
    private Event selectedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_events_attendee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String userID = b.getString("userID");
        attendee = (Attendee) MainActivity.getUserFromID(userID);

        attendeeEventsList = new ArrayList<>();
        ArrayList<Event> listOfNoNoS = new ArrayList<>();
        filteredEventsList = new ArrayList<>();

        // Populate the attendee's events list
        long currentTime = new Date().getTime();
        for (String eventID : attendee.getEventIDs()) {
            Event event = MainActivity.getEventFromID(eventID);
            if (event != null && event.getEventStartTimeMillis() > currentTime) {
                listOfNoNoS.add(event);  // Exclude registered events
            }
        }

        // Filter out past events and add to the list
        for (Event event : MainActivity.eventList) {
            // If the event is not in attendee's list and is upcoming, add it
            if (!listOfNoNoS.contains(event) && event.getEventStartTimeMillis() > currentTime) {
                attendeeEventsList.add(event);
            }
        }


        // Sort by start time
        attendeeEventsList.sort(Comparator.comparingLong(Event::getEventStartTimeMillis));
        filteredEventsList.addAll(attendeeEventsList);

        initViews();
        initializeViews();
        initializeEventAdapter();

        // Handle event selection
        eventsListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedEvent = filteredEventsList.get(position);
            Toast.makeText(getApplicationContext(), selectedEvent.getEventTitle() + " is selected", Toast.LENGTH_SHORT).show();
        });
    }

    private void initializeEventAdapter() {
        upcomingEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredEventsList);
        eventsListView.setAdapter(upcomingEventsAdapter);
    }

    private void initializeViews() {
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewEventsAttendee.this, AttendeeWelcomePage.class);
            Bundle b = new Bundle();
            b.putString("userID", attendee.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });
        goToEventButton.setOnClickListener(v -> {
            if (selectedEvent != null) {
                Intent intent = new Intent(ViewEventsAttendee.this, RequestRegistrationToEventAttendee.class);
                Bundle b = new Bundle();
                b.putString("eventID", selectedEvent.getEventID());
                b.putString("userID", attendee.getUserID());
                intent.putExtras(b);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Please select an event to view!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        backButton = findViewById(R.id.btn_go_back);
        goToEventButton = findViewById(R.id.btn_view_event);
        searchBar = findViewById(R.id.search_bar);
        eventsListView = findViewById(R.id.lv_events);

        // Add a TextWatcher to filter the event list
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No implementation needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No implementation needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().toLowerCase().trim();
                filteredEventsList.clear();

                for (Event event : attendeeEventsList) {
                    if (event.getEventTitle().toLowerCase().contains(query) ||
                            event.getDescription().toLowerCase().contains(query)) {
                        filteredEventsList.add(event);
                    }
                }

                upcomingEventsAdapter.notifyDataSetChanged();
            }
        });
    }
}