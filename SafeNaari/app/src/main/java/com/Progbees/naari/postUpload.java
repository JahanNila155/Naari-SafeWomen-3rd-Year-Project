package com.Progbees.naari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.Random;

public class postUpload extends AppCompatActivity {

    EditText Title, Post;
    Button SubmitBTn;
    ImageView imgupl;
    Uri imageUri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_upload);
        Title = findViewById(R.id.POSTTITLE);
        Post = findViewById(R.id.POSTDETAILS);
        SubmitBTn = findViewById(R.id.submitBTN);
        imgupl = findViewById(R.id.imguploadT);

        imgupl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);

            }
        });

        SubmitBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String  PostDetails = Post.getText().toString();
                String  PostTitle= Title.getText().toString();



                if(TextUtils.isEmpty(PostTitle)){
                    Title.setError("PostTitle Missing");
                    Title.requestFocus();
                }else if(TextUtils.isEmpty(PostDetails)){
                    Post.setError("PostDetails Missing");
                    Post.requestFocus();
                }else if(imageUri == null){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(postUpload.this);
                    builder1.setTitle("Alert !");
                    builder1.setMessage("Image can't selected ! Please Select Image.");
//                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });



                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
                else{


                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference uploder = storage.getReference("Image1"+new Random().nextInt(50));

                    uploder.putFile(imageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                            DatabaseReference root = rootNode.getReference("Post");
                                            postHelper helper = new postHelper(PostTitle,PostDetails,uri.toString());

                                            String key = root.push().getKey();
                                            root.child(key).setValue(helper);

                                            Post.setText("");
                                            Title.setText("");
                                            imgupl.setImageResource(R.drawable.background);
                                            Toast.makeText(postUpload.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    if(imageUri != null){


                                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                                        dialog.setMessage("Uploaded:"+(int)progress+"%");
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(postUpload.this);
                                        builder2.setTitle("Alert !");
                                        builder2.setMessage("Uploaded:"+(int)progress+"%");
                                        //                    builder1.setCancelable(true);

                                        builder2.setPositiveButton(
                                                "Ok",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });



                                        AlertDialog alert11 = builder2.create();
                                        alert11.show();
                                    }
//                                       dialog.dismiss();
                                }
                            });
                }
            }
        });



    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==1 && resultCode == RESULT_OK ){

            imageUri = data.getData();

            try {
                InputStream inputStream  = postUpload.this.getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgupl.setImageBitmap(bitmap);

            }catch (Exception e)
            {

            }



        }
    }

}