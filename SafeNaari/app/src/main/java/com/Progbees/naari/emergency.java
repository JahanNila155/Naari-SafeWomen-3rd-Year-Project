package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firestore.v1.TargetOrBuilder;

public class emergency extends AppCompatActivity implements SensorEventListener {

    TextView detect,acc;
    Sensor sensorShake;
    SensorManager sensorManager;
    LinearLayout joruriseba,policeseba,fireservice;
    private static final int REQUEST_CALL = 1;
    String number = "+8801741219496";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        detect = findViewById(R.id.ditectShake);
//        acc = findViewById(R.id.detectAcc);
        joruriseba = findViewById(R.id.jororiSeba);
        policeseba = findViewById(R.id.policeSeba);
        fireservice = findViewById(R.id.FireService);

        joruriseba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = "+880999";
                callNumber(number);

            }
        });

        policeseba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+8801309188145";
                callNumber(number);
            }
        });

        fireservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "01321315878";
                callNumber(number);
            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensorShake == null){
            Toast.makeText(emergency.this,"No accelerometer detected in this device", Toast.LENGTH_LONG).show();
            finish();
        }else {
            sensorManager.registerListener(this,sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float EG = SensorManager.GRAVITY_EARTH;
            float devAccl = (x*x*y*y*z*z)/(EG*EG);


//            acc.setText("Acceliration: "+devAccl);
            if(devAccl > 550){
//                Intent intent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:" + number));
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // Request permission
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
//                    return;
//                }
//                startActivity(intent);
                String number = "+8801792544844";

                callNumber(number);

                detect.setText("Shaking.....");
            }
        }

    }

    private void callNumber(String number) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(emergency.this, "Please grant the call permission to proceed.", Toast.LENGTH_LONG).show();
            return;
        }
        startActivityForResult(callIntent, REQUEST_CALL);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CALL) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(emergency.this, "Call sent successfully.", Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(emergency.this, "Call failed. Please try again.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}