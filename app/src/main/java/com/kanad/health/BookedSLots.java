package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookedSLots extends AppCompatActivity {
    TextView bookingid,slot_Timing,starttime,doctornameonslot;
    Button intenttovideocall;
    String meeting_ID ;
    LinearLayout extraswala,empty;
    RecyclerView recycler_view_slot;
    LinearLayout noextras;
    BookedSlotAdapter bookedSlotAdapter;
    TextView gotohomescreen;
    TextView gotohomescreen1;
    ArrayList<GetBookedDoctorInfo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_slots);
        Bundle extras = getIntent().getExtras();
        bookingid = findViewById(R.id.bookingid);
        recycler_view_slot = findViewById(R.id.recycler_view_slot);
        empty = findViewById(R.id.empty);
        extraswala = findViewById(R.id.extras);
        gotohomescreen1 = findViewById(R.id.gotohomescreen1);
        noextras = findViewById(R.id.noextras);
        slot_Timing = findViewById(R.id.slot_Timing);
        gotohomescreen = findViewById(R.id.gotohomescreen);
        starttime = findViewById(R.id.starttime);
        doctornameonslot = findViewById(R.id.doctornameonslot);
        gotohomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookedSLots.this,MainActivity.class);
                startActivity(intent);
            }
        });
        gotohomescreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookedSLots.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView back;
        back = findViewById(R.id.back___);
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
        intenttovideocall = findViewById(R.id.intenttovideocall___);
        if (extras.getString("uid")!=null){
            extraswala.setVisibility(View.VISIBLE);
            noextras.setVisibility(View.GONE);
            String uid = extras.getString("uid");
            String Start_Time = extras.getString("Start Time");
            String End_Time = extras.getString("End Time");
            String State = extras.getString("State");
            String Booked_Slot = extras.getString("Booked Slot");
            String District = extras.getString("District");
            if (GoogleSignIn.getLastSignedInAccount(BookedSLots.this)==null){
                String uid_ = FirebaseAuth.getInstance().getUid();
                if (uid_!=null){
                    bookingid.setText(uid.substring(0,5)+uid_.substring(5,10));
                    meeting_ID = uid.substring(0,5)+uid_.substring(5,10);
                }
            }else{
                String uid_ = GoogleSignIn.getLastSignedInAccount(BookedSLots.this).getId().toString();
                if (uid_!=null){
                    bookingid.setText(uid.substring(0,5)+uid_.substring(5,10));
                    meeting_ID = uid.substring(0,5)+uid_.substring(5,10);
                }
            }
            slot_Timing.setText(Booked_Slot);
            starttime.setText(Start_Time);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                    doctornameonslot.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(BookedSLots.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            intenttovideocall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BookedSLots.this,VideoCallActivity.class);
                    intent.putExtra("MeetingID",meeting_ID);
                    intent.putExtra("Start Time",Start_Time);
                    intent.putExtra("Booked Slot",Booked_Slot);
                    startActivity(intent);
                }
            });
        }else{
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            extraswala.setVisibility(View.GONE);
            noextras.setVisibility(View.VISIBLE);
            String uid_;
            if (GoogleSignIn.getLastSignedInAccount(BookedSLots.this)==null){
                uid_ = FirebaseAuth.getInstance().getUid();
            }else{
                uid_ = GoogleSignIn.getLastSignedInAccount(BookedSLots.this).getId().toString();
            }
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(uid_).child("/PreviousBookedSlots");
            recycler_view_slot.setHasFixedSize(true);
            recycler_view_slot.setLayoutManager(new LinearLayoutManager(this));
            list = new ArrayList<>();
            bookedSlotAdapter = new BookedSlotAdapter(this,list);
            recycler_view_slot.setAdapter(bookedSlotAdapter);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        String district = dataSnapshot.child("district").getValue(String.class);
                        String endtime = dataSnapshot.child("endtime").getValue(String.class);
                        String slot = dataSnapshot.child("slot").getValue(String.class);
                        String starttime = dataSnapshot.child("starttime").getValue(String.class);
                        String state = dataSnapshot.child("state").getValue(String.class);
                        String uid = dataSnapshot.child("uid").getValue(String.class);
                        String Doctorname_ = dataSnapshot.child("doctorname_").getValue(String.class);
                        GetBookedDoctorInfo getBookedDoctor = new GetBookedDoctorInfo();
                        getBookedDoctor.setDistrict(district);
                        getBookedDoctor.setDoctorname_(Doctorname_);
                        getBookedDoctor.setEndtime(endtime);
                        getBookedDoctor.setSlot(slot);
                        getBookedDoctor.setStarttime(starttime);
                        getBookedDoctor.setUid(uid);
                        getBookedDoctor.setState(state);
                        list.add(getBookedDoctor);
                    }
                    bookedSlotAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    if (list.size()==0){
                        empty.setVisibility(View.VISIBLE);
                        extraswala.setVisibility(View.GONE);
                        noextras.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(BookedSLots.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}