package com.kanad.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.util.Strings;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckDisease extends AppCompatActivity {
    AutoCompleteTextView Symptom1;
    AutoCompleteTextView Symptom2;
    AutoCompleteTextView Symptom3;
    AutoCompleteTextView Symptom4;
    AutoCompleteTextView Symptom5;
    ArrayAdapter<String> SymptomsList;
    Button CheckDisease;
    TextView Symptom_1,intenttogooglw;
    TextView Symptom_2;
    TextView Symptom_3;
    TextView Symptom_4;
    TextView Symptom_5;
    TextView DiseaseName;
    String url = "https://health-app-for-people.herokuapp.com/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_disease);
        View include = findViewById(R.id.form);
        Symptom1 = findViewById(R.id.Symptom1);
        Symptom2 = findViewById(R.id.Symptom2);
        Symptom3 = findViewById(R.id.Symptom3);
        Symptom4 = findViewById(R.id.Symptom4);
        Symptom5 = findViewById(R.id.Symptom5);
        Symptom_1 = findViewById(R.id.Symptom_1);
        Symptom_2 = findViewById(R.id.Symptom_2);
        Symptom_3 = findViewById(R.id.Symptom_3);
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
        Symptom_4 = findViewById(R.id.Symptom_4);
        Symptom_5 = findViewById(R.id.Symptom_4);
        DiseaseName = findViewById(R.id.DiseaseName);
        CheckDisease = findViewById(R.id.CheckDisease);
        intenttogooglw = findViewById(R.id.intenttogooglw);
        String[] Symptoms_List = new String[]{"back_pain", "constipation", "abdominal_pain", "diarrhoea", "mild_fever", "yellow_urine",
                "yellowing_of_eyes", "acute_liver_failure", "fluid_overload", "swelling_of_stomach",
                "swelled_lymph_nodes", "malaise", "blurred_and_distorted_vision", "phlegm", "throat_irritation",
                "redness_of_eyes", "sinus_pressure", "runny_nose", "congestion", "chest_pain", "weakness_in_limbs",
                "fast_heart_rate", "pain_during_bowel_movements", "pain_in_anal_region", "bloody_stool",
                "irritation_in_anus", "neck_pain", "dizziness", "cramps", "bruising", "obesity", "swollen_legs",
                "swollen_blood_vessels", "puffy_face_and_eyes", "enlarged_thyroid", "brittle_nails",
                "swollen_extremeties", "excessive_hunger", "extra_marital_contacts", "drying_and_tingling_lips",
                "slurred_speech", "knee_pain", "hip_joint_pain", "muscle_weakness", "stiff_neck", "swelling_joints",
                "movement_stiffness", "spinning_movements", "loss_of_balance", "unsteadiness",
                "weakness_of_one_body_side", "loss_of_smell", "bladder_discomfort", "foul_smell_of urine",
                "continuous_feel_of_urine", "passage_of_gases", "internal_itching", "toxic_look_(typhos)",
                "depression", "irritability", "muscle_pain", "altered_sensorium", "red_spots_over_body", "belly_pain",
                "abnormal_menstruation", "dischromic _patches", "watering_from_eyes", "increased_appetite", "polyuria", "family_history", "mucoid_sputum",
                "rusty_sputum", "lack_of_concentration", "visual_disturbances", "receiving_blood_transfusion",
                "receiving_unsterile_injections", "coma", "stomach_bleeding", "distention_of_abdomen",
                "history_of_alcohol_consumption", "fluid_overload", "blood_in_sputum", "prominent_veins_on_calf",
                "palpitations", "painful_walking", "pus_filled_pimples", "blackheads", "scurring", "skin_peeling",
                "silver_like_dusting", "small_dents_in_nails", "inflammatory_nails", "blister", "red_sore_around_nose",
                "yellow_crust_ooze"};
        SymptomsList = new ArrayAdapter<String>(CheckDisease.this, R.layout.list_item, Symptoms_List);
        Symptom1.setAdapter(SymptomsList);
        Symptom2.setAdapter(SymptomsList);
        Symptom3.setAdapter(SymptomsList);
        Symptom4.setAdapter(SymptomsList);
        Symptom5.setAdapter(SymptomsList);
        TextInputLayout siska1 = findViewById(R.id.Symp1);
        TextInputLayout siska2 = findViewById(R.id.Symp2);
        TextInputLayout siska3 = findViewById(R.id.Symp3);
        TextInputLayout siska4 = findViewById(R.id.Symp4);
        TextInputLayout siska5 = findViewById(R.id.Symp5);
        ProgressDialog disease_bar = new ProgressDialog(com.kanad.health.CheckDisease.this);
        disease_bar.setCanceledOnTouchOutside(false);
        disease_bar.setMessage("Loading");
        CheckDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disease_bar.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String diseasePredicted = jsonObject.getString("disease");
                                    String accuracy = jsonObject.getString("accuracy");
                                    Float number = Float.parseFloat(accuracy);
                                    if (!diseasePredicted.isEmpty()){
                                        android.app.AlertDialog.Builder builder
                                                = new android.app.AlertDialog
                                                .Builder(CheckDisease.this);
                                        builder.setMessage("We have got your result, Although this app tells you name of probable disease with more than 95% accuracy, Please Do not consider it a truth take it as reminder to see your doctor (If it comes something severe).");
                                        builder.setTitle("See Your Disease Report");
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
                                                        Intent intent = new Intent(com.kanad.health.CheckDisease.this, DiseaseReport.class);
                                                        intent.putExtra("Diseasename", diseasePredicted);
                                                        intent.putExtra("Symptom1", siska1.getEditText().getText().toString());
                                                        intent.putExtra("Symptom2", siska2.getEditText().getText().toString());
                                                        intent.putExtra("Symptom3", siska3.getEditText().getText().toString());
                                                        intent.putExtra("Symptom4", siska4.getEditText().getText().toString());
                                                        intent.putExtra("Symptom5", siska5.getEditText().getText().toString());
                                                        intent.putExtra("accuracy", accuracy);
                                                        disease_bar.dismiss();
                                                        startActivity(intent);
                                                    }
                                                });

                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }else {
                                        Toast.makeText(com.kanad.health.CheckDisease.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        disease_bar.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(com.kanad.health.CheckDisease.this, "Can't Find Name of The Disease", Toast.LENGTH_SHORT).show();
                                    disease_bar.dismiss();
                                    Snackbar snackbar = Snackbar.make(CheckDisease.this, findViewById(android.R.id.content), "We are processing your request", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error==null){
                            Snackbar snackbar = Snackbar.make(CheckDisease.this, findViewById(android.R.id.content), "WTF is this error", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        disease_bar.dismiss();
                        Snackbar snackbar = Snackbar.make(CheckDisease.this, findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("Symptom1", siska1.getEditText().getText().toString());
                        params.put("Symptom2", siska2.getEditText().getText().toString());
                        params.put("Symptom3", siska3.getEditText().getText().toString());
                        params.put("Symptom4", siska4.getEditText().getText().toString());
                        params.put("Symptom5", siska5.getEditText().getText().toString());
                        return params;
                    }
                };
                Snackbar snackbar = Snackbar.make(CheckDisease.this, findViewById(android.R.id.content), "We Are Processing Your Request", Snackbar.LENGTH_LONG);
                snackbar.show();
                RequestQueue queue = Volley.newRequestQueue(CheckDisease.this);
                queue.add(stringRequest);
            }
        });
    }
}