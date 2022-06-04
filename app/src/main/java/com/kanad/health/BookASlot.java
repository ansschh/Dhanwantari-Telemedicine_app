package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
public class BookASlot extends AppCompatActivity {
    RadioButton slot1_,slot2_,slot3_,slot4_,slot5_,slot6_,slot7_,slot8_,slot9_,slot10_;
    Button selectdate;
    String selectedText;
    TextView noslots;
    ImageView noslots2;
    TextView mDateDisplay,nextbutton7;
    TextView nextbuttoninfinity,backbutton11;
    RadioGroup radiogroup;
    String date__;
    String slot1,slot2,slot3,slot4,slot5,slot6,slot7,slot8,slot9,slot10;
    String endingtime;
    String doctorname_;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    DatePickerDialog.OnDateSetListener dateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_aslot);
        Bundle extras = getIntent().getExtras();

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

        String uid = extras.getString("uid");
        radiogroup = findViewById(R.id.radiogroup);
        nextbuttoninfinity = findViewById(R.id.nextbuttoninfinity);
        String State = extras.getString("State");
        String District = extras.getString("District");
        slot1_ = findViewById(R.id.slot1_);
        slot2_ = findViewById(R.id.slot2_);
        backbutton11 = findViewById(R.id.backbutton11);
        mDateDisplay = findViewById(R.id.mDateDisplay);
        nextbutton7 = findViewById(R.id.nextbutton7);
        noslots = findViewById(R.id.noslots);
        noslots2 = findViewById(R.id.noslots2);
        selectdate = findViewById(R.id.selectdate);
        slot3_ = findViewById(R.id.slot3_);
        slot4_ = findViewById(R.id.slot4_);
        slot5_ = findViewById(R.id.slot5_);
        slot6_ = findViewById(R.id.slot6_);
        slot7_ = findViewById(R.id.slot7_);
        slot8_ = findViewById(R.id.slot8_);
        slot9_ = findViewById(R.id.slot9_);
        slot10_ = findViewById(R.id.slot10_);
        backbutton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DAY_OF_MONTH);
        date__ = null;
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookASlot.this,
                        android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth
                        ,onDateSetListener,year,month,date
                );
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String _date__ = String.valueOf(i2)+String.valueOf(i1)+String.valueOf(i);
                String _date_ = String.valueOf(i2)+"-"+String.valueOf(i1)+"-"+String.valueOf(i);
                SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = inFormat.parse(_date_);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                String dayOfTheWeek = outFormat.format(date);
                Toast.makeText(BookASlot.this, dayOfTheWeek, Toast.LENGTH_SHORT).show();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                reference.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String s1 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_1").getValue(String.class);
                        String s2 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_2").getValue(String.class);
                        String s3 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_3").getValue(String.class);
                        String s4 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_4").getValue(String.class);
                        String s5 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_5").getValue(String.class);
                        String s6 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_6").getValue(String.class);
                        String s7 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_7").getValue(String.class);
                        String s8 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_8").getValue(String.class);
                        String s9 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_9").getValue(String.class);
                        String s10 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("start_TIME_10").getValue(String.class);
                        String e1 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_1").getValue(String.class);
                        String e2 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_2").getValue(String.class);
                        String e3 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_3").getValue(String.class);
                        String e4 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_4").getValue(String.class);
                        String e5 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_5").getValue(String.class);
                        String e6 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_6").getValue(String.class);
                        String e7 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_7").getValue(String.class);
                        String e8 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_8").getValue(String.class);
                        String e9 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_9").getValue(String.class);
                        String e10 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_10").getValue(String.class);
                        String off1 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("off_DAY_1").getValue(String.class);
                        String off2 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("off_DAY_2").getValue(String.class);
                        String off3 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("off_DAY_3").getValue(String.class);
                        String off4 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("off_DAY_4").getValue(String.class);
                        String off5 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("off_DAY_5").getValue(String.class);
                        String off6 = snapshot.child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("off_DAY_6").getValue(String.class);
                        slot1 = s1+" - "+e1;
                        slot2 = s2+" - "+e2;
                        slot3 = s3+" - "+e3;
                        slot4 = s4+" - "+e4;
                        slot5 = s5+" - "+e5;
                        slot6 = s6+" - "+e6;
                        slot7 = s7+" - "+e7;
                        slot8 = s8+" - "+e8;
                        slot9 = s9+" - "+e9;
                        slot10 = s10+" - "+e10;
                        mDateDisplay.setText("Your Selected Date is "+_date_+", "+dayOfTheWeek);
                        mDateDisplay.setVisibility(View.VISIBLE);
                        if (e1!=null || e2!=null || e3!=null || e4!=null || e5!=null || e6!=null || e7!=null || e8!=null || e9!=null || e10!=null){
                            noslots.setVisibility(View.GONE);
                            noslots2.setVisibility(View.GONE);
                            if (!s1.isEmpty() && !e1.isEmpty()){
                                slot1_.setText(slot1);
                                slot1_.setVisibility(View.VISIBLE);
                            }if (!s2.isEmpty() && !e2.isEmpty()){
                                slot2_.setText(slot2);
                                slot2_.setVisibility(View.VISIBLE);
                            }if (!s3.isEmpty() && !e3.isEmpty()){
                                slot3_.setText(slot3);
                                slot3_.setVisibility(View.VISIBLE);
                            }if (!s4.isEmpty() && !e4.isEmpty()){
                                slot4_.setText(slot4);
                                slot4_.setVisibility(View.VISIBLE);
                            }if (!s5.isEmpty() && !e5.isEmpty()){
                                slot5_.setText(slot5);
                                slot5_.setVisibility(View.VISIBLE);
                            }if (!s6.isEmpty() && !e6.isEmpty()){
                                slot6_.setText(slot6);
                                slot6_.setVisibility(View.VISIBLE);
                            }if (!s7.isEmpty() && !e7.isEmpty()){
                                slot7_.setText(slot7);
                                slot7_.setVisibility(View.VISIBLE);
                            }if (!s8.isEmpty() && !e8.isEmpty()){
                                slot8_.setText(slot1);
                                slot8_.setVisibility(View.VISIBLE);
                            }if (!s9.isEmpty() && !e9.isEmpty()){
                                slot9_.setText(slot1);
                                slot9_.setVisibility(View.VISIBLE);
                            }if (!s10.isEmpty() && !e10.isEmpty()){
                                slot10_.setText(slot1);
                                slot10_.setVisibility(View.VISIBLE);
                            }
                            nextbutton7.setEnabled(true);
                        }else{
                            slot1_.setVisibility(View.GONE);
                            nextbutton7.setEnabled(false);
                            slot2_.setVisibility(View.GONE);
                            slot3_.setVisibility(View.GONE);
                            slot4_.setVisibility(View.GONE);
                            slot5_.setVisibility(View.GONE);
                            slot6_.setVisibility(View.GONE);
                            mDateDisplay.setVisibility(View.GONE);
                            slot7_.setVisibility(View.GONE);
                            slot8_.setVisibility(View.GONE);
                            slot9_.setVisibility(View.GONE);
                            slot10_.setVisibility(View.GONE);
                            noslots.setVisibility(View.VISIBLE);
                            noslots2.setVisibility(View.VISIBLE);
                        }

                        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                                View radioButton = radioGroup.findViewById(radioButtonID);
                                int idx = radioGroup.indexOfChild(radioButton);
                                RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
                                String selectedText = r.getText().toString();
                                nextbuttoninfinity.setText(selectedText);
                            }
                        });
                        nextbutton7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!nextbuttoninfinity.getText().toString().isEmpty()){
                                    String mysourcestring = nextbuttoninfinity.getText().toString();
                                    String substr=mysourcestring.substring(10);
                                    if (mysourcestring.length()>10){
                                        endingtime = mysourcestring.substring(11);
                                    }
                                    if (e1.equalsIgnoreCase(substr) || e1.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_1");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s1,e1,State,District,slot1,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });

                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e2.equalsIgnoreCase(substr) || e2.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_2");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s2,e2,State,District,slot2,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e3.equalsIgnoreCase(substr) || e3.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_3");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s3,e3,State,District,slot3,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e4.equalsIgnoreCase(substr) || e4.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_4");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s4,e4,State,District,slot4,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e5.equalsIgnoreCase(substr) || e5.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_5");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s5,e5,State,District,slot5,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });

                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e6.equalsIgnoreCase(substr) || e6.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_6");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s6,e6,State,District,slot6,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("Start Time",s1);
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e7.equalsIgnoreCase(substr) || e7.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_7");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s7,e7,State,District,slot7,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e8.equalsIgnoreCase(substr) || e8.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_8");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s8,e8,State,District,slot8,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e9.equalsIgnoreCase(substr) || e9.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_9");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        intent.putExtra("Start Time",s1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s9,e9,State,District,slot9,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else if (e10.equalsIgnoreCase(substr) || e10.equalsIgnoreCase(endingtime)){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("Schedule Timings").child(_date__).child("end_TIME_10");
                                        reference.setValue("");
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(BookASlot.this);
                                        builder.setMessage("Congratulations, Your Slot Has Been Booked. Join The Meeting With Your Doctor On Time Through The App");
                                        builder.setTitle("Congratulations, Your Slot Has Been Booked");
                                        builder.setCancelable(false);
                                        builder.setNegativeButton(
                                                "Ok",
                                                new DialogInterface
                                                        .OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which)
                                                    {
                                                        Intent intent = new Intent(BookASlot.this,BookedSLots.class);
                                                        intent.putExtra("uid",uid);
                                                        intent.putExtra("State",State);
                                                        intent.putExtra("District",District);
                                                        intent.putExtra("Booked Slot",slot1);
                                                        sendEmail();
                                                        DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String _uid_;
                                                                String NAME_;
                                                                String GENDER_;
                                                                String AGE_;
                                                                String imageurl;
                                                                Date c = Calendar.getInstance().getTime();
                                                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                                                String formattedDate = df.format(c);
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    _uid_ = FirebaseAuth.getInstance().getUid();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }else{
                                                                    _uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId();
                                                                    NAME_ = snapshot.child(_uid_).child("first_name").getValue(String.class) +" "+snapshot.child(_uid_).child("last_name").getValue(String.class);
                                                                    GENDER_ = snapshot.child(_uid_).child("sex").getValue(String.class);
                                                                    AGE_ = snapshot.child(_uid_).child("yearofbirth").getValue(String.class);
                                                                    imageurl = snapshot.child(_uid_).child("imageurl").getValue(String.class);
                                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousDoctors").child(_uid_).child("PreviousDoctors").child(uid);
                                                                    PreviousDoctor previousDoctor = new PreviousDoctor(uid,State,District,formattedDate);
                                                                    databaseReference.setValue(previousDoctor);
                                                                }
                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        doctorname_ = snapshot.child(State).child(District).child(uid).child("EducationQualification").child("doctor_Name").getValue(String.class);
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PreviousSlots").child(_uid_).child("PreviousBookedSlots").child(uid);
                                                                        SeeBookedSlot seeBookedSlot;
                                                                        seeBookedSlot = new SeeBookedSlot(s10,e10,State,District,slot10,uid,doctorname_);
                                                                        databaseReference.setValue(seeBookedSlot);
                                                                        PreviousPatientInfo previousPatientInfo = new PreviousPatientInfo(_uid_,NAME_,GENDER_,AGE_,imageurl);
                                                                        DatabaseReference _databaseReference_ = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District).child(uid).child("PreviousPatients").child(_uid_);
                                                                        _databaseReference_.setValue(previousPatientInfo);
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                                                                    String uid_ = FirebaseAuth.getInstance().getUid();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }else{
                                                                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                                                                    if (uid_!=null){
                                                                        String meeting_ID_ = uid.substring(0,5)+uid_.substring(5,10);
                                                                        String mail_of_doctor = snapshot.child(uid).child("email").getValue(String.class);
                                                                        String message = "Congratulations, Someone has booked video conference with you at "+s1+" \nYour Meeting Id is: "+meeting_ID_+" \nYou Can Join The Video Conference Through Dhanwantari App";
                                                                        String Subject = "Congratulations, Video Conference Scheduled";
                                                                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail_of_doctor,Subject,message);
                                                                        javaMailAPI.execute();
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                        intent.putExtra("Start Time",s1);
                                                        intent.putExtra("End Time",e1);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                }else{
                                    Snackbar snackbar = Snackbar.make(BookASlot.this, findViewById(android.R.id.content), "Please Select A Slot To Proceed", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(BookASlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            private void sendEmail() {
                if (GoogleSignIn.getLastSignedInAccount(BookASlot.this)==null){
                    String uid_ = FirebaseAuth.getInstance().getUid();
                    if (uid_!=null){
                        String meeting_ID = uid.substring(0,5)+uid_.substring(5,10);
                        String mail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        String message = "Congratulations, Your Slot Has Been Booked \n"+"Your Meeting Id is: "+meeting_ID+" \nYou Can Join The Video Conference Through This App";
                        String Subject = "Congratulations, Your Slot Has Been Booked";
                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail,Subject,message);
                        javaMailAPI.execute();
                    }
                }else{
                    String uid_ = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getId().toString();
                    if (uid_!=null){
                        String meeting_ID = uid.substring(0,5)+uid_.substring(5,10);
                        String mail = GoogleSignIn.getLastSignedInAccount(BookASlot.this).getEmail();
                        String message = "Congratulations, Your Slot Has Been Booked \n"+"Your Meeting Id is: "+meeting_ID+" \nYou Can Join The Video Conference Through This App";
                        String Subject = "Congratulations, Your Slot Has Been Booked";
                        JavaMailAPI javaMailAPI = new JavaMailAPI(BookASlot.this,mail,Subject,message);
                        javaMailAPI.execute();
                    }
                }
            }
        };
    }
}