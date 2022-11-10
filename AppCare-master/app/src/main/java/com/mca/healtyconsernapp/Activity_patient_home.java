package com.mca.healtyconsernapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.CookieHandler;
import java.util.ArrayList;


public class Activity_patient_home extends AppCompatActivity  {

    RecyclerView recyclerView;
    DatabaseReference database;
    DocAdapter docAdapter;
    ArrayList<DoctorData> doclist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        recyclerView = findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance().getReference("Doctor");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        doclist = new ArrayList<>();
        docAdapter = new DocAdapter(this, doclist);
        recyclerView.setAdapter(docAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DoctorData doctorData = dataSnapshot.getValue(DoctorData.class);
                    doclist.add(doctorData);
                }

                docAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}