package com.gymapp.rick.gymapp_android.domain.schedules;

import java.io.Serializable;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackVisits implements Serializable {

    private Long id;
    private String dateTimeOfVisit;//can visit twice in one day and how long they stayed
    private String gymVisisted; //can pull from the same table as gym location
    // private String visitsThisMonth; // can be calculated from amount of times visited
    // private String visitsLastMonth; // can be calculated from amount of times visited
    // can also set what you did on that day

    public Long getId() {
        return id;
    }

    public String getDateTimeOfVisit() {
        return dateTimeOfVisit;
    }

    public String getGymVisisted() {
        return gymVisisted;
    }


    public TrackVisits() {
    }

    public TrackVisits(Builder builder) {
        this.id = builder.id;
        this.dateTimeOfVisit = builder.dateTimeOfVisit;
        this.gymVisisted = builder.gymVisisted;

    }

    public static class Builder {
        private Long id;
        private String dateTimeOfVisit;//can visit twice in one day
        private String gymVisisted; //can pull from the same table as gym location

        public Builder setId(Long value) {
            this.id = value;
            return this;
        }

        public Builder setDateTimeOfVisit(String value) {
            this.dateTimeOfVisit = value;
            return this;
        }

        public Builder setGymVisisted(String value) {
            this.gymVisisted = value;
            return this;
        }


        public Builder copy(TrackVisits value) {
            this.id = value.id;
            this.dateTimeOfVisit = value.dateTimeOfVisit;
            this.gymVisisted = value.gymVisisted;
            return this;
        }

        public TrackVisits build() {
            return new TrackVisits(this);
        }
    }
}