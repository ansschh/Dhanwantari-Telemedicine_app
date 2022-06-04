package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegularPatient extends AppCompatActivity {
    TextView personname,address,dob,email,sex;
    CircleImageView photo;
    CardView notificationcard;
    RecyclerView recyclerViewRequests;
    Button declinebutton,acceptbutton;
    RegularDoctorReuqestAdapter adapter;
    ArrayList<DoctorRequestStatus> list;
    ImageView back;
    LinearLayout norequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_patient);
        photo = findViewById(R.id.photo);
        personname = findViewById(R.id.personname);
        back = findViewById(R.id.back);
        address = findViewById(R.id.address);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        norequests = findViewById(R.id.norequests);
        sex = findViewById(R.id.sex);
        notificationcard = findViewById(R.id.notificationcard);
        declinebutton = findViewById(R.id.declinebutton);
        acceptbutton = findViewById(R.id.acceptbutton);
        recyclerViewRequests = findViewById(R.id.recyclerViewRequests);
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
        if (getIntent().getExtras()!=null){
            notificationcard.setVisibility(View.VISIBLE);
            String uid = getIntent().getExtras().getString("uid");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    personname.setText(snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class));
                    address.setText(snapshot.child("address").getValue(String.class));
                    dob.setText(snapshot.child("yearofbirth").getValue(String.class));
                    email.setText(snapshot.child("email").getValue(String.class));
                    sex.setText(snapshot.child("sex").getValue(String.class));
                    Glide.with(RegularPatient.this).load(snapshot.child("imageurl").getValue(String.class)).into(photo);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            declinebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(RegularPatient.this);
                    final EditText edittext = new EditText(RegularPatient.this);
                    alert.setMessage("Please Enter Reason Why? you are declining this request.");
                    alert.setTitle("Reason");
                    alert.setView(edittext);
                    alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String YouEditTextValue = edittext.getText().toString();
                            if (!YouEditTextValue.isEmpty()){
                                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(FirebaseAuth.getInstance().getUid()).child(uid).child("status");
                                    databaseReference1.setValue("rejected");
                                    Toast.makeText(RegularPatient.this, "Request Declined", Toast.LENGTH_SHORT).show();
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
                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(RegularPatient.this,mail,Subject,message);
                                                        javaMailAPI.execute();
                                                        Intent intent = new Intent(RegularPatient.this,MainActivity.class);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }else{
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId()).child(uid).child("status");
                                    databaseReference1.setValue("rejected");
                                    Toast.makeText(RegularPatient.this, "Request Declined", Toast.LENGTH_SHORT).show();
                                    if (uid!=null){
                                        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                                        d.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String mail = snapshot.getValue(String.class);
                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId());
                                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                                        String message = "Dr. "+doctorname+" has unfortunately declined your regular doctor request, because:\n"+YouEditTextValue;
                                                        String Subject = "Regular Doctor Request Declined";
                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(RegularPatient.this,mail,Subject,message);
                                                        javaMailAPI.execute();
                                                        Intent intent = new Intent(RegularPatient.this,MainActivity.class);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
            acceptbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(FirebaseAuth.getInstance().getUid()).child(uid).child("status");
                        databaseReference1.setValue("accepted");
                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RegularPatients").child(FirebaseAuth.getInstance().getUid()).child(uid).child("uid");
                        databaseReference2.setValue(uid);
                        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RegularDoctors").child(uid).child(FirebaseAuth.getInstance().getUid()).child("uid");
                        databaseReference3.setValue(FirebaseAuth.getInstance().getUid());
                        Toast.makeText(RegularPatient.this, "Request Accepted", Toast.LENGTH_SHORT).show();DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                        d.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String mail = snapshot.getValue(String.class);
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
                                databaseReference1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RegularPatients").child(FirebaseAuth.getInstance().getUid()).child(uid).child("uid");
                                        databaseReference2.setValue(uid);
                                        String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                        String message = "Congratulations, Dr. "+doctorname+" has accepted your regular doctor request, Now he is one of your permanent personal doctor.\n with whom you can share your health data to get monitored remotely with your homes comfort.";
                                        String Subject = "Regular Doctor Request Accepted";
                                        JavaMailAPI javaMailAPI = new JavaMailAPI(RegularPatient.this,mail,Subject,message);
                                        javaMailAPI.execute();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId()).child(uid).child("status");
                        databaseReference1.setValue("accepted");
                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RegularPatients").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId()).child(uid).child("uid");
                        databaseReference2.setValue(uid);
                        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("RegularDoctors").child(uid).child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId()).child("uid");
                        databaseReference3.setValue(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId());
                        Toast.makeText(RegularPatient.this, "Request Accpeted", Toast.LENGTH_SHORT).show();
                        if (uid!=null){
                            DatabaseReference d = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("email");
                            d.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("RegularPatients").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId()).child(uid).child("uid");
                                    databaseReference2.setValue(uid);
                                    String mail = snapshot.getValue(String.class);
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId());
                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String doctorname = snapshot.child("first_name").getValue(String.class)+" "+snapshot.child("last_name").getValue(String.class);
                                            String message = "Congratulations, Dr. "+doctorname+" has accepted your regular doctor request, Now he is one of your permanent personal doctor.\n with whom you can share your health data to get monitored remotely with your homes comfort.";
                                            String Subject = "Regular Doctor Request Accepted";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(RegularPatient.this,mail,Subject,message);
                                            javaMailAPI.execute();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(RegularPatient.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }
            });
        }
        else{
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            list = new ArrayList<>();
            recyclerViewRequests.setVisibility(View.VISIBLE);
            recyclerViewRequests.setHasFixedSize(true);
            recyclerViewRequests.setLayoutManager(new LinearLayoutManager(RegularPatient.this));
            adapter = new RegularDoctorReuqestAdapter(list,RegularPatient.this,RegularPatient.this);
            recyclerViewRequests.setAdapter(adapter);
            notificationcard.setVisibility(View.GONE);
            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(FirebaseAuth.getInstance().getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            DoctorRequestStatus doctorRequestStatus = new DoctorRequestStatus();
                            doctorRequestStatus.setStatus(dataSnapshot.child("status").getValue(String.class));
                            Toast.makeText(RegularPatient.this, dataSnapshot.child("status").getValue(String.class), Toast.LENGTH_SHORT).show();
                            doctorRequestStatus.setUseruid(dataSnapshot.child("useruid").getValue(String.class));
                            list.add(doctorRequestStatus);
                        }
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegularPatient.this, "Fuck You", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SentRegularDoctorRequests").child(GoogleSignIn.getLastSignedInAccount(RegularPatient.this).getId());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            DoctorRequestStatus doctorRequestStatus = new DoctorRequestStatus();
                            doctorRequestStatus.setStatus(dataSnapshot.child("status").getValue(String.class));
                            doctorRequestStatus.setUseruid(dataSnapshot.child("useruid").getValue(String.class));
                            list.add(doctorRequestStatus);
                        }
                        adapter.notifyDataSetChanged();
                        if (list.size()<=0){
                            norequests.setVisibility(View.VISIBLE);
                            recyclerViewRequests.setVisibility(View.GONE);
                        }
                        progressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegularPatient.this, "Fuck You", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}