package com.gymapp.rick.factory.schedules;

import com.gymapp.rick.gymapp_android.domain.schedules.TrackProgress;
import com.gymapp.rick.gymapp_android.factory.schedules.TrackProgressFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackProgressFactoryTest {
    @Test
    public void testCreate() throws Exception {
        TrackProgress trackProgress = TrackProgressFactory.getTrackProgress("6");
        Assert.assertEquals("6",trackProgress.getSessionGoal());

    }

    @Test
    public void testUpdate() throws Exception {
        TrackProgress trackProgress = TrackProgressFactory.getTrackProgress("6");
        TrackProgress trackProgressUpdate = new TrackProgress
                .Builder()
                .copy(trackProgress)
                .setSessionGoal("3")
                .build();
        Assert.assertEquals("3",trackProgressUpdate.getSessionGoal());

    }
}
