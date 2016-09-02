package com.gymapp.rick.gymapp_android.domain.member;

import java.io.Serializable;

/**
 * Created by Rick on 22-Apr-16.
 */
public class Info implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String user_email;
    private String contactNumber;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getUser_email() {
        return user_email;
    }


    public Info(){}

    public Info(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.contactNumber = builder.contactNumber;
        this.user_email = builder.user_email;
    }

    public static class Builder{
        private Long id;
        private String name;
        private String surname;
        private String contactNumber;
        private String user_email;


        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder surname(String value) {
            this.surname = value;
            return this;
        }

        public Builder contactNumber(String value) {
            this.contactNumber = value;
            return this;
        }

        public Builder user_email(String value) {
            this.user_email = value;
            return this;
        }


        public Builder copy(Info value)
        {
            this.id = value.id;
            this.name = value.name;
            this.surname = value.surname;
            this.contactNumber = value.contactNumber;
            this.user_email = value.user_email;

            return this;
        }
        public Info build() {
            return new Info(this);
        }
    }
}
