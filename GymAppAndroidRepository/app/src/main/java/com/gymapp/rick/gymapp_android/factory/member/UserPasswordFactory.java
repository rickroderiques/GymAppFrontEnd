package com.gymapp.rick.gymapp_android.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.UserPassword;

/**
 * Created by Rick on 25-Apr-16.
 */
public class UserPasswordFactory {
    public static UserPassword getUserPassword(String username, String userPassword) {
        return new UserPassword.Builder()
                .username(username)
                .userPassword(userPassword)
                .build();
    }
}