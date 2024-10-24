package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminRejectedUsersPage extends AppCompatActivity {

    private Button goBackButtonRejected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_rejected_users_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        initializeEventListeners();
    }

    private void initializeEventListeners() {
        goBackButtonRejected.setOnClickListener(v -> {
            Intent intent = new Intent(AdminRejectedUsersPage.this, AdministratorWelcomePage.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        goBackButtonRejected = findViewById(R.id.goBackButtonRejected);
    }
}