package com.gymapp.rick.gymapp_android.respository.lists.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.domain.lists.TraineeList;
import com.gymapp.rick.gymapp_android.respository.lists.ITraineeListRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TraineeListRepositoryImpl extends SQLiteOpenHelper implements ITraineeListRepository {
    public static final String TABLE_NAME = "traineeList";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TRAINEENAME = "trainerName";
    public static final String COLUMN_SPECIALITY = "speciality";
    public static final String COLUMN_GENDER = "gender";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TRAINEENAME + " TEXT  NOT NULL , "
            + COLUMN_SPECIALITY + " TEXT  NOT NULL , "
            + COLUMN_GENDER + " TEXT  NOT NULL);";

    public TraineeListRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    //open connection
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public TraineeList findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TRAINEENAME,
                        COLUMN_SPECIALITY,
                        COLUMN_GENDER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final TraineeList traineeList = new TraineeList.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setTrainerName(cursor.getString(cursor.getColumnIndex(COLUMN_TRAINEENAME)))
                    .setSpeciality(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALITY)))
                    .setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .build();
            return traineeList;
        } else {
            return null;
        }
    }

    @Override
    public TraineeList save(TraineeList entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TRAINEENAME, entity.getTrainerName());
        values.put(COLUMN_SPECIALITY, entity.getSpeciality());
        values.put(COLUMN_GENDER, entity.getGender());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        TraineeList insertedEntity = new TraineeList.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public TraineeList update(TraineeList entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TRAINEENAME, entity.getTrainerName());
        values.put(COLUMN_SPECIALITY, entity.getSpeciality());
        values.put(COLUMN_GENDER, entity.getGender());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public TraineeList delete(TraineeList entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<TraineeList> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<TraineeList> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final TraineeList userPassword = new TraineeList.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setTrainerName(cursor.getString(cursor.getColumnIndex(COLUMN_TRAINEENAME)))
                        .setSpeciality(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALITY)))
                        .setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .build();
                Person.add(userPassword);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
