package com.gymapp.rick.schedules;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.schedules.TrackProgress;
import com.gymapp.rick.gymapp_android.respository.schedules.ITrackProgressRepository;
import com.gymapp.rick.gymapp_android.respository.schedules.Impl.TrackProgressImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackProgressRepositoryTest extends AndroidTestCase {
    private static final String TAG="TRACKPROGRESS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ITrackProgressRepository repo = new TrackProgressImpl(this.getContext());
        // CREATE
        TrackProgress createEntity = new TrackProgress.Builder()
                .setSessionGoal("5")
                .build();
        TrackProgress insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<TrackProgress> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        TrackProgress entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        TrackProgress updateEntity = new TrackProgress.Builder()
                .copy(entity)
                .setSessionGoal("3")
                .build();
        repo.update(updateEntity);
        TrackProgress newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","3",newEntity.getSessionGoal());

        // DELETE ENTITY
        repo.delete(updateEntity);
        TrackProgress deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
