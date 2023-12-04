package com.Progbees.naari;

import static android.text.TextUtils.isEmpty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class signup extends AppCompatActivity {

    EditText userName, Email, upassword, phoneNo;
    Button signUp;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName =  findViewById(R.id.your_name);
        Email = findViewById(R.id.Your_email);
        phoneNo = findViewById(R.id.your_userPhone);
        upassword = findViewById(R.id.password);
        signUp = findViewById(R.id.signup);
        login = findViewById(R.id.loginUp);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();



        progressDialog = new ProgressDialog(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString();
                String email = Email.getText().toString().trim();
                String phoneNumber = phoneNo.getText().toString();
                String password = upassword.getText().toString();

                progressDialog.show();

                if(isEmpty(username)){
                    userName.setError("UserName can't be empty");
                    userName.requestFocus();

                }
               else if(isEmpty(email)){
                    userName.setError("Email can't be empty");
                    userName.requestFocus();

                }
               else if(isEmpty(phoneNumber)){
                   phoneNo.setError("Email can't be empty");
                   phoneNo.requestFocus();
                }
                else if(isEmpty(password)){
                    upassword.setError("Password can't be empty");
                    upassword.requestFocus();
                }
                else{

                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid())
                                            .set(new userHelper(username,phoneNumber,email));

                                    progressDialog.cancel();
                                    Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_LONG).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    progressDialog.cancel();
                                    Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_LONG).show();


                                }
                            });
                }


            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}