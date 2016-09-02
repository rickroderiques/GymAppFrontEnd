package com.gymapp.rick.member;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.member.UserPassword;
import com.gymapp.rick.gymapp_android.respository.member.IUserPasswordRepository;
import com.gymapp.rick.gymapp_android.respository.member.Impl.UserPasswordRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class UserPasswordRepositoryTest extends AndroidTestCase {

    private static final String TAG="PASSWORD TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        IUserPasswordRepository repo = new UserPasswordRepositoryImpl(this.getContext());
        // CREATE
        UserPassword createEntity = new UserPassword.Builder()
                .username("ricknathan")
                .userPassword("1234")
                .build();
        UserPassword insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<UserPassword> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        UserPassword entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        UserPassword updateEntity = new UserPassword.Builder()
                .copy(entity)
                .userPassword("8520")
                .build();
        repo.update(updateEntity);
        UserPassword newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","8520",newEntity.getUserPassword());

        // DELETE ENTITY
        repo.delete(updateEntity);
        UserPassword deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
