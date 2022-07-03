package com.vaibhav.solofoodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.R.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PantryLoginActivity extends AppCompatActivity {

    AutoCompleteTextView pantrytrainno;
    EditText pantrypersonemail;
    EditText pantrypersonpassword;
    Button pantryfinallogin;
    String tnoo;
    ImageView pizza;
    FirebaseAuth mAuth;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_login);

        mAuth = FirebaseAuth.getInstance();

        pantrytrainno = findViewById(R.id.etPantryTrainNo);
        pantrypersonemail = findViewById(R.id.etPantryEmail);
        pantrypersonpassword = findViewById(R.id.etPantryPassword);
        pantryfinallogin = findViewById(R.id.btnPantryFinalLogin);
        pizza = findViewById(R.id.imageView21_pizza);

        mAuth = FirebaseAuth.getInstance();

        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("trains");

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> trainnos = new ArrayList<>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String t = areaSnapshot.child("trainno").getValue(String.class);
                    trainnos.add(t);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(PantryLoginActivity.this, layout.simple_list_item_1, trainnos);
                pantrytrainno.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pantryfinallogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPref = getSharedPreferences("TrainNoSharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sPref.edit();
                myEdit.putString("trainno.", pantrytrainno.getText().toString());
                myEdit.apply();
                loginPantryManager();
            }
        });

        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PantryLoginActivity.this, PantryRegistrationCheckActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // send the user to PantryBusinessActivity. (later on i have to imnplement it ..)
            startActivity(new Intent(PantryLoginActivity.this, PantryBusinessActivity.class));
            finish();
        }
    }

    private void loginPantryManager() {
        String trainno = pantrytrainno.getText().toString();
        String email = pantrypersonemail.getText().toString();
        String password = pantrypersonpassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            pantrypersonemail.setError("Email Cannot be Blank!");
            pantrypersonemail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            pantrypersonpassword.setError("Password Cannot be Blank!");
            pantrypersonpassword.requestFocus();
        } else if (TextUtils.isEmpty(trainno)){
            pantrytrainno.setError("Train no Cannot be Blank!");
            pantrytrainno.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(PantryLoginActivity.this, "Pantry Manager Login Successful!", Toast.LENGTH_SHORT).show();
                        tnoo = pantrytrainno.getText().toString();
                        sendTrainData(tnoo);
                        startActivity(new Intent(PantryLoginActivity.this, PantryBusinessActivity.class));
                        finish();
                    } else {
                        Toast.makeText(PantryLoginActivity.this, "Login Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendTrainData(String train) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("trains").child(train);
        TrainData trainData = new TrainData(train);
        myRef.setValue(trainData);
    }
}