package com.gymapp.rick.lists;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.lists.TraineeList;
import com.gymapp.rick.gymapp_android.respository.lists.ITraineeListRepository;
import com.gymapp.rick.gymapp_android.respository.lists.Impl.TraineeListRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TraineeListRepositoryTest extends AndroidTestCase {

    private static final String TAG="TRAINEELIST TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ITraineeListRepository repo = new TraineeListRepositoryImpl(this.getContext());
        // CREATE
        TraineeList createEntity = new TraineeList.Builder()
                .setTrainerName("Bob")
                .setSpeciality("weights")
                .setGender("female")
                .build();
        TraineeList insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<TraineeList> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        TraineeList entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        TraineeList updateEntity = new TraineeList.Builder()
                .copy(entity)
                .setGender("male")
                .build();
        repo.update(updateEntity);
        TraineeList newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","male",newEntity.getGender());

        // DELETE ENTITY
        repo.delete(updateEntity);
        TraineeList deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
