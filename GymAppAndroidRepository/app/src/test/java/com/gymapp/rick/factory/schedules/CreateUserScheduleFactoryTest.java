package com.gymapp.rick.factory.schedules;

import com.gymapp.rick.gymapp_android.domain.schedules.CreateUserSchedule;
import com.gymapp.rick.gymapp_android.factory.schedules.CreateUserScheduleFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class CreateUserScheduleFactoryTest {
    @Test
    public void testCreate() throws Exception {
        CreateUserSchedule account = CreateUserScheduleFactory.getUserSchedule("M220592", "Parow", "Cardio", "17-04-2016, 19:00");
        Assert.assertEquals("M220592",account.getMembershipNumber());

    }

    @Test
    public void testUpdate() throws Exception {
        CreateUserSchedule schedule = CreateUserScheduleFactory.getUserSchedule("M220592", "Parow", "Cardio", "17-04-2016, 19:00");
        CreateUserSchedule scheduleUpdate = new CreateUserSchedule
                .Builder()
                .copy(schedule)
                .setGymLocation("Cavendish")
                .build();
        Assert.assertEquals("Cavendish",scheduleUpdate.getGymLocation());

    }
}
