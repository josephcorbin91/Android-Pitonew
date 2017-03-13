package com.jco.pitonew.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jco.pitonew.Fragments.InputFragment;
import com.jco.pitonew.Fragments.PagerFragment;
import com.jco.pitonew.Fragments.ResultFragment;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.Utility;

/**
 * Created by jco on 12/3/2016.
 */
public class DisplayActivity extends AppCompatActivity{
        private MaterialSpinner spinner;
        private Toolbar actionToolBar;
        private AppCompatButton clearButton,calculateButton;
        private String currentCalculations,currentUnits;
        private MaterialMenuDrawable materialMenu;
        private Switch unitSwitch;
        private PagerFragment pagerFragment;
        private FragmentManager fragmentManager;
        private String currentFragment;
    private InputFragment inputFragment;
    private ResultFragment resultFragment;
        private Switch circularOrRectangularSwitch;
    private DrawerLayout drawerLayout;
    private boolean isDrawerOpened;
        //private MaterialMenuDrawable materialMenu;

    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                newGame();
                return tru99999999999992                      p            case R.id.help:
                showHelp();
                return true;
                */
                return super.onOptionsItemSelected(item);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    private LinearLayout activityMainRootLayout;
    protected void onCreate(Bundle savedInstanceState) {
        currentUnits = "Metric";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);


        inputFragment = new InputFragment();
        pagerFragment = new PagerFragment();
        currentFragment = "inputFragment";
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }


            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            transaction.add(R.id.fragment_container, inputFragment);
            transaction.commit();
            Log.i("FRAG TAG", "Fragment Commited");


        }


        unitSwitch = (Switch) findViewById(R.id.unitSwitch);
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputFragment.changeUnits("US");

                    currentUnits = "US";
                    if (currentFragment.equals("resultFragment")) {
                        FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                        Gas gas = new Gas(inputFragment.getResults(), inputFragment.getDynamicPressure(), false /* unitSwitch.isChecked()*/, false/*circularOrRectangularSwitch.isChecked()*/);
                        resultFragment = ResultFragment.newInstance(gas.getResults(), "US");
                        transaction.replace(R.id.fragment_container, resultFragment);
                        transaction.commit();
                        DisplayActivity.this.currentFragment = "resultFragment";

                    }

                } else {
                    inputFragment.changeUnits("SI");

                    currentUnits = "SI";
                    if (currentFragment.equals("resultFragment")) {
                        FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                        Gas gas = new Gas(inputFragment.getResults(), inputFragment.getDynamicPressure(), true /* unitSwitch.isChecked()*/, false/*circularOrRectangularSwitch.isChecked()*/);
                        resultFragment = ResultFragment.newInstance(gas.getResults(), "SI");
                        transaction.replace(R.id.fragment_container, resultFragment);
                        transaction.commit();
                        DisplayActivity.this.currentFragment = "resultFragment";
                    }
                }
            }
        });

        //Action Toolbar Code
        actionToolBar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        setSupportActionBar(actionToolBar);


        clearButton = (AppCompatButton) findViewById(R.id.toolbarClearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DisplayActivity.this.currentFragment.equals("resultFragment")) {
                    FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                    transaction.replace(R.id.fragment_container, inputFragment);
                    transaction.commit();
                }
                else
                    inputFragment.clear();


            }
        });
        calculateButton = (AppCompatButton) findViewById(R.id.tooldbarCalculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputFragment.validInput()) {
                    FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                    Gas gas = new Gas(inputFragment.getResults(), inputFragment.getDynamicPressure(), true /* unitSwitch.isChecked()*/, false/*circularOrRectangularSwitch.isChecked()*/);
                    transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                    resultFragment = ResultFragment.newInstance(gas.getResults(), getUnits());
                    transaction.replace(R.id.fragment_container, resultFragment);
                    transaction.commit();
                    DisplayActivity.this.currentFragment = "resultFragment";
                }
                else
                    Toast.makeText(DisplayActivity.this, "Please fill in all input fields", Toast.LENGTH_SHORT).show();

            }


        });
    }



        public String getUnits(){
        return this.currentUnits;
    }





    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DisplayActivity.this);
        // builder.setCancelable(false);
        builder.setTitle("Rate Pitonew if you like it.");
        builder.setMessage("Do you want to exit Pitonew?");
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();

            }
        });
        builder.setNeutralButton("Rate",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
/*
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                */
                finish();

            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }








}






