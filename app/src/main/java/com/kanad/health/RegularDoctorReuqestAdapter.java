package com.kanad.health;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
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

public class RegularDoctorReuqestAdapter extends RecyclerView.Adapter<RegularDoctorReuqestAdapter.MyViewHolder> {
    ArrayList<DoctorRequestStatus> list;
    Context context;
    Activity activity;

    public RegularDoctorReuqestAdapter(ArrayList<DoctorRequestStatus> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.regulardoctoritem,parent,false);
        return new RegularDoctorReuqestAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DoctorRequestStatus doctorRequestStatus = list.get(position);
        String uid = doctorRequestStatus.getUseruid();
        String status = doctorRequestStatus.getStatus();
        if (doctorRequestStatus.getStatus()!=null){
            if (status.equalsIgnoreCase("waiting")){
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.personname.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                        holder.address.setText(snapshot.child("address").getValue(String.class));
                        holder.dob.setText(snapshot.child("yearofbirth").getValue(String.class));
                        holder.email.setText(snapshot.child("email").getValue(String.class));
                        holder.sex.setText(snapshot.child("sex").getValue(String.class));
                        String token = snapshot.child("token").getValue(String.class);
                        Glide.with(context).load(snapshot.child("imageurl").getValue(String.class)).into(holder.photo);
                        holder.declinebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                final EditText edittext = new EditText(context);
                                alert.setMessage("Please Enter Reason Why? you are declining this request.");
                                alert.setTitle("Reason");
                                alert.setView(edittext);
                                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String YouEditTextValue = edittext.getText().toString();
                                        if (!YouEditTextValue.isEmpty()){
                                            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(FirebaseAuth.getInstance().getUid()).child(uid);
                                                databaseReference1.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(context, "Request Declined", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                if (uid!=null){
                                                    DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                                                    d.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            String mail = snapshot.getValue(String.class);
                                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                                    String message = "Dr. "+doctorname+" has unfortunately declined your regular doctor request, because:\n"+YouEditTextValue;
                                                                    String Subject = "Regular Doctor Request Declined";
                                                                    JavaMailAPI javaMailAPI = new JavaMailAPI(context,mail,Subject,message);
                                                                    javaMailAPI.execute();
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {
                                                                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }else{
                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(GoogleSignIn.getLastSignedInAccount(context).getId()).child(uid).child("status");
                                                databaseReference1.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(context, "Request Declined", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                if (uid!=null){
                                                    DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                                                    d.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            String mail = snapshot.getValue(String.class);
                                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(context).getId());
                                                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                                    String message = "Dr. "+doctorname+" has unfortunately declined your regular doctor request, because:\n"+YouEditTextValue;
                                                                    String Subject = "Regular Doctor Request Declined";
                                                                    JavaMailAPI javaMailAPI = new JavaMailAPI(context,mail,Subject,message);
                                                                    javaMailAPI.execute();
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {
                                                                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                });
                                alert.show();
                            }
                        });
                        holder.acceptbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(FirebaseAuth.getInstance().getUid()).child(uid).child("status");
                                    databaseReference1.setValue("accepted");
                                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RegularPatients").child(FirebaseAuth.getInstance().getUid()).child(uid).child("uid");
                                    databaseReference2.setValue(uid);
                                    DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RegularDoctors").child(uid).child(FirebaseAuth.getInstance().getUid()).child("uid");
                                    databaseReference3.setValue(FirebaseAuth.getInstance().getUid());
                                    Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                                    d.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String mail = snapshot.getValue(String.class);
                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(context).getId());
                                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                    String message = "Congratulations, Dr. "+doctorname+" has accepted your regular doctor request, Now he is one of your permanent personal doctor.\n with whom you can share your health data to get monitored remotely with your homes comfort.";
                                                    String Subject = "Regular Doctor Request Declined";
                                                    JavaMailAPI javaMailAPI = new JavaMailAPI(context,mail,Subject,message);
                                                    javaMailAPI.execute();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(GoogleSignIn.getLastSignedInAccount(context).getId()).child(uid).child("status");
                                    databaseReference1.setValue("accepted");
                                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RegularPatients").child(GoogleSignIn.getLastSignedInAccount(context).getId()).child(uid).child("uid");
                                    databaseReference2.setValue(uid);
                                    DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RegularDoctors").child(uid).child(GoogleSignIn.getLastSignedInAccount(context).getId()).child("uid");
                                    databaseReference3.setValue(GoogleSignIn.getLastSignedInAccount(context).getId());
                                    Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();
                                    if (uid!=null){
                                        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                                        d.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String mail = snapshot.getValue(String.class);
                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(context).getId());
                                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                        String message = "Congratulations, Dr. "+doctorname+" has accepted your regular doctor request, Now he is one of your permanent personal doctor.\n with whom you can share your health data to get monitored remotely with your homes comfort.";
                                                        String Subject = "Regular Doctor Request Accepted";
                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(context,mail,Subject,message);
                                                        javaMailAPI.execute();
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else if (status.equalsIgnoreCase("rejected")){
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.itemView.setAlpha(0.25F);
                        holder.personname.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                        holder.address.setText(snapshot.child("address").getValue(String.class));
                        holder.dob.setText(snapshot.child("yearofbirth").getValue(String.class));
                        holder.email.setText(snapshot.child("email").getValue(String.class));
                        holder.sex.setText(snapshot.child("sex").getValue(String.class));
                        String token = snapshot.child("token").getValue(String.class);
                        Glide.with(context).load(snapshot.child("imageurl").getValue(String.class)).into(holder.photo);
                        holder.declinebutton.setVisibility(View.GONE);
                        holder.acceptbutton.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else if (status.equalsIgnoreCase("accepted")){
                holder.itemView.setVisibility(View.GONE);
            }
        }else{
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView personname,address,dob,email,sex;
        Button declinebutton,acceptbutton;
        LinearLayout norequests;
        CircleImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            personname = itemView.findViewById(R.id.personname);
            photo = itemView.findViewById(R.id.photo);
            address = itemView.findViewById(R.id.address);
            dob = itemView.findViewById(R.id.dob);
            norequests = itemView.findViewById(R.id.norequests);
            email = itemView.findViewById(R.id.email);
            sex = itemView.findViewById(R.id.sex);
            declinebutton = itemView.findViewById(R.id.declinebutton);
            acceptbutton = itemView.findViewById(R.id.acceptbutton);
        }
    }

}
