package com.Progbees.naari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class period extends AppCompatActivity {

    RecyclerView recycleViewp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period);
        recycleViewp = findViewById(R.id.recycleViewp);

                recycleViewp.setLayoutManager((new LinearLayoutManager(period.this)));
        FirebaseRecyclerOptions<modelAdapter> options =
                new FirebaseRecyclerOptions.Builder<modelAdapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), modelAdapter.class)
                        .build();

        myAdapter adapter = new myAdapter(options);
        recycleViewp.setAdapter(adapter);
        adapter.startListening();
    }


}