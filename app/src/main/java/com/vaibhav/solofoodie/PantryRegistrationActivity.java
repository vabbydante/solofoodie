package com.vaibhav.solofoodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PantryRegistrationActivity extends AppCompatActivity {

    EditText newemail;
    EditText newpass;
    Button pantryregistration;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_registration);

        mAuth = FirebaseAuth.getInstance();

        newemail = findViewById(R.id.etPantryManEmailReg);
        newpass = findViewById(R.id.etPantryManPassReg);
        pantryregistration = findViewById(R.id.btnRegNewPantry);

        pantryregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPantryManager();
            }
        });
    }

    private void createPantryManager() {
        String email = newemail.getText().toString();
        String password = newpass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            newemail.setError("Email Cannot be Blank!");
            newemail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            newpass.setError("Password Cannot be Blank!");
            newpass.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(PantryRegistrationActivity.this, "Pantry Manager Registered Successfully!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(PantryRegistrationActivity.this, PantryLoginActivity.class));
                        finish();

                    } else {
                        Toast.makeText(PantryRegistrationActivity.this, "Something went wrong..." + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}