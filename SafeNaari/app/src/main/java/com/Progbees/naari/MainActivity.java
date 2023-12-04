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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Button loginbtn;
    TextView SignUP,forgetPassword;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        email = findViewById(R.id.userName);
        pass = findViewById(R.id.password);
        loginbtn = findViewById(R.id.login);
        SignUP = findViewById(R.id.signUp);
        forgetPassword = findViewById(R.id.forgetPass);

        progressDialog = new ProgressDialog(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString().trim();
                String password = pass.getText().toString();

                String emailRegex = "^(?=^[A-Za-z0-9._%+-]+@)(?=.*gmail\\.com$).+";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(Email);

                String adminRegex = "^(?=^[A-Za-z0-9._%+-]+@)(?=.*admin\\.com$).+";
                Pattern adminpattern = Pattern.compile(adminRegex);
                Matcher adminatcher = adminpattern.matcher(Email);

                mAuth = FirebaseAuth.getInstance();

                if(isEmpty(Email)){
                    email.setError("Email cannot empty");
                    email.requestFocus();
                }else if (isEmpty(password)){
                    pass.setError("Password cannot be empty");
                    pass.requestFocus();
                }else {

                    if(matcher.matches()) {
                        mAuth.signInWithEmailAndPassword(Email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        Intent intent = new Intent(MainActivity.this, Home.class);
                                        startActivity(intent);

                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                                        email.setText("");
                                        pass.setText("");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }else if (adminatcher.matches()){
                        mAuth.signInWithEmailAndPassword(Email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        Intent intent = new Intent(MainActivity.this, admin.class);
                                        startActivity(intent);

                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                                        email.setText("");
                                        pass.setText("");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                }

            }
        });

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
                finish();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Email Sending...");
                progressDialog.show();

                mAuth.sendPasswordResetEmail(email.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this,"Email Sent", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this,"Email Sending Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}