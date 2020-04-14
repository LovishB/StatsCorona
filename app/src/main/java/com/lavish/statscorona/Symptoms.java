package com.lavish.statscorona;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Symptoms extends AppCompatActivity {
    private ImageView worldselect,statsselect, indiaselect;
    String[] symptomdata;
    private TextView mild,severe,critical,fatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        statsselect=findViewById(R.id.selectstats);
        worldselect=findViewById(R.id.selectworld);
        indiaselect=findViewById(R.id.selectindia);

        mild=findViewById(R.id.mild);
        severe=findViewById(R.id.severe);
        fatal=findViewById(R.id.fatal);
        critical=findViewById(R.id.critical);

        getsymptomsdata();

        indiaselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Symptoms.this,IndiaStats.class);
                i.putExtra("data",symptomdata);
                startActivity(i);
            }
        });

        statsselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Symptoms.this,GeneralStats.class);
                i.putExtra("data",symptomdata);
                startActivity(i);

            }
        });

        worldselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Symptoms.this,WorldStats.class);
                i.putExtra("data",symptomdata);
                startActivity(i);
            }
        });

    }

    public void getsymptomsdata()
    {
        Intent intent=  getIntent();
        symptomdata=intent.getStringArrayExtra("data");
        mild.setText(symptomdata[4]);
        critical.setText(symptomdata[6]);
        severe.setText(symptomdata[5]);
        fatal.setText(symptomdata[7]);
    }
}
