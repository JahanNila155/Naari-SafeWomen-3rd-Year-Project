package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class miscarriage extends AppCompatActivity {

    LinearLayout to,ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscarriage);

        to = findViewById(R.id.to);
        ec = findViewById(R.id.ec);

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(miscarriage.this,treatment.class));

            }
        });

        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(miscarriage.this,emotional.class));

            }
        });
    }
}