package com.example.androidbasedmedicationalarm_reminderapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.androidbasedmedicationalarm_reminderapplication.Database.DatabaseHelper;
import com.example.androidbasedmedicationalarm_reminderapplication.model.User;

public class Register extends AppCompatActivity {
    private EditText etName, etAge, etCnicNumber, etDiseaseName, etPassword;
    private Button btnRegister, btnLogin;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etCnicNumber = findViewById(R.id.etCnicNumber);
        etDiseaseName = findViewById(R.id.etDiseaseName);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        databaseHelper= DatabaseHelper.getInstance(getApplicationContext());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   String name = etName.getText().toString().trim();
                   String ageString = etAge.getText().toString().trim();
                   String cnicNumber = etCnicNumber.getText().toString().trim();
                   String diseaseName = etDiseaseName.getText().toString().trim();
                   String password = etPassword.getText().toString().trim();
                   if (TextUtils.isEmpty(name)) {
                       etName.setError("Name is required");
                       return;
                   }

                   if (TextUtils.isEmpty(ageString)) {
                       etAge.setError("Age is required");
                       return;
                   }

                   if (TextUtils.isEmpty(cnicNumber)) {
                       etCnicNumber.setError("CNIC Number is required");
                       return;
                   }

                   if (TextUtils.isEmpty(diseaseName)) {
                       etDiseaseName.setError("Disease Name is required");
                       return;
                   }

                   if (TextUtils.isEmpty(password)) {
                       etPassword.setError("Password is required");
                       return;
                   }

                   // Check if age is a valid integer
                   int age;
                   try {
                       age = Integer.parseInt(ageString);
                   } catch (NumberFormatException e) {
                       etAge.setError("Invalid age format");
                       return;
                   }

                   User user= new User(name,age,cnicNumber,diseaseName,password);
                   if(databaseHelper.isUserRegistered(cnicNumber)){
                       Toast.makeText(Register.this, "This User is already registered. Please use a different Cnic Number.", Toast.LENGTH_SHORT).show();

                   } else {
                       boolean result = databaseHelper.registerUser(user);
                       if(result){
                           Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent( Register.this, Login.class));
                           finish();
                       } else {
                           Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               } catch (Exception e){
                   Toast.makeText(Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}