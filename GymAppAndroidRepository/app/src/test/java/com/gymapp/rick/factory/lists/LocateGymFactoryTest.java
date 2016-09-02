package com.gymapp.rick.factory.lists;

import com.gymapp.rick.gymapp_android.domain.lists.LocateGym;
import com.gymapp.rick.gymapp_android.factory.lists.LocateGymFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class LocateGymFactoryTest {
    @Test
    public void testCreate() throws Exception {
        LocateGym locateGym = LocateGymFactory.getLocateGym("FULL", "Parow");
        Assert.assertEquals("Parow",locateGym.getArea());

    }

    @Test
    public void testUpdate() throws Exception {
        LocateGym locateGym = LocateGymFactory.getLocateGym("FULL", "Parow");
        LocateGym locateGymUpdate = new LocateGym
                .Builder()
                .copy(locateGym)
                .setGymType("GRID")
                .build();
        Assert.assertEquals("GRID",locateGymUpdate.getGymType());

    }
}
