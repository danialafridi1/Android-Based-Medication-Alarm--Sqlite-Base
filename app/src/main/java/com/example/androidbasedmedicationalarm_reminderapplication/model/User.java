package com.example.androidbasedmedicationalarm_reminderapplication.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User {

    // Column names
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_CNIC_NUMBER = "cnic_number";
    public static final String COLUMN_DISEASE_NAME = "disease_name";
    public static final String COLUMN_PASSWORD = "password";
    // SQLite table name
    public static final String TABLE_NAME = "users";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + User.TABLE_NAME + " (" +
                    User.COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    User.COLUMN_NAME + " TEXT, " +
                    User.COLUMN_AGE + " INTEGER, " +
                    User.COLUMN_CNIC_NUMBER + " TEXT, " +
                    User.COLUMN_DISEASE_NAME + " TEXT, " +
                    User.COLUMN_PASSWORD + " TEXT" +
                    ")";
    public static final String LOGIN_QUERY =
            "SELECT * FROM " + User.TABLE_NAME +
                    " WHERE " + User.COLUMN_CNIC_NUMBER + " = ? AND " +
                    User.COLUMN_PASSWORD + " = ?";
    public static final String CHECK_CNIC_EXISTS_QUERY =
            "SELECT 1 FROM " + User.TABLE_NAME +
                    " WHERE " + User.COLUMN_CNIC_NUMBER + " = ?";


    // Fields
    private int uid;
    private String name;
    private int age;
    private String cnicNumber;
    private String diseaseName;
    private String password;

    // Constructor
    public User(int uid, String name, int age, String cnicNumber, String diseaseName, String password) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.cnicNumber = cnicNumber;
        this.diseaseName = diseaseName;
        this.password = password;
    }

    public User() {
    }

    public User(@NonNull String name, int age, String cnicNumber, String diseaseName, String password) {
        this.name = name;
        this.age = age;
        this.cnicNumber = cnicNumber;
        this.diseaseName = diseaseName;
        this.password = password;
    }

    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.cnicNumber = cnicNumber;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cnicNumber='" + cnicNumber + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
