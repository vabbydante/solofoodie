package com.vaibhav.solofoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button pantrylogin;
    Button passengerlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pantrylogin = findViewById(R.id.btnPantryLogin);
        passengerlogin = findViewById(R.id.btnPassengerLogin);

        pantrylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PantryLoginActivity.class));
            }
        });

        passengerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PassengerLoginActivity.class));
            }
        });
    }
}