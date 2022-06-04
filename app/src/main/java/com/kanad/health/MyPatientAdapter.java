package com.kanad.health;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPatientAdapter extends RecyclerView.Adapter<MyPatientAdapter.MyViewHolder> {
    static ArrayList<MyPatientUid> list;
    static Context context;

    public MyPatientAdapter(ArrayList<MyPatientUid> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.mypatienttab,parent, false);
        return new MyPatientAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyPatientUid myPatientUid = list.get(position);
        if (myPatientUid!=null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(myPatientUid.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    holder.patient_Name.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                    holder.gender_on_list.setText(snapshot.child("sex").getValue(String.class));
                    holder.age_on_list.setText(snapshot.child("yearofbirth").getValue(String.class));
                    Glide.with(context).load(snapshot.child("imageurl").getValue(String.class)).into(holder.image_patient);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView patient_Name,gender_on_list,age_on_list;
        CircleImageView image_patient;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_Name = itemView.findViewById(R.id.patient_Name);
            gender_on_list = itemView.findViewById(R.id.gender_on_list);
            age_on_list = itemView.findViewById(R.id.age_on_list);
            image_patient = itemView.findViewById(R.id.image_patient);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            MyPatientUid myPatientUid = list.get(position);
            if (myPatientUid!=null){
                String uid = myPatientUid.getUid();
                Intent intent = new Intent(context,RegularPatientProfile.class);
                intent.putExtra("uid",uid);
                context.startActivity(intent);
            }
        }
    }
}
