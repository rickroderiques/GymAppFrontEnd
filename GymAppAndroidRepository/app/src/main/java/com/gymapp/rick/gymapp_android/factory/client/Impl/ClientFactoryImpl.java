package com.gymapp.rick.gymapp_android.factory.client.Impl;

import com.gymapp.rick.gymapp_android.domain.client.Client;
import com.gymapp.rick.gymapp_android.factory.client.IClientFactory;

import java.util.Map;

/**
 * Created by Rick on 02-Sep-16.
 */
public class ClientFactoryImpl  implements IClientFactory {

    public Client createClient(Map<String, String> clientData) {
        return new Client.Builder()
                .password(clientData.get("password"))
                .name(clientData.get("name"))
                .surname(clientData.get("surname"))
                .email(clientData.get("email"))
                .id(Long.parseLong(clientData.get("id")))
                .build();
    }

}
