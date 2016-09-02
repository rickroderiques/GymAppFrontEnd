package com.gymapp.rick.gymapp_android.factory.schedules;

import com.gymapp.rick.gymapp_android.domain.schedules.TrackVisits;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackVisitsFactory {
    public static TrackVisits getTrackVisit(String dateTimeOfVisit, String gymVisisted) {
        return new TrackVisits.Builder()
                .setDateTimeOfVisit(dateTimeOfVisit)
                .setGymVisisted(gymVisisted)
                .build();
    }

}
