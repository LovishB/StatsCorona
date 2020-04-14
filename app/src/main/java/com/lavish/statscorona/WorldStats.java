package com.lavish.statscorona;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WorldStats extends AppCompatActivity {

    String url ="https://www.worldometers.info/coronavirus/#countries";
    ProgressDialog progressDialog;
    String worldDataSelector = "#maincounter-wrap > div";

    private ImageView indiaselect,statsselect,symptomsselect;
    private TextView worldtotalcases,deathsworld,recoveredworld;
    String[] worlddata;
    String TotalCases,Death,Recovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_stats);


        worldtotalcases=findViewById(R.id.totalcasesworld);
        deathsworld=findViewById(R.id.deathsworld);
        recoveredworld=findViewById(R.id.recoveredworld);

        statsselect=findViewById(R.id.selectstats);
        symptomsselect=findViewById(R.id.selectsymptoms);
        indiaselect=findViewById(R.id.selectindia);


        Content content=new Content();
        content.execute();
        getworlddata();

        indiaselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WorldStats.this,IndiaStats.class);
                i.putExtra("data",worlddata);
                startActivity(i);
            }
        });

        statsselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WorldStats.this,GeneralStats.class);
                i.putExtra("data",worlddata);
                startActivity(i);

            }
        });

        symptomsselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WorldStats.this,Symptoms.class);
                i.putExtra("data",worlddata);
                startActivity(i);
            }
        });
    }

    public void getworlddata()
    {




        Intent intent=  getIntent();
        worlddata=intent.getStringArrayExtra("data");

        //deathsworld.setText(worlddata[1]);
       // recoveredworld.setText(worlddata[2]);
      // worldtotalcases.setText(worlddata[0]);





    }


    public class Content extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(WorldStats.this);
            progressDialog.setMessage("Fetching Live Data");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            worldtotalcases.setText(TotalCases);
            deathsworld.setText(Death);
            recoveredworld.setText(Recovered);
            progressDialog.dismiss();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document = Jsoup.connect(url).get();
                //Title=document.title();
                Elements worldDataElements = document.select(worldDataSelector);
                ArrayList<String> worldDataTitles = new ArrayList<>();

                for(Element e:worldDataElements) {
                    worldDataTitles.add(e.text());
                }

                TotalCases=worldDataTitles.get(0);
                Death=worldDataTitles.get(1);
                Recovered=worldDataTitles.get(2);

            }catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }






}
