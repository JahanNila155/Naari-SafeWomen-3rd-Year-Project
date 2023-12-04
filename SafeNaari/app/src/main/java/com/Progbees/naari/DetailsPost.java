package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsPost extends AppCompatActivity {

    TextView title,post;
    CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_post);
        title = findViewById(R.id.card_titile);
        post = findViewById(R.id.post);
        img = findViewById(R.id.tfetchimag);

        String Title = getIntent().getExtras().getString("Title","defaultKey");
        String details = getIntent().getExtras().getString("Details","defaultKey");
        String picurl = getIntent().getExtras().getString("pic","defaultKey");


        title.setText(Title);
        post.setText(details);
        Glide.with(DetailsPost.this).load(picurl).into(img);

    }
    public void onBackPressed() {
        Intent intent = new Intent(DetailsPost.this, period.class);
        startActivity(intent);
        finish();
    }
}