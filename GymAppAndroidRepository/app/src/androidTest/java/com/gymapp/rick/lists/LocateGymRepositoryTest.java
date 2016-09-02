package com.gymapp.rick.lists;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.lists.LocateGym;
import com.gymapp.rick.gymapp_android.respository.lists.ILocateGymRepository;
import com.gymapp.rick.gymapp_android.respository.lists.Impl.LocateGymRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class LocateGymRepositoryTest extends AndroidTestCase {

    private static final String TAG="LOCATEGYM TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ILocateGymRepository repo = new LocateGymRepositoryImpl(this.getContext());
        // CREATE
        LocateGym createEntity = new LocateGym.Builder()
                .setGymType("GRID")
                .setArea("Parow")
                .build();
        LocateGym insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<LocateGym> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        LocateGym entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        LocateGym updateEntity = new LocateGym.Builder()
                .copy(entity)
                .setArea("Willowbridge")
                .build();
        repo.update(updateEntity);
        LocateGym newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Willowbridge",newEntity.getArea());

        // DELETE ENTITY
        repo.delete(updateEntity);
        LocateGym deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
