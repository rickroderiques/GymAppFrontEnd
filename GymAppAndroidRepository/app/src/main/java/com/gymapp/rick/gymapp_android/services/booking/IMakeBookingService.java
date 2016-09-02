package com.gymapp.rick.gymapp_android.services.booking;

import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;

import java.util.ArrayList;

/**
 * Created by Rick on 09-May-16.
 */
public interface IMakeBookingService {

    MakeBooking createBooking(MakeBooking booking);
    boolean updateBooking(MakeBooking booking);
    ArrayList<MakeBooking> findByName(String name);
    ArrayList<MakeBooking> getMakeBooking();

}
