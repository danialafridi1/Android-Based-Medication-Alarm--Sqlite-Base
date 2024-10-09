package com.example.androidbasedmedicationalarm_reminderapplication.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.androidbasedmedicationalarm_reminderapplication.model.Medicine;
import com.example.androidbasedmedicationalarm_reminderapplication.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
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
            db.execSQL(Medicine.CREATE_TABLE);


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
                db.execSQL("DROP TABLE IF EXISTS " + Medicine.TABLE_NAME);

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

    public User getUserInfoByCnic(String cnic) {
        User user = null; 
        SQLiteDatabase db = this.getReadableDatabase(); 
        Cursor cursor = null;
        try {
            // Query to select user info by CNIC
            String query = "SELECT * FROM users WHERE cnic_number = ?";
            cursor = db.rawQuery(query, new String[]{cnic}); 

            if (cursor != null && cursor.moveToFirst()) { 
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(User.COLUMN_UID)); 
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)); 
                @SuppressLint("Range") String diseaseName = cursor.getString(cursor.getColumnIndex(User.COLUMN_DISEASE_NAME));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(User.COLUMN_AGE));

                // Create a User object with the retrieved data
                user = new User(id, name, cnic, age,diseaseName);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (cursor != null) {
                cursor.close(); 
            }
            db.close(); 
        }

        return user;
    }

    public boolean addMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable database
        ContentValues values = new ContentValues();

        // Populate ContentValues with medicine data
        values.put(Medicine.COLUMN_PATIENT_ID, medicine.getPatient_id());
        values.put(Medicine.COLUMN_PATIENT_NAME, medicine.getPatientName());
        values.put(Medicine.COLUMN_DISEASE_NAME, medicine.getDiseaseName());
        values.put(Medicine.COLUMN_MEDICINE_NAME, medicine.getMedicineName());
        values.put(Medicine.COLUMN_MEDICINE_TYPE, medicine.getMedicineType()); // Assuming enum can be converted to String
        values.put(Medicine.COLUMN_DOSE, medicine.getDose());
        values.put(Medicine.COLUMN_DATE, medicine.getDate()); // Convert Date to String format
        values.put(Medicine.COLUMN_TIME, medicine.getTime()); // Convert Time to String format
        values.put(Medicine.COLUMN_PRESCRIPTION_PLAN, medicine.getPrescriptionPlan());

        long newRowId = db.insert(Medicine.TABLE_NAME, null, values); // Insert into 'medicines' table
        db.close(); // Close the database connection

        return newRowId != -1; // Return true if insertion is successful, false otherwise
    }

}
