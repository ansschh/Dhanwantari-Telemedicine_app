package com.kanad.health;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RemoteMonitoringAdapter extends RecyclerView.Adapter<RemoteMonitoringAdapter.MyViewHolder>{
    ArrayList<RPMdata> list;
    IUserRecycler mListener;
    static Context context;
    static String uid1;

    public RemoteMonitoringAdapter(ArrayList<RPMdata> list, Context context,IUserRecycler mListener) {
        this.list = list;
        this.mListener = mListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rpmrequestitem,parent,false);
        return new RemoteMonitoringAdapter.MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setVisibility(View.GONE);
        RPMdata rpMdata = list.get(position);
        String uid = rpMdata.getUid();

        if (GoogleSignIn.getLastSignedInAccount(context)!=null){
            uid1 = rpMdata.getUid();
            String useruid = GoogleSignIn.getLastSignedInAccount(context).getId();
            DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(useruid);
            databaseReference3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("status").getValue(String.class)!=null){
                        if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("accepted")){
                            holder.itemView.setVisibility(View.GONE);
                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("rejected")){
                            holder.itemView.setVisibility(View.GONE);
                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("stopped")){
                            holder.itemView.setVisibility(View.GONE);
                        }else{
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    holder.personname.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                                    holder.address.setText(snapshot.child("address").getValue(String.class));
                                    holder.email.setText(snapshot.child("email").getValue(String.class));
                                    holder.dob.setText(snapshot.child("dateofbirth").getValue(String.class));
                                    if (context.getApplicationContext()!=null){
                                        Glide.with(holder.photo.getContext().getApplicationContext()).load(snapshot.child("imageurl").getValue(String.class)).into(holder.photo);
                                    }
                                    String state = snapshot.child("states").getValue(String.class);
                                    String distircts = snapshot.child("districts").getValue(String.class);
                                    holder.itemView.setVisibility(View.VISIBLE);
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Doctor").child(state).child(distircts).child(uid);
                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            holder.sex.setText(snapshot.child("yearsOfExpirience").getValue(String.class));
                                            DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(useruid);
                                            databaseReference3.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.child("status").getValue(String.class)!=null){
                                                        if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("accepted")){
                                                            holder.itemView.setVisibility(View.GONE);
                                                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("rejected")){
                                                            holder.itemView.setVisibility(View.GONE);
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(context, error.getMessage()  , Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            holder.declinebutton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    AlertDialog.Builder builder
                                                            = new AlertDialog
                                                            .Builder(context);
                                                    builder.setMessage("Please,  Confirm that you are sure to deny this request.");
                                                    builder.setTitle("Do you really want to deny this request?");
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton(
                                                            "Yes",
                                                            new DialogInterface
                                                                    .OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    dialog.cancel();
                                                                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(useruid).child("status");
                                                                    databaseReference2.setValue("rejected").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {
                                                                            Toast.makeText(context, "Request Rejected", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                                }
                                                            });
                                                    builder.setPositiveButton(
                                                            "No",
                                                            new DialogInterface
                                                                    .OnClickListener() {

                                                                @Override
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                }
                                            });
                                            holder.acceptbutton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    String name = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                    AlertDialog.Builder builder
                                                            = new AlertDialog
                                                            .Builder(context);
                                                    builder.setMessage("Please,  Confirm that you are sure to start remote patient monitoring with "+name+".");
                                                    builder.setTitle("Start Reading Your Health Data?");
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton(
                                                            "Yes",
                                                            new DialogInterface
                                                                    .OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    dialog.cancel();
                                                                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(useruid).child("status");
                                                                    databaseReference2.setValue("accepted").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {
                                                                            Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();
                                                                            mListener.ChangeView(uid);

                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                                }
                                                            });
                                                    builder.setPositiveButton(
                                                            "No",
                                                            new DialogInterface
                                                                    .OnClickListener() {

                                                                @Override
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(context);
            builder.setMessage("Please, Sign in with your google account to continue as we use Google Fit API to monitor patient remotely");
            builder.setTitle("Sign in with google");
            builder.setCancelable(false);
            builder.setNegativeButton(
                    "Log Out",
                    new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.cancel();
                            if (GoogleSignIn.getLastSignedInAccount(context) != null) {
                                GoogleSignInOptions gso = new GoogleSignInOptions.
                                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                        build();
                                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, gso);
                                googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent i = new Intent(context, LoginActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        context.startActivity(i);
                                        ((Activity)context).finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                FirebaseAuth.getInstance().signOut();
                                Intent i = new Intent(context, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(i);
                                ((Activity)context).finish();
                            }
                        }
                    });
            builder.setPositiveButton(
                    "Cancel",
                    new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.cancel();
                            ((Activity)context).finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView personname,address,dob,email,sex,rpmstarted;
        Button declinebutton,acceptbutton;
        CircleImageView photo;
        IUserRecycler mListener;
        public MyViewHolder(@NonNull View itemView, IUserRecycler mListener) {
            super(itemView);
            this.mListener = mListener;
            photo = itemView.findViewById(R.id.photo);
            rpmstarted = itemView.findViewById(R.id.rpmstarted);
            declinebutton = itemView.findViewById(R.id.declinebutton);
            acceptbutton = itemView.findViewById(R.id.acceptbutton);
            personname = itemView.findViewById(R.id.personname);
            address = itemView.findViewById(R.id.address);
            dob = itemView.findViewById(R.id.dob);
            email = itemView.findViewById(R.id.email);
            sex = itemView.findViewById(R.id.sex);
        }
    }

    interface IUserRecycler{
        void  ChangeView(String uid);
    }
}
