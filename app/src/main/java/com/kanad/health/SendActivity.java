package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;

public class SendActivity extends AppCompatActivity{
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        context = SendActivity.this.getParent().getApplicationContext();

        if (context==null){
            Toast.makeText(context, "Context is Null", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Context is Not Null", Toast.LENGTH_SHORT).show();
        }
    }
}