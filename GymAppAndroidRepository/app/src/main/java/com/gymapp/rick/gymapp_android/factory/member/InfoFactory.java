package com.gymapp.rick.gymapp_android.factory.member;
import com.gymapp.rick.gymapp_android.domain.member.Info;

/**
 * Created by Rick on 22-Apr-16.
 */
public class InfoFactory {

    public static Info getMemberInfo(String name, String surname, String contactNumber, String user_email) {
       return new Info.Builder()
                .name(name)
                .surname(surname)
                .contactNumber(contactNumber)
                .user_email(user_email)
                .build();
    }
}
