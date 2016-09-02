package com.gymapp.rick.gymapp_android.activities.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.gymapp.rick.gymapp_android.R;
import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.services.previewBooking;

/**
 * Created by Rick on 08-Jun-16.
 */
public class CreateBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbooking);
    }

    protected void openCreateBooking(View view)
    {
        EditText bookId = (EditText)findViewById(R.id.txtBookingID);
        EditText bookingName = (EditText)findViewById(R.id.txtBookingName);
        EditText date = (EditText)findViewById(R.id.txtBookingTime);
        EditText bookingTime = (EditText)findViewById(R.id.txtBookingTime);
        EditText instructor = (EditText)findViewById(R.id.txtInstructor);
        EditText gymLocation = (EditText)findViewById(R.id.txtLocation);

        MakeBooking newBooking = new MakeBooking.Builder()
                .setId(Long.parseLong(bookId.getText().toString()))
                .setBookingName(bookingName.getText().toString())
                .setDate(date.getText().toString())
                .setTimeSlot(bookingTime.getText().toString())
                .setInstructor(instructor.getText().toString())
                .setGymLocation(gymLocation.getText().toString())
                .build();


        Intent previewActivity = new Intent(this, previewBooking.class);
        previewActivity.putExtra("setId", newBooking.getId());
        previewActivity.putExtra("setBookingName", newBooking.getBookingName());
        previewActivity.putExtra("setDate", newBooking.getGymLocation());
        previewActivity.putExtra("setTimeSlot", newBooking.getTimeSlot());
        previewActivity.putExtra("setInstructor", newBooking.getInstructor());
        previewActivity.putExtra("setGymLocation", newBooking.getGymLocation());

        startActivity(previewActivity);
    }
}
