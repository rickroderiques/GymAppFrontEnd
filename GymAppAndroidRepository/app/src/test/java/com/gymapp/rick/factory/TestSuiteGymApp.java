package com.gymapp.rick.factory;

import com.gymapp.rick.factory.booking.MakeBookingFactoryTest;
import com.gymapp.rick.factory.lists.LocateGymFactoryTest;
import com.gymapp.rick.factory.lists.SessionListFactoryTest;
import com.gymapp.rick.factory.lists.TraineeListFactoryTest;
import com.gymapp.rick.factory.member.*;
import com.gymapp.rick.factory.schedules.CreateUserScheduleFactoryTest;
import com.gymapp.rick.factory.schedules.TrackProgressFactoryTest;
import com.gymapp.rick.factory.schedules.TrackVisitsFactoryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Rick on 23-Apr-16.*/


 @RunWith(Suite.class)
 @Suite.SuiteClasses({
        InfoFactoryTest.class,
        TypeFactoryTest.class,
        MakeBookingFactoryTest.class,
        AccountFactoryTest.class,
        CreateUserScheduleFactoryTest.class,
        TrackVisitsFactoryTest.class,
        TrackProgressFactoryTest.class,
        UserPasswordFactoryTest.class,
        LocateGymFactoryTest.class,
        SessionListFactoryTest.class,
        TraineeListFactoryTest.class
 })
 public class TestSuiteGymApp {}
