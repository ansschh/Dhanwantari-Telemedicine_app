package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.Login;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class SignupActivity extends AppCompatActivity {
    Button button;
    Animation scale_up, scale_down;
    EditText name_login, mobile_number_login, email_login, Password_login;
    private FirebaseAuth mAuth;
    TextView login_intent_btn,learnmore;
    ProgressBar signup_progress_bar;
    CheckBox showpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        learnmore = findViewById(R.id.learnmore);
        learnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.freeprivacypolicy.com/live/02650b0d-d309-4526-8f1e-b2297a6ea9bb";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        showpassword = findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Password_login.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    Password_login.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
        }
        else{
            Snackbar snackbar = Snackbar.make(SignupActivity.this, findViewById(android.R.id.content), "NO INTERNET CONNECTION", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        login_intent_btn = findViewById(R.id.login_intent_btn);
        login_intent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scale_down = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        login_intent_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    login_intent_btn.startAnimation(scale_up);

                }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    login_intent_btn.startAnimation(scale_down);

                }
                return false;
            }

        });

        mAuth = FirebaseAuth.getInstance();
        name_login = findViewById(R.id.name_login);
        mobile_number_login = findViewById(R.id.mobile_number_login);
        email_login = findViewById(R.id.email_login);
        Password_login = findViewById(R.id.Password_login);
        signup_progress_bar = findViewById(R.id.signup_progress_bar);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String name = name_login.getText().toString().trim();
        String mobile_number = mobile_number_login.getText().toString().trim();
        String email = email_login.getText().toString().trim();
        String password = Password_login.getText().toString().trim();

        if (!name.isEmpty()){
            if (!mobile_number.isEmpty()){
                if (!email.isEmpty()){
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        if (!password.isEmpty()){
                            if (Password_login.getText().toString().length()>8){
                                CheckBox privacypolicy;
                                privacypolicy = findViewById(R.id.privacypolicy);
                                if (!privacypolicy.isChecked()){
                                    Toast.makeText(this, "Please check the privacy policy box you cannot continue without it.", Toast.LENGTH_SHORT).show();
                                }else{
                                    signup_progress_bar.setVisibility(View.VISIBLE);
                                    button.setVisibility(View.GONE);
                                    mAuth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()){
                                                        User user = new User(name, mobile_number, email, password);
                                                        FirebaseDatabase.getInstance().getReference("Users")
                                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()){
                                                                    signup_progress_bar.setVisibility(View.GONE);
                                                                    button.setVisibility(View.VISIBLE);
                                                                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                                                        @Override
                                                                        public void onSuccess(String s) {
                                                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("token");
                                                                            databaseReference.setValue(s);
                                                                        }
                                                                    });
                                                                    Toast.makeText(SignupActivity.this, "Wellcome to Dhanwantari", Toast.LENGTH_SHORT).show();
                                                                    Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                    startActivity(intent);
                                                                }
                                                                if (!task.isSuccessful()){
                                                                    Toast.makeText(SignupActivity.this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                                                                    signup_progress_bar.setVisibility(View.GONE);
                                                                    button.setVisibility(View.VISIBLE);
                                                                }
                                                            }
                                                        });
                                                    }else if (!task.isSuccessful()){
                                                        Toast.makeText(SignupActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                        button.setVisibility(View.VISIBLE);
                                                        signup_progress_bar.setVisibility(View.GONE);
                                                    }
                                                }
                                            });
                                }
                            }else{
                                Password_login.setError("Please Enter a Strong Password");
                                Password_login.requestFocus();
                            }
                        }else{
                            Password_login.setError("Password is Required");
                            Password_login.requestFocus();
                        }
                    }else{
                        email_login.setError("Please enter Valid Email");
                        email_login.requestFocus();
                    }
                }else{
                    email_login.setError("Please enter Valid Email");
                    email_login.requestFocus();
                }
            }else{
                mobile_number_login.setError("Mobile Number is Required");
                mobile_number_login.requestFocus();
            }
        }else{
            name_login.setError("Name is Required");
            name_login.requestFocus();
        }
    }

}