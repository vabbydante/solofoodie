package com.vaibhav.solofoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PantryRegistrationCheckActivity extends AppCompatActivity {

    EditText pass;
    Button btnAuthenticate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_registration_check);

        pass = findViewById(R.id.etAuthPass);
        btnAuthenticate = findViewById(R.id.btnAuth);

        btnAuthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals("vabby,98")){
                    startActivity(new Intent(PantryRegistrationCheckActivity.this, PantryRegistrationActivity.class));
                } else {
                    Toast.makeText(PantryRegistrationCheckActivity.this, "Wrong PIN !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}