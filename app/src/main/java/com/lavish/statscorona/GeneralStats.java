package com.lavish.statscorona;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GeneralStats extends AppCompatActivity {
    private ImageView worldselect,symptomsselect, indiaselect;
    String[] statsdata;
    private TextView growthfactor,deathrate,genderwise,period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_stats);

        symptomsselect=findViewById(R.id.selectsymptoms);
        worldselect=findViewById(R.id.selectworld);
        indiaselect=findViewById(R.id.selectindia);

        growthfactor=findViewById(R.id.growthfactor);
        deathrate=findViewById(R.id.deathrate);
       // genderwise=findViewById(R.id.genderrate);
        period=findViewById(R.id.textView);

        getstatsdata();

        indiaselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GeneralStats.this,IndiaStats.class);
                i.putExtra("data",statsdata);
                startActivity(i);
            }
        });

        symptomsselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GeneralStats.this,Symptoms.class);
                i.putExtra("data",statsdata);
                startActivity(i);

            }
        });

        worldselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GeneralStats.this,WorldStats.class);
                i.putExtra("data",statsdata);
                startActivity(i);
            }
        });

    }
    public void getstatsdata()
    {
        Intent intent=  getIntent();
        statsdata=intent.getStringArrayExtra("data");
        growthfactor.setText(statsdata[0]);
        deathrate.setText(statsdata[1]);
        //genderwise.setText(statsdata[9]);

        period.setText(statsdata[3]);



    }

}
