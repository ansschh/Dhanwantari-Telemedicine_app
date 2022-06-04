package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoctorFillForm6 extends AppCompatActivity {
    TextView stateofdoctorfill1,gotohomescreen,districtofdoctorfill,remove1,remove2,remove3,remove4,remove5,remove0,nextbutton6;
    Button add,add1,add2,add3,add4,selectdate__;
    ArrayAdapter<CharSequence> Name_of_Districts;
    ArrayAdapter<String> timesofdays;
    AutoCompleteTextView days,days1,days2,days3,days4,days5;
    String Application_Status,VerificationStatus,uid;
    String state,district;
    TextInputLayout except1,except2,except3,except4,except5,except0;
    ImageButton delete3,delete4,delete5,delete6,delete7,delete8,delete9,delete10;
    CardView slot1,slot2,slot3,slot4,slot5,slot6,slot7,slot8,slot9,slot10;
    DatePickerDialog.OnDateSetListener onDateSetListener1;
    AutoCompleteTextView s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int date = calendar.get(Calendar.DAY_OF_MONTH);
    String date___;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_fill_form6);
        except1 = findViewById(R.id.except1);
        delete3 = findViewById(R.id.delete3);
        s1 = findViewById(R.id.s1);
        e1 =findViewById(R.id.e1);
        nextbutton6 = findViewById(R.id.nextbutton6);
        s2 = findViewById(R.id.s2);
        e2 =findViewById(R.id.e2);
        selectdate__ = findViewById(R.id.selectdate__);
        s3 = findViewById(R.id.s3);
        e3 =findViewById(R.id.e3);
        s4 = findViewById(R.id.s4);
        e4 =findViewById(R.id.e4);
        s5 = findViewById(R.id.s5);
        e5 =findViewById(R.id.e5);
        s6 = findViewById(R.id.s6);
        e6 =findViewById(R.id.e6);
        s7 = findViewById(R.id.s7);
        e7 =findViewById(R.id.e7);
        s8 = findViewById(R.id.s8);
        e8 =findViewById(R.id.e8);
        s9 = findViewById(R.id.s9);
        e9 =findViewById(R.id.e9);
        s10 = findViewById(R.id.s10);
        e10 =findViewById(R.id.e10);

        ImageView back1;
        back1 = findViewById(R.id.back);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        String[] time_with_interval = {
        "12:00 AM",
                "12:15 AM",
                "12:30 AM",
                "12:45 AM",
                "1:00 AM",
                "1:15 AM",
                "1:30 AM",
                "1:45 AM",
                "2:00 AM",
                "2:15 AM",
                "2:30 AM",
                "2:45 AM",
                "3:00 AM",
                "3:15 AM",
                "3:30 AM",
                "3:45 AM",
                "4:00 AM",
                "4:15 AM",
                "4:30 AM",
                "4:45 AM",
                "5:00 AM",
                "5:15 AM",
                "5:30 AM",
                "5:45 AM",
                "6:00 AM",
                "6:15 AM",
                "6:30 AM",
                "6:45 AM",
                "7:00 AM",
                "7:15 AM",
                "7:30 AM",
                "7:45 AM",
                "8:00 AM",
                "8:15 AM",
                "8:30 AM",
                "8:45 AM",
                "9:00 AM",
                "9:15 AM",
                "9:30 AM",
                "9:45 AM",
                "10:00 AM",
                "10:15 AM",
                "10:30 AM",
                "10:45 AM",
                "11:00 AM",
                "11:15 AM",
                "11:30 AM",
                "11:45 AM",
                "12:00 PM",
                "12:15 PM",
                "12:30 PM",
                "12:45 PM",
                "1:00 PM",
                "1:15 PM",
                "1:30 PM",
                "1:45 PM",
                "2:00 PM",
                "2:15 PM",
                "2:30 PM",
                "2:45 PM",
                "3:00 PM",
                "3:15 PM",
                "3:30 PM",
                "3:45 PM",
                "4:00 PM",
                "4:15 PM",
                "4:30 PM",
                "4:45 PM",
                "5:00 PM",
                "5:15 PM",
                "5:30 PM",
                "5:45 PM",
                "6:00 PM",
                "6:15 PM",
                "6:30 PM",
                "6:45 PM",
                "7:00 PM",
                "7:15 PM",
                "7:30 PM",
                "7:45 PM",
                "8:00 PM",
                "8:15 PM",
                "8:30 PM",
                "8:45 PM",
                "9:00 PM",
                "9:15 PM",
                "9:30 PM",
                "9:45 PM",
                "10:00 PM",
                "10:15 PM",
                "10:30 PM",
                "10:45 PM",
                "11:00 PM",
                "11:15 PM",
                "11:30 PM",
                "11:45 PM"
    };

        delete4 = findViewById(R.id.delete4);
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        slot4 = findViewById(R.id.slot4);
        slot5 = findViewById(R.id.slot5);
        slot6 = findViewById(R.id.slot6);
        slot7 = findViewById(R.id.slot7);
        slot8 = findViewById(R.id.slot8);
        slot9 = findViewById(R.id.slot9);
        slot10 = findViewById(R.id.slot10);
        delete5 = findViewById(R.id.delete5);
        delete6 = findViewById(R.id.delete6);
        delete7 = findViewById(R.id.delete7);
        delete8 = findViewById(R.id.delete8);
        delete9 = findViewById(R.id.delete9);
        delete10 = findViewById(R.id.delete10);
        add2 = findViewById(R.id.add2);
        days1 = findViewById(R.id.days1);
        days = findViewById(R.id.days);
        remove0 = findViewById(R.id.remove0);
        days2 = findViewById(R.id.days2);
        days3 = findViewById(R.id.days3);
        days4 = findViewById(R.id.days4);
        days5 = findViewById(R.id.days5);
        add3 = findViewById(R.id.add3);
        remove2 = findViewById(R.id.remove2);
        except0 = findViewById(R.id.except0);
        remove5 = findViewById(R.id.remove5);
        remove3 = findViewById(R.id.remove3);
        remove4 = findViewById(R.id.remove4);
        except2 = findViewById(R.id.except2);
        except3 = findViewById(R.id.except3);
        except4 = findViewById(R.id.except4);
        except5 = findViewById(R.id.except5);
        add4 = findViewById(R.id.add4);
        remove1 = findViewById(R.id.remove1);
        add = findViewById(R.id.add);
        stateofdoctorfill1 = findViewById(R.id.stateofdoctorfill1);
        districtofdoctorfill = findViewById(R.id.districtofdoctorfill);
        add1 = findViewById(R.id.add1);

        selectdate__.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DoctorFillForm6.this,
                        android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth
                        ,onDateSetListener1,year,month,date
                );
                datePickerDialog.show();
            }
        });
        onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                date___ = String.valueOf(i2)+String.valueOf(i1)+String.valueOf(i);
            }
        };

        if (GoogleSignIn.getLastSignedInAccount(DoctorFillForm6.this)!=null){
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(DoctorFillForm6.this);
            String user_id_google = GoogleSignIn.getLastSignedInAccount(DoctorFillForm6.this).getId();
            if (acct != null){
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild(user_id_google);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String STATE = snapshot.child(user_id_google).child("states").getValue(String.class);
                        String DISTRICT = snapshot.child(user_id_google).child("districts").getValue(String.class);
                        stateofdoctorfill1.setText(STATE);
                        districtofdoctorfill.setText(DISTRICT);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DoctorFillForm6.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String user_uid = user.getUid().toString();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            Query checkUser = reference.orderByChild(user_uid);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String FIRST_NAME = snapshot.child(user_uid).child("first_name").getValue(String.class);
                    String LAST_NAME = snapshot.child(user_uid).child("last_name").getValue(String.class);
                    String STATE = snapshot.child(user_uid).child("states").getValue(String.class);
                    String DISTRICT = snapshot.child(user_uid).child("districts").getValue(String.class);
                    stateofdoctorfill1.setText(STATE);
                    districtofdoctorfill.setText(DISTRICT);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DoctorFillForm6.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Name_of_Districts = ArrayAdapter.createFromResource(DoctorFillForm6.this, R.array.days_of_week, R.layout.list_item);
        days.setAdapter(Name_of_Districts);
        Name_of_Districts = ArrayAdapter.createFromResource(DoctorFillForm6.this, R.array.days_of_week, R.layout.list_item);
        days1.setAdapter(Name_of_Districts);
        Name_of_Districts = ArrayAdapter.createFromResource(DoctorFillForm6.this, R.array.days_of_week, R.layout.list_item);
        days2.setAdapter(Name_of_Districts);
        Name_of_Districts = ArrayAdapter.createFromResource(DoctorFillForm6.this, R.array.days_of_week, R.layout.list_item);
        days3.setAdapter(Name_of_Districts);
        Name_of_Districts = ArrayAdapter.createFromResource(DoctorFillForm6.this, R.array.days_of_week, R.layout.list_item);
        days4.setAdapter(Name_of_Districts);
        Name_of_Districts = ArrayAdapter.createFromResource(DoctorFillForm6.this, R.array.days_of_week, R.layout.list_item);
        days5.setAdapter(Name_of_Districts);


        timesofdays = new ArrayAdapter<String>(DoctorFillForm6.this, R.layout.list_item, time_with_interval);
        s1.setAdapter(timesofdays);
        s2.setAdapter(timesofdays);
        s3.setAdapter(timesofdays);
        s4.setAdapter(timesofdays);
        s5.setAdapter(timesofdays);
        s6.setAdapter(timesofdays);
        s7.setAdapter(timesofdays);
        s8.setAdapter(timesofdays);
        s9.setAdapter(timesofdays);
        s10.setAdapter(timesofdays);
        e1.setAdapter(timesofdays);
        e2.setAdapter(timesofdays);
        e3.setAdapter(timesofdays);
        e4.setAdapter(timesofdays);
        e5.setAdapter(timesofdays);
        e6.setAdapter(timesofdays);
        e7.setAdapter(timesofdays);
        e8.setAdapter(timesofdays);
        e9.setAdapter(timesofdays);
        e10.setAdapter(timesofdays);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove1.setVisibility(View.VISIBLE);
                except1.setVisibility(View.VISIBLE);
                add1.setVisibility(View.VISIBLE);
            }
        });
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove2.setVisibility(View.VISIBLE);
                except2.setVisibility(View.VISIBLE);
                add2.setVisibility(View.VISIBLE);
            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove3.setVisibility(View.VISIBLE);
                except3.setVisibility(View.VISIBLE);
                add3.setVisibility(View.VISIBLE);
            }
        });
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove4.setVisibility(View.VISIBLE);
                except4.setVisibility(View.VISIBLE);
                add4.setVisibility(View.VISIBLE);
            }
        });
        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove5.setVisibility(View.VISIBLE);
                except5.setVisibility(View.VISIBLE);
            }
        });

        remove0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                except0.setVisibility(View.GONE);
                add.setVisibility(View.GONE);
                remove0.setVisibility(View.GONE);
            }
        });
        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove1.setVisibility(View.GONE);
                except1.setVisibility(View.GONE);
                add1.setVisibility(View.GONE);
            }
        });
        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove2.setVisibility(View.GONE);
                except2.setVisibility(View.GONE);
                add2.setVisibility(View.GONE);
            }
        });
        remove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove3.setVisibility(View.GONE);
                except3.setVisibility(View.GONE);
                add3.setVisibility(View.GONE);
            }
        });
        remove4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove4.setVisibility(View.GONE);
                except4.setVisibility(View.GONE);
                add4.setVisibility(View.GONE);
            }
        });
        remove5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove5.setVisibility(View.GONE);
                except5.setVisibility(View.GONE);
            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            class editTextListener8 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot3.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 3 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener8());
                snackbar.show();
            }
        });
        delete4.setOnClickListener(new View.OnClickListener() {
            class editTextListener7 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot4.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 4 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener7());
                snackbar.show();
            }
        });
        delete5.setOnClickListener(new View.OnClickListener() {
            class editTextListener6 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot5.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot5.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 5 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener6());
                snackbar.show();
            }
        });
        delete6.setOnClickListener(new View.OnClickListener() {
            class editTextListener5 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot6.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot6.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 6 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener5());
                snackbar.show();
            }
        });
        delete7.setOnClickListener(new View.OnClickListener() {
            class editTextListener4 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot7.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot7.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 7 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener4());
                snackbar.show();
            }
        });
        delete8.setOnClickListener(new View.OnClickListener() {
            class editTextListener3 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot8.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot8.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 8 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener3());
                snackbar.show();
            }
        });
        delete9.setOnClickListener(new View.OnClickListener() {
            class editTextListener implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot9.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot9.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 9 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener());
                snackbar.show();
            }
        });
        delete10.setOnClickListener(new View.OnClickListener() {
            class editTextListener1 implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    slot10.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                slot10.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "SLOT NUMBER 10 REMOVED", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new editTextListener1());
                snackbar.show();
            }
        });

        nextbutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (days.getText().toString().isEmpty()){
                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(DoctorFillForm6.this);
                    builder.setMessage("We recommend taking breaks, As we know that doctors are also humans, and we cannot care less about them");
                    builder.setTitle("Still Want to Proceed");
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
                                        }
                                    });

                    // Create the Alert dialog
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();

                    // Show the Alert Dialog box
                    alertDialog.show();
                }
                if (!s1.getText().toString().isEmpty() && !e1.getText().toString().isEmpty()){
                    if (!s2.getText().toString().isEmpty() && !e2.getText().toString().isEmpty()){
                        uploadschedule();
                    }else{
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(DoctorFillForm6.this);
                        builder.setMessage("Please Fill At Least Two Slots");
                        builder.setTitle("Less Than Two Slots Filled");
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
                                    }
                                });

                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();

                        // Show the Alert Dialog box
                        alertDialog.show();
                    }
                }else{
                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(DoctorFillForm6.this);
                    builder.setMessage("Please Fill At Least Two Slots");
                    builder.setTitle("Less Than Two Slots Filled");
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
                                }
                            });

                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();

                    // Show the Alert Dialog box
                    alertDialog.show();
                }
            }
        });
    }
    private void uploadschedule() {
        String OFF_DAY_1 = days.getText().toString();
        String OFF_DAY_2 = days1.getText().toString();
        String OFF_DAY_3 = days2.getText().toString();
        String OFF_DAY_4 = days3.getText().toString();
        String OFF_DAY_5 = days4.getText().toString();
        String OFF_DAY_6 = days5.getText().toString();
        String START_TIME_1 = s1.getText().toString();
        String START_TIME_2 = s2.getText().toString();
        String START_TIME_3 = s3.getText().toString();
        String START_TIME_4= s4.getText().toString();
        String START_TIME_5 = s5.getText().toString();
        String START_TIME_6 = s6.getText().toString();
        String START_TIME_7 = s7.getText().toString();
        String START_TIME_8 = s8.getText().toString();
        String START_TIME_9 = s9.getText().toString();
        String START_TIME_10 = s10.getText().toString();
        String END_TIME_1 = e1.getText().toString();
        String END_TIME_2 = e2.getText().toString();
        String END_TIME_3 = e3.getText().toString();
        String END_TIME_4 = e4.getText().toString();
        String END_TIME_5 = e5.getText().toString();
        String END_TIME_6 = e6.getText().toString();
        String END_TIME_7 = e7.getText().toString();
        String END_TIME_8 = e8.getText().toString();
        String END_TIME_9 = e9.getText().toString();
        String END_TIME_10 = e10.getText().toString();
        String APPLICATION_STATUS = "true";
        if (GoogleSignIn.getLastSignedInAccount(DoctorFillForm6.this) == null){
            String user_id_for_profile = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            if (date___!=null){
                DatabaseReference profiledata = FirebaseDatabase.getInstance().getReference("Doctor").child(stateofdoctorfill1.getText().toString()).child(districtofdoctorfill.getText().toString()).child(user_id_for_profile).child("Schedule Timings").child(date___);
                TimingUpload timingUpload = new TimingUpload(OFF_DAY_1, OFF_DAY_2, OFF_DAY_3, OFF_DAY_4, OFF_DAY_5, OFF_DAY_6, START_TIME_1,START_TIME_2, START_TIME_3, START_TIME_4, START_TIME_5, START_TIME_6, START_TIME_7,START_TIME_8,START_TIME_9,START_TIME_10,END_TIME_1,END_TIME_2,END_TIME_3,END_TIME_4,END_TIME_5,END_TIME_6,END_TIME_7,END_TIME_8,END_TIME_9,END_TIME_10, APPLICATION_STATUS);
                profiledata.setValue(timingUpload).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String state1 = snapshot.child(user_id_for_profile).child("states").getValue(String.class);
                                String districts = snapshot.child(user_id_for_profile).child("districts").getValue(String.class);
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(state1).child(districts).child(user_id_for_profile).child("Schedule Timings").child("application_STATUS");
                                databaseReference.setValue("true");
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
                                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        state = snapshot.child(user_id_for_profile).child("states").getValue(String.class);
                                        district = snapshot.child(user_id_for_profile).child("districts").getValue(String.class);
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(state).child(district).child(user_id_for_profile);
                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Application_Status = snapshot.child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                                VerificationStatus = snapshot.child("EducationQualification").child("approved").getValue(String.class);
                                                if (Application_Status != null) {
                                                    AlertDialog.Builder builder
                                                            = new AlertDialog
                                                            .Builder(DoctorFillForm6.this);
                                                    builder.setMessage("Schedule for selected date has been saved.");
                                                    builder.setTitle("Schedule Saved");
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton(
                                                            "Ok",
                                                            new DialogInterface
                                                                    .OnClickListener() {

                                                                @Override
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    dialog.cancel();
                                                                    Intent intent = new Intent(DoctorFillForm6.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                    finish();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                }else{
                                                    Intent toRviewPage = new Intent(DoctorFillForm6.this, SentForReview.class);
                                                    toRviewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(toRviewPage);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(DoctorFillForm6.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(DoctorFillForm6.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(DoctorFillForm6.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "Something Went Wrong", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
            }else{
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "Please Select Date", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        else {
            if (date___!=null){
                String user_id_google = GoogleSignIn.getLastSignedInAccount(DoctorFillForm6.this).getId().toString();
                DatabaseReference profiledata = FirebaseDatabase.getInstance().getReference("Doctor").child(stateofdoctorfill1.getText().toString()).child(districtofdoctorfill.getText().toString()).child(user_id_google).child("Schedule Timings").child(date___);
                TimingUpload timingUpload = new TimingUpload(OFF_DAY_1, OFF_DAY_2, OFF_DAY_3, OFF_DAY_4, OFF_DAY_5, OFF_DAY_6, START_TIME_1,START_TIME_2, START_TIME_3, START_TIME_4, START_TIME_5, START_TIME_6, START_TIME_7,START_TIME_8,START_TIME_9,START_TIME_10,END_TIME_1,END_TIME_2,END_TIME_3,END_TIME_4,END_TIME_5,END_TIME_6,END_TIME_7,END_TIME_8,END_TIME_9,END_TIME_10,APPLICATION_STATUS);
                profiledata.setValue(timingUpload).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String state1 = snapshot.child(user_id_google).child("states").getValue(String.class);
                                String district = snapshot.child(user_id_google).child("districts").getValue(String.class);
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor").child(state1).child(district).child(user_id_google).child("Schedule Timings").child("application_STATUS");
                                databaseReference.setValue("true");
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
                                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        state = snapshot.child(user_id_google).child("states").getValue(String.class);
                                        String district = snapshot.child(user_id_google).child("districts").getValue(String.class);
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor").child(state).child(district).child(user_id_google);
                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Application_Status = snapshot.child("Schedule Timings").child("application_STATUS").getValue(String.class);
                                                VerificationStatus = snapshot.child("EducationQualification").child("approved").getValue(String.class);
                                                if (Application_Status != null) {
                                                    AlertDialog.Builder builder
                                                            = new AlertDialog
                                                            .Builder(DoctorFillForm6.this);
                                                    builder.setMessage("Schedule for selected date has been saved.");
                                                    builder.setTitle("Schedule Saved");
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton(
                                                            "Ok",
                                                            new DialogInterface
                                                                    .OnClickListener() {

                                                                @Override
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    dialog.cancel();
                                                                    Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "Schedule Saved", Snackbar.LENGTH_LONG);
                                                                    snackbar.show();
                                                                    Intent intent = new Intent(DoctorFillForm6.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                    finish();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = builder.create();
                                                    alertDialog.show();
                                                } else {
                                                    Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "Schedule Saved", Snackbar.LENGTH_LONG);
                                                    snackbar.show();
                                                    Intent toRviewPage = new Intent(DoctorFillForm6.this, SentForReview.class);
                                                    toRviewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(toRviewPage);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(DoctorFillForm6.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(DoctorFillForm6.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(DoctorFillForm6.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "Something Went Wrong", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
            }else{
                Snackbar snackbar = Snackbar.make(DoctorFillForm6.this, findViewById(android.R.id.content), "Please Select Date", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

    }
}