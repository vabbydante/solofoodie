package com.vaibhav.solofoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PantryBusinessActivity extends AppCompatActivity {

    Button logout;
    Button availablefood;
    Button orderlist;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_business);

        mAuth = FirebaseAuth.getInstance();

        logout = findViewById(R.id.btnLogoutPantry);
        availablefood = findViewById(R.id.btnAvailCatalog);
        orderlist = findViewById(R.id.btnOrderList);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PantryBusinessActivity.this, LoginActivity.class));
                finish();
            }
        });

        availablefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PantryBusinessActivity.this, AvailabilityCatalogActivity.class));
            }
        });

        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PantryBusinessActivity.this, OrderListActivity.class));
            }
        });
    }
}