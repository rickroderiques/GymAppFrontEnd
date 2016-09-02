package com.gymapp.rick.gymapp_android.factory.schedules;

import com.gymapp.rick.gymapp_android.domain.schedules.TrackProgress;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackProgressFactory {
    public static TrackProgress getTrackProgress(String sessionGoal) {
        return new TrackProgress.Builder()
                .setSessionGoal(sessionGoal)
                .build();
    }

}
