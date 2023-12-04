package com.Progbees.naari;

import android.app.TaskInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class videoUpload extends AppCompatActivity {

    EditText videoUpload,Title;
    Button submit;
    FirebaseDatabase DataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_upload);


        videoUpload = findViewById(R.id.videoLink);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vLink = videoUpload.getText().toString();
                DataBase = FirebaseDatabase.getInstance();
                DatabaseReference root = DataBase.getReference("VideoLink");
                VideoAdapter VAdapter = new VideoAdapter(vLink);
                String key = root.push().getKey();
                root.child(key).setValue(VAdapter);

                videoUpload.setText("");
                Toast.makeText(videoUpload.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();




            }
        });



    }
}