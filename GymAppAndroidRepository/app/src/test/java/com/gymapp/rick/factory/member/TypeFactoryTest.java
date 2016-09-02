package com.gymapp.rick.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.Type;
import com.gymapp.rick.gymapp_android.factory.member.TypeFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 23-Apr-16.
 */
public class TypeFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Type userType = TypeFactory.getMemberType("M4068", "Premium");
        Assert.assertEquals("Premium",userType.getMembershipType());

    }

    @Test
    public void testUpdate() throws Exception {
        Type userType = TypeFactory.getMemberType("M4068", "Premium");
        Type userTypeUpdate = new Type
                .Builder()
                .copy(userType)
                .membershipType("Standard")
                .build();
        Assert.assertEquals("Standard",userTypeUpdate.getMembershipType());

    }
}
