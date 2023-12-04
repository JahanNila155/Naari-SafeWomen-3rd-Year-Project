package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class walkthrough extends AppCompatActivity {

    LinearLayout mda,sga;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        mda=  findViewById(R.id.mda);
        sga=  findViewById(R.id.sga);

        mda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(walkthrough.this,mda.class));

            }
        });
        sga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(walkthrough.this,sga.class));

            }
        });
    }
}