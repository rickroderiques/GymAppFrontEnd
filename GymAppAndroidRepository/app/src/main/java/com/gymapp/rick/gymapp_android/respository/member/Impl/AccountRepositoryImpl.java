package com.gymapp.rick.gymapp_android.respository.member.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gymapp.rick.gymapp_android.conf.databases.DBConstants;
import com.gymapp.rick.gymapp_android.domain.member.Account;
import com.gymapp.rick.gymapp_android.respository.member.IAccountRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rick on 25-Apr-16.
 */
public class AccountRepositoryImpl extends SQLiteOpenHelper implements IAccountRepository{
    public static final String TABLE_NAME = "account";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEMBERSHIPNUMBER = "membershipNumber";
    public static final String COLUMN_PAYMENTDUE = "paymentDue";
    public static final String COLUMN_LASTPAYMENT = "lastPayment";
    public static final String COLUMN_BALANCE = "balance";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MEMBERSHIPNUMBER + " TEXT  NOT NULL , "
            + COLUMN_PAYMENTDUE + " TEXT NOT NULL , "
            + COLUMN_LASTPAYMENT + " TEXT  NOT NULL , "
            + COLUMN_BALANCE + " TEXT NOT NULL );";

    public AccountRepositoryImpl(Context context)
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
    public Account findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_MEMBERSHIPNUMBER,
                        COLUMN_PAYMENTDUE,
                        COLUMN_LASTPAYMENT,
                        COLUMN_BALANCE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Account account = new Account.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .setMembershipNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERSHIPNUMBER)))
                    .setPaymentDue(cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENTDUE)))
                    .setLastPayment(cursor.getString(cursor.getColumnIndex(COLUMN_LASTPAYMENT)))
                    .setBalance(cursor.getString(cursor.getColumnIndex(COLUMN_BALANCE)))
                    .build();
            return account;
        } else {
            return null;
        }
    }

    @Override
    public Account save(Account entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MEMBERSHIPNUMBER, entity.getMembershipNumber());
        values.put(COLUMN_PAYMENTDUE, entity.getPaymentDue());
        values.put(COLUMN_LASTPAYMENT, entity.getLastPayment());
        values.put(COLUMN_BALANCE, entity.getBalance());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Account insertedEntity = new Account.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Account update(Account entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MEMBERSHIPNUMBER, entity.getMembershipNumber());
        values.put(COLUMN_PAYMENTDUE, entity.getPaymentDue());
        values.put(COLUMN_LASTPAYMENT, entity.getLastPayment());
        values.put(COLUMN_BALANCE, entity.getBalance());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Account delete(Account entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Account> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Account> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Account account = new Account.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .setMembershipNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERSHIPNUMBER)))
                        .setPaymentDue(cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENTDUE)))
                        .setLastPayment(cursor.getString(cursor.getColumnIndex(COLUMN_LASTPAYMENT)))
                        .setBalance(cursor.getString(cursor.getColumnIndex(COLUMN_BALANCE)))
                        .build();
                Person.add(account);
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
