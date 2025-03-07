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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.TextView;
import android.widget.Toast;

public class EventRegistrationPage extends AppCompatActivity {

    private Button backButtonEvent;
    private Button createButtonEvent;
    private EditText eventTitleET;
    private EditText descriptionET;
    private TextView dateET;
    private TextView startTimeET;
    private TextView endTimeET;
    private EditText eventAddressET;
    private CheckBox checkBoxManual;


    private Calendar calendar = Calendar.getInstance();
    private Date date = null;
    private Date startTime = null;
    private Date endTime = null;
    private Organizer organizer;


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

        Bundle b = getIntent().getExtras();
        assert b != null;
        String userID = b.getString("userID");
        organizer = (Organizer) MainActivity.getUserFromID(userID);

        initViews();
        SetOnClickListeners();
    }

    private void SetOnClickListeners() {
       this.backButtonEvent.setOnClickListener( v -> {
           //Create intent to go back to Organizer login page
           Intent intent = new Intent(EventRegistrationPage.this, OrganizerWelcomePage.class);
           Bundle b = new Bundle();
           b.putString("userID", organizer.getUserID());
           intent.putExtras(b);
           startActivity(intent);
       });

       this.createButtonEvent.setOnClickListener( v -> {
           //Create intent to go back to organizer registration page, however, first validate all entered fields
           String eventTitle = this.eventTitleET.getText().toString();
           String description = this.descriptionET.getText().toString();
           long dateEvent;
           long startTimeEvent;
           long endTimeEvent;

           if (date != null) {
               dateEvent = this.date.getTime();
           }
           else {
               dateEvent = 0;
           }

           if (startTime != null) {
               startTimeEvent = this.startTime.getTime();
           }
           else {
               startTimeEvent = 0;
           }
           if (endTime != null) {
               endTimeEvent = this.endTime.getTime();
           }
           else {
               endTimeEvent = 0;
           }

           String address = this.eventAddressET.getText().toString();
           boolean registrationType = this.checkBoxManual.isChecked();

           try {
               dateEvent = getTimeFromDate(dateET.getText().toString());
           } catch (ParseException e) {
               throw new RuntimeException(e);
           }

           startTimeEvent = dateEvent + getTimeFromString(startTimeET.getText().toString());

           endTimeEvent = dateEvent + getTimeFromString(endTimeET.getText().toString());

//           Toast.makeText(getApplicationContext(), new Date(dateEvent).toString(), Toast.LENGTH_SHORT).show();
//
//           Toast.makeText(getApplicationContext(), new Date(startTimeEvent).toString(), Toast.LENGTH_SHORT).show();
//
//           Toast.makeText(getApplicationContext(), new Date(endTimeEvent).toString(), Toast.LENGTH_SHORT).show();

           try {
               if (!Validator.validateDate(startTimeEvent)) {
                   throw new IllegalArgumentException("Date has already passed or has been left blank!");
               }

               Event event = new Event(eventTitle,description,dateEvent,startTimeEvent,endTimeEvent,address,registrationType,organizer.getUserID());
               MainActivity.addEvent(event);

               Intent intent = new Intent(EventRegistrationPage.this, OrganizerWelcomePage.class);
               Bundle b = new Bundle();
               b.putString("userID", organizer.getUserID());
               intent.putExtras(b);
               startActivity(intent);

           }
           catch (IllegalArgumentException e) {
               Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
           }
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
        minute = (minute / 30) * 30;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            int roundedMinute = (minute1 < 30) ? 0 : 30;
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, roundedMinute);
            if (typeOfTime) {
                startTimeET.setText(hourOfDay + ":" + String.format("%02d", roundedMinute));
                this.startTime = calendar.getTime();  // No casting to Time
            } else {
                endTimeET.setText(hourOfDay + ":" + String.format("%02d", roundedMinute));
                this.endTime = calendar.getTime();  // No casting to Time
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private long getTimeFromDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate = format.parse(date);

        assert newDate != null;
        return newDate.getTime();
    }

    private long getTimeFromString(String time) {
        String[] times = time.split(":");

        long hours = Long.parseLong(times[0]) * 3600 * 1000;
        long minutes = Long.parseLong(times[1]) * 60 * 1000;

        return hours + minutes;
    }
}
