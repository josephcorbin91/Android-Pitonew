package com.jco.pitonew.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jco.pitonew.R;
import com.jco.pitonew.Titanic;
import com.jco.pitonew.TitanicTextView;
import com.jco.pitonew.Typefaces;

public class InitialScreen extends AppCompatActivity {
    private Thread welcomeThread;
    private TextView loadingScreenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_loading_screen);



        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.setDuration(4000);
        container.setBaseAlpha(0.7f);
        container.startShimmerAnimation();





                    Thread welcomeThread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                super.run();
                                sleep(5000);
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(InitialScreen.this,
                            DisplayActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();




    }


}
