package com.kanad.health;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.MyViewHolder>{
    Context context;
    ArrayList<SchemeFill> list;

    public SchemeAdapter(Context context, ArrayList<SchemeFill> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.schemeslayout,parent,false);
        return new SchemeAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SchemeFill schemeFill = list.get(position);
        holder.scheme_Name.setText(schemeFill.getName());
        Glide.with(holder.image_scheme.getContext()).load(schemeFill.getImageUrl()).into(holder.image_scheme);
        holder.schemelearnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(context);
                builder.setMessage(schemeFill.getAbout());
                builder.setTitle(schemeFill.getName());
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Ok",
                        new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                dialog.cancel();
                            }
                        });
                builder.setPositiveButton(
                        "Go To Website",
                        new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(schemeFill.getLink()));
                                context.startActivity(i);
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image_scheme;
        TextView scheme_Name;
        TextView schemelearnmore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_scheme = itemView.findViewById(R.id.image_scheme);
            scheme_Name = itemView.findViewById(R.id.scheme_Name);
            schemelearnmore = itemView.findViewById(R.id.schemelearnmore);
        }
    }

}
