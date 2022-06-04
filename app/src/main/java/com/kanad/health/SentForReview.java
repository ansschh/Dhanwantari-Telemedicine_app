package com.kanad.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;

public class SentForReview extends AppCompatActivity {
    TextView gotohomescreen,appid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_for_review);
        appid = findViewById(R.id.appid);
        gotohomescreen = findViewById(R.id.gotohomescreen);
        gotohomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tohomescreen = new Intent(SentForReview.this, MainActivity.class);
                tohomescreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(tohomescreen);
            }
        });
        if (GoogleSignIn.getLastSignedInAccount(SentForReview.this)==null){
            String uid = FirebaseAuth.getInstance().getUid().toString();
            appid.setText("Application ID: "+uid.substring(0,10));
        }else{
            String uid = GoogleSignIn.getLastSignedInAccount(SentForReview.this).getId().toString();
            appid.setText("Application ID: "+uid.substring(0,10));
        }
    }
}