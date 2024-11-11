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

public class PastEventAttendees extends AppCompatActivity {
    private TextView firstNameView;
    private TextView lastNameView;
    private TextView usernameView;
    private TextView phoneNumberView;
    private TextView adrressTextView;
    private ListView attendeeEventListView;
    private Button backButton;
    Event event;
    protected Attendee selectedUser;
    private List<Attendee> acceptedAttendees = new ArrayList<>();
    private ArrayAdapter<Attendee> attendeeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_past_event_attendees);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String eventID = b.getString("eventID");
        event = MainActivity.getEventFromID(eventID);

        for(int i = 0; i < event.getAcceptedAttendeeIDs().size(); i++) {
            acceptedAttendees.add((Attendee) MainActivity.getUserFromID(event.getAcceptedAttendeeIDs().get(i)));
        }

        initViews();
        initializeViewEventListeners();

        // Set up the ListView and ArrayAdapter to display the accepted registration requests
        attendeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, acceptedAttendees);
        attendeeEventListView.setAdapter(attendeeAdapter);  // Set the adapter to the ListView

        // Set an item click listener for each item in the ListView
        attendeeEventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = acceptedAttendees.get(position);

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
            Intent intent = new Intent(PastEventAttendees.this, PastEventsPage.class);
            Bundle b = new Bundle();
            b.putString("userID", event.getOrganizerID());
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    private void initViews() {
        firstNameView = findViewById(R.id.pastNameText);
        lastNameView = findViewById(R.id.pastLastNameText);
        usernameView = findViewById(R.id.pastUsernameText);
        phoneNumberView = findViewById(R.id.pastPhoneNumberText);
        adrressTextView = findViewById(R.id.pastAddressText);
        attendeeEventListView = findViewById(R.id.pastAttendeeEventListView);
        backButton = findViewById(R.id.pastGoBackButton);
    }
}
