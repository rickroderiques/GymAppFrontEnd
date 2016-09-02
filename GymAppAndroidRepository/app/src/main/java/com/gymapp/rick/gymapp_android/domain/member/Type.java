package com.gymapp.rick.gymapp_android.domain.member;

import java.io.Serializable;

/**
 * Created by Rick on 22-Apr-16.
 */
public class Type implements Serializable {

    private Long id;
    private String membershipNumber;
    private String membershipType;

    public Long getId() {
        return id;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public Type(){}

    public Type(Builder builder){
        this.id = builder.id;
        this.membershipNumber = builder.membershipNumber;
        this.membershipType = builder.membershipType;

    }

    public static class Builder{
        private Long id;
        private String membershipNumber;
        private String membershipType;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder membershipNumber(String value) {
            this.membershipNumber = value;
            return this;
        }

        public Builder membershipType(String value) {
            this.membershipType = value;
            return this;
        }

        public Builder copy(Type value)
        {
            this.id = value.id;
            this.membershipNumber = value.membershipNumber;
            this.membershipType = value.membershipType;
            return this;
        }

        public Type build() {
            return new Type(this);
        }
    }
}
