package com.gymapp.rick.gymapp_android.services.client;

import android.content.Context;

import com.gymapp.rick.gymapp_android.domain.client.Client;

/**
 * Created by Rick on 02-Sep-16.
 */
public interface IClientService {
    void addClient(Context context, Client client);
    void updateClient(Context context,Client client);
    void deleteClient(Context context,Client client);
}
