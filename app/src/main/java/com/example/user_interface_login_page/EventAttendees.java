package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        event =  MainActivity.getEventFromID(eventID);

        initViews();
        initializeViewEventListeners();



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
                selectedUser.addEventIDs(event.getEventID());
                event.acceptAttendee(selectedUser.getUserID());
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