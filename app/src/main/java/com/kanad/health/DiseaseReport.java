package com.kanad.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiseaseReport extends AppCompatActivity {
    TabLayout DiseaseTabLayout;
    ViewPager DiseaseLayoutViewPager;
    TextView nameonreport,symptomonreport,accuracy_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_report);
        DiseaseTabLayout = findViewById(R.id.DiseaseTabLayout);
        nameonreport = findViewById(R.id.nameonreport);
        DiseaseLayoutViewPager = findViewById(R.id.DiseaseLayoutViewPager);
        symptomonreport = findViewById(R.id.symptomonreport);
        accuracy_text = findViewById(R.id.accuracy);
        DiseaseTabLayout.setupWithViewPager(DiseaseLayoutViewPager);
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
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new AboutSectionForReport(), "About");
        vpAdapter.addFragment(new TreatmentForDisease(), "Treatment");
        vpAdapter.addFragment(new SymptomForDiseaseReport(), "Consult");
        DiseaseLayoutViewPager.setAdapter(vpAdapter);
        Bundle extras = getIntent().getExtras();
        String Diseasename = extras.getString("Diseasename");
        String Symptom1 = extras.getString("Symptom1");
        String Symptom2 = extras.getString("Symptom2");
        String Symptom3 = extras.getString("Symptom3");
        String Symptom4 = extras.getString("Symptom4");
        String Symptom5 = extras.getString("Symptom5");
        String accuracy = extras.getString("accuracy");
        Float percentage_accuracy = Float.parseFloat(accuracy)*100;
        accuracy_text.setText("Accuracy: "+percentage_accuracy+"%");
        switch (Diseasename){
            case "Fungal infection":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Fungal Infection", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img1 = findViewById(R.id.fungalinfection);
                img1.setVisibility(View.VISIBLE);
                break;
            case "Allergy":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Allergy", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img2 = findViewById(R.id.Allergy);
                img2.setVisibility(View.VISIBLE);
                break;
            case "GERD":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is GERD", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img3 = findViewById(R.id.GERD);
                img3.setVisibility(View.VISIBLE);
                break;
            case "Chronic cholestasis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Chronic cholestasis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img4 = findViewById(R.id.Chroniccholestasis);
                img4.setVisibility(View.VISIBLE);
                break;
            case "Drug Reaction":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Drug Reaction", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img5 = findViewById(R.id.drugreaction);
                img5.setVisibility(View.VISIBLE);
                break;
            case "Peptic ulcer diseae":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Peptic ulcer diseae", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img6 = findViewById(R.id.Pepticulcerdiseae);
                img6.setVisibility(View.VISIBLE);
                break;
            case "AIDS":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Peptic ulcer AIDS", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img7 = findViewById(R.id.aids);
                img7.setVisibility(View.VISIBLE);
                break;
            case "Diabetes":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Diabetes", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img8 = findViewById(R.id.Diabetes);
                img8.setVisibility(View.VISIBLE);
                break;
            case "Gastroenteritis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Gastroenteritis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img9 = findViewById(R.id.Gastroenteritis);
                img9.setVisibility(View.VISIBLE);
                break;
            case "Bronchial Asthma":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Bronchial Asthma", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img10 = findViewById(R.id.bronchialasthma);
                img10.setVisibility(View.VISIBLE);
                break;
            case "Hypertension":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hypertension", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img11 = findViewById(R.id.tension);
                img11.setVisibility(View.VISIBLE);
                break;
            case "Migraine":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Migraine", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img12 = findViewById(R.id.migraine);
                img12.setVisibility(View.VISIBLE);
                break;
            case "Cervical spondylosis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Cervical spondylosis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img13 = findViewById(R.id.Cervicalspondylosis);
                img13.setVisibility(View.VISIBLE);
                break;
            case "Paralysis (brain hemorrhage)":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Cervical Paralysis (brain hemorrhage)", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img14 = findViewById(R.id.Paralysisbrainhemorrhage);
                img14.setVisibility(View.VISIBLE);
                break;
            case "Jaundice":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Jaundice", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img15 = findViewById(R.id.Jaundice);
                img15.setVisibility(View.VISIBLE);
                break;
            case "Malaria":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Malaria", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img16 = findViewById(R.id.Malaria);
                img16.setVisibility(View.VISIBLE);
                break;
            case "Chicken pox":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Chicken pox", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img17 = findViewById(R.id.Chickenpox);
                img17.setVisibility(View.VISIBLE);
                break;
            case "Dengue":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Dengue", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img18 = findViewById(R.id.dengue);
                img18.setVisibility(View.VISIBLE);
                break;
            case "Typhoid":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Typhoid", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img19 = findViewById(R.id.Typhoid);
                img19.setVisibility(View.VISIBLE);
                break;
            case "hepatitis A":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is hepatitis A", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img20 = findViewById(R.id.hepatitisa);
                img20.setVisibility(View.VISIBLE);
                break;
            case "Hepatitis B":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hepatitis B", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img21 = findViewById(R.id.hepatitisb);
                img21.setVisibility(View.VISIBLE);
                break;
            case "Hepatitis C":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hepatitis C", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img22 = findViewById(R.id.hepatitisc);
                img22.setVisibility(View.VISIBLE);
                break;
            case "Hepatitis D":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hepatitis D", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img23 = findViewById(R.id.hepatitisd);
                img23.setVisibility(View.VISIBLE);
                break;
            case "Hepatitis E":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hepatitis E", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img24 = findViewById(R.id.hepatitise);
                img24.setVisibility(View.VISIBLE);
                break;
            case "Alcoholic hepatitis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Alcoholic hepatitis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img25 = findViewById(R.id.alcoholichepatitis);
                img25.setVisibility(View.VISIBLE);
                break;
            case "Tuberculosis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Tuberculosis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img26 = findViewById(R.id.Tuberculosis);
                img26.setVisibility(View.VISIBLE);
                break;
            case "Common Cold":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Common Cold", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img27 = findViewById(R.id.CommonCold);
                img27.setVisibility(View.VISIBLE);
                break;
            case "Pneumonia":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Pneumonia", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img28 = findViewById(R.id.Pneumonia);
                img28.setVisibility(View.VISIBLE);
                break;
            case "Dimorphic hemmorhoids(piles)":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Dimorphic hemmorhoids(piles)", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img29 = findViewById(R.id.piles);
                img29.setVisibility(View.VISIBLE);
                break;
            case "Heart attack":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Heart attack", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img30 = findViewById(R.id.Heartattack);
                img30.setVisibility(View.VISIBLE);
                break;
            case "Varicose veins":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Varicose veins", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img31 = findViewById(R.id.varicoseveins);
                img31.setVisibility(View.VISIBLE);
                break;
            case "Hypothyroidism":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hypothyroidism", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img32 = findViewById(R.id.hypothyroidism);
                img32.setVisibility(View.VISIBLE);
                break;
            case "Hyperthyroidism":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hyperthyroidism", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img33 = findViewById(R.id.hyperthyroidism);
                img33.setVisibility(View.VISIBLE);
                break;
            case "Hypoglycemia":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Hypoglycemia", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img34 = findViewById(R.id.hypoglycemia);
                img34.setVisibility(View.VISIBLE);
                break;
            case "Osteoarthristis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Osteoarthristis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img35 = findViewById(R.id.osteoarthristis);
                img35.setVisibility(View.VISIBLE);
                break;
            case "Arthritis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Arthritis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img36 = findViewById(R.id.Arthritis);
                img36.setVisibility(View.VISIBLE);
                break;
            case "(vertigo) Paroymsal  Positional Vertigo":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is (vertigo) Paroymsal  Positional Vertigo", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img37 = findViewById(R.id.vertigo);
                img37.setVisibility(View.VISIBLE);
                break;
            case "Acne":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Acne", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img38 = findViewById(R.id.Acne);
                img38.setVisibility(View.VISIBLE);
                break;
            case "Urinary tract infection":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Urinary tract infection", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img39 = findViewById(R.id.Urinarytractinfection);
                img39.setVisibility(View.VISIBLE);
                break;
            case "Psoriasis":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Psoriasis", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img40 = findViewById(R.id.Psoriasis);
                img40.setVisibility(View.VISIBLE);
                break;
            case "Impetigo":
                Toast.makeText(com.kanad.health.DiseaseReport.this, "This is Impetigo", Toast.LENGTH_SHORT).show();
                symptomonreport.setText(Symptom1+", "+Symptom2+", "+Symptom3+", "+Symptom4+", "+Symptom5);
                nameonreport.setText(Diseasename);
                CircleImageView img41 = findViewById(R.id.Impetigo);
                img41.setVisibility(View.VISIBLE);
                break;
        }
    }
    public String getMyData() {
        TextView nameonreport = findViewById(R.id.nameonreport);
        String Diseasename = nameonreport.getText().toString();
        return Diseasename;
    }
}