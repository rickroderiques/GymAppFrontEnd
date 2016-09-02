package com.gymapp.rick.gymapp_android.factory.member;

import com.gymapp.rick.gymapp_android.domain.member.Type;

/**
 * Created by Rick on 22-Apr-16.
 */
public class TypeFactory {

    public static Type getMemberType(String membershipNumber, String membershipType) {
        return new Type.Builder()
                .membershipNumber(membershipNumber)
                .membershipType(membershipType)
                .build();
    }
}
