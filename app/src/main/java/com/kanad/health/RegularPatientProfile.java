package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class RegularPatientProfile extends AppCompatActivity {
    String token,username;
    CircleImageView patientimg;
    Button startrpm;
    TextView patientname,nameontop,patientstate,patientdistrict,patientaddress,patientlanguage,patientmobilenumber,patientage,patientemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_patient_profile);
        patientimg = findViewById(R.id.patientimg);
        patientname = findViewById(R.id.patientname);
        patientstate = findViewById(R.id.patientstate);
        patientdistrict = findViewById(R.id.patientdistrict);
        patientaddress = findViewById(R.id.patientaddress);
        startrpm = findViewById(R.id.startrpm);
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
        patientlanguage = findViewById(R.id.patientlanguage);
        nameontop = findViewById(R.id.nameontop);
        patientmobilenumber = findViewById(R.id.patientmobilenumber);
        patientage = findViewById(R.id.patientage);
        patientemail = findViewById(R.id.patientemail);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(bundle.getString("uid"));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    username = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                    patientname.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                    Glide.with(RegularPatientProfile.this).load(snapshot.child("imageurl").getValue(String.class)).into(patientimg);
                    patientstate.setText(snapshot.child("states").getValue(String.class));
                    patientdistrict.setText(snapshot.child("districts").getValue(String.class));
                    patientaddress.setText(snapshot.child("address").getValue(String.class));
                    patientlanguage.setText(snapshot.child("language").getValue(String.class));
                    patientmobilenumber.setText("+91 "+snapshot.child("mobilenumber").getValue(String.class));
                    patientmobilenumber.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            patientmobilenumber.setPaintFlags(patientmobilenumber.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            String number = "+91 "+snapshot.child("mobilenumber").getValue(String.class);
                            intent.setData(Uri.parse("tel:"+number));
                            startActivity(intent);
                        }
                    });
                    patientage.setText(snapshot.child("yearofbirth").getValue(String.class));
                    patientemail.setText(snapshot.child("email").getValue(String.class));
                    String email = snapshot.child("email").getValue(String.class);
                    patientemail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            patientemail.setPaintFlags(patientmobilenumber.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                            intent.putExtra(Intent.EXTRA_EMAIL, email);
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Dhanwantari doctor update");
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    });
                    token = snapshot.child("token").getValue(String.class);
                    nameontop.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class)+"'s Profile");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            String uid;
            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                uid = FirebaseAuth.getInstance().getUid();
            }else{
                uid = GoogleSignIn.getLastSignedInAccount(this).getId();
            }
            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = "Dr. "+snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                    startrpm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(bundle.getString("uid"));
                            RPMRequest request = new RPMRequest(uid,"pending");
                            FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token,name+" has sent you Remote Patient Monitoring request",name+" has sent you request to start remote patient monitoring.\n \nNOTE: Through remote patient monitoring you will be sharing your critical health data to "+name+"which can be misused, so we recommend you to use this feature appropriately.",RegularPatientProfile.this,RegularPatientProfile.this,"RemotePatientMonitoring",uid);
                            notificationsSender.SendNotifications();
                            databaseReference2.setValue(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    DialogTimeoutListener listener = new DialogTimeoutListener();
                                    AlertDialog dialog = new AlertDialog.Builder(RegularPatientProfile.this)
                                            .setTitle("Please wait..")
                                            .setMessage("We have sent remote patient monitoring request to "+username+", Please, wait till "+username+" starts sending data from their end.")
                                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            })
                                            .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .create();
                                    dialog.setOnShowListener(listener);
                                    dialog.setOnDismissListener(listener);
                                    dialog.setCanceledOnTouchOutside(false);
                                    dialog.show();
                                    DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RemotePatientMonitoringRequests").child(uid).child(bundle.getString("uid"));
                                    databaseReference3.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("accepted")){
                                                Intent intent = new Intent(RegularPatientProfile.this,RemotePatientMonitoring.class);
                                                intent.putExtra("uid",bundle.getString("uid"));
                                                startActivity(intent);
                                                dialog.cancel();
                                            }else if (snapshot.child("status").getValue(String.class).equalsIgnoreCase("rejected")){
                                                dialog.dismiss();
                                                android.app.AlertDialog.Builder builder
                                                        = new android.app.AlertDialog
                                                        .Builder(RegularPatientProfile.this);
                                                builder.setMessage("Request that you sent was cancelled by the patient.");
                                                builder.setTitle("Patient Cancelled the request");
                                                builder.setCancelable(false);
                                                builder.setNegativeButton(
                                                        "OK",
                                                        new DialogInterface
                                                                .OnClickListener() {

                                                            @Override
                                                            public void onClick(DialogInterface dialog,
                                                                                int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                android.app.AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(RegularPatientProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegularPatientProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RegularPatientProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}