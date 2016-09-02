package com.gymapp.rick.gymapp_android.factory.booking;

import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;

/**
 * Created by Rick on 22-Apr-16.
 */
public class MakeBookingFactory {

    public static MakeBooking getBooking(String bookingName, String date, String timeSlot, String instructor, String gymLocation){
        return new MakeBooking.Builder()
                .setBookingName(bookingName)
                .setDate(date)
                .setTimeSlot(timeSlot)
                .setInstructor(instructor)
                .setGymLocation(gymLocation)
                .build();
    }
}
