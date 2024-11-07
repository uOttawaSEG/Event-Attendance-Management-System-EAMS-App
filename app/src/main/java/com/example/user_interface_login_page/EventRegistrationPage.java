package com.example.user_interface_login_page;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventRegistrationPage extends AppCompatActivity {

    private Button backButtonEvent;
    private Button createButtonEvent;
    private EditText eventTitleET;
    private EditText descriptionET;
    private EditText dateET;
    private EditText startTimeET;
    private EditText endTimeET;
    private EditText eventAddressET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_registration_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        SetOnClickListeners();
    }

    private void SetOnClickListeners() {
       this.backButtonEvent.setOnClickListener( v -> {
           //Create intent to go back to Organizer login page
       });

       this.createButtonEvent.setOnClickListener( v -> {
           //Create intent to go back to organizer registration page, however, first validate all entered fields
       });

       this.dateET.setOnClickListener( v -> {
           //Pull up calendar and get date. Afterwards, place date into dateET textview
       });

       this.startTimeET.setOnClickListener( v -> {
           //Pull up clock and get time. Afterwards, place date into startTimeET textview
       });

       this.endTimeET.setOnClickListener( v -> {
           //Pull up clock and get time. Afterwards, place date into endTimeET textview
       });
    }

    private void initViews() {
        this.backButtonEvent = findViewById(R.id.backButtonEvent);
        this.createButtonEvent = findViewById(R.id.createButtonEvent);
        this.eventTitleET = findViewById(R.id.eventTitleET);
        this.descriptionET = findViewById(R.id.descriptionET);
        this.dateET = findViewById(R.id.dateET);
        this.startTimeET = findViewById(R.id.startTimeET);
        this.endTimeET = findViewById(R.id.endTimeET);
        this.eventAddressET = findViewById(R.id.eventAddressET);
    }


}
