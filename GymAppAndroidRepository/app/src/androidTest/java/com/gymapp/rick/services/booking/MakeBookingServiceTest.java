package com.gymapp.rick.services.booking;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.conf.util.App;
import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.respository.booking.IMakeBookingRepository;
import com.gymapp.rick.gymapp_android.respository.booking.Impl.MakeBookingRepositoryImpl;
import com.gymapp.rick.gymapp_android.services.booking.Impl.MakeBookingServiceImpl;

import junit.framework.Assert;

//import org.junit.Test;

/**
 * Created by Rick on 12-May-16.
 */
public class MakeBookingServiceTest extends AndroidTestCase {

    private MakeBookingServiceImpl activateService;
    IMakeBookingRepository repo = new MakeBookingRepositoryImpl(this.getContext());
    private boolean isBound;

    Long newId;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), MakeBookingServiceImpl.class);
        App.context = this.getContext();
        activateService = MakeBookingServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MakeBookingServiceImpl.ActivateServiceLocalBinder binder
                    = (MakeBookingServiceImpl.ActivateServiceLocalBinder) service;
            activateService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

   //@Test
    public void testCreateAnimal()
    {
        MakeBooking booking = new MakeBooking.Builder()
                .setBookingName("Yoga")
                .setDate("12-April-2016")
                .setTimeSlot("16:00-17:30")
                .setInstructor("Goku")
                .setGymLocation("Cavendish")
                .build();
        booking = activateService.createBooking(booking);
        if(booking.getId() != null)
            newId = booking.getId();
        Assert.assertNotNull(booking.getId());
    }

    //@Test
    public void testUpdateAnimal()
    {
        MakeBooking original = new MakeBooking.Builder()
                .setBookingName("Yoga")
                .setDate("12-April-2016")
                .setTimeSlot("16:00-17:30")
                .setInstructor("Goku")
                .setGymLocation("Cavendish")
                .build();

        original = activateService.createBooking(original);

        MakeBooking updatedBooking = new MakeBooking.Builder().copy(original).setInstructor("Master Roshi").build();
        Assert.assertTrue(activateService.updateBooking(updatedBooking)); // testing the boolean statement
    }

   // @Test
    public void testFindByName()
    {
        Assert.assertNotNull(activateService.findByName("Yoga"));
    }

    public void testGetBooking()
    {
        Assert.assertNotNull(activateService.getMakeBooking());
    }

}
