package com.gymapp.rick.gymapp_android.respository.client;

import com.gymapp.rick.gymapp_android.domain.client.Client;
import com.gymapp.rick.gymapp_android.respository.Repository;

/**
 * Created by Rick on 02-Sep-16.
 */
public interface IClientRepository extends Repository<Client, Long> {
    public boolean validateUser(String email, String password);
}
