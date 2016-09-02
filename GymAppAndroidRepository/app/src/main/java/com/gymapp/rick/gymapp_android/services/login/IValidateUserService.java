package com.gymapp.rick.gymapp_android.services.login;

/**
 * Created by Rick on 02-Sep-16.
 */
public interface IValidateUserService {
    boolean isValidUser(String email, String password);
}