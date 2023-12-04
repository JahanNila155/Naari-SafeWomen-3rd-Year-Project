package com.Progbees.naari;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class myvideoDAdapter extends FirebaseRecyclerAdapter<videomodel,myvideoDAdapter.myviewholder> {


    public myvideoDAdapter(@NonNull FirebaseRecyclerOptions<videomodel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myvideoDAdapter.myviewholder holder, int position, @NonNull videomodel model) {



//        holder.Title.setText(model.getvLink());
        holder.Title.loadUrl(model.getvLink());
        WebSettings webSettings = holder.Title.getSettings();
        webSettings.setJavaScriptEnabled(true);

        System.out.println("Check_link: "+model.getvLink());

//        holder.Details.setText(model.getPostDetails());

//        System.out.println("Title: "+model.getTitle());
//        System.out.println("Post: "+model.getDetails());

//        Glide.with(holder.imgl.getContext()).load(model.getPostPic()).into(holder.imgl);
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Title.getContext());
                builder.setTitle("Delete Post");
                builder.setMessage("Delete....!");
                builder.setPositiveButton("Yes",(dialog, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("VideoLink")
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
    public myvideoDAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_delete,parent,false);
        return new myvideoDAdapter.myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


        //        ImageView imgl;
        WebView Title;
        Button Delete;
        View viewLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
//            imgl = (ImageView) itemView.findViewById(R.id.imagep);
            Title = itemView.findViewById(R.id.link);
//            Details = itemView.findViewById(R.id.card_d);
            viewLayout = itemView.findViewById(R.id.cardLayout);
            Delete = itemView.findViewById(R.id.delete);


        }
    }

}


