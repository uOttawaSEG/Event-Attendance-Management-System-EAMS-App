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
import java.util.Date;
import java.util.List;

public class ViewEventsAttendee extends AppCompatActivity {
    private ArrayAdapter<Event> upcomingEventsAdapter;
    private EditText searchBar;
    private Button backButton;
    private Button goToEventButton;
    private ListView eventsListView;
    ArrayList<Event> attendeeEventsList;
    Attendee attendee;
    Event event;

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
        ArrayList<Event> listOfEventsForAttendees = new ArrayList<>();

        for(int i = 0; i < attendee.getEventIDs().size(); i++) {
            listOfEventsForAttendees.add(MainActivity.getEventFromID(attendee.getEventIDs().get(i)));
        }

        for (Event event: MainActivity.eventList) {
            boolean foundEvent = false;
            for (int i = 0; i < attendee.getEventIDs().size(); i++) {
                if (event.equals(listOfEventsForAttendees.get(i))) {
                    foundEvent = true;
                    break;
                }
            }
            Date date = new Date();
            long currentTime = date.getTime();
            if (!foundEvent && event.getEventStartTimeMillis() > currentTime) {
                attendeeEventsList.add(event);
            }
        }

        initViews();
        initializeViews();
        initializeEventAdapter();

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                event = attendeeEventsList.get(position);
                Toast.makeText(getApplicationContext(), event.getEventTitle() + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeEventAdapter() {
        upcomingEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, attendeeEventsList);
        eventsListView.setAdapter(upcomingEventsAdapter);
    }

    private void initializeViews() {
        backButton.setOnClickListener(v->{
            Intent intent = new Intent(ViewEventsAttendee.this,AttendeeWelcomePage.class);
            Bundle b = new Bundle();
            b.putString("userID", attendee.getUserID());
            intent.putExtras(b);
            startActivity(intent);
        });

        goToEventButton.setOnClickListener(v->{
            if (event != null) {
                Intent intent = new Intent(ViewEventsAttendee.this, RequestRegistrationToEventAttendee.class);
                Bundle b = new Bundle();
                b.putString("eventID", event.getEventID());
                b.putString("userID", attendee.getUserID());
                intent.putExtras(b);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please select to view!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        backButton = findViewById(R.id.btn_go_back);
        goToEventButton = findViewById(R.id.btn_view_event);
        searchBar= findViewById(R.id.search_bar);
        eventsListView = findViewById(R.id.lv_events);

        searchBar.addTextChangedListener(new TextWatcher() {
            // imported TextWatcher, it is used to track changes to text entered in an EditText field.

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No implementation, just here to satisfy the interface
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Satisfying the interface  TextWatcher
            }

            @Override
            public void afterTextChanged(Editable s) {
                upcomingEventsAdapter.getFilter().filter(s.toString()); // turns every keystroke into a search query
            }
        });


    }
}