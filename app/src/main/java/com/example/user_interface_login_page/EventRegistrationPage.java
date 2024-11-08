package com.example.user_interface_login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

public class EventRegistrationPage extends AppCompatActivity {

    private Button backButtonEvent;
    private Button createButtonEvent;
    private EditText eventTitleET;
    private EditText descriptionET;
    private EditText dateET;
    private EditText startTimeET;
    private EditText endTimeET;
    private EditText eventAddressET;
    private CheckBox checkBoxAutomatic;
    private CheckBox checkBoxManual;


    private Calendar calendar = Calendar.getInstance();
    private Date date;
    private Time startTime;
    private Time endTime;
    private Boolean autoRegistration;


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
           Intent intent = new Intent(EventRegistrationPage.this, OrganizerWelcomePage.class);
           startActivity(intent);
       });

       this.createButtonEvent.setOnClickListener( v -> {
           //Create intent to go back to organizer registration page, however, first validate all entered fields
           String eventTitle = this.eventTitleET.getText().toString();
           String description = this.descriptionET.getText().toString();
           Date date = this.date;
           Time startTime = this.startTime;
           Time endTime = this.endTime;
           String address = this.eventAddressET.getText().toString();
           Boolean registrationType = this.checkBoxManual.isChecked();
       });

       this.dateET.setOnClickListener( v -> {
           //Pull up calendar and get date. Afterwards, place date into dateET textview
           selectDate();
       });

       this.startTimeET.setOnClickListener( v -> {
           //Pull up clock and get time. Afterwards, place date into startTimeET textview
           selectTime(true);
       });

       this.endTimeET.setOnClickListener( v -> {
           //Pull up clock and get time. Afterwards, place date into endTimeET textview
           selectTime(false);
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
        this.checkBoxManual = findViewById(R.id.checkBoxManual);
    }

    private void selectDate() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            calendar.set(year1, month1, dayOfMonth);
            dateET.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
            this.date = calendar.getTime();
        }, year, month, day);
        datePickerDialog.show();
    }

    private void selectTime(boolean typeOfTime) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        minute = (minute / 30) * 30;  // Round to nearest 30 minutes

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            int roundedMinute = (minute1 < 30) ? 0 : 30;  // Set to nearest 30-minute interval
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, roundedMinute);
            if (typeOfTime) {
                startTimeET.setText(hourOfDay + ":" + String.format("%02d", roundedMinute));
                this.startTime = (Time) calendar.getTime();
            }
            else {
                endTimeET.setText(hourOfDay + ":" + String.format("%02d", roundedMinute));
                this.endTime = (Time) calendar.getTime();
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}
