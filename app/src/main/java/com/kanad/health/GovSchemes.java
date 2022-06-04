package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GovSchemes extends AppCompatActivity {
    RecyclerView scheme_list;
    DatabaseReference databaseReference;
    ArrayList<SchemeFill> schemeFills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_schemes);
        scheme_list = findViewById(R.id.scheme_list);
        databaseReference = FirebaseDatabase.getInstance().getReference("Schemes");
        scheme_list.setHasFixedSize(true);
        scheme_list.setLayoutManager(new LinearLayoutManager(this));
        ImageView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        schemeFills = new ArrayList<>();
        SchemeAdapter schemeAdapter = new SchemeAdapter(this,schemeFills);
        scheme_list.setAdapter(schemeAdapter);

        ProgressDialog progressDialog = new ProgressDialog(GovSchemes.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SchemeFill schemeFill = dataSnapshot.getValue(SchemeFill.class);
                    schemeFills.add(schemeFill);
                }
                schemeAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GovSchemes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}