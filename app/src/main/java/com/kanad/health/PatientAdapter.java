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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {

    static Context context;
    static ArrayList<PatientData> patientData;

    public PatientAdapter(Context context, ArrayList<PatientData> patientData) {
        this.context = context;
        this.patientData = patientData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patientitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PatientData patientData1 = patientData.get(position);
        holder.patient_Name.setText(patientData1.getName_());
        holder.age_on_list.setText(patientData1.getAge_());
        holder.gender_on_list.setText(patientData1.getGender_());
        Glide.with(holder.image_patient.getContext()).load(patientData1.getImageurl()).into(holder.image_patient);
    }

    @Override
    public int getItemCount() {
        return patientData.size();
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
            PatientData patientData1 = patientData.get(position);
            String uid = patientData1.getUid();
            Intent intent = new Intent(context,Prescription.class);
            intent.putExtra("uid",uid);
            intent.putExtra("Name",patientData1.getName_());
            intent.putExtra("Age",patientData1.getAge_());
            intent.putExtra("Gender",patientData1.getGender_());
            context.startActivity(intent);
        }
    }
}
