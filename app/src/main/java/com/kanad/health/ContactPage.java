package com.kanad.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactPage extends AppCompatActivity {
    EditText subject,message;
    Button submitqna;
    TextView credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        submitqna = findViewById(R.id.submitqna);
        credits = findViewById(R.id.credits);
        submitqna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!subject.getText().toString().isEmpty()){
                    if (!message.getText().toString().isEmpty()){
                        String uid;
                        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                            uid = FirebaseAuth.getInstance().getUid();
                        }else{
                            uid = GoogleSignIn.getLastSignedInAccount(ContactPage.this).getId();
                        }
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Contacts").child(uid);
                        ContactMessage contactMessage = new ContactMessage(message.getText().toString(),subject.getText().toString());
                        databaseReference.setValue(contactMessage).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                AlertDialog.Builder builder
                                        = new AlertDialog
                                        .Builder(ContactPage.this);
                                builder.setMessage("Your message has been sent, we will reach out to you soon.");
                                builder.setTitle("Message Sent");
                                builder.setCancelable(false);
                                builder.setNegativeButton(
                                        "Ok",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                dialog.cancel();
                                                finish();
                                            }
                                        });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    }else{
                        message.setError("");
                    }
                }else{
                    subject.setError("");
                }
            }
        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                credits.setPaintFlags(credits.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(ContactPage.this);
                builder.setMessage("https://www.medicalnewstoday.com/articles/191530#_noHeaderPrefixedContent\n" +
                        "https://www.alcohol.org/app/uploads/2019/07/alcoholichepatitis.jpg\n" +
                        "https://wikiimg.tojsiabtv.com/wikipedia/commons/thumb/b/bb/Hives_on_back.jpg/1280px-Hives_on_back.jpg\n" +
                        "https://2rdnmg1qbg403gumla1v9i2h-wpengine.netdna-ssl.com/wp-content/uploads/sites/3/2021/12/psoriaticRheumatoidArthritis1-182657432-770x533-1-745x490.jpg\n" +
                        "https://safemedtrip.com/images/blog/brain-haemorrhage.jpg\n" +
                        "https://images.indianexpress.com/2018/05/asthma_759_getty.jpg\n" +
                        "https://cdn.yashodahospitals.com/wp-content/uploads/Cervical-Spondylosis-Complications.jpg\n" +
                        "https://www.verywellhealth.com/thmb/yRogyW2adUWQKwHtBnPCwrKhdAU=/614x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/rear-view-of-shirtless-girl-with-chickenpox-1035538836-5bfd8d8f46e0fb00269c436a.jpg\n" +
                        "https://www.healthline.com/health/cholestasis\n" +
                        "https://media.npr.org/assets/artslife/books/2010/09/ah-choo/common-cold-093a75e6c0c4ac8e57a7c9861f2332d29966ac15-s400-c85.webp\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/virus\" title=\"virus icons\">Virus icons created by Freepik - Flaticon</a>\n" +
                        "https://npr.brightspotcdn.com/dims4/default/9991fe2/2147483647/strip/true/crop/700x460+0+0/resize/880x578!/quality/90/?url=https%3A%2F%2Fwusfnews.wusf.usf.edu%2Fsites%2Fwusf%2Ffiles%2F201910%2FAedes-aegypti.jpg\n" +
                        "https://news.yale.edu/sites/default/files/styles/featured_media/public/adobestock_276205639.jpeg?itok=PjcxEgaC&c=07307e7d6a991172b9f808eb83b18804\n" +
                        "https://link.springer.com/chapter/10.1007/978-3-319-18627-6_38\n" +
                        "https://i0.wp.com/medicosnext.com/wp-content/uploads/2020/02/cs-Misdiagnosis-Cutaneous-Fungal-Infections-RM-63651.jpg?resize=300%2C200&ssl=1\n" +
                        "http://rajasthangastrohospital.com/images/Gastroenteritis.jpg\n" +
                        "https://d3b6u46udi9ohd.cloudfront.net/wp-content/uploads/2018/12/24065145/gastro-gerd.png\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/doctor\" title=\"doctor icons\">Doctor icons created by Freepik - Flaticon</a>\n" +
                        "https://www.parkview.com/media/Image/Dashboard_952_heartattack_9_20.jpg\n" +
                        "https://www.cdc.gov/dotw/hepatitisa/images/main_928px.jpg\n" +
                        "https://www.drugtargetreview.com/wp-content/uploads/shutterstock_1103225849.jpg\n" +
                        "https://medlineplus.gov/images/HepatitisC.jpg\n" +
                        "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/rich_media_quiz/topic/rmq_hepatitis_myths_facts/405x250_hepatitis_virus_rmq.jpg?resize=750px:*&output-quality=75\n" +
                        "https://d2jx2rerrg6sh3.cloudfront.net/image-handler/ts/20180822093912/ri/673/picture/2018/8/shutterstock_663568408.jpg\n" +
                        "https://ysm-res.cloudinary.com/image/upload/ar_16:9,c_fill,dpr_2.0,f_auto,g_faces:auto,q_auto:eco,w_1500/v1/yale-medicine/live-prod/ym_new/images/c5dcc21c-ef9c-437f-8ab7-c3c4cbb60c4d_tcm990-401203.jpg\n" +
                        "https://images.everydayhealth.com/images/thyroid-conditions/hyperthyroidism/hyperthyroidism-symptoms-and-diagnosis-722x406.jpg?w=1110\n" +
                        "https://www.january.ai/wp-content/uploads/2021/12/what-is-hypoglycemia.jpeg\n" +
                        "https://magazine.medlineplus.gov/images/uploads/main_images/hypothyroidism-research-log-term.jpg\n" +
                        "https://images.everydayhealth.com/images/skin-beauty/impetigo/10-common-questions-about-impetigo-answered-722x406.jpg?w=1110\n" +
                        "https://www.istockphoto.com/photo/hepatitis-gm1196559718-341369100\n" +
                        "https://www.yourgenome.org/sites/default/files/images/photos/Anopheles%20mosquito%20-%20dangerous%20vehicle%20of%20infection%20%282%29.jpg\n" +
                        "https://2rdnmg1qbg403gumla1v9i2h-wpengine.netdna-ssl.com/wp-content/uploads/sites/3/2014/12/migraine-951541836-770x553-1-745x490.jpg\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/patient\" title=\"patient icons\">Patient icons created by Freepik - Flaticon</a>\n" +
                        "https://files.nccih.nih.gov/s3fs-public/osteoarthritis%20knee_GettyImages-694311050_square.jpg\n" +
                        "https://cdn.sanity.io/images/0vv8moc6/mhe/9e5f061813e5be4352ff81473cb13d98bba67b7e-1000x563.png/Ulcer_194276273.png?w=1500&fit=max&auto=format\n" +
                        "https://i2.wp.com/www.extrachai.com/wp-content/uploads/2018/10/hemorrhoids-2790200_1920.jpg?w=1920&ssl=1\n" +
                        "https://st1.thehealthsite.com/wp-content/uploads/2021/11/pneumonia.jpg?impolicy=Medium_Widthonly&w=710\n" +
                        "https://upload.wikimedia.org/wikipedia/commons/f/f5/Psoriasis_on_back1.jpg\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/health\" title=\"health icons\">Health icons created by Freepik - Flaticon</a>\n" +
                        "https://www.healthline.com/health/high-blood-pressure-hypertension-symptoms#overview\n" +
                        "https://www.cdc.gov/tb/topic/basics/howtbspreads.htm\n" +
                        "https://dca2kl2ymltr7.cloudfront.net/mediaLibrary/images/large/id094_typhoid_fever_img1_au.large.jpg\n" +
                        "https://lirp.cdn-website.com/92602df1/dms3rep/multi/opt/AdobeStock_179353469-1920w.jpeg\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/reservation\" title=\"reservation icons\">Reservation icons created by iconixar - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/family\" title=\"family icons\">Family icons created by Flat Icons - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/patient\" title=\"patient icons\">Patient icons created by Freepik - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/friend-request\" title=\"friend request icons\">Friend request icons created by Freepik - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/message\" title=\"message icons\">Message icons created by Freepik - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/doctor\" title=\"doctor icons\">Doctor icons created by Freepik - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/privacy\" title=\"privacy icons\">Privacy icons created by Freepik - Flaticon</a>\n" +
                        "https://www.verywellhealth.com/thmb/16itx06DcyNW-uyDg5mJkwZa8lo=/614x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/elderly-woman-shows-varicose-1145186766-cfb1326c1dac4189bbc78ca19847128b.jpg\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/verified\" title=\"verified icons\">Verified icons created by Freepik - Flaticon</a>\n" +
                        "https://www.narayanahealth.org/blog/wp-content/uploads/2020/02/shutterstock_1415434781.jpg\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/virus\" title=\"virus icons\">Virus icons created by Freepik - Flaticon</a>\n" +
                        "<a href=\"https://www.flaticon.com/free-icons/alert\" title=\"alert icons\">Alert icons created by Freepik - Flaticon</a>\n");
                builder.setTitle("Visual Credit(s)");
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Ok",
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
}