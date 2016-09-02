package com.gymapp.rick.gymapp_android.activities.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//import com.example.rick.gymapp_android.R;
import com.gymapp.rick.gymapp_android.R;
import com.gymapp.rick.gymapp_android.activities.LogIn;

/**
 * Created by Rick on 02-Sep-16.
 */
public class RegLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
    }

    public void nextActionRegister(View view )
    {
        Intent intent = new Intent(this, CreateBookingActivity.class);
        startActivity(intent);
    }
    public void nextActionLogin(View view){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}
