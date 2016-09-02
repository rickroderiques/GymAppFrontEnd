package com.gymapp.rick.schedules;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.schedules.CreateUserSchedule;
import com.gymapp.rick.gymapp_android.respository.schedules.ICreateUserScheduleRepository;
import com.gymapp.rick.gymapp_android.respository.schedules.Impl.CreateUserScheduleImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class CreateUserScheduleRepositoryTest  extends AndroidTestCase {

    private static final String TAG="SCHEDULE TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ICreateUserScheduleRepository repo = new CreateUserScheduleImpl(this.getContext());
        // CREATE
        CreateUserSchedule createEntity = new CreateUserSchedule.Builder()
                .setMembershipNumber("M220592")
                .setGymLocation("Parow")
                .setSessionType("Cardio")
                .setDateTime("17-04-2016, 19:00")
                .build();
        CreateUserSchedule insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<CreateUserSchedule> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        CreateUserSchedule entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        CreateUserSchedule updateEntity = new CreateUserSchedule.Builder()
                .copy(entity)
                .setSessionType("Weights")
                .build();
        repo.update(updateEntity);
        CreateUserSchedule newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Weights",newEntity.getSessionType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        CreateUserSchedule deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
