package com.gymapp.rick.factory.lists;

import com.gymapp.rick.gymapp_android.domain.lists.TraineeList;
import com.gymapp.rick.gymapp_android.factory.lists.TraineeListFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TraineeListFactoryTest {
    @Test
    public void testCreate() throws Exception {
        TraineeList traineeList = TraineeListFactory.getTraineeList("Bob", "weights", "male");
        Assert.assertEquals("male",traineeList.getGender());

    }

    @Test
    public void testUpdate() throws Exception {
        TraineeList traineeList = TraineeListFactory.getTraineeList("Bob", "weights", "male");
        TraineeList traineeListUpdate = new TraineeList
                .Builder()
                .copy(traineeList)
                .setTrainerName("Rick")
                .build();
        Assert.assertEquals("Rick",traineeListUpdate.getTrainerName());

    }
}
