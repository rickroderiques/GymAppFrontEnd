package com.gymapp.rick.member;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.member.Info;
import com.gymapp.rick.gymapp_android.respository.member.IInfoRepository;
import com.gymapp.rick.gymapp_android.respository.member.Impl.InfoRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 24-Apr-16.
 */
public class InfoRepositoryTest extends AndroidTestCase{

    private static final String TAG="INFO TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        IInfoRepository repo = new InfoRepositoryImpl(this.getContext());
        // CREATE
        Info createEntity = new Info.Builder()
                .name("Rick")
                .surname("Roderiques")
                .contactNumber("0789187468")
                .user_email("213066521@mycput.ac.za")
                .build();
        Info insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Info> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Info entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Info updateEntity = new Info.Builder()
                .copy(entity)
                .surname("Nathan")
                .build();
        repo.update(updateEntity);
        Info newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Nathan",newEntity.getSurname());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Info deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
