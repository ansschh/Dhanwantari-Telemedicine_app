package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorFillForm2 extends AppCompatActivity {
    ArrayAdapter<String> Coucils;
    ArrayAdapter<CharSequence> registrationyears;
    AutoCompleteTextView nameofcoucils,registrationyear;
    TextInputLayout registration_input,reg_coucils,reg_year;
    TextView nextbutton2,dummy_of_doctors_name;
    EditText reg_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_fill_form2);
        nameofcoucils = findViewById(R.id.nameofcoucils);
        reg_year = findViewById(R.id.reg_year);
        nextbutton2 = findViewById(R.id.nextbutton2);
        reg_number = findViewById(R.id.reg_number);
        dummy_of_doctors_name = findViewById(R.id.dummy_of_doctors_name);
        reg_coucils = findViewById(R.id.reg_coucils);
        TextView back;
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        registration_input = findViewById(R.id.registration_input);
        registrationyear = findViewById(R.id.registrationyear);
        String[] Registration_Council = {"Andhra Pradesh Medical Council", "Arunachal Pradesh Medical Council", "Assam Medical Council", "Bihar Medical Council", "Chattisgarh Medical Council", "Delhi Medical Council", "Goa Medical Council", "Gujarat Medical Council", "Haryana Medical Council", "Himanchal Pradesh Medical Council", "Jammu & Kashmir Medical Council", "Jharkhand Medical Council", "Karnataka Medical Council", "Madhya Pradesh Medical Council", "Maharashtra Medical Council", "Manipur Medical Council", "Mizoram Medical Council", "Nagaland Medical Council", "Orissa Council of Medical Registration", "Punjab Medical Council", "Rajasthan Medical Council", "Sikkim Medical Council", "Tamil Nadu Medical Council", "Telangana State Medical Council", "Travancore Cochin Medical Council, Trivandrum", "Tripura State Medical Council", "Uttarakhand Medical Council", "Uttar Pradesh Medical Council", "West Bengal Medical Council", "Medical Council of India (MCI)"};
        registrationyears = ArrayAdapter.createFromResource(getApplicationContext(), R.array.years, R.layout.list_item);
        registrationyear.setAdapter(registrationyears);
        Coucils = new ArrayAdapter<String>(DoctorFillForm2.this, R.layout.list_item, Registration_Council);
        nameofcoucils.setAdapter(Coucils);

        Bundle extras = getIntent().getExtras();
        dummy_of_doctors_name.setText(extras.getString("Doctor Name"));

        nextbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!reg_number.getText().toString().isEmpty()){
                    if (!nameofcoucils.getText().toString().isEmpty()){
                        if (!registrationyear.getText().toString().isEmpty()){
                            uploadmoredetailsofdoctor();
                        }else{
                            reg_year.setError("Please Enter Your Registraton Year");
                        }
                    }else{
                        reg_coucils.setError("Please Select Your Registration Council");
                    }
                }else{
                    registration_input.setError("Please Enter Your Registration");
                }
            }
        });

    }

    private void uploadmoredetailsofdoctor() {
        String Reg_Numer = reg_number.getText().toString();
        String Council_Name = nameofcoucils.getText().toString();
        String Reg_Year = registrationyear.getText().toString();
        Bundle bundle = getIntent().getExtras();
        String States = bundle.getString("State");
        String Districts = bundle.getString("District");
        String Doctor_Name = dummy_of_doctors_name.getText().toString();
        if (GoogleSignIn.getLastSignedInAccount(DoctorFillForm2.this)==null){
            String user_id_for_profile = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference doctorInfo = FirebaseDatabase.getInstance().getReference("Doctor").child(States).child(Districts).child(user_id_for_profile).child("Medical Verification");
            MedicalRegistration medicalRegistration = new MedicalRegistration(Reg_Numer, Council_Name, Reg_Year,Doctor_Name);
            doctorInfo.setValue(medicalRegistration).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent toproof = new Intent(DoctorFillForm2.this, DoctorFillForm3.class);
                    startActivity(toproof);
                    Snackbar snackbar = Snackbar.make(DoctorFillForm2.this, findViewById(android.R.id.content), "Information Saved Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DoctorFillForm2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            String user_id_for_profile = GoogleSignIn.getLastSignedInAccount(DoctorFillForm2.this).getId().toString();
            DatabaseReference doctorInfo = FirebaseDatabase.getInstance().getReference("Doctor").child(States).child(Districts).child(user_id_for_profile).child("Medical Verification");
            MedicalRegistration medicalRegistration = new MedicalRegistration(Reg_Numer, Council_Name, Reg_Year,Doctor_Name);
            doctorInfo.setValue(medicalRegistration).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent toproof = new Intent(DoctorFillForm2.this, DoctorFillForm3.class);
                    startActivity(toproof);
                    Snackbar snackbar = Snackbar.make(DoctorFillForm2.this, findViewById(android.R.id.content), "Information Saved Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DoctorFillForm2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}