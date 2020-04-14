package com.lavish.statscorona;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.evgenii.jsevaluator.JsEvaluator;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends AppCompatActivity {
    private ImageView coronatext;
    JsEvaluator jsEvaluator;
    String[] list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LBnt36S4u23P70F8IDOX6C2XQN9tUO8z9JTeE7PX")
                .clientKey("DMOT2jzCeqAwXYSIdgSMTytpRmHIwgonaOyViSHd")
                .server("https://parseapi.back4app.com")
                .build()
        );

        coronatext=findViewById(R.id.coronatext);
       // jsEvaluator = new JsEvaluator(this);

       // jsEvaluator.callFunction();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Numbers");
        try {
            ParseObject object=query.getFirst();

             list=new String[]{object.get("growthfactor")+"",object.get("deathrate")+"",object.get("genderwise")+"",object.get("period")+"",
                     object.get("mild")+"",object.get("severe")+"",object.get("critical")+"",object.get("fatal")+""};



        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Can't Connect to Server",Toast.LENGTH_LONG).show();
        }


        new CountDownTimer(5000,5000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i =new Intent(MainActivity.this,IndiaStats.class);
                i.putExtra("data",list);
                startActivity(i);

            }
        }.start();

        new CountDownTimer(3500,5000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                coronatext.setAlpha(1f);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .playOn(coronatext);

              //  coronatext.setAlpha(1f);


            }
        }.start();


    }


}
