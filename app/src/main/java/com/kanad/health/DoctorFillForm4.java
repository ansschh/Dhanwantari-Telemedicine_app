package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DoctorFillForm4 extends AppCompatActivity {
    Button medicalproof;
    TextView nextbutton4;
    TextView imageuploaded,errorforimage;
    private Uri filePath;
    StorageReference storageReference;
    private int PICK_IMAGE_REQUEST = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_fill_frm4);
        medicalproof = findViewById(R.id.medicalproof);
        nextbutton4 = findViewById(R.id.nextbutton4);
        imageuploaded = findViewById(R.id.imageuploaded);
        errorforimage = findViewById(R.id.errorforimage);
        TextView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath!=null){
                    String path = filePath.getPath();
                    if (!path.isEmpty()){
                        Intent tonextDoctorfill = new Intent(DoctorFillForm4.this, DoctorFillForm5.class);
                        startActivity(tonextDoctorfill);
                    }else{
                        Toast.makeText(DoctorFillForm4.this, "Please Upload Photos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DoctorFillForm4.this, "Please Upload Photos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        medicalproof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }
    private void selectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent2 = new Intent();
        intent2.setType("image/*");
        intent2.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent2,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            ProgressDialog ppdd1 = new ProgressDialog(DoctorFillForm4.this);
            ppdd1.setMessage("Uploading");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();if (user != null){
                String user_id = FirebaseAuth.getInstance().getUid().toString();
                storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Medical Verification").child("Image");
                storageReference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                ppdd1.setCanceledOnTouchOutside(false);
                                imageuploaded.setVisibility(View.VISIBLE);
                                ppdd1.dismiss();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                                ppdd1.setMessage("Uploading " + (int)progress + "%");
                                ppdd1.setCanceledOnTouchOutside(false);
                                ppdd1.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                ppdd1.dismiss();
                                errorforimage.setText(e.getMessage());
                                errorforimage.setVisibility(View.VISIBLE);
                            }
                        });
            }else{
                String user_id = GoogleSignIn.getLastSignedInAccount(DoctorFillForm4.this).getId();
                storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Medical Verification").child("Image");
                storageReference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                ppdd1.setCanceledOnTouchOutside(false);
                                imageuploaded.setVisibility(View.VISIBLE);
                                ppdd1.dismiss();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                                ppdd1.setMessage("Uploading " + (int)progress + "%");
                                ppdd1.setCanceledOnTouchOutside(false);
                                ppdd1.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                ppdd1.dismiss();
                                errorforimage.setVisibility(View.VISIBLE);
                            }
                        });
            }
        }
    }
}