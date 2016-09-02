package com.gymapp.rick.gymapp_android.factory.lists;

import com.gymapp.rick.gymapp_android.domain.lists.LocateGym;

/**
 * Created by Rick on 25-Apr-16.
 */
public class LocateGymFactory {
    public static LocateGym getLocateGym(String gymType, String area) {
        return new LocateGym.Builder()
                .setGymType(gymType)
                .setArea(area)
                .build();
    }
}
