package com.jco.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Initial_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setContentView(R.layout.activity_initial_screen);
        TitanicTextView loadingScreenTextView = (TitanicTextView) findViewById(R.id.loading_screen_textview);
        loadingScreenTextView.setTypeface(Typefaces.get(this, "trench100free.ttf"));
        // loadingScreenTextView .setTypeface(font);
        new Titanic().start(loadingScreenTextView);





        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(7000);  //Delay of 10 seconds

                } catch (Exception e) {

                } finally {

                    //Intent i = new Intent(Initial_Screen.this,
                      //      Activity.class);
                    //startActivity(i);
                    //finish();
                }
            }
        };
        welcomeThread.start();




    }


}

