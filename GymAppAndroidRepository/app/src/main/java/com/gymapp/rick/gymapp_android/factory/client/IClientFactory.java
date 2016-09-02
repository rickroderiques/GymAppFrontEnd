package com.gymapp.rick.gymapp_android.factory.client;

import com.gymapp.rick.gymapp_android.domain.client.Client;

import java.util.Map;

/**
 * Created by Rick on 02-Sep-16.
 */
public interface IClientFactory {
    public Client createClient(Map<String, String> clientData);
}
