package com.Progbees.naari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    LinearLayout UploadVideo,UploadPost,DeletePost,DeleteVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        UploadVideo = findViewById(R.id.VideoUpload);
        UploadPost = findViewById(R.id.postUpload);
        DeletePost = findViewById(R.id.postDelete);
        DeleteVideo = findViewById(R.id.VideoDelete);


        DeleteVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this, DeleteVideo.class);
                startActivity(intent);
            }
        });


        DeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this, DeletePost.class);
                startActivity(intent);
            }
        });

        UploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(admin.this, videoUpload.class);
              startActivity(intent);
            }
        });

        UploadPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this, postUpload.class);
                startActivity(intent);
            }
        });



    }
}