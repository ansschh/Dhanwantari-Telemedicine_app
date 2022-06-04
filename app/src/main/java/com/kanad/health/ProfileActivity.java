package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.intellij.lang.annotations.Language;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class ProfileActivity extends AppCompatActivity {
    TextView name_profile,dummy_for_status,dummy_for_district,dummy_for_state;
    EditText First_name, Last_name, mobile_number_profile, password_profile, email_profile, address_profile;
    TextView age_profile, getAge_to_check,logoutfromprofile;
    TextView save_profile_text,dummy_for_uri;
    AutoCompleteTextView I_am_person, STATES;
    String age_check;
    ArrayAdapter<String> adapterItems;
    String imageuri;
    AutoCompleteTextView LANGUAGE;
    String uid;
    String name;
    int age;
    TextView age1, age2;
    ArrayAdapter<String> LANGUAGES;
    String personimageurl;
    String Image_Url;
    ArrayAdapter<String> Name_of_states;
    ArrayAdapter<CharSequence> Name_of_Districts;
    String APPLICATION_STATUS;
    AutoCompleteTextView states;
    String _DISTRICT_,_STATES_;
    ArrayAdapter<String> gender;
    private Uri filePath;
    DatePickerDialog .OnDateSetListener onDateSetListener;
    FirebaseStorage storage;
    StorageReference storageReference;
    AutoCompleteTextView GENDER;
    AutoCompleteTextView DISTRICTS;
    private final int PICK_IMAGE_REQUEST = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        age1 = findViewById(R.id.age1);
        dummy_for_district = findViewById(R.id.dummy_for_district);
        dummy_for_uri = findViewById(R.id.dummy_for_uri);
        age2 = findViewById(R.id.age2);
        dummy_for_status = findViewById(R.id.dummy_for_status);
        dummy_for_state = findViewById(R.id.dummy_for_state);
        logoutfromprofile = findViewById(R.id.logoutfromprofile);
        dummy_for_status.setText("");
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

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("images/"+GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    imageuri = uri.toString();
                    dummy_for_uri.setText(uri.toString());
                    Image_Url = uri.toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }else{
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    imageuri = uri.toString();
                    dummy_for_uri.setText(uri.toString());
                    Image_Url = uri.toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
        }
        else{
            Snackbar snackbar = Snackbar.make(ProfileActivity.this, findViewById(android.R.id.content), "NO INTERNET CONNECTION", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        FirebaseUser user_id_for_profile_picture = FirebaseAuth.getInstance().getCurrentUser();
        ProgressDialog ppdd = new ProgressDialog(ProfileActivity.this);
        ppdd.setCanceledOnTouchOutside(false);
        ppdd.setMessage("Loading");
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        age_profile = findViewById(R.id.age_profile);
        getAge_to_check = findViewById(R.id.age_for_checking);
        age_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                            android.R.style.Theme_Holo_Dialog_MinWidth,
                            onDateSetListener,
                            year, month, day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                }
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = month+1;
                String date = i2+"/"+i1+"/"+i;
                age_profile.setTextColor(getResources().getColor(R.color.black));
                age_profile.setText(date);
                int current_year = Calendar.getInstance().get(Calendar.YEAR);
                age = current_year - i;
                age_check = String.valueOf(age);
                getAge_to_check.setVisibility(View.VISIBLE);
                getAge_to_check.setText(age_check);
                age1.setVisibility(View.VISIBLE);
                age2.setVisibility(View.VISIBLE);
                if (age<18){
                    TextView save_profile_text;
                    save_profile_text = findViewById(R.id.save_profile_text);
                    save_profile_text.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(ProfileActivity.this, findViewById(android.R.id.content), "You are under age you cannot proceed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    save_profile_text.setVisibility(View.VISIBLE);
                }
            }
        };
        if (user_id_for_profile_picture != null) {
            StorageReference ref_for_uri;
            String user_id_firebase = FirebaseAuth.getInstance().getUid().toString();
            ref_for_uri = FirebaseStorage.getInstance().getReference("images").child(user_id_firebase);
            try {
                final File localFile = File.createTempFile(user_id_firebase, "jpg");
                ref_for_uri.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                CircleImageView profile_image;
                                profile_image = findViewById(R.id.profile_image);
                                profile_image.setImageBitmap(bitmap);
                                ppdd.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ppdd.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull FileDownloadTask.TaskSnapshot snapshot) {
                        double progress
                                = (100.0
                                * snapshot.getBytesTransferred()
                                / snapshot.getTotalByteCount());
                        ppdd.setMessage("Recieved " + (int)progress + "%");
                        ppdd.show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // User is signed out
        }
        if (GoogleSignIn.getLastSignedInAccount(ProfileActivity.this)!=null){
            StorageReference ref_for_uri;
            String user_id_google = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString();
            ref_for_uri = FirebaseStorage.getInstance().getReference("images").child(user_id_google);
            try {
                final File localFile = File.createTempFile(user_id_google, "jpg");
                ref_for_uri.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                CircleImageView profile_image;
                                profile_image = findViewById(R.id.profile_image);
                                profile_image.setImageBitmap(bitmap);
                                ppdd.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ppdd.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull FileDownloadTask.TaskSnapshot snapshot) {
                        double progress
                                = (100.0
                                * snapshot.getBytesTransferred()
                                / snapshot.getTotalByteCount());
                        ppdd.setMessage("Recieved " + (int)progress + "%");
                        ppdd.show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Intent i = getIntent();
        String[] sex = {"Male", "Female", "Transgender", "Other"};
        Bundle bundle = i.getExtras();

        String[] name_of_states = {"Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jammu and Kashmir",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharashtra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttarakhand",
                "Uttar Pradesh",
                "West Bengal",
                "Andaman and Nicobar Islands",
                "Chandigarh",
                "Dadra and Nagar Haveli",
                "Daman and Diu",
                "Delhi",
                "Lakshadweep",
                "Puducherry"};
        String[] I_AM = {"I AM A DOCTOR", "I AM A NORMAL PERSON"};
        First_name = findViewById(R.id.First_name);
        name_profile = findViewById(R.id.name_profile);
        Last_name = findViewById(R.id.Last_name);
        mobile_number_profile = findViewById(R.id.mobile_number_profile);
        String urdu1 = "ڈوگری";
        String urdu2 = "سنڌي";
        String urdu3 = "اردو";
        String[] Langs = new String[]{"हिन्दी", "ENGLISH", "বাংলা", "પસાર", "ಕನ್ನಡ", "कॉशुर", "कोंकणी", "മലയാളം", "मणिपुरी", "मराठी", "नेपाली", "ଓଡ଼ିଆ ଭାଷା", "ਪੰਜਾਬੀ", "संस्कृतम्", "தமிழ்", "తెలుగు", "बड़ो", "ᱥᱟᱱᱛᱟᱲᱤ", urdu1.toString(), urdu2.toString(), urdu3.toString()};
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (GoogleSignIn.getLastSignedInAccount(ProfileActivity.this) == null)
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String user_uid = user.getUid().toString();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            Query checkUser = reference.orderByChild(user_uid);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name_profile = findViewById(R.id.name_profile);
                    TextInputLayout Sex_for_profile;
                    Sex_for_profile = findViewById(R.id.Sex_for_profile);
                    TextInputLayout STATE_FOR_PROFILE;
                    STATE_FOR_PROFILE = findViewById(R.id.STATE_FOR_PROFILE);
                    TextInputLayout User_id;
                    User_id = findViewById(R.id.User_id);
                    TextInputLayout Language_for_profile;
                    Language_for_profile = findViewById(R.id.Language_for_profile);
                    String first_name = snapshot.child(user_uid).child("first_name").getValue(String.class);
                    First_name.setText(first_name);
                    name_profile.setText(first_name);
                    String last_name = snapshot.child(user_uid).child("last_name").getValue(String.class);
                    Last_name.setText(last_name);
                    String mobilenumber = snapshot.child(user_uid).child("mobilenumber").getValue(String.class);
                    mobile_number_profile.setText(mobilenumber);
                    String password = snapshot.child(user_uid).child("password").getValue(String.class);
                    password_profile = findViewById(R.id.password_profile);
                    password_profile.setText(password);
                    String email = snapshot.child(user_uid).child("email").getValue(String.class);
                    email_profile = findViewById(R.id.email_profile);
                    email_profile.setText(email);
                    String age_r = snapshot.child(user_uid).child("dateofbirth").getValue(String.class);
                    age_profile = findViewById(R.id.age_profile);
                    age_profile.setText(age_r);
                    String address_r = snapshot.child(user_uid).child("address").getValue(String.class);
                    address_profile = findViewById(R.id.address_profile);
                    address_profile.setText(address_r);
                    String sex = snapshot.child(user_uid).child("sex").getValue(String.class);
                    Sex_for_profile.getEditText().setText(sex);
                    String lang_r = snapshot.child(user_uid).child("language").getValue(String.class);
                    Language_for_profile.getEditText().setText(lang_r);
                    String user_type_r = snapshot.child(user_uid).child("usertype").getValue(String.class);
                    User_id.getEditText().setText(user_type_r);
                    String state_r = snapshot.child(user_uid).child("states").getValue(String.class);
                    STATE_FOR_PROFILE.getEditText().setText(state_r);
                    String district_r = snapshot.child(user_uid).child("districts").getValue(String.class);
                    DISTRICTS = findViewById(R.id.DISTRICTS);
                    DISTRICTS.setText(district_r);
                    dummy_for_district.setText(district_r);
                    dummy_for_state.setText(state_r);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this);
            String user_id_google_profile = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString();
            if (acct != null) {
                name_profile = findViewById(R.id.name_profile);
                First_name = findViewById(R.id.First_name);
                password_profile = findViewById(R.id.password_profile);
                email_profile = findViewById(R.id.email_profile);
                Last_name = findViewById(R.id.Last_name);
                mobile_number_profile = findViewById(R.id.mobile_number_profile);
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                name_profile.setText(personName);
                First_name.setText(personGivenName);
                Last_name.setText(personFamilyName);
                email_profile.setText(personEmail);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild(user_id_google_profile);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        TextInputLayout Sex_for_profile;
                        Sex_for_profile = findViewById(R.id.Sex_for_profile);
                        TextInputLayout STATE_FOR_PROFILE;
                        STATE_FOR_PROFILE = findViewById(R.id.STATE_FOR_PROFILE);
                        TextInputLayout User_id;
                        User_id = findViewById(R.id.User_id);
                        TextInputLayout Language_for_profile;
                        Language_for_profile = findViewById(R.id.Language_for_profile);
                        String mobilenumber = snapshot.child(user_id_google_profile).child("mobilenumber").getValue(String.class);
                        mobile_number_profile.setText(mobilenumber);
                        String password = snapshot.child(user_id_google_profile).child("password").getValue(String.class);
                        password_profile = findViewById(R.id.password_profile);
                        password_profile.setText(password);
                        String age_r = snapshot.child(user_id_google_profile).child("dateofbirth").getValue(String.class);
                        age_profile = findViewById(R.id.age_profile);
                        age_profile.setText(age_r);
                        String address_r = snapshot.child(user_id_google_profile).child("address").getValue(String.class);
                        address_profile = findViewById(R.id.address_profile);
                        address_profile.setText(address_r);
                        String sex = snapshot.child(user_id_google_profile).child("sex").getValue(String.class);
                        Sex_for_profile.getEditText().setText(sex);
                        String lang_r = snapshot.child(user_id_google_profile).child("language").getValue(String.class);
                        Language_for_profile.getEditText().setText(lang_r);
                        String user_type_r = snapshot.child(user_id_google_profile).child("usertype").getValue(String.class);
                        User_id.getEditText().setText(user_type_r);
                        String state_r = snapshot.child(user_id_google_profile).child("states").getValue(String.class);
                        STATE_FOR_PROFILE.getEditText().setText(state_r);
                        dummy_for_state.setText(state_r);
                        String district_r = snapshot.child(user_id_google_profile).child("districts").getValue(String.class);
                        DISTRICTS = findViewById(R.id.DISTRICTS);
                        dummy_for_district.setText(district_r);
                        DISTRICTS.setText(district_r);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProfileActivity.this, "Please comeplete your profile", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        I_am_person = findViewById(R.id.I_am_person);
        adapterItems = new ArrayAdapter<String>(ProfileActivity.this, R.layout.list_item, I_AM);
        I_am_person.setAdapter(adapterItems);
        I_am_person.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                TextView doctor = findViewById(R.id.doctor);
                TextView normal_person = findViewById(R.id.normal_person);
                if (item=="I AM A DOCTOR"){
                    normal_person.setVisibility(View.GONE);
                    doctor.setVisibility(View.VISIBLE);
                } else if (item=="I AM A NORMAL PERSON"){
                    normal_person.setVisibility(View.VISIBLE);
                    doctor.setVisibility(View.GONE);
                }
            }
        });
        LANGUAGE = findViewById(R.id.LANGUAGE);
        LANGUAGES = new ArrayAdapter<String>(ProfileActivity.this, R.layout.lang, Langs);
        LANGUAGE.setAdapter(LANGUAGES);
        LANGUAGE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        states = findViewById(R.id.STATES);
        Name_of_states = new ArrayAdapter<String>(ProfileActivity.this, R.layout.lang, name_of_states);
        states.setAdapter(Name_of_states);
        states.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_state = adapterView.getItemAtPosition(i).toString();
                DISTRICTS = findViewById(R.id.DISTRICTS);
                switch(selected_state){
                    case "Andhra Pradesh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_andhra_pradesh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Arunachal Pradesh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_arunachal_pradesh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Assam":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_assam_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Bihar":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_bihar_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Chhattisgarh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_chhattisgarh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Goa":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_goa_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Gujarat":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_gujarat_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Haryana":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_haryana_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Himachal Pradesh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_himachal_pradesh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Jammu and Kashmir":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_jammu_kashmir_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Jharkhand":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_jharkhand_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Karnataka":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_karnataka_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Kerala":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_kerala_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Madhya Pradesh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_madhya_pradesh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Maharshtra":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_maharashtra_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                        break;
                    case "Manipur":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_manipur_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Meghalaya":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_meghalaya_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Mizoram":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_mizoram_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Nagaland":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_nagaland_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Odisha":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_odisha_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Punjab":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_punjab_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Rajashthan":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_rajasthan_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Sikkim":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_sikkim_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Tamil Nadu":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_tamil_nadu_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Telangana":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_telangana_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Tripura":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_tripura_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Uttarakhand":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_uttarakhand_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Uttar Pradesh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_uttar_pradesh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "West Bengal":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_west_bengal_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Andaman and Nicobar Islands":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_andaman_nicobar_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Chandigarh":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_chandigarh_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Dadra and Nagar haveli":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_dadra_nagar_haveli_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Daman and Diu":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_daman_diu_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Delhi":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_delhi_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Lakshwadeep":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_lakshadweep_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    case "Puducherry":
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.array_puducherry_districts, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                    break;
                    default:
                        Name_of_Districts = ArrayAdapter.createFromResource(adapterView.getContext(), R.array.select_your_state, R.layout.list_item);
                        DISTRICTS.setAdapter(Name_of_Districts);
                }
            }
        });
        GENDER = findViewById(R.id.GENDER);
        gender = new ArrayAdapter<String>(ProfileActivity.this, R.layout.lang, sex);
        GENDER.setAdapter(gender);
        GENDER.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_state = adapterView.getItemAtPosition(i).toString();
            }
        });
        TextView change_profile_picture;
        CircularImageView profile_image;
        change_profile_picture = findViewById(R.id.change_profile_picture);
        change_profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        save_profile_text = findViewById(R.id.save_profile_text);
        save_profile_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProfileDetailes();
            }
        });


        ProgressDialog progressDialog_ = new ProgressDialog(ProfileActivity.this);
        progressDialog_.setMessage("Loading");
        progressDialog_.setCanceledOnTouchOutside(false);
        progressDialog_.show();
        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("images/"+GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    personimageurl = uri.toString();
                    progressDialog_.dismiss();
                    Toast.makeText(ProfileActivity.this, "Image Recieved", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    progressDialog_.dismiss();
                }
            });
        }else{
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            storageRef.child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    personimageurl = uri.toString();
                    Toast.makeText(ProfileActivity.this, "Image Recieved", Toast.LENGTH_SHORT).show();
                    progressDialog_.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    progressDialog_.dismiss();
                }
            });
        }

        logoutfromprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(ProfileActivity.this);
                builder.setMessage("Please,  Confirm that you are sure to log out of your account. You can log in again with the same account anytime");
                builder.setTitle("Are You Sure?");
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Log Out",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                if (GoogleSignIn.getLastSignedInAccount(ProfileActivity.this) != null) {
                                    GoogleSignInOptions gso = new GoogleSignInOptions.
                                            Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                            build();
                                    GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(ProfileActivity.this, gso);
                                    googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                builder.setPositiveButton(
                        "Cancel",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }
    private void uploadProfileDetailes() {
        First_name = findViewById(R.id.First_name);
        Last_name = findViewById(R.id.Last_name);
        mobile_number_profile = findViewById(R.id.mobile_number_profile);
        password_profile= findViewById(R.id.password_profile);
        email_profile = findViewById(R.id.email_profile);
        age_profile = findViewById(R.id.age_profile);
        address_profile = findViewById(R.id.address_profile);
        GENDER = findViewById(R.id.GENDER);
        LANGUAGE = findViewById(R.id.LANGUAGE);
        I_am_person = findViewById(R.id.I_am_person);
        STATES = findViewById(R.id.STATES);
        DISTRICTS = findViewById(R.id.DISTRICTS);
        String firstname = First_name.getText().toString();
        String lastname = First_name.getText().toString();
        String mobile_number = mobile_number_profile.getText().toString();
        String password = password_profile.getText().toString();
        String email = email_profile.getText().toString();
        String dateofbirth = age_profile.getText().toString();
        String address = address_profile.getText().toString();
        String sex = GENDER.getText().toString();
        String language = LANGUAGE.getText().toString();
        String usertype = I_am_person.getText().toString();
        String state = STATES.getText().toString();
        String district = DISTRICTS.getText().toString();
        if (!firstname.isEmpty()){
            if (!lastname.isEmpty()){
                if (!mobile_number.isEmpty()){
                    if (mobile_number_profile.length()==10){
                        if (!password.isEmpty()){
                            if (!email.isEmpty()){
                                if (!dateofbirth.isEmpty()){
                                    if (!address.isEmpty()){
                                        if (!sex.isEmpty()){
                                            if (!language.isEmpty()){
                                                if (!usertype.isEmpty()){
                                                    if (!state.isEmpty()){
                                                        if (!district.isEmpty()){
                                                            if (true){
                                                                if (imageuri!=null){
                                                                    UploadUserProfileInformation();
                                                                }else{
                                                                    Snackbar snackbar = Snackbar.make(ProfileActivity.this, findViewById(android.R.id.content), "Your Image Was not uploaded Properly, Upload Again.", Snackbar.LENGTH_LONG);
                                                                    snackbar.show();
                                                                }
                                                            }else{
                                                                Snackbar snackbar = Snackbar.make(ProfileActivity.this, findViewById(android.R.id.content), "You are under age you cannot proceed", Snackbar.LENGTH_LONG);
                                                                snackbar.show();
                                                            }
                                                        }else{
                                                            DISTRICTS.setError("Please Select Your District");
                                                        }
                                                    }else{
                                                        STATES.setError("Please Select Your State");
                                                    }
                                                }else{
                                                    I_am_person.setError("Please Select Your User Type");
                                                }
                                            }else{
                                                LANGUAGE.setError("Please Select Your Language");
                                            }
                                        }else{
                                            GENDER.setError("Please Enter Your Gender");
                                        }
                                    }else{
                                        address_profile.setError("Please Enter Your Address");
                                    }
                                }else{
                                    age_profile.setError("Please Enter Your Date of Birth");
                                }
                            }else{
                                email_profile.setError("Please Enter Your Email");
                            }
                        }else{
                            password_profile.setError("Please Create a Password");
                        }
                    }else{
                        mobile_number_profile.setError("Please Valid Mobile Number");
                    }
                }else{
                    mobile_number_profile.setError("Please Enter Your Mobile Number");
                }
            }else {
                Last_name.setError("Please Enter Your Last Name");
            }
        }else{
            First_name.setError("Please Enter Your First Name");
        }
    }

    private void UploadUserProfileInformation() {
        AutoCompleteTextView STATES;
        STATES = findViewById(R.id.STATES);
        if (GoogleSignIn.getLastSignedInAccount(ProfileActivity.this) == null){
            String user_id_for_profile = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            DatabaseReference profiledata = FirebaseDatabase.getInstance().getReference("Users").child(user_id_for_profile);
            String first_name = First_name.getText().toString();
            String yearofbirth = String.valueOf(age);
            String last_name = Last_name.getText().toString();
            String mobilenumber = mobile_number_profile.getText().toString();
            String password = password_profile.getText().toString();
            String email = email_profile.getText().toString();
            String dateofbirth = age_profile.getText().toString();
            String address = address_profile.getText().toString();
            String email_for_profile = email_profile.getText().toString();
            String sex = GENDER.getText().toString();
            String language = LANGUAGE.getText().toString();
            String usertype = I_am_person.getText().toString();
            String states = STATES.getText().toString();
            String districts = DISTRICTS.getText().toString();
            String profile_complete = "yes";
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            ProfileData profileData = new ProfileData(first_name, last_name, mobilenumber, password, email, dateofbirth, address, sex, language, usertype, states, districts, yearofbirth,profile_complete,personimageurl);
            profiledata.setValue(profileData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                databaseReference.setValue(s);
                            }else{
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                databaseReference.setValue(s);
                            }
                        }
                    });

                    switch (I_am_person.getText().toString()) {
                    case "I AM A DOCTOR":
                        ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
                        progressDialog.setMessage("Loading");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(states).child(districts).child(user_id_for_profile);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                APPLICATION_STATUS = snapshot.child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                if (APPLICATION_STATUS!=null){
                                    progressDialog.show();
                                    AlertDialog.Builder builder
                                            = new AlertDialog
                                            .Builder(ProfileActivity.this);
                                    builder.setMessage("You profile Has Been Saved");
                                    builder.setTitle("Profile Saved");
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
                                                    if (dummy_for_uri.getText().toString()!=null){
                                                        if (FirebaseAuth.getInstance().getCurrentUser()==null){
                                                            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                                @Override
                                                                public void onSuccess(String s) {
                                                                    if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }else{
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }
                                                                }
                                                            });
                                                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).child("Image Url");
                                                            dbref.setValue(dummy_for_uri.getText().toString());
                                                        }else{
                                                            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                                @Override
                                                                public void onSuccess(String s) {
                                                                    if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }else{
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }
                                                                }
                                                            });
                                                            Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                            todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(todoctoractvity);
                                                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("Image Url");
                                                            dbref.setValue(dummy_for_uri.getText().toString());
                                                        }
                                                    }
                                                    Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                    todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(todoctoractvity);
                                                }
                                            });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }else{
                                    progressDialog.show();
                                    AlertDialog.Builder builder
                                            = new AlertDialog
                                            .Builder(ProfileActivity.this);
                                    builder.setMessage("As you have selected your profession as a doctor, you have to submit some more things to verify you");
                                    builder.setTitle("Let's Create Your Doctor Profile");
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
                                                    if (dummy_for_uri.getText().toString()!=null){
                                                        if (FirebaseAuth.getInstance().getCurrentUser().getUid().toString()==null){
                                                            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                                @Override
                                                                public void onSuccess(String s) {
                                                                    if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }else{
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }
                                                                }
                                                            });
                                                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).child("Image Url");
                                                            dbref.setValue(dummy_for_uri.getText().toString());
                                                        }else{
                                                            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                                @Override
                                                                public void onSuccess(String s) {
                                                                    if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }else{
                                                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                                                        databaseReference.setValue(s);
                                                                    }
                                                                }
                                                            });
                                                            Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                            todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(todoctoractvity);
                                                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("Image Url");
                                                            dbref.setValue(dummy_for_uri.getText().toString());
                                                        }
                                                    }
                                                    Intent todoctoractvity = new Intent(ProfileActivity.this, DoctorFillForm.class);
                                                    todoctoractvity.putExtra("First_Name", First_name.getText().toString());
                                                    todoctoractvity.putExtra("Last_Name", Last_name.getText().toString());
                                                    todoctoractvity.putExtra("Image_Url",dummy_for_uri.getText().toString());
                                                    todoctoractvity.putExtra("State", STATES.getText().toString());
                                                    todoctoractvity.putExtra("District", DISTRICTS.getText().toString());
                                                    startActivity(todoctoractvity);
                                                }
                                            });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                            break;
                        default:
                            AlertDialog.Builder builder
                                    = new AlertDialog
                                    .Builder(ProfileActivity.this);
                            builder.setMessage("Your Profile Has Been Saved");
                            builder.setTitle("Profile Saved Successfully");
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
                                            if (dummy_for_uri.getText().toString()!=null){
                                                if (FirebaseAuth.getInstance().getCurrentUser().getUid().toString()==null){
                                                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                        @Override
                                                        public void onSuccess(String s) {
                                                            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                databaseReference.setValue(s);
                                                            }else{
                                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                                                databaseReference.setValue(s);
                                                            }
                                                        }
                                                    });
                                                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).child("Image Url");
                                                    dbref.setValue(dummy_for_uri.getText().toString());
                                                }else{
                                                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                        @Override
                                                        public void onSuccess(String s) {
                                                            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                databaseReference.setValue(s);
                                                            }else{
                                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                                                databaseReference.setValue(s);
                                                            }
                                                        }
                                                    });
                                                    Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                    todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(todoctoractvity);
                                                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("Image Url");
                                                    dbref.setValue(dummy_for_uri.getText().toString());
                                                }
                                            }
                                            Intent tomainactivity = new Intent(ProfileActivity.this, MainActivity.class);
                                            tomainactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(tomainactivity);
                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            break;
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar snackbar = Snackbar.make(ProfileActivity.this, findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }else{
            String user_id_google = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString();
            DatabaseReference profiledata = FirebaseDatabase.getInstance().getReference("Users").child(user_id_google);
            String first_name = First_name.getText().toString();
            String last_name = Last_name.getText().toString();
            String mobilenumber = mobile_number_profile.getText().toString();
            String password = password_profile.getText().toString();
            String email = email_profile.getText().toString();
            String dateofbirth = age_profile.getText().toString();
            String address = address_profile.getText().toString();
            String email_for_profile = email_profile.getText().toString();
            TextView age_check_for_health = findViewById(R.id.age_check_for_health);
            String yearofbirth = age_check_for_health.getText().toString();
            String sex = GENDER.getText().toString();
            String language = LANGUAGE.getText().toString();
            String usertype = I_am_person.getText().toString();
            String states = STATES.getText().toString();
            String districts = DISTRICTS.getText().toString();
            String profile_complete = "yes";
            ProfileData profileData = new ProfileData(first_name, last_name, mobilenumber, password, email, dateofbirth, address, sex, language, usertype, states, districts, yearofbirth,profile_complete,personimageurl);
            profiledata.setValue(profileData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                databaseReference.setValue(s);
                            }else{
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId()).child("token");
                                databaseReference.setValue(s);
                            }
                        }
                    });
                    switch (I_am_person.getText().toString()) {
                        case "I AM A DOCTOR":
                            ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
                            progressDialog.setMessage("Loading");
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(states).child(districts).child(user_id_google);
                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    APPLICATION_STATUS = snapshot.child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                    if (APPLICATION_STATUS!=null){
                                        progressDialog.dismiss();
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(ProfileActivity.this);
                                        builder.setMessage("You profile Has Been Saved");
                                        builder.setTitle("Profile Saved");
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
                                                        if (dummy_for_uri.getText().toString()!=null){
                                                            progressDialog.dismiss();
                                                            if (FirebaseAuth.getInstance().getCurrentUser()==null){
                                                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).child("Image Url");
                                                                dbref.setValue(dummy_for_uri.getText().toString());
                                                                progressDialog.dismiss();
                                                            }else{
                                                                progressDialog.dismiss();
                                                                Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                                todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                startActivity(todoctoractvity);
                                                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("Image Url");
                                                                dbref.setValue(dummy_for_uri.getText().toString());
                                                            }
                                                        }
                                                        Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                        todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(todoctoractvity);
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else{
                                        progressDialog.dismiss();
                                        AlertDialog.Builder builder
                                                = new AlertDialog
                                                .Builder(ProfileActivity.this);
                                        builder.setMessage("As you have selected your profession as a doctor, you have to submit some more things to verify you");
                                        builder.setTitle("Let's Create Your Doctor Profile");
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
                                                        if (dummy_for_uri.getText().toString()!=null){
                                                            progressDialog.dismiss();
                                                            if (FirebaseAuth.getInstance().getCurrentUser()==null){
                                                                progressDialog.dismiss();
                                                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).child("Image Url");
                                                                dbref.setValue(dummy_for_uri.getText().toString());
                                                            }else{
                                                                progressDialog.dismiss();
                                                                Intent todoctoractvity = new Intent(ProfileActivity.this, MainActivity.class);
                                                                todoctoractvity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                startActivity(todoctoractvity);
                                                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Doctor").child(dummy_for_state.getText().toString()).child(dummy_for_district.getText().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("Image Url");
                                                                dbref.setValue(dummy_for_uri.getText().toString());
                                                            }
                                                        }
                                                        Intent todoctoractvity = new Intent(ProfileActivity.this, DoctorFillForm.class);
                                                        todoctoractvity.putExtra("First_Name", First_name.getText().toString());
                                                        todoctoractvity.putExtra("Last_Name", Last_name.getText().toString());
                                                        todoctoractvity.putExtra("Image_Url",dummy_for_uri.getText().toString());
                                                        todoctoractvity.putExtra("State", STATES.getText().toString());
                                                        todoctoractvity.putExtra("District", DISTRICTS.getText().toString());
                                                        startActivity(todoctoractvity);
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            break;
                        default:
                            AlertDialog.Builder builder
                                    = new AlertDialog
                                    .Builder(ProfileActivity.this);
                            builder.setMessage("Your Profile Has Been Saved");
                            builder.setTitle("Profile Saved Successfully");
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
                                            Intent tomainactivity = new Intent(ProfileActivity.this, MainActivity.class);
                                            tomainactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(tomainactivity);
                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            break;
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar snackbar = Snackbar.make(ProfileActivity.this, findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }
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

    CircleImageView profile_image;
    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            profile_image = findViewById(R.id.profile_image);
            profile_image.setImageBitmap(bitmap);
            ProgressDialog ppdd1 = new ProgressDialog(ProfileActivity.this);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String user_id = FirebaseAuth.getInstance().getUid().toString();
                storageReference = FirebaseStorage.getInstance().getReference("images").child(user_id);
                storageReference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ProfileActivity.this, "DONE!!!!", Toast.LENGTH_SHORT).show();
                                ppdd1.dismiss();

                                if (FirebaseAuth.getInstance().getCurrentUser()==null){
                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                    storageRef.child("images/"+GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageuri = uri.toString();
                                            dummy_for_uri.setText(uri.toString());
                                            Image_Url = uri.toString();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });
                                }else{
                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                    storageRef.child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageuri = uri.toString();
                                            dummy_for_uri.setText(uri.toString());
                                            Image_Url = uri.toString();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        ppdd1.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        ppdd1.setMessage("Uploading " + (int)progress + "%");
                        ppdd1.setCanceledOnTouchOutside(false);
                        ppdd1.show();
                    }
                });
            }else if (GoogleSignIn.getLastSignedInAccount(ProfileActivity.this)!=null){
                final ProgressDialog ppdd = new ProgressDialog(ProfileActivity.this);
                ppdd.setTitle("Please wait");
                ppdd.setMessage("Uploading your profile picture");
                String user_id = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString();
                storageReference = FirebaseStorage.getInstance().getReference("images").child(user_id);
                storageReference.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ProfileActivity.this, "DONE!!!!", Toast.LENGTH_SHORT).show();
                                ppdd.dismiss();

                                if (FirebaseAuth.getInstance().getCurrentUser()==null){
                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                    storageRef.child("images/"+GoogleSignIn.getLastSignedInAccount(ProfileActivity.this).getId().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageuri = uri.toString();
                                            dummy_for_uri.setText(uri.toString());
                                            Image_Url = uri.toString();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });
                                }else{
                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                    storageRef.child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageuri = uri.toString();
                                            dummy_for_uri.setText(uri.toString());
                                            Image_Url = uri.toString();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ppdd.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {double progress
                            = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        Toast.makeText(ProfileActivity.this, "Uploading Your Picture", Toast.LENGTH_SHORT).show();
                        ppdd.setMessage("Uploading " + (int)progress + "%");
                        ppdd.show();
                    }
                });
            }
        }
    }
}