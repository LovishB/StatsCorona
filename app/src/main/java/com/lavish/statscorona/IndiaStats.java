package com.lavish.statscorona;

import android.app.AlertDialog;
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

public class IndiaStats extends AppCompatActivity {

    private ImageView worldselect,statsselect,symptomsselect;
    private TextView totalindiacases,recoveredindia,newcasesindia,deathsindia;
    String indiadata[];
    private ImageView info;

    private String url ="https://www.mohfw.gov.in/";
    private  ProgressDialog progressDialog;
    //private String indiaDataSelector = "#main_table_countries_today > tbody:nth-child(2) > tr:nth-child(41)";
    private String indiaDataSelectorDeath = "body > div.main-section > div > div.contribution.col-sm-9 > div > div > div:nth-child(4) > div > span";
    private String indiaDataSelectorActive = "body > div.main-section > div > div.contribution.col-sm-9 > div > div > div:nth-child(2) > div > span";
    private String indiaDataSelectorRecovered = "body > div.main-section > div > div.contribution.col-sm-9 > div > div > div:nth-child(3) > div > span";
    private String indiaDataSelectorMigrated = "body > div.main-section > div > div.contribution.col-sm-9 > div > div > div:nth-child(5) > div > span";
    private String a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_stats);

        totalindiacases=findViewById(R.id.totalcasesindia);
        recoveredindia=findViewById(R.id.textView3);
        newcasesindia=findViewById(R.id.indianewcases);
        deathsindia=findViewById(R.id.textView2);
        info=findViewById(R.id.info);

        statsselect=findViewById(R.id.selectstats);
        symptomsselect=findViewById(R.id.selectsymptoms);
        worldselect=findViewById(R.id.selectworld);


        IndiaStats.Content contenta=new IndiaStats.Content();
        contenta.execute();

        getindiadata();

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(IndiaStats.this);
                alertDialogBuilder.setMessage("- World Numbers are scarped from www.worldometers.info"+"\n"+"\n"
                +"- Indian Numbers are scarped from www.mohfw.gov.in"+"\n"+"\n"+
                        "- Splash screen animation is created by Alexandr Sidorovich"+"\n"+"\n"+
                        "- In case of any query, write us at lavishbadlanis7@gmail.com");
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        worldselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(IndiaStats.this,WorldStats.class);
                i.putExtra("data",indiadata);
                startActivity(i);
            }
        });

        statsselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(IndiaStats.this,GeneralStats.class);
                i.putExtra("data",indiadata);
                startActivity(i);

            }
        });

        symptomsselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(IndiaStats.this,Symptoms.class);
                i.putExtra("data",indiadata);
                startActivity(i);
            }
        });
    }

    public void onBackPressed(){
        finishAffinity();
        finish();

    }

    public void getindiadata()
    {

          Intent intent=  getIntent();
           indiadata=intent.getStringArrayExtra("data");

    }

    public class Content extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(IndiaStats.this);
            progressDialog.setMessage("Fetching Live Data");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            totalindiacases.setText(c);
            deathsindia.setText(a);
            recoveredindia.setText(b);
            newcasesindia.setText(d);

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document page = Jsoup.connect(url).get();
                //Elements indiaDataElements = pGE.select(indiaDataSelector);
                Elements indiaDataDealth = page.select(indiaDataSelectorDeath);
                ArrayList<String> indiaDataDeath2 = new ArrayList<>();

                for(Element e:indiaDataDealth) {
                    indiaDataDeath2.add((e.text()));
                }

                Elements indiaDataMigrated = page.select(indiaDataSelectorMigrated);
                ArrayList<String> indiaDataMigrated2 = new ArrayList<>();

                for(Element e:indiaDataMigrated) {
                    indiaDataMigrated2.add((e.text()));
                }

                Elements indiaDataActive = page.select(indiaDataSelectorActive);
                ArrayList<String> indiaDataActive2 = new ArrayList<>();

                for(Element e:indiaDataActive) {
                    indiaDataActive2.add((e.text()));
                }

                Elements indiaDataRecovered = page.select(indiaDataSelectorRecovered);
                ArrayList<String> indiaDataRecovered2 = new ArrayList<>();

                for(Element e:indiaDataRecovered) {
                    indiaDataRecovered2.add((e.text()));
                }

                int sum=Integer.parseInt(indiaDataActive2.get(0))+Integer.parseInt(indiaDataDeath2.get(0))+Integer.parseInt(indiaDataMigrated2.get(0))+Integer.parseInt(indiaDataRecovered2.get(0));

                a=indiaDataDeath2.get(0);
                d=indiaDataMigrated2.get(0);
                c=String.valueOf(sum);
                b=indiaDataRecovered2.get(0);




            }catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }
}
