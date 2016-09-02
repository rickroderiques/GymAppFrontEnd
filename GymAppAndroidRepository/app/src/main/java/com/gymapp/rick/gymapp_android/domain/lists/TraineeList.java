package com.gymapp.rick.gymapp_android.domain.lists;

import java.io.Serializable;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TraineeList implements Serializable {

    private Long id;
    private String trainerName; //instructor from make booking
    private String speciality;
    private String gender;

    public Long getId() {
        return id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getGender() {
        return gender;
    }

    public TraineeList() {
    }
    public TraineeList(Builder builder) {
        this.id = builder.id;
        this.trainerName = builder.trainerName;
        this.speciality = builder.speciality;
        this.gender = builder.gender;
    }

    public static class Builder{

        Long id;
        String trainerName;
        String speciality;
        String gender;

        public Builder setId(Long value) {
            this.id = value;
            return this;
        }

        public Builder setTrainerName(String value) {
            this.trainerName = value;
            return this;
        }

        public Builder setSpeciality(String value) {
            this.speciality = value;
            return this;
        }

        public Builder setGender(String value) {
            this.gender = value;
            return this;
        }

        public Builder copy(TraineeList value)
        {
            this.id = value.id;
            this.trainerName = value.trainerName;
            this.speciality = value.speciality;
            this.gender = value.gender;

            return this;
        }

        public TraineeList build()
        {
            return new TraineeList(this);
        }
    }
}
