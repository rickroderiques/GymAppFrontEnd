package com.gymapp.rick.factory.booking;


import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.factory.booking.MakeBookingFactory;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 23-Apr-16.
 */
public class MakeBookingFactoryTest {
    @Test
    public void testCreate() throws Exception {
        MakeBooking userBooking = MakeBookingFactory.getBooking("Yoga", "12-April-2016","16:00-17:30", "Goku", "Cavendish");
        Assert.assertEquals("Goku",userBooking.getInstructor());

    }

    @Test
    public void testUpdate() throws Exception {
        MakeBooking userBooking = MakeBookingFactory.getBooking("Yoga", "12-April-2016","16:00-17:30", "Goku", "Cavendish");
        MakeBooking userBookingUpdate = new MakeBooking
                .Builder()
                .copy(userBooking)
                .setTimeSlot("15:00-16:30")
                .build();
        Assert.assertEquals("15:00-16:30",userBookingUpdate.getTimeSlot());

    }


}
