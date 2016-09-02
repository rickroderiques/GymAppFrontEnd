package com.gymapp.rick.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.UserPassword;
import com.gymapp.rick.gymapp_android.factory.member.UserPasswordFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class UserPasswordFactoryTest {
    @Test
    public void testCreate() throws Exception {
        UserPassword user = UserPasswordFactory.getUserPassword("rick", "1234");
        Assert.assertEquals("1234",user.getUserPassword());

    }

    @Test
    public void testUpdate() throws Exception {
        UserPassword user = UserPasswordFactory.getUserPassword("rick", "1234");
        UserPassword userUpdate = new UserPassword
                .Builder()
                .copy(user)
                .userPassword("6544")
                .build();
        Assert.assertEquals("6544",userUpdate.getUserPassword());

    }
}
