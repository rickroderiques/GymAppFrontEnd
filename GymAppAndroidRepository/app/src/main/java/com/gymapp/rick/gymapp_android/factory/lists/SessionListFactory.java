package com.gymapp.rick.gymapp_android.factory.lists;

import com.gymapp.rick.gymapp_android.domain.lists.SessionList;

/**
 * Created by Rick on 25-Apr-16.
 */
public class SessionListFactory {
    public static SessionList getSessionList(String name, String type) {
        return new SessionList.Builder()
                .setSessionName(name)
                .setSessionType(type)
                .build();
    }
}
