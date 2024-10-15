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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Firebase database reference
    private static DatabaseReference databaseReference;

    // List to hold users
    private static List<User> userList;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Initialize task list and adapter
        userList = new ArrayList<>();

        // Read tasks from Firebase
        readUsers();

        Intent intent = new Intent(MainActivity.this,InitialPage.class);
        startActivity(intent);
    }

    // Method to add user
    protected static void addUser(User user) {
        // Generate and set user ID
        String userID = databaseReference.push().getKey();
        user.setUserID(userID);

        // Add user to database and userList
        databaseReference.child(userID).setValue(user);
        userList.add(user);
    }

    // Method to read users from Firebase into userList
    private void readUsers() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String firstName = userSnapshot.child("firstName").getValue(String.class);
                    String lastName = userSnapshot.child("lastName").getValue(String.class);
                    String emailAddress = userSnapshot.child("emailAddress").getValue(String.class);
                    String accountPassword = userSnapshot.child("accountPassword").getValue(String.class);
                    String phoneNumber = userSnapshot.child("phoneNumber").getValue(String.class);
                    String address = userSnapshot.child("address").getValue(String.class);
                    String userID = userSnapshot.child("userID").getValue(String.class);
                    String userType = userSnapshot.child("userType").getValue(String.class);

                    if (userType.equals("Attendee")) {
                        Attendee temp = new Attendee(firstName,lastName,emailAddress,accountPassword,phoneNumber,address);
                        temp.setUserID(userID);
                        userList.add(temp);
                    }

                    if (userType.equals("Organizer")) {
                        String organizationName = userSnapshot.child("organizationName").getValue(String.class);
                        Organizer temp = new Organizer(firstName,lastName,emailAddress,accountPassword,phoneNumber,address,organizationName);
                        temp.setUserID(userID);
                        userList.add(temp);
                    }

                    if (userType.equals("Administrator")) {
                        Administrator temp = new Administrator(firstName,lastName,emailAddress,accountPassword,phoneNumber,address);
                        temp.setUserID(userID);
                        userList.add(temp);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("MainActivity", "The read failed: " + databaseError.getCode());
            }
        });
    }

    // Method to check if email exists
    protected static boolean emailExists(String email) {
        for (int i=0;i<userList.size();i++) {
            if (userList.get(i).getEmailAddress().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // Method to get user from userID
    protected static User getUserFromID(String userID) {
        for (int i=0;i<userList.size();i++) {
            if (userList.get(i).getUserID().equals(userID)) {
                return userList.get(i);
            }
        }
        throw new IllegalArgumentException("Invalid user ID");
    }

    // Method to get user from emailAddress
    protected static User getUserFromEmail(String emailAddress) {
        for (int i=0;i<userList.size();i++) {
            if (userList.get(i).getEmailAddress().equals(emailAddress)) {
                return userList.get(i);
            }
        }
        throw new IllegalArgumentException("Invalid email address");
    }
}