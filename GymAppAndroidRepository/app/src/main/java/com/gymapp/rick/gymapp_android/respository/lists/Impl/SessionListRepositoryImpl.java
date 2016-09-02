package com.gymapp.rick.gymapp_android.respository.lists.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.domain.lists.SessionList;
import com.gymapp.rick.gymapp_android.respository.lists.ISessionTypeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class SessionListRepositoryImpl extends SQLiteOpenHelper implements ISessionTypeRepository{
    public static final String TABLE_NAME = "sessionType";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SESSIONAME = "name";
    public static final String COLUMN_SESSIONTYPE = "type";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SESSIONAME + " TEXT  NOT NULL , "
            + COLUMN_SESSIONTYPE + " TEXT  NOT NULL);";

    public SessionListRepositoryImpl(Context context)
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
    public SessionList findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SESSIONAME,
                        COLUMN_SESSIONTYPE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final SessionList sessionList = new SessionList.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setSessionName(cursor.getString(cursor.getColumnIndex(COLUMN_SESSIONAME)))
                    .setSessionType(cursor.getString(cursor.getColumnIndex(COLUMN_SESSIONTYPE)))
                    .build();
            return sessionList;
        } else {
            return null;
        }
    }

    @Override
    public SessionList save(SessionList entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SESSIONAME, entity.getSessionName());
        values.put(COLUMN_SESSIONTYPE, entity.getSessionType());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        SessionList insertedEntity = new SessionList.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public SessionList update(SessionList entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SESSIONAME, entity.getSessionName());
        values.put(COLUMN_SESSIONTYPE, entity.getSessionType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public SessionList delete(SessionList entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<SessionList> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<SessionList> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final SessionList userPassword = new SessionList.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setSessionName(cursor.getString(cursor.getColumnIndex(COLUMN_SESSIONAME)))
                        .setSessionType(cursor.getString(cursor.getColumnIndex(COLUMN_SESSIONTYPE)))
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
