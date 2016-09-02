package com.gymapp.rick.gymapp_android.domain.member;

import java.io.Serializable;

/**
 * Created by Rick on 25-Apr-16.
 */
public class Account implements Serializable {
    private Long id;
    private String membershipNumber; //from memberDetails class / table
    private String paymentDue;
    private String lastPayment;
    private String balance;

    public Long getId() {
        return id;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public String getPaymentDue() {
        return paymentDue;
    }

    public String getLastPayment() {
        return lastPayment;
    }

    public String getBalance() {
        return balance;
    }

    public Account(){}

    public Account(Builder builder)
    {
        this.id = builder.id;
        this.membershipNumber = builder.membershipNumber;
        this.paymentDue = builder.paymentDue;
        this.lastPayment = builder.lastPayment;
        this.balance = builder.balance;
    }

    public static class Builder
    {
        private Long id;
        private String membershipNumber; //from memberDetails class / table
        private String paymentDue;
        private String lastPayment;
        private String balance;


        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder setMembershipNumber(String value) {
            this.membershipNumber = value;
            return this;
        }

        public Builder setPaymentDue(String value) {
            this.paymentDue = value;
            return this;
        }

        public Builder setLastPayment(String value) {
            this.lastPayment = value;
            return this;
        }

        public Builder setBalance(String value) {
            this.balance = value;
            return this;
        }

        public Builder copy(Account value)
        {
            this.id = value.id;
            this.membershipNumber = value.membershipNumber;
            this.paymentDue = value.paymentDue;
            this.lastPayment = value.lastPayment;
            this.balance = value.balance;

            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
