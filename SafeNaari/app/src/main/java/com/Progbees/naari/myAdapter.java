package com.Progbees.naari;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firestore.v1.TargetOrBuilder;

public class myAdapter extends FirebaseRecyclerAdapter<modelAdapter,myAdapter.myviewholder> {

    private boolean isExpanded = false;
    public myAdapter(@NonNull FirebaseRecyclerOptions<modelAdapter> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modelAdapter model) {



        holder.Title.setText(model.getPostTitle());



        Glide.with(holder.imgl.getContext()).load(model.getPostPic()).into(holder.imgl);

        holder.viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailsPost.class);
                intent.putExtra("Title", model.getPostTitle());
                intent.putExtra("Details", model.getPostDetails());
                intent.putExtra("pic", model.getPostPic());
                context.startActivity(intent);


                // finish the current activity
                ((Activity) context).finish();
//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(myAdapter.this,new detailsInfo(model.getName(),model.getDesignation(),model.getAera_of_study(),model.getEmail(),model.getPhone(),model.getAdress(),model.getPicurl())).addToBackStack(null).commit();
            }
        });





    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


        ImageView imgl;
        TextView Title,shortTextView;
        Button Delete;
        View viewLayout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.imagep);
            Title = itemView.findViewById(R.id.card_name);
            viewLayout = itemView.findViewById(R.id.cardLayout);
            Delete = itemView.findViewById(R.id.delete);

        }
    }

}

