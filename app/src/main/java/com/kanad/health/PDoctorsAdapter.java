package com.kanad.health;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class PDoctorsAdapter extends RecyclerView.Adapter<PDoctorsAdapter.MyViewHolder>{
    ArrayList<previousdocs> list;
    static Context context;
    static String uid;
    static Activity activity;

    public PDoctorsAdapter(ArrayList<previousdocs> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.previousdoctoritem,parent,false);
        return new PDoctorsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        previousdocs previousDoctor = list.get(position);
        uid = previousDoctor.getUid();
        String state = previousDoctor.getState();
        String district = previousDoctor.getDistrict();
        String date = previousDoctor.getDate();
        holder.date_previous.setText(date);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            String uuseruid = FirebaseAuth.getInstance().getUid();
            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(uuseruid);
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    if (snapshot.child("status").getValue(String.class)!=null){
                        if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("waiting")){
                            holder.waitclock.setVisibility(View.VISIBLE);
                            holder.bookmarkselected.setVisibility(View.GONE);
                            holder.bookmarkunselected.setVisibility(View.GONE);
                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("rejected")){
                            holder.waitclock.setVisibility(View.GONE);
                            holder.bookmarkselected.setVisibility(View.GONE);
                            holder.bookmarkunselected.setVisibility(View.VISIBLE);
                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("accepted")){
                            holder.waitclock.setVisibility(View.GONE);
                            holder.bookmarkselected.setVisibility(View.VISIBLE);
                            holder.bookmarkunselected.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(GoogleSignIn.getLastSignedInAccount(context).getId());
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("status").getValue(String.class)!=null){
                        if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("waiting")){
                            holder.waitclock.setVisibility(View.VISIBLE);
                            holder.bookmarkselected.setVisibility(View.GONE);
                            holder.bookmarkunselected.setVisibility(View.GONE);
                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("rejected")){
                            holder.waitclock.setVisibility(View.GONE);
                            holder.bookmarkselected.setVisibility(View.GONE);
                            holder.bookmarkunselected.setVisibility(View.VISIBLE);
                        }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("accepted")){
                            holder.waitclock.setVisibility(View.GONE);
                            holder.bookmarkselected.setVisibility(View.VISIBLE);
                            holder.bookmarkunselected.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(state).child(district).child(uid).child("EducationQualification");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String img = snapshot.child("image_Url").getValue(String.class);
                Glide.with(holder.image_doctor_previous.getContext()).load(img).into(holder.image_doctor_previous);
                String name = snapshot.child("doctor_Name").getValue(String.class);
                holder.Doctor_Name_previous.setText(name);
                String approved = snapshot.child("yearsOfExpirience").getValue(String.class);
                holder.doctor_verification_on_list_previous.setText(approved);
                String institute = snapshot.child("institute_").getValue(String.class);
                holder.doctor_institute_on_list.setText(institute);
                String specialization = snapshot.child("yearsOfExpirience").getValue(String.class);
                holder.doctor_specilization_on_list_previous.setText(specialization);
                if (approved.equalsIgnoreCase("true")){
                    holder.verifiedcheck_previous.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.bookmarkunselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(context);
                builder.setMessage("To add "+holder.Doctor_Name_previous.getText().toString()+" to your regular doctor list, by confirming. We will send request to "+holder.Doctor_Name_previous.getText().toString()+". If he/she approves, you will be able to see it from my doctor list tab or you will get a mail for the same.");
                builder.setTitle("Send Regular Doctor Request?");
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Confirm",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                    String _uid_ = FirebaseAuth.getInstance().getUid();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(_uid_);
                                    RegularDoctorRequest request = new RegularDoctorRequest("waiting",_uid_);
                                    databaseReference.setValue(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("token");
                                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String token = snapshot.getValue(String.class);
                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
                                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            String name = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                            FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token,name+" has sent you request",name+" has sent you request to become their regular doctor",context,activity,"RegularPatient",_uid_);
                                                            notificationsSender.SendNotifications();
                                                            sendmail();
                                                            holder.bookmarkunselected.setVisibility(View.GONE);
                                                            holder.bookmarkselected.setVisibility(View.GONE);
                                                            holder.waitclock.setVisibility(View.VISIBLE);
                                                            Snackbar snackbar = Snackbar.make(context, view, "Regular Doctor Request has been sent to "+holder.Doctor_Name_previous.getText().toString()+", please wait till approval.", Snackbar.LENGTH_LONG);
                                                            snackbar.show();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{
                                    String _uid_ = GoogleSignIn.getLastSignedInAccount(context).getId();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(_uid_);
                                    RegularDoctorRequest request = new RegularDoctorRequest("waiting",_uid_);
                                    databaseReference.setValue(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("token");
                                            databaseReference1.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String token = snapshot.getValue(String.class);
                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(context).getId());
                                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            String name = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                            FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token,name+" has sent you request",name+" has sent you request to become their regular doctor",context,activity,"RegularPatient",_uid_);
                                                            notificationsSender.SendNotifications();
                                                            holder.bookmarkunselected.setVisibility(View.GONE);
                                                            holder.bookmarkselected.setVisibility(View.GONE);
                                                            holder.waitclock.setVisibility(View.VISIBLE);
                                                            Snackbar snackbar = Snackbar.make(context, view, "Regular Doctor Request has been sent to "+holder.Doctor_Name_previous.getText().toString()+", please wait till approval.", Snackbar.LENGTH_LONG);
                                                            snackbar.show();
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
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
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
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            private void sendmail() {
                if (GoogleSignIn.getLastSignedInAccount(context)==null){
                    String uid_ = FirebaseAuth.getInstance().getUid();
                    if (uid_!=null){
                        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid_).child("email");
                        d.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String mail = snapshot.getValue(String.class);
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                        String message = "Regular Doctor Reuqest Has Been Sent";
                                        String Subject = "Your Regular Doctor Reuqest Has Been Sent to Dr. "+doctorname;
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
                }else {
                    String uid_ = GoogleSignIn.getLastSignedInAccount(context).getId().toString();
                    if (uid_!=null){
                        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid_).child("email");
                        d.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String mail = snapshot.getValue(String.class);
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                        String message = "Regular Doctor Reuqest Has Been Sent";
                                        String Subject = "Your Regular Doctor Reuqest Has Been Sent to Dr. "+doctorname;
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
        holder.bookmarkselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(context);
                builder.setMessage("By removing "+holder.Doctor_Name_previous.getText().toString()+" from regular doctor list, you will not be able to take advantage of remote patient monitoring feature.");
                builder.setTitle("Are you sure?");
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Confirm",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
                                    String _uid_ = FirebaseAuth.getInstance().getUid();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(_uid_);
                                    databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            holder.bookmarkunselected.setVisibility(View.VISIBLE);
                                            holder.bookmarkselected.setVisibility(View.GONE);
                                            Snackbar snackbar = Snackbar.make(context, view, holder.Doctor_Name_previous.getText().toString()+" has been removed from your regular doctors list", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Snackbar snackbar = Snackbar.make(context, view, e.getMessage(), Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    });
                                }else{
                                    String _uid_ =GoogleSignIn.getLastSignedInAccount(context).getId();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(_uid_);
                                    databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            holder.bookmarkunselected.setVisibility(View.VISIBLE);
                                            holder.bookmarkselected.setVisibility(View.GONE);
                                            Snackbar snackbar = Snackbar.make(context, view, holder.Doctor_Name_previous.getText().toString()+" has been removed from your regular doctors list", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Snackbar snackbar = Snackbar.make(context, view, e.getMessage(), Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    });
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
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.waitclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(context);
                builder.setMessage("Unsent Regular Doctor Request");
                builder.setTitle("Unsent Regular Doctor Request?");
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();

                                if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
                                    String _uid_ = FirebaseAuth.getInstance().getUid();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(_uid_);
                                    databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            holder.bookmarkunselected.setVisibility(View.VISIBLE);
                                            holder.bookmarkselected.setVisibility(View.GONE);
                                            holder.waitclock.setVisibility(View.GONE);
                                            Snackbar snackbar = Snackbar.make(context, view, "Regular Doctor Request Unsent", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Snackbar snackbar = Snackbar.make(context, view, e.getMessage(), Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    });
                                }else{
                                    String _uid_ =GoogleSignIn.getLastSignedInAccount(context).getId();
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(uid).child(_uid_);
                                    databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            holder.bookmarkunselected.setVisibility(View.VISIBLE);
                                            holder.bookmarkselected.setVisibility(View.GONE);
                                            holder.waitclock.setVisibility(View.GONE);
                                            Snackbar snackbar = Snackbar.make(context, view, "Regular Doctor Request Unsent", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Snackbar snackbar = Snackbar.make(context, view, e.getMessage(), Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    });
                                }
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
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView image_doctor_previous;
        ImageView waitclock,verifiedcheck_previous,bookmarkunselected,bookmarkselected;
        TextView Doctor_Name_previous,date_previous,doctor_verification_on_list_previous,doctor_specilization_on_list_previous,doctor_institute_on_list;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            waitclock = itemView.findViewById(R.id.waitclock);
            bookmarkselected = itemView.findViewById(R.id.bookmarkselected);
            date_previous = itemView.findViewById(R.id.date_previous);
            bookmarkunselected = itemView.findViewById(R.id.bookmarkunselected);
            verifiedcheck_previous = itemView.findViewById(R.id.verifiedcheck_previous);
            image_doctor_previous = itemView.findViewById(R.id.image_doctor_previous);
            Doctor_Name_previous = itemView.findViewById(R.id.Doctor_Name_previous);
            doctor_specilization_on_list_previous = itemView.findViewById(R.id.doctor_specilization_on_list_previous);
            doctor_institute_on_list = itemView.findViewById(R.id.doctor_institute_on_list);
            doctor_verification_on_list_previous = itemView.findViewById(R.id.doctor_verification_on_list_previous);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,DoctorProfileShow.class);
            intent.putExtra("uid",uid);
            context.startActivity(intent);
        }
    }
}
