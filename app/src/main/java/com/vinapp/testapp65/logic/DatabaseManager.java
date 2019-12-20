package com.vinapp.testapp65.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.vinapp.testapp65.logic.data.Worker;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "testapp65_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORKERS = "WORKERS";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_LAST_NAME = "LAST_NAME";
    private static final String COLUMN_BIRTHDAY = "BIRTHDAY";
    private static final String COLUMN_AGE = "AGE";
    private static final String COLUMN_SPECIALTY_ID = "SPECIALTY_ID";
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
                + COLUMN_SPECIALTY_ID + " INTEGER," + COLUMN_SPECIALTY_NAME + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKERS);
        onCreate(db);
    }

    public void addWorker(Worker worker) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, worker.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, worker.getLastName());
        contentValues.put(COLUMN_BIRTHDAY, worker.getBirthday());
        contentValues.put(COLUMN_SPECIALTY_NAME, worker.getSpecialty());
        contentValues.put(COLUMN_AGE, worker.getAge());
        db.insert(TABLE_WORKERS, null, contentValues);
        db.close();
    }

    public void getAllWorkers() {
        Log.e("-------", "1");
        ArrayList<Worker> workers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_BIRTHDAY, COLUMN_SPECIALTY_NAME, COLUMN_AGE};
        Cursor cursor = db.query(TABLE_WORKERS, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            Log.e("-------", "2");
            do {
                Log.e("-------", "3");
                Worker worker = new Worker();
                worker.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                worker.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));

                Log.e("-----------", worker.getFirstName());
            } while (cursor.moveToNext());
        }
    }

    public void addSepcialty() {

    }

    public void getSpecialty() {

    }
}
