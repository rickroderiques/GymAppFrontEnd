package com.gymapp.rick.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.Account;
import com.gymapp.rick.gymapp_android.factory.member.AccountFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class AccountFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Account account = AccountFactory.getMemberAccount("M220592", "20.50", "150.00", "1500.00");
        Assert.assertEquals("M220592",account.getMembershipNumber());

    }

    @Test
    public void testUpdate() throws Exception {
        Account account = AccountFactory.getMemberAccount("M220592", "20.50", "150.00", "1500.00");
        Account accountUpdate = new Account
                .Builder()
                .copy(account)
                .setLastPayment("50.00")
                .build();
        Assert.assertEquals("50.00",accountUpdate.getLastPayment());

    }
}
