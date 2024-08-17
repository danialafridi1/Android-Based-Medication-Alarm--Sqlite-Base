package com.example.androidbasedmedicationalarm_reminderapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.androidbasedmedicationalarm_reminderapplication.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Medication_DB";
    private static DatabaseHelper instance;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(User.CREATE_TABLE);

            Log.d("databaseEvent","Created database");

        } catch (Exception e){
            Log.d("databaseEvent","Table not created\n"+e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            if (oldVersion < newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

                onCreate(db);
                Log.d("databaseEvent","Upgraded database");
            }
        } catch (Exception e){
            Log.d("databaseEvent","Table not Upgraded\n"+e.getMessage());

        }
    }
    public boolean isUserRegistered(String cnic) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(User.CHECK_CNIC_EXISTS_QUERY, new String[]{cnic});

        boolean isRegistered = false;

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isRegistered = true;
            }
            cursor.close();
        }
        db.close();

        return isRegistered;
    }

    public boolean registerUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, user.getName());
        values.put(User.COLUMN_AGE, user.getAge());
        values.put(User.COLUMN_CNIC_NUMBER, user.getCnicNumber());
        values.put(User.COLUMN_DISEASE_NAME, user.getDiseaseName());
        values.put(User.COLUMN_PASSWORD, user.getPassword());





        long newRowId = db.insert(User.TABLE_NAME, null, values);
        db.close();

        return newRowId != -1; // Returns true if the insertion was successful, false otherwise
    }
    public boolean loginUser(String cnic, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = { cnic, password };



        Cursor cursor = db.rawQuery(User.LOGIN_QUERY, selectionArgs);

        boolean isAuthenticated = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return isAuthenticated;
    }
}
