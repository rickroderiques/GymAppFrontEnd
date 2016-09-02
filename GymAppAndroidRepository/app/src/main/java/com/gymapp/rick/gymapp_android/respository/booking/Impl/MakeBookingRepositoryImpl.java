package com.gymapp.rick.gymapp_android.respository.booking.Impl;

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
import com.gymapp.rick.gymapp_android.respository.booking.IMakeBookingRepository;
import com.gymapp.rick.gymapp_android.domain.booking.MakeBooking;

/**
 * Created by Rick on 24-Apr-16.
 */
public class MakeBookingRepositoryImpl extends SQLiteOpenHelper implements IMakeBookingRepository {
        public static final String TABLE_NAME = "makeBooking";
        private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BOOKINGNAME = "bookingName";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIMESLOT = "timeslot";
    public static final String COLUMN_INSTRUCTOR = "instructor";
    public static final String COLUMN_GYMLOCATION = "gymLocation";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BOOKINGNAME + " TEXT NOT NULL , "
            + COLUMN_DATE + " TEXT NOT NULL , "
            + COLUMN_TIMESLOT + " TEXT  NOT NULL , "
            + COLUMN_INSTRUCTOR + " TEXT  NOT NULL , "
            + COLUMN_GYMLOCATION + " TEXT NOT NULL );";

    public MakeBookingRepositoryImpl(Context context)
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
    public MakeBooking findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_BOOKINGNAME,
                        COLUMN_DATE,
                        COLUMN_TIMESLOT,
                        COLUMN_INSTRUCTOR,
                        COLUMN_GYMLOCATION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final MakeBooking makeBooking = new MakeBooking.Builder()
                    .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setBookingName(cursor.getString(cursor.getColumnIndex(COLUMN_BOOKINGNAME)))
                    .setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .setTimeSlot(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESLOT)))
                    .setInstructor(cursor.getString(cursor.getColumnIndex(COLUMN_INSTRUCTOR)))
                    .setGymLocation(cursor.getString(cursor.getColumnIndex(COLUMN_GYMLOCATION)))
                    .build();
            return makeBooking;
        } else {
            return null;
        }
    }

    @Override
    public MakeBooking save(MakeBooking entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BOOKINGNAME, entity.getBookingName());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_TIMESLOT, entity.getTimeSlot());
        values.put(COLUMN_INSTRUCTOR, entity.getInstructor());
        values.put(COLUMN_GYMLOCATION, entity.getGymLocation());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        MakeBooking insertedEntity = new MakeBooking.Builder()
                .copy(entity)
                .setId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public MakeBooking update(MakeBooking entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BOOKINGNAME, entity.getBookingName());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_TIMESLOT, entity.getTimeSlot());
        values.put(COLUMN_INSTRUCTOR, entity.getInstructor());
        values.put(COLUMN_GYMLOCATION, entity.getGymLocation());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public MakeBooking delete(MakeBooking entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<MakeBooking> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<MakeBooking> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final MakeBooking makeBooking = new MakeBooking.Builder()
                        .setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setBookingName(cursor.getString(cursor.getColumnIndex(COLUMN_BOOKINGNAME)))
                        .setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .setTimeSlot(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESLOT)))
                        .setInstructor(cursor.getString(cursor.getColumnIndex(COLUMN_INSTRUCTOR)))
                        .setGymLocation(cursor.getString(cursor.getColumnIndex(COLUMN_GYMLOCATION)))
                        .build();
                Person.add(makeBooking);
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
