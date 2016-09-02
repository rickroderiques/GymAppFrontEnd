package com.gymapp.rick.gymapp_android.domain.lists;

import java.io.Serializable;

/**
 * Created by Rick on 25-Apr-16.
 */
public class SessionList implements Serializable {

    private Long id;
    private String sessionName;
    private String sessionType;

    public Long getId() {
        return id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getSessionType() {
        return sessionType;
    }

    public SessionList() {
    }

    public SessionList(Builder builder) {
        this.id = builder.id;
        this.sessionName = builder.sessionName;
        this.sessionType = builder.sessionType;
    }

    public static class Builder{
        private Long id;
        private String sessionName;
        private String sessionType;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder setSessionName(String value) {
            this.sessionName = value;
            return this;
        }

        public Builder setSessionType(String value) {
            this.sessionType = value;
            return this;
        }

        public Builder copy(SessionList value)
        {
            this.id = value.id;
            this.sessionName = value.sessionName;
            this.sessionType = value.sessionType;

            return this;
        }
        public SessionList build()
        {
            return new SessionList(this);
        }
    }
}
