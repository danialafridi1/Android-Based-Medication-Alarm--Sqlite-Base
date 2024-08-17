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

public class Login extends AppCompatActivity {
    private EditText etCnicNumberLogin;
    private EditText etPasswordLogin;
    private Button btnLoginUser;
    private Button btnRegisterUser;
    DatabaseHelper databaseHelper;
SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etCnicNumberLogin = findViewById(R.id.etCnicNumber_login);
        etPasswordLogin = findViewById(R.id.etPassword_login);
        btnLoginUser = findViewById(R.id.btnLogin_user);
        btnRegisterUser = findViewById(R.id.btnRegister_user);
sessionManager = new SessionManager(getApplicationContext());
        databaseHelper= DatabaseHelper.getInstance(getApplicationContext());
        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cnicNumber = etCnicNumberLogin.getText().toString().trim();
                String password = etPasswordLogin.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(cnicNumber)) {
                    etCnicNumberLogin.setError("CNIC Number is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPasswordLogin.setError("Password is required");
                    return;
                }
                boolean result = databaseHelper.loginUser(cnicNumber,password);
                if(result){
                    sessionManager.createLoginSession(cnicNumber,password);
                    sessionManager.setEmail(cnicNumber);

                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Dashboard.class));
                } else {

                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}