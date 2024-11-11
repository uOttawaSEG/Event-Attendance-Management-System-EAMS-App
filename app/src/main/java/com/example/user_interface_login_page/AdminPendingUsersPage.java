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

public class AdminPendingUsersPage extends AppCompatActivity {

    private ListView registrationListView;  // ListView to display pending user registration requests
    private ArrayAdapter<User> registrationAdapter;  // Adapter to manage the data displayed in the ListView
    private TextView nameTextView;
    private TextView lastNameTextView;
    private TextView usernameTextView;
    private TextView phoneNumberTextView;
    private TextView addressTextView;
    private TextView organizationNameTextView;
    private TextView userTypeTextView;

    private Button goBackButton;
    private Button acceptButton;
    private Button rejectButton;

    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Enables edge-to-edge display on the activity
        setContentView(R.layout.activity_admin_pending_users_page);  // Sets the layout for the activity
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        initializeEventListeners();

        // Set up the ListView and ArrayAdapter to display the pending registration requests
        registrationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.pendingUserList);
        registrationListView.setAdapter(registrationAdapter);  // Set the adapter to the ListView

        // Set an item click listener for each item in the ListView
        registrationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = MainActivity.pendingUserList.get(position);

                nameTextView.setText("First Name: " + selectedUser.getFirstName());
                lastNameTextView.setText("Last Name: " + selectedUser.getLastName());
                usernameTextView.setText("Email: " + selectedUser.getEmailAddress());
                phoneNumberTextView.setText("Phone Number: " + selectedUser.getPhoneNumber());
                addressTextView.setText("Address: " + selectedUser.getAddress());
                userTypeTextView.setText("User Type: " + selectedUser.getUserType());

                if (selectedUser.getUserType().equals("Organizer")) {
                    Organizer organizer = (Organizer) selectedUser;
                    organizationNameTextView.setText("Org. Name: " + organizer.getOrganizationName());
                }
                else {
                    organizationNameTextView.setText("N/A");
                }
            }

        });
    }

    private void initializeEventListeners() {
        goBackButton.setOnClickListener( v -> {
            Intent intent = new Intent(AdminPendingUsersPage.this, AdministratorWelcomePage.class);
            startActivity(intent);
        });

        acceptButton.setOnClickListener(v -> {
            if (selectedUser != null) {
                String selectedUserId = selectedUser.getUserID();
                MainActivity.databaseReferenceUsers.child(selectedUserId).child("registrationStatus").setValue("accepted");
                registrationAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), selectedUser.getEmailAddress() + " added!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AdminPendingUsersPage.this, AdminPendingUsersPage.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please select a user to accept!", Toast.LENGTH_SHORT).show();
            }
        });

        rejectButton.setOnClickListener(v -> {
            if (selectedUser != null) {
                String selectedUserId = selectedUser.getUserID();
                MainActivity.databaseReferenceUsers.child(selectedUserId).child("registrationStatus").setValue("rejected");
                registrationAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), selectedUser.getEmailAddress() + " rejected!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AdminPendingUsersPage.this, AdminPendingUsersPage.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please select a user to be rejected!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        registrationListView = findViewById(R.id.registrationListView);
        nameTextView = findViewById(R.id.nameText);
        lastNameTextView = findViewById(R.id.lastNameText);
        usernameTextView = findViewById(R.id.usernameText);
        phoneNumberTextView = findViewById(R.id.phoneNumberText);
        addressTextView = findViewById(R.id.addressText);
        organizationNameTextView = findViewById(R.id.organizationNameText);
        userTypeTextView = findViewById(R.id.userTypeText);

        goBackButton = findViewById(R.id.goBackButton);
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
    }

}
