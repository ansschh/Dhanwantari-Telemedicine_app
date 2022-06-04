package com.kanad.health;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.api.Page;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Prescription extends AppCompatActivity {
    Button submitprescription;
    EditText founddisease,drugname,dose,frequency,duration_of_order;
    SignaturePad signature_pad;
    TextView clear,uu,uu1;
    Bitmap bitmap;
    String identification;
    int pagewidth = 1200;
    Bitmap bmp,scaledbmp;
    String Pdf_Url;
    String DateOfBirth,PatientAddress,State,District,doctoraddress1;
    String doctors_address,Doctor_License_Number,doctor_name,institute,patientaddress1,degree,doctor_phoneNumber,nameofdoctor_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        submitprescription = findViewById(R.id.submitprescription);
        founddisease = findViewById(R.id.founddisease);
        signature_pad = findViewById(R.id.signature_pad);
        uu = findViewById(R.id.uu);
        uu1 = findViewById(R.id.uu1);
        drugname = findViewById(R.id.drugname);
        ImageView back;
        back = findViewById(R.id.back);
        uu.setText("");
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
        dose = findViewById(R.id.dose);
        frequency = findViewById(R.id.frequency);
        clear = findViewById(R.id.clear);
        duration_of_order = findViewById(R.id.duration_of_order);
        Bundle bundle = getIntent().getExtras();
        String PATIENT_NAME = bundle.getString("Name");
        String PATIENT_AGE = bundle.getString("Age");
        String PATIENT_GENDER = bundle.getString("Gender");
        ActivityCompat.requestPermissions(this,new String[]{
                WRITE_EXTERNAL_STORAGE
        },PackageManager.PERMISSION_GRANTED);

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    nameofdoctor_ = "Dr. "+snapshot.child(GoogleSignIn.getLastSignedInAccount(Prescription.this).getId()).child("first_name").getValue(String.class)+" "+snapshot.child(GoogleSignIn.getLastSignedInAccount(Prescription.this).getId()).child("last_name").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    nameofdoctor_ = "Dr. "+snapshot.child(FirebaseAuth.getInstance().getUid()).child("first_name").getValue(String.class)+" "+snapshot.child(FirebaseAuth.getInstance().getUid()).child("last_name").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DAY_OF_MONTH);
        month = month+1;
        String date_ = String.valueOf(date)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
        ProgressDialog progressDialog = new ProgressDialog(Prescription.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        String uid = bundle.getString("uid");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DateOfBirth = snapshot.child("dateofbirth").getValue(String.class);
                PatientAddress = snapshot.child("states").getValue(String.class)+", "+snapshot.child("districts").getValue(String.class);
                patientaddress1 = snapshot.child("address").getValue(String.class);
                String State = snapshot.child("states").getValue(String.class);
                String District = snapshot.child("districts").getValue(String.class);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(State).child(District);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (GoogleSignIn.getLastSignedInAccount(Prescription.this)==null){
                            String user_id = FirebaseAuth.getInstance().getUid();
                            doctor_name = snapshot.child(user_id).child("EducationQualification").child("doctor_Name").getValue(String.class);
                            Doctor_License_Number = snapshot.child(user_id).child("Medical Verification").child("reg_Numer").getValue(String.class);
                            institute = snapshot.child(user_id).child("EducationQualification").child("institute_").getValue(String.class);
                            degree = snapshot.child(user_id).child("EducationQualification").child("degree_").getValue(String.class);
                            DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users").child(user_id);
                            databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    doctors_address = doctor_name+", \n"+State+", \n"+District;
                                    doctoraddress1 = snapshot.child("address").getValue(String.class);
                                    doctor_phoneNumber = snapshot.child(user_id).child("mobilenumber").getValue(String.class);
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }else{
                            String user_id = GoogleSignIn.getLastSignedInAccount(Prescription.this).getId();
                            doctor_name = snapshot.child(user_id).child("EducationQualification").child("doctor_Name").getValue(String.class);
                            Doctor_License_Number = snapshot.child(user_id).child("Medical Verification").child("reg_Numer").getValue(String.class);
                            institute = snapshot.child(user_id).child("EducationQualification").child("institute_").getValue(String.class);
                            degree = snapshot.child(user_id).child("EducationQualification").child("degree_").getValue(String.class);
                            DatabaseReference databaseReference_ = FirebaseDatabase.getInstance().getReference("Users");
                            databaseReference_.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    doctors_address = doctor_name+",\n "+snapshot.child(user_id).child("states").getValue(String.class)+", \n"+snapshot.child(user_id).child("districts").getValue(String.class)+", \n"+snapshot.child(user_id).child("address").getValue(String.class);
                                    doctor_phoneNumber = snapshot.child(user_id).child("mobilenumber").getValue(String.class);
                                    doctoraddress1 = snapshot.child("address").getValue(String.class);
                                    uu.setText(doctoraddress1);
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(Prescription.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Prescription.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Prescription.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signature_pad.clear();
            }
        });
        ProgressDialog progressDialog1 = new ProgressDialog(Prescription.this);
        progressDialog1.setMessage("Sending Prescription To Patient");
        progressDialog1.setCanceledOnTouchOutside(false);
        submitprescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = signature_pad.getSignatureBitmap();
                String Disease = founddisease.getText().toString();
                String DrugName = drugname.getText().toString();
                String Dose = dose.getText().toString();
                String Frequency = frequency.getText().toString();
                String Duration = duration_of_order.getText().toString();
                if (!Disease.isEmpty()){
                    if (!DrugName.isEmpty()){
                        if (!Dose.isEmpty()){
                            if (!Frequency.isEmpty()){
                                if (!Duration.isEmpty()){
                                    createPDF();
                                    progressDialog1.show();
                                }else{
                                    TextInputLayout duration;
                                    duration = findViewById(R.id.duration);
                                    duration.setError("");
                                }
                            }else{
                                TextInputLayout frequency_;
                                frequency_ = findViewById(R.id.frequency_);
                                frequency_.setError("");
                            }
                        }else{
                            TextInputLayout dose_;
                            dose_ = findViewById(R.id.dose_);
                            dose_.setError("");
                        }
                    }else{
                        TextInputLayout drug_;
                        drug_ = findViewById(R.id.drug_);
                        drug_.setError("");
                    }
                }else{
                    TextInputLayout _disease_;
                    _disease_ = findViewById(R.id._disease_);
                    _disease_.setError("");
                }
            }
            private void createPDF() {
                String date__  = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+String.valueOf(calendar.get(Calendar.MONTH)+1)+String.valueOf(calendar.get(Calendar.YEAR));
                String date__1  = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"/"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"/"+String.valueOf(calendar.get(Calendar.YEAR));
                PdfDocument pdfDocument = new PdfDocument();
                Paint paint = new Paint();
                Paint title = new Paint();
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200,1800,1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                Canvas canvas = page.getCanvas();
                String _name_ = "Patient Name: "+PATIENT_NAME;
                String dob = "Patient Date Of Birth/Age: "+DateOfBirth;
                String address = "Patient Address: "+PatientAddress;
                paint.setColor(Color.BLACK);
                String age = PATIENT_AGE;
                String date = "Date: "+date_;
                String doctorid = "Prescriber ID: "+Doctor_License_Number;
                long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
//                Image
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.imggg);
                scaledbmp = Bitmap.createScaledBitmap(bmp,1200,1800,false);
                canvas.drawBitmap(scaledbmp,0,0,paint);
