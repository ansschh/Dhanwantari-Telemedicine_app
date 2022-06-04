package com.kanad.health;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SymptomForDiseaseReport extends Fragment {
    TextView seedoctor_frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_symptom_for_disease_report, container, false);
        seedoctor_frag = view.findViewById(R.id.seedoctor_frag);
        seedoctor_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DoctorShow.class);
                startActivity(intent);
            }
        });
        return view;
    }
}