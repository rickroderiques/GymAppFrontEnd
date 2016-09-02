package com.gymapp.rick.gymapp_android.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gymapp.rick.gymapp_android.R;
import com.gymapp.rick.gymapp_android.activities.client.CreateBookingActivity;
import com.gymapp.rick.gymapp_android.activities.client.ViewBookingRecords;
import com.gymapp.rick.gymapp_android.conf.util.App;
import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.services.booking.Impl.MakeBookingServiceImpl;

/**
 * Created by Rick on 08-Jun-16.
 */
public class previewBooking extends AppCompatActivity {

    private MakeBookingServiceImpl activate_service;
    private boolean isBound;
    private MakeBooking addObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_booking);

        Intent myIntent = getIntent();

        TextView bookId = (TextView)findViewById(R.id.txtBookingID);
        TextView bookingName = (TextView)findViewById(R.id.txtBookingName);
        TextView date = (TextView)findViewById(R.id.txtBookingDate);
        TextView bookingTime = (TextView)findViewById(R.id.txtBookingTime);
        TextView instructor = (TextView)findViewById(R.id.txtInstructor);
        TextView gymLocation = (TextView)findViewById(R.id.txtLocation);

        MakeBooking newBooking = new MakeBooking.Builder()
                .setId(myIntent.getLongExtra("setId", 1))
                .setBookingName(myIntent.getStringExtra("setBookingName"))
                .setDate(myIntent.getStringExtra("setDate"))
                .setTimeSlot(myIntent.getStringExtra("setTimeSlot"))
                .setInstructor(myIntent.getStringExtra("setInstructor"))
                .setGymLocation(myIntent.getStringExtra("setGymLocation"))
                .build();


        addObject = new MakeBooking.Builder()
                .copy(newBooking)
                .setId(null)
                .build();

        bookId.setText(newBooking.getId()+"");
        bookingName.setText(newBooking.getBookingName()+"");
        date.setText(newBooking.getDate()+"");
        bookingTime.setText(newBooking.getTimeSlot()+"");
        instructor.setText(newBooking.getInstructor()+"");
        gymLocation.setText(newBooking.getGymLocation()+ "");

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder localService) {
            MakeBookingServiceImpl.ActivateServiceLocalBinder binder
                    = (MakeBookingServiceImpl.ActivateServiceLocalBinder) localService;
            activate_service = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, MakeBookingServiceImpl.class);


        App.context = this;
        activate_service = MakeBookingServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public void addBooking(View view)
    {
        addObject = activate_service.createBooking(addObject);

        if(addObject.getId() != null) {
            Toast.makeText(previewBooking.this, "SUCCESSFULLY ADDED BOOKING", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewBookingRecords.class);
            startActivity(intent);
        }
        else
            Toast.makeText(previewBooking.this,"COULD NOT ADD RECORD",Toast.LENGTH_LONG).show();
    }

    public void Cancel(View view)
    {
        Intent intent = new Intent (this, CreateBookingActivity.class);
        startActivity(intent);
    }

}
