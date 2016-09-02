package com.gymapp.rick.gymapp_android.services.login.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.gymapp.rick.gymapp_android.respository.client.Impl.ClientRepositoryImpl;
import com.gymapp.rick.gymapp_android.services.login.IValidateUserService;

/**
 * Created by Rick on 02-Sep-16.
 */
public class ValidateUserImpl extends Service implements IValidateUserService{

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class RetrieveAccountInfoLocalBinder extends Binder {
        public ValidateUserImpl getService()
        {
            return  ValidateUserImpl.this;
        }
    }
    public ValidateUserImpl() {
    }

    @Override
    public boolean isValidUser(String email, String idNumber) {
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl(getBaseContext());
        return clientRepository.validateUser(email, idNumber);
    }
}
