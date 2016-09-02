package com.gymapp.rick.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.Info;
import com.gymapp.rick.gymapp_android.factory.member.InfoFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 23-Apr-16.
 */
public class InfoFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Info userInfo = InfoFactory.getMemberInfo("Rick,", "Roderiques", "0789187468", "213066521@mycput.ac.za");
        Assert.assertEquals("0789187468",userInfo.getContactNumber());

    }

    @Test
    public void testUpdate() throws Exception {
        Info userInfo = InfoFactory.getMemberInfo("Rick,", "Roderiques", "0789187468", "213066521@mycput.ac.za");
        Info userInfoUpdate = new Info
                .Builder()
                .copy(userInfo)
                .user_email("rroderiques22@gmail.com")
                .build();
        Assert.assertEquals("rroderiques22@gmail.com",userInfoUpdate.getUser_email());

    }

}
