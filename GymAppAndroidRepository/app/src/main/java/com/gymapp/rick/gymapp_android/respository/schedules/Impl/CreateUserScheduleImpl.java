package com.gymapp.rick.gymapp_android.respository.schedules.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.domain.schedules.CreateUserSchedule;
import com.gymapp.rick.gymapp_android.respository.schedules.ICreateUserScheduleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class CreateUserScheduleImpl extends SQLiteOpenHelper implements ICreateUserScheduleRepository{
    public static final String TABLE_NAME = "userSchedule";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEMBERNUMBER = "membershipNumber";
    public static final String COLUMN_GYMLOCATION = "gymLocation";
    public static final String COLUMN_SESSIONTYPE = "sessionType";
    public static final String COLUMN_DATETIME = "dateTime";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MEMBERNUMBER + " TEXT NOT NULL , "
            + COLUMN_GYMLOCATION + " TEXT NOT NULL , "
            + COLUMN_SESSIONTYPE + " TEXT  NOT NULL , "
            + COLUMN_DATETIME + " TEXT  NOT NULL);";

    public CreateUserScheduleImpl(Context context)
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
    public CreateUserSchedule findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_MEMBERNUMBER,
                        COLUMN_GYMLOCATION,
                        COLUMN_SESSIONTYPE,
                        COLUMN_DATETIME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final CreateUserSchedule schedule = new CreateUserSchedule.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setMembershipNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERNUMBER)))
                    .setGymLocation(cursor.getString(cursor.getColumnIndex(COLUMN_GYMLOCATION)))
                    .setSessionType(cursor.getString(cursor.getColumnIndex(COLUMN_SESSIONTYPE)))
                    .setDateTime(cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME)))
                    .build();
            return schedule;
        } else {
            return null;
        }
    }

    @Override
    public CreateUserSchedule save(CreateUserSchedule entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MEMBERNUMBER, entity.getMembershipNumber());
        values.put(COLUMN_GYMLOCATION, entity.getGymLocation());
        values.put(COLUMN_SESSIONTYPE, entity.getSessionType());
        values.put(COLUMN_DATETIME, entity.getDateTime());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        CreateUserSchedule insertedEntity = new CreateUserSchedule.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public CreateUserSchedule update(CreateUserSchedule entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MEMBERNUMBER, entity.getMembershipNumber());
        values.put(COLUMN_GYMLOCATION, entity.getGymLocation());
        values.put(COLUMN_SESSIONTYPE, entity.getSessionType());
        values.put(COLUMN_DATETIME, entity.getDateTime());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public CreateUserSchedule delete(CreateUserSchedule entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<CreateUserSchedule> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<CreateUserSchedule> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final CreateUserSchedule schedule = new CreateUserSchedule.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setMembershipNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERNUMBER)))
                        .setGymLocation(cursor.getString(cursor.getColumnIndex(COLUMN_GYMLOCATION)))
                        .setSessionType(cursor.getString(cursor.getColumnIndex(COLUMN_SESSIONTYPE)))
                        .setDateTime(cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME)))
                        .build();
                Person.add(schedule);
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
