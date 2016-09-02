package com.gymapp.rick.gymapp_android.factory.schedules;

import com.gymapp.rick.gymapp_android.domain.schedules.CreateUserSchedule;

/**
 * Created by Rick on 25-Apr-16.
 */
public class CreateUserScheduleFactory {

    public static CreateUserSchedule getUserSchedule(String membershipNumber, String gymLocation, String sessionType, String dateTime) {
        return new CreateUserSchedule.Builder()
                .setMembershipNumber(membershipNumber)
                .setGymLocation(gymLocation)
                .setSessionType(sessionType)
                .setDateTime(dateTime)
                .build();
    }
}
