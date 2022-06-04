package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.react.modules.core.PermissionListener;
import com.google.android.material.snackbar.Snackbar;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetOngoingConferenceService;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.JitsiMeetViewListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class VideoCallActivity extends AppCompatActivity {
    EditText secretcode;
    Button joinmeeting,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);
        joinmeeting = findViewById(R.id.joinmeeting);
        secretcode = findViewById(R.id.secretcode);
        secretcode.setText("");
        share = findViewById(R.id.share);
        boolean connected = false;
        ImageView back;
        back = findViewById(R.id.back);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String MeetingID = bundle.getString("MeetingID");
            secretcode.setText(MeetingID);
        }
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
        joinmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!secretcode.getText().toString().isEmpty()){
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        JitsiMeetConferenceOptions options;
                        try {
                            options = new JitsiMeetConferenceOptions.Builder()
                                    .setServerURL(new URL("https://meet.jit.si"))
                                    .setRoom(secretcode.getText().toString()    )
                                    .setAudioMuted(false)
                                    .setVideoMuted(false)
                                    .setAudioOnly(false)
                                    .setWelcomePageEnabled(true)
                                    .setSubject("Video Conference With Doctor")
                                    .setWelcomePageEnabled(false)
                                    .build();
                            JitsiMeetActivity.launch(VideoCallActivity.this,options);
                            if (options.describeContents()==0){

                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Snackbar snackbar = Snackbar.make(VideoCallActivity.this, findViewById(android.R.id.content), "NO INTERNET CONNECTION", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }else{
                    Snackbar snackbar = Snackbar.make(VideoCallActivity.this, findViewById(android.R.id.content), "Please Enter A Meeting Code To Join A Meeting", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        if (!secretcode.getText().toString().isEmpty()){
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "Hey, I am Inviting You To Join This Healthcare Meeting Tith This Code "+"\""+secretcode.getText().toString()+"\""+"On Dhanwantari App");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    }
                });
            }
        }
    }
}