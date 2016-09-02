package com.gymapp.rick.gymapp_android.activities.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gymapp.rick.gymapp_android.R;
import com.gymapp.rick.gymapp_android.conf.util.App;
import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.services.booking.Impl.MakeBookingServiceImpl;

import java.util.ArrayList;

/**
 * Created by Rick on 08-Jun-16.
 */
public class ViewBookingRecords extends AppCompatActivity {
    private MakeBookingServiceImpl activate_service;
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_database);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Intent intent = new Intent(this, MakeBookingServiceImpl.class);
        App.context = this;
        activate_service = MakeBookingServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        try{
            ArrayList<MakeBooking> myList = activate_service.getMakeBooking();
            ArrayList<String> displayValues = new ArrayList<String>();

            for(MakeBooking booking: myList)
                displayValues.add(booking.getId() + " - " + booking.getBookingName());

            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, displayValues);

            ListView listView = (ListView)findViewById(R.id.listViewBookings);

            listView.setAdapter(arrayAdapter);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
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

    public void returnHome(View view)
    {
        Intent intent = new Intent (this, CreateBookingActivity.class);
        startActivity(intent);
    }
}
