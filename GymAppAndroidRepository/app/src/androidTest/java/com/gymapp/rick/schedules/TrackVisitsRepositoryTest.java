package com.gymapp.rick.schedules;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.schedules.TrackVisits;
import com.gymapp.rick.gymapp_android.respository.schedules.ITrackVisitsRepository;
import com.gymapp.rick.gymapp_android.respository.schedules.Impl.TrackVisitsImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackVisitsRepositoryTest extends AndroidTestCase{
    private static final String TAG="TRACK TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ITrackVisitsRepository repo = new TrackVisitsImpl(this.getContext());
        // CREATE
        TrackVisits createEntity = new TrackVisits.Builder()
                .setDateTimeOfVisit("17-04-2016, 19:00")
                .setGymVisisted("Cape Town")
                .build();
        TrackVisits insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<TrackVisits> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        TrackVisits entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        TrackVisits updateEntity = new TrackVisits.Builder()
                .copy(entity)
                .setGymVisisted("Parow")
                .build();
        repo.update(updateEntity);
        TrackVisits newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Parow",newEntity.getGymVisisted());

        // DELETE ENTITY
        repo.delete(updateEntity);
        TrackVisits deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
