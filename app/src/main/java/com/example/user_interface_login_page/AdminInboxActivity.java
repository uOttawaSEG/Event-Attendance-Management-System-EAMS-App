package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class AdminInboxActivity extends AppCompatActivity {

    private ListView registrationListView;  // ListView to display pending user registration requests
    private ArrayAdapter<User> registrationAdapter;  // Adapter to manage the data displayed in the ListView
    private List<User> pendingRequests;  // List to store users with pending registration requests

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Enables edge-to-edge display on the activity
        setContentView(R.layout.activity_admin_inbox);  // Sets the layout for the activity

        // Initialize the list to hold pending registration requests
        pendingRequests = new ArrayList<>();

        // Fetch pending registration requests from the Firebase database
        fetchPendingRequests();

        // Set up the ListView and ArrayAdapter to display the pending registration requests
        registrationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingRequests);
        registrationListView.setAdapter(registrationAdapter);  // Set the adapter to the ListView

        // Set an item click listener for each item in the ListView
        registrationListView.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = pendingRequests.get(position);  // Get the selected user
            // Start a new activity to view approval/rejection details for the selected user
            Intent intent = new Intent(AdminInboxActivity.this, RegistrationDetailActivity.class);
            intent.putExtra("userID", selectedUser.getUserID());  // Pass the userID of the selected user
            startActivity(intent);  // Start the RegistrationDetailActivity
        });
    }

    // Function to fetch pending registration requests from the Firebase database
    private void fetchPendingRequests() {

        // Get a reference to the "users" node in the Firebase database
        DatabaseReference datatabaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Attach a listener to fetch data from the database
        datatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pendingRequests.clear();  // Clear the list before adding new data
                // Iterate over each child (user) in the "users" node
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    User user = userSnapshot.getValue(User.class);  // Convert snapshot to User object
                    // If the user's registration status is "Pending", add them to the list
                    if (user != null && user.getRegistrationStatus().equals("Pending")) {
                        pendingRequests.add(user);
                    }
                }
                registrationAdapter.notifyDataSetChanged();  // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Show a toast message if there's an error fetching the data
                Toast.makeText(AdminInboxActivity.this, "Error fetching requests", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
