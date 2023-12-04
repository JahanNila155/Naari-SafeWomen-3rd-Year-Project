package com.Progbees.naari;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myvideoAdapter extends FirebaseRecyclerAdapter<videomodel,myvideoAdapter.myviewholder> {


    public myvideoAdapter(@NonNull FirebaseRecyclerOptions<videomodel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myvideoAdapter.myviewholder holder, int position, @NonNull videomodel model) {



//        holder.Title.setText(model.getvLink());
        holder.Title.loadUrl(model.getvLink());
        WebSettings webSettings = holder.Title.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        holder.Details.setText(model.getPostDetails());

//        System.out.println("Title: "+model.getTitle());
//        System.out.println("Post: "+model.getDetails());

//        Glide.with(holder.imgl.getContext()).load(model.getPostPic()).into(holder.imgl);


    }

    @NonNull
    @Override
    public myvideoAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        return new myvideoAdapter.myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


//        ImageView imgl;
        WebView Title;
//        Button Delete;
        View viewLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
//            imgl = (ImageView) itemView.findViewById(R.id.imagep);
            Title = itemView.findViewById(R.id.link);
//            Details = itemView.findViewById(R.id.card_d);
            viewLayout = itemView.findViewById(R.id.cardLayout);
//            Delete = itemView.findViewById(R.id.delete);


        }
    }

}

