package com.gymapp.rick.factory.lists;

import com.gymapp.rick.gymapp_android.domain.lists.SessionList;
import com.gymapp.rick.gymapp_android.factory.lists.SessionListFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class SessionListFactoryTest {
    @Test
    public void testCreate() throws Exception {
        SessionList sessionList = SessionListFactory.getSessionList("RIDE", "cardio");
        Assert.assertEquals("cardio",sessionList.getSessionType());

    }

    @Test
    public void testUpdate() throws Exception {
        SessionList sessionList = SessionListFactory.getSessionList("RIDE", "cardio");
        SessionList sessionListUpdate = new SessionList
                .Builder()
                .copy(sessionList)
                .setSessionType("RIDE")
                .build();
        Assert.assertEquals("RIDE",sessionListUpdate.getSessionType());

    }
}
