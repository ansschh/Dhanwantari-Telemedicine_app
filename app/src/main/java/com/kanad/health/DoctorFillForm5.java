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

public class DoctorFillForm5 extends AppCompatActivity {
    Button eshtablishmentproof;
    TextView eshtablishmentimageuploaded, errorforimage,nextbutton5;
    private int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_fill_form5);
        eshtablishmentproof = findViewById(R.id.eshtablishmentproof);
        eshtablishmentimageuploaded = findViewById(R.id.eshtablishmentimageuploaded);
        nextbutton5 = findViewById(R.id.nextbutton5);
        errorforimage = findViewById(R.id.errorforimage);
        TextView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath!=null){
                    String file = filePath.getPath().toString();
                    if (!file.isEmpty()){
                        Intent tonextDoctorfill = new Intent(DoctorFillForm5.this, DoctorFillForm6.class);
                        startActivity(tonextDoctorfill);
                    }else{
                        Toast.makeText(DoctorFillForm5.this, "Please Upload Image of Proof", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DoctorFillForm5.this, "Please Upload Image Of Proof", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eshtablishmentproof.setOnClickListener(new View.OnClickListener() {
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
            ProgressDialog ppdd1 = new ProgressDialog(DoctorFillForm5.this);
            ppdd1.setMessage("Uploading");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();if (user != null){
                String user_id = FirebaseAuth.getInstance().getUid().toString();
                storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Eshtablishment Proof").child("Image");
                storageReference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                ppdd1.setCanceledOnTouchOutside(false);
                                eshtablishmentimageuploaded.setVisibility(View.VISIBLE);
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
                String user_id = GoogleSignIn.getLastSignedInAccount(DoctorFillForm5.this).getId();
                storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Eshtablishment Proof").child("Image");
                storageReference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                ppdd1.setCanceledOnTouchOutside(false);
                                eshtablishmentimageuploaded.setVisibility(View.VISIBLE);
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