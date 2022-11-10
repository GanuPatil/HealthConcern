package com.mca.healtyconsernapp;

import static android.R.layout.*;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Login<dataAdapter, simple_spinner_dropdown_item> extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView btnsignup;

    EditText txtinputname, txtlpass;
    Button btnlogin;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    Spinner logintype;
    List<String> type=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignup = findViewById(R.id.txtsignup);
        txtinputname = findViewById(R.id.txtloginemail);
        txtlpass = findViewById(R.id.txtlpass);
        logintype = findViewById(R.id.logintype);

        btnlogin = findViewById(R.id.btnlogin);

        type.add("Doctor");
        type.add("Patient");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        logintype.setAdapter(dataAdapter);
        logintype.setOnItemSelectedListener(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(Login.this);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, register.class));
            }
        });
    }



//        Toast.makeText(getApplicationContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

    private void checkCredentials() {

            String username = txtinputname.getText().toString();
            String pass = txtlpass.getText().toString();

            if (username.isEmpty() || username.length() < 7) {
                showError(txtinputname, "Your username is not valid!");
            } else if (username.isEmpty() || !username.contains("@")) {
                showError(txtinputname, "Email is not valid");
            } else if (pass.isEmpty() || pass.length() < 8) {
                showError(txtlpass, "Password must be 8 Character");
            } else
            {
                // logic for realtimedb
                mLoadingBar.setTitle("Login");
                mLoadingBar.setMessage("Please Wait, While Check Your Credentials");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();

                mAuth.signInWithEmailAndPassword(username, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //user the else dr part
                            Toast.makeText(Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            mLoadingBar.dismiss();
                            Intent intent =new Intent(Login.this,Activity_patient_home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                    }
                });
            }

        }

        private void showError(EditText input, String s) {
            input.setError(s);
            input.requestFocus();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
