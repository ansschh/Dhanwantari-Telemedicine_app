package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.IOException;

public class DoctorFillForm3 extends AppCompatActivity {
    TextView instruction,frontimageuploaded,backimageuploaded,errorforimages1,errorforimages2,yesorno;
    Button proofimgbutton,proofimgbutton_back;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    ImageView IDPROOF1,IDPROOF2;
    TextView nextbutton2;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_fill_form3);
        instruction = findViewById(R.id.instruction);
        yesorno = findViewById(R.id.yesorno);
        nextbutton2 = findViewById(R.id.nextbutton2);
        errorforimages2 = findViewById(R.id.errorforimages2);
        errorforimages1 = findViewById(R.id.errorforimage);
        proofimgbutton_back = findViewById(R.id.proofimgbutton_back);
        frontimageuploaded = findViewById(R.id.frontimageuploaded);
        backimageuploaded = findViewById(R.id.backimageuploaded);
        proofimgbutton = findViewById(R.id.proofimgbutton);
        TextView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        instruction.setText("Please upload your identity proof to ensure that the ownership of your profile remains with only you.\n" +
                "Acceptable documents\n" +
                "• Aadhar Card\n" +
                "• Driving License\n" +
                "• Voter Card\n" +
                "• Any other Govt. ID\n");

        nextbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath!=null){
                    String path = filePath.getPath();
                    if (!path.isEmpty()){
                        Intent tonextDoctorfill = new Intent(DoctorFillForm3.this, DoctorFillForm4.class);
                        startActivity(tonextDoctorfill);
                    }else{
                        Toast.makeText(DoctorFillForm3.this, "Please Upload Photos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DoctorFillForm3.this, "Please Upload Photos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        proofimgbutton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backimageuploaded.setVisibility(View.GONE);
                selectImageBack();
            }
            private Uri filepath;
            private void selectImageBack() {
                yesorno.setText("back");
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
        });
        proofimgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frontimageuploaded.setVisibility(View.GONE);
                selectImage();
            }
        });
    }

    private void selectImage() {
            // Defining Implicit Intent to mobile gallery
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(
                            intent,
                            "Select Image from here..."),
                    PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            ProgressDialog ppdd1 = new ProgressDialog(DoctorFillForm3.this);
            ppdd1.setMessage("Uploading");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (yesorno.getText().toString()=="back"){
                if (user != null){
                    String user_id = FirebaseAuth.getInstance().getUid().toString();
                    storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Identity Proof").child("BackImage");
                    storageReference.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ppdd1.setCanceledOnTouchOutside(false);
                                    backimageuploaded.setVisibility(View.VISIBLE);
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
                                    errorforimages2.setText(e.getMessage());
                                    errorforimages2.setVisibility(View.VISIBLE);
                                }
                            });
                }else{
                    String user_id = GoogleSignIn.getLastSignedInAccount(DoctorFillForm3.this).getId();
                    storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Identity Proof").child("BackImage");
                    storageReference.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ppdd1.setCanceledOnTouchOutside(false);
                                    backimageuploaded.setVisibility(View.VISIBLE);
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
                                    errorforimages2.setVisibility(View.VISIBLE);
                                }
                            });
                }
            }else{
                if (user != null){
                    String user_id = FirebaseAuth.getInstance().getUid().toString();
                    storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Identity Proof").child("FrontImage");
                    storageReference.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ppdd1.setCanceledOnTouchOutside(false);
                                    frontimageuploaded.setVisibility(View.VISIBLE);
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
                                    errorforimages1.setText(e.getMessage());
                                    errorforimages1.setVisibility(View.VISIBLE);
                                }
                            });
                }else{
                    String user_id = GoogleSignIn.getLastSignedInAccount(DoctorFillForm3.this).getId();
                    storageReference = FirebaseStorage.getInstance().getReference("Doctors").child(user_id).child("Identity Proof").child("FrontImage");
                    storageReference.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ppdd1.setCanceledOnTouchOutside(false);
                                    frontimageuploaded.setVisibility(View.VISIBLE);
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
                                    errorforimages1.setVisibility(View.VISIBLE);
                                }
                            });
                }
            }
        }
    }
}