package com.gymapp.rick.gymapp_android.respository.member.Impl;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.respository.member.ITypeRepository;
import com.gymapp.rick.gymapp_android.domain.member.Type;
/**
 * Created by Rick on 23-Apr-16.
 */
public class TypeRepositoryImpl extends SQLiteOpenHelper implements ITypeRepository{
    public static final String TABLE_NAME = "memberType";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEMBERNUMBER = "membershipNumber";
    public static final String COLUMN_MEMBERTYPE = "membershipType";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MEMBERNUMBER + " TEXT  NOT NULL , "
            + COLUMN_MEMBERTYPE + " TEXT  NOT NULL);";

    public TypeRepositoryImpl(Context context)
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
    public Type findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_MEMBERNUMBER,
                        COLUMN_MEMBERTYPE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Type type = new Type.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .membershipNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERNUMBER)))
                    .membershipType(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERTYPE)))
                    .build();
            return type;
        } else {
            return null;
        }
    }

    @Override
    public Type save(Type entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MEMBERNUMBER, entity.getMembershipNumber());
        values.put(COLUMN_MEMBERTYPE, entity.getMembershipType());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Type insertedEntity = new Type.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Type update(Type entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MEMBERNUMBER, entity.getMembershipNumber());
        values.put(COLUMN_MEMBERTYPE, entity.getMembershipType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Type delete(Type entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Type> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Type> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Type type = new Type.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .membershipNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERNUMBER)))
                        .membershipType(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERTYPE)))
                        .build();
                Person.add(type);
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
