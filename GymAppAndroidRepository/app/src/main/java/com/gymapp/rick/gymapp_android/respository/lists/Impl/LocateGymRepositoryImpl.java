package com.gymapp.rick.gymapp_android.respository.lists.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.domain.lists.LocateGym;
import com.gymapp.rick.gymapp_android.respository.lists.ILocateGymRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class LocateGymRepositoryImpl extends SQLiteOpenHelper implements ILocateGymRepository{
    public static final String TABLE_NAME = "gymLocation";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GYMTYPE = "gymType";
    public static final String COLUMN_AREA = "area";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_GYMTYPE + " TEXT  NOT NULL , "
            + COLUMN_AREA + " TEXT  NOT NULL);";

    public LocateGymRepositoryImpl(Context context)
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
    public LocateGym findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_GYMTYPE,
                        COLUMN_AREA},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final LocateGym locateGym = new LocateGym.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setGymType(cursor.getString(cursor.getColumnIndex(COLUMN_GYMTYPE)))
                    .setArea(cursor.getString(cursor.getColumnIndex(COLUMN_AREA)))
                    .build();
            return locateGym;
        } else {
            return null;
        }
    }

    @Override
    public LocateGym save(LocateGym entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_GYMTYPE, entity.getGymType());
        values.put(COLUMN_AREA, entity.getArea());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        LocateGym insertedEntity = new LocateGym.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public LocateGym update(LocateGym entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_GYMTYPE, entity.getGymType());
        values.put(COLUMN_AREA, entity.getArea());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public LocateGym delete(LocateGym entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<LocateGym> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<LocateGym> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final LocateGym userPassword = new LocateGym.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setGymType(cursor.getString(cursor.getColumnIndex(COLUMN_GYMTYPE)))
                        .setArea(cursor.getString(cursor.getColumnIndex(COLUMN_AREA)))
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
