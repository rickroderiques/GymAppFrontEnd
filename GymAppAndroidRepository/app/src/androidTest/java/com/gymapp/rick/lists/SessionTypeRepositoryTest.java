package com.gymapp.rick.lists;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.lists.SessionList;
import com.gymapp.rick.gymapp_android.respository.lists.ISessionTypeRepository;
import com.gymapp.rick.gymapp_android.respository.lists.Impl.SessionListRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class SessionTypeRepositoryTest extends AndroidTestCase {

    private static final String TAG="SESSIONLIST TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ISessionTypeRepository repo = new SessionListRepositoryImpl(this.getContext());
        // CREATE
        SessionList createEntity = new SessionList.Builder()
                .setSessionName("RIDE")
                .setSessionType("cadio")
                .build();
        SessionList insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<SessionList> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        SessionList entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        SessionList updateEntity = new SessionList.Builder()
                .copy(entity)
                .setSessionType("indurance")
                .build();
        repo.update(updateEntity);
        SessionList newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","indurance",newEntity.getSessionType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        SessionList deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
