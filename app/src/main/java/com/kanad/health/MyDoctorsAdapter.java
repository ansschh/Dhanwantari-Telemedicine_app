package com.kanad.health;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.Circle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyDoctorsAdapter extends RecyclerView.Adapter<MyDoctorsAdapter.MyViewHolder>{
    ArrayList<MyDoctorData> list;
    Context context;

    public MyDoctorsAdapter(ArrayList<MyDoctorData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.mydoctoritem,parent,false);
        return new MyDoctorsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyDoctorData myDoctorData = list.get(position);
        String uid = myDoctorData.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.personname.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                holder.address.setText(snapshot.child("address").getValue(String.class));
                holder.dob.setText(snapshot.child("dateofbirth").getValue(String.class));
                holder.email.setText(snapshot.child("email").getValue(String.class));
                holder.sex.setText(snapshot.child("sex").getValue(String.class));
                Glide.with(holder.photo.getContext()).load(snapshot.child("imageurl").getValue(String.class)).into(holder.photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DoctorProfileShow.class);
                intent.putExtra("uid",uid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView photo;
        TextView personname,address,dob,email,sex;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            personname = itemView.findViewById(R.id.personname);
            dob = itemView.findViewById(R.id.dob);
            address = itemView.findViewById(R.id.address);
            email = itemView.findViewById(R.id.email);
            sex = itemView.findViewById(R.id.sex);
        }
    }
}
