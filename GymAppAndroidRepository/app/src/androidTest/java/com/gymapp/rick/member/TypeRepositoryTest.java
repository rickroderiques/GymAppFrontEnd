package com.gymapp.rick.member;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.member.Type;
import com.gymapp.rick.gymapp_android.respository.member.ITypeRepository;
import com.gymapp.rick.gymapp_android.respository.member.Impl.TypeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 24-Apr-16.
 */
public class TypeRepositoryTest extends AndroidTestCase {

    private static final String TAG="TYPE TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ITypeRepository repo = new TypeRepositoryImpl(this.getContext());
        // CREATE
        Type createEntity = new Type.Builder()
                .membershipNumber("M4068")
                .membershipType("Premium")
                .build();
        Type insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Type> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Type entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Type updateEntity = new Type.Builder()
                .copy(entity)
                .membershipType("Basic")
                .build();
        repo.update(updateEntity);
        Type newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Basic",newEntity.getMembershipType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Type deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
