package com.gymapp.rick.gymapp_android.domain.schedules;

import java.io.Serializable;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackProgress implements Serializable {

    private Long id;
    private String sessionGoal; //keeping competition with your friend list how much times gone to the gym.

    //news feed

    public Long getId() {
        return id;
    }

    public String getSessionGoal() {
        return sessionGoal;
    }

    public TrackProgress() {
    }
    public TrackProgress(Builder builder) {
        this.id = builder.id;
        this.sessionGoal = builder.sessionGoal;

    }

    public static class Builder{
        private Long id;
        private String sessionGoal; //keeping competition with your friend list how much times gone to the gym.


        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder setSessionGoal(String value) {
            this.sessionGoal = value;
            return this;
        }

        public Builder copy(TrackProgress value)
        {
            this.id = value.id;
            this.sessionGoal = value.sessionGoal;
            return this;
        }

        public TrackProgress build() {
            return new TrackProgress(this);
        }
    }
}
