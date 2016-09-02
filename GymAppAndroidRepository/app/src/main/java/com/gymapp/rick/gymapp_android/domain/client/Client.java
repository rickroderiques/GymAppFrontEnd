package com.gymapp.rick.gymapp_android.domain.client;

import java.io.Serializable;

/**
 * Created by Rick on 02-Sep-16.
 */
public class Client implements Serializable {
    private Long id;
    private String password;
    private String name;
    private String surname;
    private String email;

    public String getPassword() {
        return password;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }


    public Client(){}

    public Client(Builder builder){
        this.password = builder.password;
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
    }

    public static class Builder{
        private Long id;
        private String password;
        private String name;
        private String surname;
        private String email;


        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder password(String value) {
            this.password = value;
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

        public Builder email(String value) {
            this.email = value;
            return this;
        }


        public Builder copy(Client value)
        {
            this.password = value.password;
            this.id = value.id;
            this.name = value.name;
            this.surname = value.surname;
            this.email = value.email;

            return this;
        }
        public Client build() {
            return new Client(this);
        }
    }
}
