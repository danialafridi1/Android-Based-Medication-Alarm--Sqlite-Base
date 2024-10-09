package com.example.androidbasedmedicationalarm_reminderapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.androidbasedmedicationalarm_reminderapplication.Database.DatabaseHelper;
import com.example.androidbasedmedicationalarm_reminderapplication.model.Medicine;
import com.example.androidbasedmedicationalarm_reminderapplication.model.User;

public class AddMedicineActivity extends AppCompatActivity {
    private EditText etPatientName;
    private EditText etDiseaseName;
    private EditText etMedicineName;
    private Spinner spinnerMedicineType;
    private EditText etDose;
    private EditText etDate;
    private EditText etTime;
    private EditText etPrescriptionPlan;
    private Button btnSubmit;
    private Button btnCancel;
    SessionManager sessionManager;
    DatabaseHelper databaseHelper;
    int patientID;
    String  CNIC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        etPatientName = findViewById(R.id.etPatientName);
        etDiseaseName = findViewById(R.id.etDiseaseName);
        etMedicineName = findViewById(R.id.etMedicineName);
        spinnerMedicineType = findViewById(R.id.spinnerMedicineType);
        etDose = findViewById(R.id.etDose);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etPrescriptionPlan = findViewById(R.id.etPrescriptionPlan);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        databaseHelper= new DatabaseHelper(this);
        sessionManager = new SessionManager(getApplicationContext());
        CNIC= sessionManager.getEmail();
        User user= databaseHelper.getUserInfoByCnic(CNIC);
        if(user!=null){
            patientID = user.getUid();
            etPatientName.setText(user.getName());
            etDiseaseName.setText(user.getDiseaseName());
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    String patientName = etPatientName.getText().toString().trim();
                    String diseaseName = etDiseaseName.getText().toString().trim();
                    String medicineName = etMedicineName.getText().toString().trim();
                    String medicineType = spinnerMedicineType.getSelectedItem().toString(); // Assuming spinner contains string values
                    String dose = etDose.getText().toString().trim();
                    String date = etDate.getText().toString().trim();
                    String time = etTime.getText().toString().trim();
                    String prescriptionPlan = etPrescriptionPlan.getText().toString().trim();
                    Medicine medicine= new Medicine(patientID,patientName,diseaseName,medicineName,medicineType,dose,date,time,prescriptionPlan);
                    boolean result = databaseHelper.addMedicine(medicine);
if(result){
    Toast.makeText(AddMedicineActivity.this, "Medicine Added", Toast.LENGTH_SHORT).show();

startActivity(new Intent(AddMedicineActivity.this, Dashboard.class));
} else {
    Toast.makeText(AddMedicineActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

}

                }
            }
        });
    }
    private boolean validateInputs() {
        if (TextUtils.isEmpty(etPatientName.getText().toString().trim())) {
            etPatientName.setError("Patient Name is required");
            return false;
        }

        if (TextUtils.isEmpty(etDiseaseName.getText().toString().trim())) {
            etDiseaseName.setError("Disease Name is required");
            return false;
        }

        if (TextUtils.isEmpty(etMedicineName.getText().toString().trim())) {
            etMedicineName.setError("Medicine Name is required");
            return false;
        }

        if (TextUtils.isEmpty(etDose.getText().toString().trim())) {
            etDose.setError("Dose is required");
            return false;
        }

        if (TextUtils.isEmpty(etDate.getText().toString().trim())) {
            etDate.setError("Date is required");
            return false;
        }

        if (TextUtils.isEmpty(etTime.getText().toString().trim())) {
            etTime.setError("Time is required");
            return false;
        }

        if (TextUtils.isEmpty(etPrescriptionPlan.getText().toString().trim())) {
            etPrescriptionPlan.setError("Prescription Plan is required");
            return false;
        }

        // All validations passed
        return true;
    }
}