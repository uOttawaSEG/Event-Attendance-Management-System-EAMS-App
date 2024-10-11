package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {
    // Firebase database reference
    protected static DatabaseReference mDatabase;

    // Method to add user
    protected static void addUser(User user) {
        // Generate and set user ID
        String userID = mDatabase.push().getKey();
        user.setUserID((userID));

        // Write task to Firebase
        mDatabase.child(userID).setValue(user);
    }

    // Method to read user for Firebase
    protected static void readUser() {
        return;
    }

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
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        Intent intent = new Intent(MainActivity.this,InitialPage.class);
        startActivity(intent);
    }


}