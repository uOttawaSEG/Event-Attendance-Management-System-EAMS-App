package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
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

public class MainActivity extends AppCompatActivity {
    // Firebase database
    private FirebaseDatabase firebaseDatabase;

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

        // Initialize Firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Initialize Firebase database reference
        databaseReference = firebaseDatabase.getReference("users");

        // Initialize task list and adapter
        userList = new ArrayList<>();

        // Read tasks from Firebase
        //readUsers();

        Intent intent = new Intent(MainActivity.this,InitialPage.class);
        startActivity(intent);
    }

    // Method to add user
    protected static void addUser(User user) {
        // Generate and set user ID
        String userID = databaseReference.push().getKey();
        user.setUserID(userID);

        // Add user to database and userList
        databaseReference.setValue(user);
        userList.add(user);

        // Write task to Firebase
        databaseReference.child(userID).setValue(user);
    }

    // Method to read users from Firebase into userList
    protected static void readUsers() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    userList.add(user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
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
    protected static User getUser(String userID) {
        for (int i=0;i<userList.size();i++) {
            if (userList.get(i).getUserID().equals(userID)) {
                return userList.get(i);
            }
        }
        throw new IllegalArgumentException("Invalid userID");
    }
}