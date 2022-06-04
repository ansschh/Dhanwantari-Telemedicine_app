package com.kanad.health;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    static String uid;
    static Context context;
    static ArrayList<userData> list;

    public MyAdapter(Context context, ArrayList<userData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        userData userData = list.get(position);
        if (userData!=null){
            if (userData.getApproved().equalsIgnoreCase("false")){
                holder.verifiedcheck.setVisibility(View.GONE);
            } else if (userData.getApproved().equalsIgnoreCase("true")){
                holder.verifiedcheck.setVisibility(View.VISIBLE);
            }
            holder.institute_.setText(userData.getInstitute_());
            holder.specilization_.setText(userData.getSpecilization_());
            holder.approved.setText(userData.getYearsOfExpirience());
            holder.doctor_Name.setText(userData.getDoctor_Name());
            Glide.with(context).load(userData.getImage_Url()).into(holder.image_doctor);
            holder.Doctor_uid_dummy.setText(userData.getdoctor_uid_dummy());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView specilization_,approved,institute_,doctor_Name,Doctor_uid_dummy;
        ImageView verifiedcheck;
        CircleImageView image_doctor;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            verifiedcheck = itemView.findViewById(R.id.verifiedcheck);
            Doctor_uid_dummy = itemView.findViewById(R.id.Doctor_uid_dummy);
            doctor_Name = itemView.findViewById(R.id.Doctor_Name);
            image_doctor = itemView.findViewById(R.id.image_doctor);
            specilization_ = itemView.findViewById(R.id.doctor_specilization_on_list);
            institute_ = itemView.findViewById(R.id.doctor_institute_on_list);
            approved = itemView.findViewById(R.id.doctor_verification_on_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            userData userData = list.get(position);
            if (userData!=null){
                uid = userData.getdoctor_uid_dummy();
                Intent intent = new Intent(context,DoctorProfileShow.class);
                intent.putExtra("uid",uid);
                context.startActivity(intent);
            }
        }
    }
}
