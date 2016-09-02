package com.gymapp.rick.gymapp_android.factory.lists;

import com.gymapp.rick.gymapp_android.domain.lists.TraineeList;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TraineeListFactory {
    public static TraineeList getTraineeList(String trainerName, String speciality, String gender) {
        return new TraineeList.Builder()
                .setTrainerName(trainerName)
                .setSpeciality(speciality)
                .setGender(gender)
                .build();
    }
}
