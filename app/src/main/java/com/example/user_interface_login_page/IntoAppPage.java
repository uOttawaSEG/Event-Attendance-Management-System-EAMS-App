package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntoAppPage extends AppCompatActivity {

    private Button buttonSignOut;
    private User user;
    private TextView welcomeMessageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_into_app_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        assert b != null;
        String userID = b.getString("userID");
        user = MainActivity.getUserFromID(userID);

        initializeViews();
        initializeEventListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "You are logged in as an " + user.getUserType(), Toast.LENGTH_SHORT).show();
    }
    private void initializeViews(){
        buttonSignOut = findViewById(R.id.buttonSignOut);
        welcomeMessageType = findViewById(R.id.welcomeMessageType);
        welcomeMessageType.setText("You are logged in as an " + user.getUserType());
    }
    private void initializeEventListeners() {
        buttonSignOut.setOnClickListener(v -> {
            Intent intent = new Intent(IntoAppPage.this, InitialPage.class);
            startActivity(intent);
        });
    }
}