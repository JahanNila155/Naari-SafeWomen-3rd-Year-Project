package com.Progbees.naari;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class myDAdapter extends FirebaseRecyclerAdapter<modelAdapter,myDAdapter.myviewholder> {


    public myDAdapter(@NonNull FirebaseRecyclerOptions<modelAdapter> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myDAdapter.myviewholder holder, int position, @NonNull modelAdapter model) {



        holder.Title.setText(model.getPostTitle());
        holder.Details.setText(model.getPostDetails());

//        System.out.println("Title: "+model.getTitle());
//        System.out.println("Post: "+model.getDetails());

        Glide.with(holder.imgl.getContext()).load(model.getPostPic()).into(holder.imgl);

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgl.getContext());
                builder.setTitle("Delete Post");
                builder.setMessage("Delete....!");
                builder.setPositiveButton("Yes",(dialog, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("Post")
                            .child(getRef(position).getKey()).removeValue();
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });

                builder.show();


            }
        });
    }

    @NonNull
    @Override
    public myDAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_post,parent,false);
        return new myDAdapter.myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


        ImageView imgl;
        TextView Title,Details;
        Button Delete;
        View viewLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.imagep);
            Title = itemView.findViewById(R.id.card_name);
            Details = itemView.findViewById(R.id.card_d);
            viewLayout = itemView.findViewById(R.id.cardLayout);
            Delete = itemView.findViewById(R.id.delete);


        }
    }

}

