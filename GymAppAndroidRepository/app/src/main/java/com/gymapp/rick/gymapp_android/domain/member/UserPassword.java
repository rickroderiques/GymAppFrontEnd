package com.gymapp.rick.gymapp_android.domain.member;

import java.io.Serializable;

/**
 * Created by Rick on 25-Apr-16.
 */
public class UserPassword implements Serializable {

    private Long id;
    private String username;
    private String userPassword;

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    private UserPassword() {
    }

    private UserPassword(Builder builder)
    {
        this.id = builder.id;
        this.username = builder.username;
        this.userPassword = builder.userPassword;
    }


    /**Builder class*/
    public static class Builder {
        private Long id;
        private String username;
        private String userPassword;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder username(String value) {
            this.username = value;
            return this;
        }

        public Builder userPassword(String value) {
            this.userPassword = value;
            return this;
        }

        public Builder copy(UserPassword value)
        {
            this.id = value.id;
            this.username = value.username;
            this.userPassword = value.userPassword;

            return this;
        }

        public UserPassword build()
        {
            return new UserPassword(this);
        }
    }
}
