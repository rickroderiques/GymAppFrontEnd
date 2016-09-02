package com.gymapp.rick.member;

import android.test.AndroidTestCase;

import com.gymapp.rick.gymapp_android.domain.member.Account;
import com.gymapp.rick.gymapp_android.respository.member.IAccountRepository;
import com.gymapp.rick.gymapp_android.respository.member.Impl.AccountRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class AccountRepositoryTest extends AndroidTestCase {

    private static final String TAG="ACCOUNT TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        IAccountRepository repo = new AccountRepositoryImpl(this.getContext());
        // CREATE
        Account createEntity = new Account.Builder()
                .setMembershipNumber("M220592")
                .setPaymentDue("20.50")
                .setLastPayment("150.00")
                .setBalance("1500.00")
                .build();
        Account insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Account> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Account entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Account updateEntity = new Account.Builder()
                .copy(entity)
                .setLastPayment("50.00")
                .build();
        repo.update(updateEntity);
        Account newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","50.00",newEntity.getLastPayment());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Account deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
