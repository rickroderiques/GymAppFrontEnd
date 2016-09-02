package com.gymapp.rick.gymapp_android.services.lists.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.gymapp.rick.gymapp_android.domain.lists.LocateGym;
import com.gymapp.rick.gymapp_android.respository.lists.ILocateGymRepository;

import java.util.Set;

/**
 * Created by Rick on 12-May-16.
 */
public class LocateGymServiceImpl extends Service implements ILocateGymRepository{
    private static LocateGymServiceImpl ourInstance = new LocateGymServiceImpl();

    public static LocateGymServiceImpl getInstance() {
        return ourInstance;
    }

    private LocateGymServiceImpl() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public LocateGym findById(Long aLong) {
        return null;
    }

    @Override
    public LocateGym save(LocateGym entity) {
        return null;
    }

    @Override
    public LocateGym update(LocateGym entity) {
        return null;
    }

    @Override
    public LocateGym delete(LocateGym entity) {
        return null;
    }

    @Override
    public Set<LocateGym> findAll() {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
