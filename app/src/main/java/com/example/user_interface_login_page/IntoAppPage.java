package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntoAppPage extends AppCompatActivity {

    private Button buttonSignOut;

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

        initializeViews();
        initializeEventListeners();
    }


    private void initializeViews(){

        buttonSignOut = findViewById(R.id.buttonSignOut);



    }
    private void initializeEventListeners() {
        buttonSignOut.setOnClickListener(v -> {
            Intent intent = new Intent(IntoAppPage.this, InitialPage.class);
            startActivity(intent);
        });
    }
}