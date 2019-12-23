package com.vinapp.testapp65.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.logic.data.Worker;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "testapp65_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORKERS = "WORKERS";
    private static final String TABLE_SPECIALTIES = "SPECIALTIES";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_LAST_NAME = "LAST_NAME";
    private static final String COLUMN_BIRTHDAY = "BIRTHDAY";
    private static final String COLUMN_AGE = "AGE";
    private static final String COLUMN_SPECIALTY_NAME = "SPECIALTY_NAME";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table of workers
        db.execSQL("CREATE TABLE " + TABLE_WORKERS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRST_NAME + " TEXT," + COLUMN_LAST_NAME + " TEXT,"
                + COLUMN_BIRTHDAY + " NUMERIC," + COLUMN_AGE + " INTEGER,"
                + COLUMN_SPECIALTY_NAME + " TEXT)");
        //Create table of specialties
        db.execSQL("CREATE TABLE " + TABLE_SPECIALTIES + "(" + COLUMN_ID
                + " INTEGER," + COLUMN_SPECIALTY_NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIALTIES);
        onCreate(db);
    }

    public void addWorker(Worker worker) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WORKERS
                + " WHERE " + COLUMN_FIRST_NAME + " = '" + worker.getFirstName()
                + "' AND " + COLUMN_LAST_NAME + " = '" + worker.getLastName()
                + "' AND " + COLUMN_BIRTHDAY + " = '" + worker.getBirthday()
                + "' AND " + COLUMN_SPECIALTY_NAME + " = '" + worker.getSpecialty().getName() + "'", null);
        Log.e("LOG", cursor.getCount() + " ");
        if (cursor.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_FIRST_NAME, worker.getFirstName());
            contentValues.put(COLUMN_LAST_NAME, worker.getLastName());
            contentValues.put(COLUMN_BIRTHDAY, worker.getBirthday());
            contentValues.put(COLUMN_SPECIALTY_NAME, worker.getSpecialty().getName());
            contentValues.put(COLUMN_AGE, worker.getAge());
            db.insert(TABLE_WORKERS, null, contentValues);
        }
        db.close();
    }

    public ArrayList<Worker> getAllWorkers() {
        ArrayList<Worker> workers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_BIRTHDAY, COLUMN_SPECIALTY_NAME, COLUMN_AGE};
        Cursor cursor = db.query(TABLE_WORKERS, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Worker worker = new Worker();
                worker.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                worker.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
                worker.setBirthday(cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDAY)));
                Specialty specialty = new Specialty();
                specialty.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                specialty.setName(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALTY_NAME)));
                worker.setSpecialty(specialty);
                workers.add(worker);
            } while (cursor.moveToNext());
        }
        for (int i = 0; i < workers.size(); i++) {
            Log.e("DB_WORKERS", workers.get(i).getFirstName() + " " + workers.get(i).getLastName() + " " + workers.get(i).getBirthday() + " " + workers.get(i).getSpecialty().getName());
        }
        cursor.close();
        return workers;
    }

    public void addSpecialty(Specialty specialty) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SPECIALTIES
                + " WHERE " + COLUMN_ID + " = " + specialty.getId()
                + " AND " + COLUMN_SPECIALTY_NAME + " = '" + specialty.getName() + "'", null);
        if (cursor.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ID, specialty.getId());
            contentValues.put(COLUMN_SPECIALTY_NAME, specialty.getName());
            db.insert(TABLE_SPECIALTIES, null, contentValues);
        }
        db.close();
    }

    public ArrayList<Specialty> getAllSpecialties() {
        ArrayList<Specialty> specialties = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_SPECIALTY_NAME};
        Cursor cursor = db.query(TABLE_SPECIALTIES, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Specialty specialty = new Specialty();
                specialty.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                specialty.setName(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALTY_NAME)));
                specialties.add(specialty);
            } while (cursor.moveToNext());
        }
        for (int i = 0; i < specialties.size(); i++) {
            Log.e("DB_SPECIALTIES", specialties.get(i).getId() + " " + specialties.get(i).getName());
        }
        cursor.close();
        return specialties;
    }
}
