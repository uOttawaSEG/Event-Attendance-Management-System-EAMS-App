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
import java.util.List;

public class EventAttendees extends AppCompatActivity {
    private TextView firstNameView;
    private TextView lastNameView;
    private TextView usernameView;
    private TextView phoneNumberView;
    private TextView adrressTextView;
    private ListView attendeeEventListView;
    private Button acceptButton;
    private Button rejectButton;
    private Button backButton;
    private Button approveAllButton;
    Event event;
    protected Attendee selectedUser;
    private List<Attendee> pendingAttendees = new ArrayList<>();
    private ArrayAdapter<Attendee> attendeeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_attendees);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String eventID = b.getString("eventID");
        event = MainActivity.getEventFromID(eventID);

        for(int i = 0; i < event.getPendingAttendeeIDs().size(); i++) {
            pendingAttendees.add((Attendee) MainActivity.getUserFromID(event.getPendingAttendeeIDs().get(i)));
        }

        initViews();
        initializeViewEventListeners();

        // Set up the ListView and ArrayAdapter to display the pending registration requests
        attendeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingAttendees);
        attendeeEventListView.setAdapter(attendeeAdapter);  // Set the adapter to the ListView

        // Set an item click listener for each item in the ListView
        attendeeEventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = pendingAttendees.get(position);

                firstNameView.setText("First Name: " + selectedUser.getFirstName());
                lastNameView.setText("Last Name: " + selectedUser.getLastName());
                usernameView.setText("Email: " + selectedUser.getEmailAddress());
                phoneNumberView.setText("Phone Number: " + selectedUser.getPhoneNumber());
                adrressTextView.setText("Address: " + selectedUser.getAddress());
            }
        });
    }

    private void initializeViewEventListeners() {
        backButton.setOnClickListener(v->{
            Intent intent = new Intent(EventAttendees.this, UpcomingEventsPage.class);
            Bundle b = new Bundle();
            b.putString("userID", event.getOrganizerID());
            intent.putExtras(b);
            startActivity(intent);
        });

        acceptButton.setOnClickListener(v->{
            if (selectedUser != null) {
                selectedUser.registerToEvent(event.getEventID());
                event.acceptAttendee(selectedUser.getUserID());
                attendeeAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), selectedUser.getEmailAddress() + " added!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EventAttendees.this, EventAttendees.class);
                Bundle b = new Bundle();
                b.putString("eventID", event.getEventID());
                intent.putExtras(b);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please select a user to accept!", Toast.LENGTH_SHORT).show();
            }
        });

        rejectButton.setOnClickListener(v->{
            if (selectedUser != null) {
                event.rejectAttendee(selectedUser.getUserID());
                attendeeAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), selectedUser.getEmailAddress() + " rejected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EventAttendees.this, EventAttendees.class);
                Bundle b = new Bundle();
                b.putString("eventID", event.getEventID());
                intent.putExtras(b);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please select a user to reject!", Toast.LENGTH_SHORT).show();
            }

        });

        approveAllButton.setOnClickListener( v -> {
            if (!pendingAttendees.isEmpty()) {
                for (Attendee attendee : new ArrayList<>(pendingAttendees)) {
                    attendee.registerToEvent(event.getEventID());
                    event.acceptAttendee(attendee.getUserID());
                    }
                pendingAttendees.clear();
                attendeeAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "All attendees have been registered!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EventAttendees.this, EventAttendees.class);
                Bundle b = new Bundle();
                b.putString("eventID", event.getEventID());
                intent.putExtras(b);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "All attendees are registered!", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(EventAttendees.this, UpcomingEventsPage.class);
            Bundle b = new Bundle();
            b.putString("userID", event.getOrganizerID());
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    private void initViews() {
        firstNameView = findViewById(R.id.nameText);
        lastNameView = findViewById(R.id.lastNameText);
        usernameView = findViewById(R.id.usernameText);
        phoneNumberView = findViewById(R.id.phoneNumberText);
        adrressTextView = findViewById(R.id.addressText);
        attendeeEventListView = findViewById(R.id.attendeeEventListView);
        acceptButton = findViewById(R.id.acceptRegistrationButton);
        rejectButton = findViewById(R.id.rejectRegistrationButton);
        backButton = findViewById(R.id.goBackButton);
        approveAllButton = findViewById(R.id.buttonApproveAll);
    }
}