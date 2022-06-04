package com.kanad.health;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.MyViewHolder> {
    Context context;
    ArrayList<DownloadLink> list;

    public DownloadAdapter(Context context, ArrayList<DownloadLink> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.prescriptionlink,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DownloadLink downloadLink = list.get(position);
        holder.tvdate.setText(downloadLink.getDate());
        holder.downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(downloadLink.getPrescriptionLink()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvdate;
        Button downloadbutton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvdate = itemView.findViewById(R.id.tvdate);
            downloadbutton = itemView.findViewById(R.id.downloadbutton);
        }
    }
}
