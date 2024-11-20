package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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


        initViews();
        initializeViews();
        initializeEventAdapter();
    }

    private void initializeEventAdapter() {
        upcomingEventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, attendeeEventsList);
        eventsListView.setAdapter(upcomingEventsAdapter);
    }

    private void initializeViews() {
        backButton.setOnClickListener(v->{
            Intent intent = new Intent(ViewEventsAttendee.this,AttendeeWelcomePage.class);
            startActivity(intent);
        });

        goToEventButton.setOnClickListener(v->{
            Intent intent = new Intent(ViewEventsAttendee.this,RequestRegistrationToEventAttendee.class);
            Bundle b = new Bundle();
            b.putString("eventID", event.getEventID());
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    private void initViews() {
        backButton = findViewById(R.id.btn_go_back);
        goToEventButton = findViewById(R.id.btn_view_event);
        searchBar= findViewById(R.id.search_bar);
        eventsListView = findViewById(R.id.lv_events);
    }
}