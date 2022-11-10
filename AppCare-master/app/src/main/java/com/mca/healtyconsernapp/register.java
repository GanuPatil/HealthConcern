package com.mca.healtyconsernapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class register extends AppCompatActivity {
    private EditText txtinputname,txtemail,txtpass,txtconfpass;
    Button btnregister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    Spinner CombinLog;
    List<String> type=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtinputname = findViewById(R.id.txtusername);
        txtemail = findViewById(R.id.txtlemail);
        txtpass = findViewById(R.id.txtpass);
        txtconfpass = findViewById(R.id.txtconfpass);
        CombinLog = findViewById(R.id.CombinLog);
        btnregister = findViewById(R.id.btnregister);
        type.add("Doctor");
        type.add("Patient");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CombinLog.setAdapter(dataAdapter);
        /*CombinLog.setOnItemSelectedListener(this);*/
        CombinLog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), "Hello Users Welcome", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        /*CombinLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });*/
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(register.this);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });
    }

        private void checkCredentials()
    {

            String username = txtinputname.getText().toString();
            String email = txtemail.getText().toString();
            String pass = txtpass.getText().toString();
            String confirmpass = txtconfpass.getText().toString();
            if (username.isEmpty() || username.length()<7)
            {
                showError(txtinputname, "Your username is not valid!");
            }
            else if (email.isEmpty() || !email.contains("@"))
            {
                showError(txtemail, "Email is not valid");
            }
            else if (pass.isEmpty() || pass.length()<8)
            {
                showError(txtpass, "Password must be 8 Character");
            }
            else if (confirmpass.isEmpty() || !confirmpass.equals(pass)) {
                showError(txtconfpass, "Password not matched");
            }


        else
                {
                    mLoadingBar.setTitle("Registration");
                    mLoadingBar.setMessage("Please Wait, While Check Your Credentials");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                // same condition as login page diffend ke liye he
                                Toast.makeText(register.this, "Successfully Registration", Toast.LENGTH_SHORT).show();

                                mLoadingBar.dismiss();

                                Intent intent =new Intent(register.this,Activity_patient_home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(register.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
}

        private void showError(EditText input, String s) {
            input.setError(s);
            input.requestFocus();

        }
    }