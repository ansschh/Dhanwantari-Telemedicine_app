package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorProfileShow extends AppCompatActivity {
    String STATE;
    String DISTRICT,Doctor_Name;
    Button book_a_slot;
    CircleImageView image_on_doctor_profile;
    TextView doctor_name_on_profile,Degree_Status_for_profile,Verification_Status_for_profile,expirience_for_profil_doctor,state_for_profil_doctor,district_for_profil_doctor,specilization_for_profil_doctor,institute_for_profil_doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile_show);
        image_on_doctor_profile = findViewById(R.id.image_on_doctor_profile);
        doctor_name_on_profile = findViewById(R.id.doctor_name_on_profile);
        TextView nameontop;
        nameontop = findViewById(R.id.nameontop_);
        district_for_profil_doctor = findViewById(R.id.district_for_profil_doctor);
        state_for_profil_doctor = findViewById(R.id.state_for_profil_doctor);
        Verification_Status_for_profile = findViewById(R.id.Verification_Status_for_profile);
        book_a_slot = findViewById(R.id.book_a_slot);
        ImageView back1;
        back1 = findViewById(R.id.back);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        expirience_for_profil_doctor = findViewById(R.id.expirience_for_profil_doctor);
        specilization_for_profil_doctor = findViewById(R.id.specilization_for_profil_doctor);
        institute_for_profil_doctor = findViewById(R.id.institute_for_profil_doctor);
        Degree_Status_for_profile = findViewById(R.id.Degree_Status_for_profile);
        Bundle extras = getIntent().getExtras();
        String uid = extras.getString("uid");
        ProgressDialog progressDialog = new ProgressDialog(DoctorProfileShow.this,R.style.Base_Theme_AppCompat_Dialog_MinWidth);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        if (GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this)!=null){
            if (GoogleSignIn.getLastSignedInAccount(this) != null){
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        STATE = snapshot.child(uid).child("states").getValue(String.class);
                        DISTRICT = snapshot.child(uid).child("districts").getValue(String.class);
                        Doctor_Name = "Dr. "+snapshot.child(uid).child("first_name").getValue(String.class) +" "+ snapshot.child(uid).child("last_name").getValue(String.class);
                        doctor_name_on_profile.setText(Doctor_Name);
                        nameontop.setText(Doctor_Name+"'s"+" Profile");
                        if (GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this)!=null){
                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this);
                            String user_id_google = GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this).getId().toString();
                            if (acct != null){
                                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Doctor");
                                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        state_for_profil_doctor.setText(STATE);
                                        district_for_profil_doctor.setText(DISTRICT);
                                        if (STATE!=null && DISTRICT!=null){
                                            if (snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class).equalsIgnoreCase("false")){
                                                Verification_Status_for_profile.setText("Currently Under Review");
                                            }else{
                                                Verification_Status_for_profile.setText("Verified");
                                            }
                                            String image_url_for_doctor = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("image_Url").getValue(String.class);
                                            Glide.with(image_on_doctor_profile.getContext()).load(image_url_for_doctor).into(image_on_doctor_profile);
                                            specilization_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                            String institute = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("institute_").getValue(String.class);
                                            institute_for_profil_doctor.setText(institute);
                                            String degree = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("degree_").getValue(String.class);
                                            Degree_Status_for_profile.setText(degree);
                                            expirience_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("yearsOfExpirience").getValue(String.class));
                                            progressDialog.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(DoctorProfileShow.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }else{
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String user_uid = user.getUid().toString();
                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Doctor");
                            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (STATE!=null && DISTRICT!=null){
                                        state_for_profil_doctor.setText(STATE);
                                        district_for_profil_doctor.setText(DISTRICT);
                                        if (snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class).equalsIgnoreCase("false")){
                                            Verification_Status_for_profile.setText("Currently Under Review");
                                        }else{
                                            Verification_Status_for_profile.setText("Verified");
                                        }
                                        String image_url_for_doctor = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("image_Url").getValue(String.class);
                                        Glide.with(image_on_doctor_profile.getContext()).load(image_url_for_doctor).into(image_on_doctor_profile);
                                        specilization_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                        String institute = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("institute_").getValue(String.class);
                                        institute_for_profil_doctor.setText(institute);
                                        String degree = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("degree_").getValue(String.class);
                                        Degree_Status_for_profile.setText(degree);
                                        expirience_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("yearsOfExpirience").getValue(String.class));
                                        progressDialog.dismiss();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(DoctorProfileShow.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DoctorProfileShow.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    STATE = snapshot.child(uid).child("states").getValue(String.class);
                    DISTRICT = snapshot.child(uid).child("districts").getValue(String.class);
                    Doctor_Name = "Dr. "+snapshot.child(uid).child("first_name").getValue(String.class) +" "+ snapshot.child(uid).child("last_name").getValue(String.class);
                    doctor_name_on_profile.setText(Doctor_Name);
                    nameontop.setText(Doctor_Name+"'s"+" Profile");
                    STATE = snapshot.child(uid).child("states").getValue(String.class);
                    DISTRICT = snapshot.child(uid).child("districts").getValue(String.class);
                    doctor_name_on_profile.setText(Doctor_Name);
                    if (GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this)!=null){
                        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this);
                        String user_id_google = GoogleSignIn.getLastSignedInAccount(DoctorProfileShow.this).getId().toString();
                        if (acct != null){
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (STATE!=null && DISTRICT!=null){
                                        state_for_profil_doctor.setText(STATE);
                                        district_for_profil_doctor.setText(DISTRICT);
                                        if (snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class)=="false"){
                                            Verification_Status_for_profile.setText("Currently Under Review");
                                        }else{
                                            Verification_Status_for_profile.setText("Verified");
                                        }
                                        String image_url_for_doctor = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("image_Url").getValue(String.class);
                                        Glide.with(image_on_doctor_profile.getContext()).load(image_url_for_doctor).into(image_on_doctor_profile);
                                        specilization_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                        institute_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("institute_").getValue(String.class));
                                        Degree_Status_for_profile.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("degree_").getValue(String.class));
                                        expirience_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("yearsOfExpirience").getValue(String.class));
                                        progressDialog.dismiss();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(DoctorProfileShow.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else{
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String user_uid = user.getUid().toString();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (STATE!=null && DISTRICT!=null){
                                    state_for_profil_doctor.setText(STATE);
                                    district_for_profil_doctor.setText(DISTRICT);
                                    if (snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("approved").getValue(String.class)=="false")
                                    {
                                        Verification_Status_for_profile.setText("Currently Under Review");
                                    }else
                                    {
                                        Verification_Status_for_profile.setText("Verified");
                                    }
                                    String image_url_for_doctor = snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("image_Url").getValue(String.class);
                                    Glide.with(image_on_doctor_profile.getContext()).load(image_url_for_doctor).into(image_on_doctor_profile);
                                    specilization_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("specilization_").getValue(String.class));
                                    institute_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("institute_").getValue(String.class));
                                    Degree_Status_for_profile.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("degree_").getValue(String.class));
                                    expirience_for_profil_doctor.setText(snapshot.child(STATE).child(DISTRICT).child(uid).child("EducationQualification").child("yearsOfExpirience").getValue(String.class));
                                    progressDialog.dismiss();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(DoctorProfileShow.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DoctorProfileShow.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        book_a_slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tobookslot = new Intent(DoctorProfileShow.this,BookASlot.class);
                tobookslot.putExtra("uid",uid);
                tobookslot.putExtra("State",STATE);
                tobookslot.putExtra("District",DISTRICT);
                startActivity(tobookslot);
            }
        });
    }
}