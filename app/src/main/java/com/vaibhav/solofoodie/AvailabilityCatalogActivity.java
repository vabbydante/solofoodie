package com.vaibhav.solofoodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AvailabilityCatalogActivity extends AppCompatActivity {

    CheckBox vegthali, nonvegthali, boiledeggs, chickenextra, veggravy, nonveggravy, icecream, teakits, tissue,
            snacks, waterbottle, coffeekits, hotwater, breads;
    Button updatecatalog;
    TextView heading;
    SharedPreferences sPref;
    String tno;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_catalog);

        vegthali = findViewById(R.id.cbvegthali);
        nonvegthali = findViewById(R.id.cbnonvegthali);
        boiledeggs = findViewById(R.id.cbboiledeggs);
        chickenextra = findViewById(R.id.cbchickenextra);
        veggravy = findViewById(R.id.cbveggravy);
        nonveggravy = findViewById(R.id.cbnonveggravy);
        icecream = findViewById(R.id.cbicecream);
        teakits = findViewById(R.id.cbteakits);
        tissue = findViewById(R.id.cbtissues);
        snacks = findViewById(R.id.cbsnacks);
        waterbottle = findViewById(R.id.cbwaterbottle);
        coffeekits = findViewById(R.id.cbcoffeekits);
        hotwater = findViewById(R.id.cbhotwater);
        breads = findViewById(R.id.cbbreads);
        updatecatalog = findViewById(R.id.btnUpdateCatalog);
        heading = findViewById(R.id.tvCatalogHeading);

        sPref = getSharedPreferences("TrainNoSharedPref", MODE_PRIVATE);
        tno = sPref.getString("trainno.", "");
        heading.setText("Catalog For : " + tno);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference().child("catalogues").child(tno);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CatalogData catalogData = dataSnapshot.getValue(CatalogData.class);
                if(catalogData.getVegt()==false){
                    vegthali.setChecked(false);
                }
                if(catalogData.getVegt()==true){
                    vegthali.setChecked(true);
                }
                if(catalogData.getNonvegt()==false){
                    nonvegthali.setChecked(false);
                }
                if(catalogData.getNonvegt()==true){
                    nonvegthali.setChecked(true);
                }
                if(catalogData.getBeggs()==false) {
                    boiledeggs.setChecked(false);
                }
                if(catalogData.getBeggs()==true) {
                    boiledeggs.setChecked(true);
                }
                if(catalogData.getChickenx()==false){
                    chickenextra.setChecked(false);
                }
                if(catalogData.getChickenx()==true){
                    chickenextra.setChecked(true);
                }
                if(catalogData.getVeggrav()==false){
                    veggravy.setChecked(false);
                }
                if(catalogData.getVeggrav()==true){
                    veggravy.setChecked(true);
                }
                if(catalogData.getNonveggrav()==false){
                    nonveggravy.setChecked(false);
                }
                if(catalogData.getNonveggrav()==true){
                    nonveggravy.setChecked(true);
                }
                if(catalogData.getIcecrem()==false){
                    icecream.setChecked(false);
                }
                if(catalogData.getIcecrem()==true){
                    icecream.setChecked(true);
                }
                if(catalogData.getTeak()==false){
                    teakits.setChecked(false);
                }
                if(catalogData.getTeak()==true){
                    teakits.setChecked(true);
                }
                if(catalogData.getTiss()==false){
                    tissue.setChecked(false);
                }
                if(catalogData.getTiss()==true){
                    tissue.setChecked(true);
                }
                if(catalogData.getSnak()==false){
                    snacks.setChecked(false);
                }
                if(catalogData.getSnak()==true){
                    snacks.setChecked(true);
                }
                if(catalogData.getWbottle()==false){
                    waterbottle.setChecked(false);
                }
                if(catalogData.getWbottle()==true){
                    waterbottle.setChecked(true);
                }
                if(catalogData.getCkit()==false){
                    coffeekits.setChecked(false);
                }
                if(catalogData.getCkit()==true){
                    coffeekits.setChecked(true);
                }
                if(catalogData.getHotw()==false){
                    hotwater.setChecked(false);
                }
                if(catalogData.getHotw()==true){
                    hotwater.setChecked(true);
                }
                if(catalogData.getBred()==false){
                    breads.setChecked(false);
                }
                if(catalogData.getBred()==true){
                    breads.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updatecatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCatalogData();
            }
        });
    }

    private void sendCatalogData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("catalogues").child(tno);
//        CatalogData catalogData = new CatalogData();
        if (vegthali.isChecked()) {
            myRef.child("vegt").setValue(true);
        }
        if (!vegthali.isChecked()) {
            myRef.child("vegt").setValue(false);
        }
        if (nonvegthali.isChecked()) {
            myRef.child("nonvegt").setValue(true);
        }
        if (!nonvegthali.isChecked()) {
            myRef.child("nonvegt").setValue(false);
        }
        if (boiledeggs.isChecked()) {
            myRef.child("beggs").setValue(true);
        }
        if (!boiledeggs.isChecked()) {
            myRef.child("beggs").setValue(false);
        }
        if (chickenextra.isChecked()) {
            myRef.child("chickenx").setValue(true);
        }
        if (!chickenextra.isChecked()) {
            myRef.child("chickenx").setValue(false);
        }
        if (veggravy.isChecked()) {
            myRef.child("veggrav").setValue(true);
        }
        if (!veggravy.isChecked()) {
            myRef.child("veggrav").setValue(false);
        }
        if (nonveggravy.isChecked()) {
            myRef.child("nonveggrav").setValue(true);
        }
        if (!nonveggravy.isChecked()) {
            myRef.child("nonveggrav").setValue(false);
        }
        if (icecream.isChecked()) {
            myRef.child("icecrem").setValue(true);
        }
        if (!icecream.isChecked()) {
            myRef.child("icecrem").setValue(false);
        }
        if (teakits.isChecked()) {
            myRef.child("teak").setValue(true);
        }
        if (!teakits.isChecked()) {
            myRef.child("teak").setValue(false);
        }
        if (tissue.isChecked()) {
            myRef.child("tiss").setValue(true);
        }
        if (!tissue.isChecked()) {
            myRef.child("tiss").setValue(false);
        }
        if (snacks.isChecked()) {
            myRef.child("snak").setValue(true);
        }
        if (!snacks.isChecked()) {
            myRef.child("snak").setValue(false);
        }
        if (waterbottle.isChecked()) {
            myRef.child("wbottle").setValue(true);
        }
        if (!waterbottle.isChecked()) {
            myRef.child("wbottle").setValue(false);
        }
        if (coffeekits.isChecked()) {
            myRef.child("ckit").setValue(true);
        }
        if (!coffeekits.isChecked()) {
            myRef.child("ckit").setValue(false);
        }
        if (hotwater.isChecked()) {
            myRef.child("hotw").setValue(true);
        }
        if (!hotwater.isChecked()) {
            myRef.child("hotw").setValue(false);
        }
        if (breads.isChecked()) {
            myRef.child("bred").setValue(true);
        }
        if (!breads.isChecked()) {
            myRef.child("bred").setValue(false);
        }
    }
}