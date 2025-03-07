package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Firebase database reference

    protected static DatabaseReference databaseReference;
    protected static DatabaseReference databaseReferenceUsers;
    protected static DatabaseReference databaseReferenceEvents;

    // List to hold users
    protected static List<User> userList;

    // List to hold pending users
    protected static List<User> pendingUserList;

    // List to hold rejected users
    protected static List<User> rejectedUserList;

    // List to hold events
    protected static List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase database reference
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        databaseReferenceUsers = databaseReference.child("users");
        databaseReferenceEvents = databaseReference.child("events");

        // Initialize user lists
        userList = new ArrayList<>();
        pendingUserList = new ArrayList<>();
        rejectedUserList = new ArrayList<>();
        eventList = new ArrayList<>();

        // Read users and events from Firebase
        readUsers();
        readEvents();

        Intent intent = new Intent(MainActivity.this, InitialPage.class);
        startActivity(intent);
    }

    // Method to add user
    protected static void addUser(User user) {
        // Generate and set user ID
        String userID = databaseReferenceUsers.push().getKey();
        user.setUserID(userID);

        // Add user to database and userList
        databaseReferenceUsers.child(userID).setValue(user);

        if (user.getUserType().equals("Organizer")) {
            ArrayList<String> eventIDs = ((Organizer) user).getEventIDs();
            databaseReferenceUsers.child(userID).push().setValue(eventIDs);
        }
    }

    // Method to add event
    protected static void addEvent(Event event) {
        // Generate and set event ID
        String eventID = databaseReferenceEvents.push().getKey();
        event.setEventID(eventID);

        // Add user to database and eventList
        databaseReferenceEvents.child(eventID).setValue(event);

        // Add event to organizer
        String tempKey = databaseReferenceUsers.child(event.getOrganizerID()).child("eventIDs").push().getKey();
        databaseReferenceUsers.child(event.getOrganizerID()).child("eventIDs").child(tempKey).setValue(eventID);
    }

    // Method to add event ID to attendee eventID list
    protected static void addEventToAttendee(String attendeeID, String eventID) {
        String tempKey = databaseReferenceUsers.child(attendeeID).child("eventIDs").push().getKey();
        databaseReferenceUsers.child(attendeeID).child("eventIDs").child(tempKey).setValue(eventID);
    }

    // Method to add attendee ID to event acceptedAttendeeID list
    protected static void addAcceptedAttendeeToEvent(String eventID, String attendeeID) {
        String tempKey = databaseReferenceEvents.child(eventID).child("acceptedAttendeeIDs").push().getKey();
        databaseReferenceEvents.child(eventID).child("acceptedAttendeeIDs").child(tempKey).setValue(attendeeID);
    }

    // Method to add attendee ID to event pendingAttendeeID list
    protected static void addPendingAttendeeToEvent(String eventID, String attendeeID) {
        String tempKey = databaseReferenceEvents.child(eventID).child("pendingAttendeeIDs").push().getKey();
        databaseReferenceEvents.child(eventID).child("pendingAttendeeIDs").child(tempKey).setValue(attendeeID);
    }

    // Method to remove event ID from attendee eventIDs list
    protected static void removeEventFromAttendee(String attendeeID, String eventID) {
        DatabaseReference ref = databaseReferenceUsers.child(attendeeID).child("eventIDs");
        ref.orderByValue().equalTo(eventID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the key that has the specific value
                    String key = snapshot.getKey();
                    // Delete the key
                    ref.child(key).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error deleting key: " + databaseError.getMessage());
            }
        });
    }

    // Method to remove attendee ID from event acceptedAttendeeID list
    protected static void removeAcceptedAttendeeFromEvent(String eventID, String attendeeID) {
        DatabaseReference ref = databaseReferenceEvents.child(eventID).child("acceptedAttendeeIDs");
        ref.orderByValue().equalTo(attendeeID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the key that has the specific value
                    String key = snapshot.getKey();
                    // Delete the key
                    ref.child(key).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error deleting key: " + databaseError.getMessage());
            }
        });
    }

    // Method to remove attendee ID from event pendingAttendeeID list
    protected static void removePendingAttendeeFromEvent(String eventID, String attendeeID) {
        DatabaseReference ref = databaseReferenceEvents.child(eventID).child("pendingAttendeeIDs");
        ref.orderByValue().equalTo(attendeeID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the key that has the specific value
                    String key = snapshot.getKey();
                    // Delete the key
                    ref.child(key).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error deleting key: " + databaseError.getMessage());
            }
        });
    }

    // Method to read users from Firebase into userList
    protected static void readUsers() {
        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                pendingUserList.clear();
                rejectedUserList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String firstName = userSnapshot.child("firstName").getValue(String.class);
                    String lastName = userSnapshot.child("lastName").getValue(String.class);
                    String emailAddress = userSnapshot.child("emailAddress").getValue(String.class);
                    String accountPassword = userSnapshot.child("accountPassword").getValue(String.class);
                    String phoneNumber = userSnapshot.child("phoneNumber").getValue(String.class);
                    String address = userSnapshot.child("address").getValue(String.class);
                    String userID = userSnapshot.child("userID").getValue(String.class);
                    String userType = userSnapshot.child("userType").getValue(String.class);
                    String registrationStatus = userSnapshot.child("registrationStatus").getValue(String.class);

                    if (userType.equals("Attendee")) {
                        Attendee temp = new Attendee(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
                        temp.setUserID(userID);
                        temp.setRegistrationStatus(registrationStatus);

                        for (DataSnapshot eventIDSnapshot : userSnapshot.child("eventIDs").getChildren()) {
                            String eventID = eventIDSnapshot.getValue(String.class);
                            temp.addEventID(eventID);
                        }
                        userList.add(temp);
                    }

                    if (userType.equals("Organizer")) {
                        String organizationName = userSnapshot.child("organizationName").getValue(String.class);
                        Organizer temp = new Organizer(firstName, lastName, emailAddress, accountPassword, phoneNumber, address, organizationName);
                        temp.setUserID(userID);
                        temp.setRegistrationStatus(registrationStatus);

                        for (DataSnapshot eventIDSnapshot : userSnapshot.child("eventIDs").getChildren()) {
                            String eventID = eventIDSnapshot.getValue(String.class);
                            temp.addEventID(eventID);
                        }

                        userList.add(temp);
                    }

                    if (userType.equals("Administrator")) {
                        Administrator temp = new Administrator(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
                        temp.setUserID(userID);
                        temp.setRegistrationStatus(registrationStatus);
                        userList.add(temp);
                    }
                }

                for (User user : userList) {
                    if (user.getRegistrationStatus().equals("pending")) {
                        pendingUserList.add(user);
                    } else if (user.getRegistrationStatus().equals("rejected")) {
                        rejectedUserList.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("MainActivity", "The read failed: " + databaseError.getCode());
            }
        });
    }

    // Method to read users from Firebase into userList
    protected static void readEvents() {
        databaseReferenceEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String eventTitle = userSnapshot.child("eventTitle").getValue(String.class);
                    String description = userSnapshot.child("description").getValue(String.class);
                    long eventDateMillis = userSnapshot.child("eventDateMillis").getValue(Long.class);
                    long eventStartTimeMillis = userSnapshot.child("eventStartTimeMillis").getValue(Long.class);
                    long eventEndTimeMillis = userSnapshot.child("eventEndTimeMillis").getValue(Long.class);
                    String eventAddress = userSnapshot.child("eventAddress").getValue(String.class);
                    Boolean autoRegistration = userSnapshot.child("autoRegistration").getValue(Boolean.class);
                    String organizerID = userSnapshot.child("organizerID").getValue(String.class);
                    String eventID = userSnapshot.child("eventID").getValue(String.class);


                    Event temp = new Event(eventTitle, description, eventDateMillis, eventStartTimeMillis, eventEndTimeMillis, eventAddress, autoRegistration,organizerID);
                    temp.setEventID(eventID);

                    for (DataSnapshot eventIDSnapshot : userSnapshot.child("acceptedAttendeeIDs").getChildren()) {
                        String attendeeID = eventIDSnapshot.getValue(String.class);
                        temp.addAcceptedAttendeeID(attendeeID);
                    }

                    for (DataSnapshot eventIDSnapshot : userSnapshot.child("pendingAttendeeIDs").getChildren()) {
                        String attendeeID = eventIDSnapshot.getValue(String.class);
                        temp.addPendingAttendeeID(attendeeID);
                    }

                    eventList.add(temp);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("MainActivity", "The read failed: " + databaseError.getCode());
            }
        });
    }

    protected static void deleteEvent(String eventID){
        List<String> allAttendees =  getEventFromID(eventID).getAcceptedAttendeeIDs();
        allAttendees.addAll(getEventFromID(eventID).getPendingAttendeeIDs());

        // remove event from attendees
        for (int i=0;i<allAttendees.size();i++) {
            DatabaseReference ref = databaseReferenceUsers.child(allAttendees.get(i)).child("eventIDs");
            ref.orderByValue().equalTo(eventID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Get the key that has the specific value
                        String key = snapshot.getKey();
                        // Delete the key
                        ref.child(key).removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Firebase", "Error deleting key: " + databaseError.getMessage());
                }
            });
        }

        // remove event from organizer
        DatabaseReference ref = databaseReferenceUsers.child(getEventFromID(eventID).getOrganizerID()).child("eventIDs");
        ref.orderByValue().equalTo(eventID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the key that has the specific value
                    String key = snapshot.getKey();
                    // Delete the key
                    ref.child(key).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error deleting key: " + databaseError.getMessage());
            }
        });
        // remove event from database
        databaseReferenceEvents.child(eventID).removeValue();
    }

    // Method to check if email exists
    protected static boolean emailExists(String email) {
        for (User user : userList) {
            if (user.getEmailAddress().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // Method to get user from userID
    protected static User getUserFromID(String userID) {
        for (User user : userList) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        throw new IllegalArgumentException("Invalid user ID");
    }

    // Method to get user from emailAddress
    protected static User getUserFromEmail(String emailAddress) {
        for (User user : userList) {
            if (user.getEmailAddress().equals(emailAddress)) {
                return user;
            }
        }
        throw new IllegalArgumentException("Invalid email address");
    }

    // Method to get event from eventID
    protected static Event getEventFromID(String eventID) {
        for (Event event : eventList) {
            if (event.getEventID().equals(eventID)) {
                return event;
            }
        }
        throw new IllegalArgumentException("Invalid event ID");
    }
}
