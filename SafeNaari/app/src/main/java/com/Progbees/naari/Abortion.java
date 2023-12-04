package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Abortion extends AppCompatActivity {

    LinearLayout wkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abortion);

        wkt = findViewById(R.id.wkt);


        wkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Abortion.this,walkthrough.class));






            }
        });
    }
}