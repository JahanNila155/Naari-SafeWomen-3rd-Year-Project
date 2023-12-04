package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DeletePost extends AppCompatActivity {

    RecyclerView recycleViewp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);
        recycleViewp = findViewById(R.id.recycleViewpd);

        recycleViewp.setLayoutManager((new LinearLayoutManager(DeletePost.this)));
        FirebaseRecyclerOptions<modelAdapter> options =
                new FirebaseRecyclerOptions.Builder<modelAdapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), modelAdapter.class)
                        .build();

        myDAdapter adapter = new myDAdapter(options);
        recycleViewp.setAdapter(adapter);
        adapter.startListening();
    }
}