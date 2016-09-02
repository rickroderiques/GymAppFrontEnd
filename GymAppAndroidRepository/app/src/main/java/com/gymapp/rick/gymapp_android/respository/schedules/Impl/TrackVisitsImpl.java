package com.gymapp.rick.gymapp_android.respository.schedules.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.domain.schedules.TrackVisits;
import com.gymapp.rick.gymapp_android.respository.schedules.ITrackVisitsRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class TrackVisitsImpl  extends SQLiteOpenHelper implements ITrackVisitsRepository{
    public static final String TABLE_NAME = "trackVisits";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATETIME = "dateTimeOfVisit";
    public static final String COLUMN_GYMVISTED = "gymVisisted";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATETIME + " TEXT  NOT NULL , "
            + COLUMN_GYMVISTED + " TEXT  NOT NULL);";

    public TrackVisitsImpl(Context context)
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
    public TrackVisits findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DATETIME,
                        COLUMN_GYMVISTED},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final TrackVisits trackVisits = new TrackVisits.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setDateTimeOfVisit(cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME)))
                    .setGymVisisted(cursor.getString(cursor.getColumnIndex(COLUMN_GYMVISTED)))
                    .build();
            return trackVisits;
        } else {
            return null;
        }
    }

    @Override
    public TrackVisits save(TrackVisits entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_DATETIME, entity.getDateTimeOfVisit());
        values.put(COLUMN_GYMVISTED, entity.getGymVisisted());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        TrackVisits insertedEntity = new TrackVisits.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public TrackVisits update(TrackVisits entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_DATETIME, entity.getDateTimeOfVisit());
        values.put(COLUMN_GYMVISTED, entity.getGymVisisted());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public TrackVisits delete(TrackVisits entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<TrackVisits> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<TrackVisits> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final TrackVisits trackVisits = new TrackVisits.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setDateTimeOfVisit(cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME)))
                        .setGymVisisted(cursor.getString(cursor.getColumnIndex(COLUMN_GYMVISTED)))
                        .build();
                Person.add(trackVisits);
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
