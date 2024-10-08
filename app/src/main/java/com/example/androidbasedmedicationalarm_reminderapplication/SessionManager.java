package com.example.androidbasedmedicationalarm_reminderapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.HashMap;

public class SessionManager {

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private Context _context;

    private int Private_mode = 0;

    private static final String PREF_NAME = "User";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_PASSWORD = "password";


    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Private_mode);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String password){
        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_EMAIL, email);

        editor.putString(KEY_PASSWORD, password);

        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, Login.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }
    }

    public HashMap<String , String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put("", pref.getString(KEY_EMAIL,null));

        //user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD,null));

        return user;
    }

    public String getEmail(){
        return pref.getString(KEY_EMAIL,null);
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }
}