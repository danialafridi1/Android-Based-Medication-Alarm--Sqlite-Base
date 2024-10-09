package com.example.androidbasedmedicationalarm_reminderapplication.model;

import java.sql.Time;
import java.util.Date;

public class Medicine {
    private  int id;
    private  int patient_id;
    private String patientName;
    private String diseaseName;
    private String medicineName;
    private String  medicineType; // enum for dropdown menu
    private String dose;
    private String date;
    private String time;
    private String prescriptionPlan;

    // SQLite column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PATIENT_ID = "patient_id";
    public static final String COLUMN_PATIENT_NAME = "patient_name";
    public static final String COLUMN_DISEASE_NAME = "disease_name";
    public static final String COLUMN_MEDICINE_NAME = "medicine_name";
    public static final String COLUMN_MEDICINE_TYPE = "medicine_type"; // enum stored as text
    public static final String COLUMN_DOSE = "dose";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_PRESCRIPTION_PLAN = "prescription_plan";

    // SQLite table name
    public static final String TABLE_NAME = "medicines";

    // SQL statement for creating the table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PATIENT_ID + " INTEGER, " +
                    COLUMN_PATIENT_NAME + " TEXT, " +
                    COLUMN_DISEASE_NAME + " TEXT, " +
                    COLUMN_MEDICINE_NAME + " TEXT, " +
                    COLUMN_MEDICINE_TYPE + " TEXT, " + // Enum stored as text
                    COLUMN_DOSE + " TEXT, " + // Double data type for dose
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_TIME + " TEXT, " +
                    COLUMN_PRESCRIPTION_PLAN + " TEXT" +
                    ")";

    // SQL query to select prescription by patient_id
    public static final String SELECT_BY_PATIENT_ID =
            "SELECT * FROM " + TABLE_NAME +
                    " WHERE " + COLUMN_PATIENT_ID + " = ?";

    // SQL query to check if a prescription exists for a specific date
    public static final String CHECK_PRESCRIPTION_EXISTS =
            "SELECT 1 FROM " + TABLE_NAME +
                    " WHERE " + COLUMN_PATIENT_ID + " = ? AND " +
                    COLUMN_DATE + " = ?";




    public Medicine() {
    }

    public Medicine(int patient_id, String patientName, String diseaseName, String medicineName,
                    String  medicineType, String  dose, String date, String time, String prescriptionPlan) {
        this.patient_id = patient_id;
        this.patientName = patientName;
        this.diseaseName = diseaseName;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.dose = dose;
        this.date = date;
        this.time = time;
        this.prescriptionPlan = prescriptionPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    // getters and setters
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrescriptionPlan() {
        return prescriptionPlan;
    }

    public void setPrescriptionPlan(String prescriptionPlan) {
        this.prescriptionPlan = prescriptionPlan;
    }

    // enum for dropdown menu
    public enum MedicineType {
        TAB, CAP, INJECTION
    }
}