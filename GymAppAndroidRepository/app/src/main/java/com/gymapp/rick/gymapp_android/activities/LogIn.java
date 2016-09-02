package com.gymapp.rick.gymapp_android.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gymapp.rick.gymapp_android.R;
import com.gymapp.rick.gymapp_android.activities.client.RegLogin;
import com.gymapp.rick.gymapp_android.services.login.Impl.ValidateUserImpl;
import com.gymapp.rick.gymapp_android.services.login.IValidateUserService;

/**
 * Created by Rick on 02-Sep-16.
 */
public class LogIn extends AppCompatActivity {
    IValidateUserService validateUser;
    boolean isBound = false;
    boolean isValid;
    String email = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(getBaseContext(), ValidateUserImpl.class);
        getBaseContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void validateUser(View view) {
        try {
            email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
            password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();
            isValid = validateUser.isValidUser(email, password);

            if (!isValid) {
                Toast.makeText(LogIn.this, "Incorrect Login details", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent1 = new Intent(this, Menu.class);
                startActivity(intent1);
            }
        } catch (Exception ex) {
            Toast.makeText(LogIn.this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view){
        Intent intent = new Intent(this, RegLogin.class);
        startActivity(intent);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ValidateUserImpl.RetrieveAccountInfoLocalBinder binder
                    = (ValidateUserImpl.RetrieveAccountInfoLocalBinder) service;
            validateUser = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };
}