//                Title
                title.setTextAlign(Paint.Align.CENTER);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                title.setTextSize(70);
                canvas.drawText("Prescription",pagewidth/2,370,title);
//                Doctor Information
                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(doctors_address,1160,50,paint);
//                Doctor Information
                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(institute,1160,90,paint);
//                Doctor Information
                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(degree,1160,130,paint);
//                Doctor Information
                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(uu.getText().toString(),1160,170,paint);
//                Doctor Information
                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("+91 "+doctor_phoneNumber,1160,190,paint);

//                Line
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                canvas.drawRect(20,280,pagewidth-20,1800-30,paint);
//                Line
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                canvas.drawRect(20,280,pagewidth-20,410,paint);

//                PatientDetails
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText(_name_,40,520,title);
//                PatientDetails
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText(dob+", "+age,40,560,title);
//                PatientDetails
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText(address,40,600,title);
//                PatientDetails
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText(patientaddress1,40,640,title);
//                PatientDetails
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText("Date: "+date_,40,680,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText("Disease: "+founddisease.getText().toString(),40,775,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText("Rx: "+drugname.getText().toString(),40,815,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText("Sig: "+dose.getText().toString(),40,855,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText("Disp: "+frequency.getText().toString(),40,895,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(35f);
                canvas.drawText("Duration of Order: "+duration_of_order.getText().toString(),40,935,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.CENTER);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(25f);
                canvas.drawText("This is a auto generated prescription",600,1600,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.CENTER);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(25f);
                canvas.drawText("through online medical management app, this prescription is prepared by a verified doctor",600,1630,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.CENTER);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(25f);
                canvas.drawText("It is totally usable and can be used both online and offline.",600,1660,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.CENTER);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
                title.setTextSize(25f);
                canvas.drawText(" For more download dhanwantari app, from indian app store",600,1690,title);
//                Prescriptions
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                title.setTextSize(35f);
                canvas.drawText("Prescription ID: "+number,40,1200,title);
//                Image
                scaledbmp = Bitmap.createScaledBitmap(bitmap,500,230,false);
                canvas.drawBitmap(scaledbmp,635,1210,paint);
//                Signature
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                title.setTextSize(35f);
                canvas.drawText("Prescriber Signature: ",410,1450,title);
//                License Number
                title.setTextAlign(Paint.Align.LEFT);
                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                title.setTextSize(35f);
                canvas.drawText("Prescriber's Licence Number: "+ Doctor_License_Number,410,1500,title);
//                Line
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(2);
                canvas.drawRect(755,1450,1135,1445,paint);
                pdfDocument.finishPage(page);
                File file = new File(Environment.getExternalStorageDirectory(), "Prescription.pdf");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    String identification = user.getUid();
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference("Prescriptions").child(uid).child(date__);
                    storageReference.putFile(Uri.fromFile(file))
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(Prescription.this, "Prescription Sent", Toast.LENGTH_SHORT).show();
                                    progressDialog1.dismiss();
                                    ProgressDialog progressDialog3 = new ProgressDialog(Prescription.this);
                                    progressDialog3.setMessage("Please Wait...");
                                    progressDialog3.setCanceledOnTouchOutside(false);
                                    progressDialog3.show();
                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                    storageRef.child("Prescriptions/"+uid+"/"+date__).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Pdf_Url = uri.toString();
                                            DatabaseReference databaseReference112211  = FirebaseDatabase.getInstance().getReference("Prescriptions").child(uid).child("Prescriptions").child(date__).child("PrescriptionLink");
                                            databaseReference112211.setValue(Pdf_Url);
                                            DatabaseReference databaseReference112211_  = FirebaseDatabase.getInstance().getReference("Prescriptions").child(uid).child("Prescriptions").child(date__).child("Date");
                                            databaseReference112211_.setValue(date__1);
                                            DatabaseReference databaseReference___ = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                                            databaseReference___.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String mail = snapshot.child("email").getValue(String.class);
                                                    String message = "Congratulations, "+nameofdoctor_+" Saw Your Case, Below Is Your Prescription Report.\nYou can show this prescription to your nearest Medical Store or use it on Amazon, Pharmeasy, etc to get prescribed medicine\n"+"Download Your Prescription Report From: \n"+Pdf_Url;
                                                    String Subject = "Congratulations, Your Prescription Is Available";
                                                    progressDialog3.dismiss();
                                                    JavaMailAPI javaMailAPI = new JavaMailAPI(Prescription.this,mail,Subject,message);
                                                    javaMailAPI.execute();
                                                    AlertDialog.Builder builder
                                                            = new AlertDialog
                                                            .Builder(Prescription.this);
                                                    builder.setMessage("Prescription Sent to your patient, Successfully. Come here again to change anything from prescription.");
                                                    builder.setTitle("Prescription Sent to your patient, Successfully");
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
                                                                    Intent intent = new Intent(Prescription.this,MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(Prescription.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                    progressDialog3.dismiss();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            Toast.makeText(Prescription.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog3.dismiss();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog1.dismiss();
                            Toast.makeText(Prescription.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            progressDialog1.setMessage("Sending: "+progress);
                            progressDialog1.show();
                        }
                    });
                }else{
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference("Prescriptions").child(uid).child(date__);
                    storageReference.putFile(Uri.fromFile(file))
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(Prescription.this, "Prescription Sent", Toast.LENGTH_SHORT).show();
                                    progressDialog1.dismiss();
                                    ProgressDialog progressDialog3 = new ProgressDialog(Prescription.this);
                                    progressDialog3.setMessage("Please Wait...");
                                    progressDialog3.setCanceledOnTouchOutside(false);
                                    progressDialog3.show();
                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                    storageRef.child("Prescriptions/"+uid+"/"+date__).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Pdf_Url = uri.toString();
                                            DatabaseReference databaseReference112211  = FirebaseDatabase.getInstance().getReference("Prescriptions").child(uid).child("Prescriptions").child(date__).child("PrescriptionLink");
                                            databaseReference112211.setValue(Pdf_Url);
                                            DatabaseReference databaseReference112211_  = FirebaseDatabase.getInstance().getReference("Prescriptions").child(uid).child("Prescriptions").child(date__).child("Date");
                                            databaseReference112211_.setValue(date__1);
                                            DatabaseReference databaseReference___ = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                                            databaseReference___.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String mail = snapshot.child("email").getValue(String.class);
                                                    String message = "Congratulations, "+nameofdoctor_+" Saw Your Case, Below Is Your Prescription Report.\nYou can show this prescription to your nearest Medical Store or use it on Amazon, Pharmeasy, etc to get prescribed medicine\n"+"Download Your Prescription Report From: \n"+Pdf_Url;
                                                    String Subject = "Congratulations, Your Prescription Is Available";
                                                    progressDialog3.dismiss();
                                                    JavaMailAPI javaMailAPI = new JavaMailAPI(Prescription.this,mail,Subject,message);
                                                    javaMailAPI.execute();
                                                    AlertDialog.Builder builder
                                                            = new AlertDialog
                                                            .Builder(Prescription.this);
                                                    builder.setMessage("Prescription Sent to your patient, Successfully. Come here again to change anything from prescription.");
                                                    builder.setTitle("Prescription Sent to your patient, Successfully");
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
                                                                    Intent intent = new Intent(Prescription.this,MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(Prescription.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                    progressDialog3.dismiss();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            Toast.makeText(Prescription.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog3.dismiss();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog1.dismiss();
                            Toast.makeText(Prescription.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            progressDialog1.setMessage("Sending: "+progress);
                            progressDialog1.show();
                        }
                    });
                }
                try {
                    pdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pdfDocument.close();
            }
        });
    }
}