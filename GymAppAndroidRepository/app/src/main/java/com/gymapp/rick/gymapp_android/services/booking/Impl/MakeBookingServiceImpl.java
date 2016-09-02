package com.gymapp.rick.gymapp_android.services.booking.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.gymapp.rick.gymapp_android.conf.util.App;
import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;
import com.gymapp.rick.gymapp_android.respository.booking.IMakeBookingRepository;
import com.gymapp.rick.gymapp_android.respository.booking.Impl.MakeBookingRepositoryImpl;
import com.gymapp.rick.gymapp_android.services.booking.IMakeBookingService;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Rick on 09-May-16.
 */
public class MakeBookingServiceImpl extends Service implements IMakeBookingService {

    private IMakeBookingRepository repo;

    private final IBinder localBinder = new ActivateServiceLocalBinder();
    private static MakeBookingServiceImpl service = null;

    public static MakeBookingServiceImpl getInstance()
    {
        if (service == null)
            service = new MakeBookingServiceImpl();
        return service;
    }

    //constructor
    private MakeBookingServiceImpl()
    {
        repo = new MakeBookingRepositoryImpl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public MakeBookingServiceImpl getService() {
            return MakeBookingServiceImpl.this;
        }
    }

    @Override
    public MakeBooking createBooking(MakeBooking value) {
        try{
            return repo.save(value);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    public boolean updateBooking(MakeBooking booking){
        try{

            MakeBooking updateBooking = new MakeBooking.Builder()
                    .setId(booking.getId())
                    .setBookingName(booking.getBookingName())
                    .setDate(booking.getDate())
                    .setTimeSlot(booking.getTimeSlot())
                    .setInstructor(booking.getInstructor())
                    .setGymLocation(booking.getGymLocation())
                    .build();
            return repo.update(updateBooking).getId() == booking.getId();
        }
        catch(Exception x)
        {
            x.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<MakeBooking> findByName(String name) {
        try{
            ArrayList<MakeBooking> myList = new ArrayList<>();
            ArrayList<MakeBooking> result = new ArrayList<>();
            Set<MakeBooking> mySet = repo.findAll();

            if(!myList.addAll(mySet))
                return null;

            for(int i=0; i<myList.size(); i++)
                if(myList.get(i).getBookingName().equalsIgnoreCase(name))
                    result.add(myList.get(i));

            if(result.size() > 1)
                return result;
            else
                return new ArrayList<MakeBooking>();

        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    public ArrayList<MakeBooking> getMakeBooking() {
        try{
            ArrayList<MakeBooking> result = new ArrayList<>();
            if(result.addAll(repo.findAll()))
                return result;
            else
                return new ArrayList<MakeBooking>();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

}
