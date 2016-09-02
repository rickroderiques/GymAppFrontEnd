package com.gymapp.rick.gymapp_android.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.Account;

/**
 * Created by Rick on 25-Apr-16.
 */
public class AccountFactory {

    public static Account getMemberAccount(String membershipNumber, String paymentDue, String lastPayment, String balance) {
        return new Account.Builder()
                .setMembershipNumber(membershipNumber)
                .setPaymentDue(paymentDue)
                .setLastPayment(lastPayment)
                .setBalance(balance)
                .build();
    }
}
