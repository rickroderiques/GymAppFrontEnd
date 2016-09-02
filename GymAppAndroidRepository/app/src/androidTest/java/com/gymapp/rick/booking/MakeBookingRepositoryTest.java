package com.gymapp.rick.booking;

import android.test.AndroidTestCase;


import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.respository.booking.IMakeBookingRepository;
import com.gymapp.rick.gymapp_android.respository.booking.Impl.MakeBookingRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 24-Apr-16.
 */
public class MakeBookingRepositoryTest extends AndroidTestCase {

    private static final String TAG="MAKEBOOKING TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        IMakeBookingRepository repo = new MakeBookingRepositoryImpl(this.getContext());
        // CREATE
        MakeBooking createEntity = new MakeBooking.Builder()
                .setBookingName("Yoga")
                .setDate("12-April-2016")
                .setTimeSlot("16:00-17:30")
                .setInstructor("Goku")
                .setGymLocation("Cavendish")
                .build();
        MakeBooking insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<MakeBooking> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        MakeBooking entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        MakeBooking updateEntity = new MakeBooking.Builder()
                .copy(entity)
                .setInstructor("Master Roshi")
                .build();
        repo.update(updateEntity);
        MakeBooking newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Master Roshi",newEntity.getInstructor());

        // DELETE ENTITY
        repo.delete(updateEntity);
        MakeBooking deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
