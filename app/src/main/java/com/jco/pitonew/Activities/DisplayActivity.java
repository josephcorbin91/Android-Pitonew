package com.jco.pitonew.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.health.SystemHealthManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jco.pitonew.Fragments.InputFragment;
import com.jco.pitonew.Fragments.ResultFragment;
import com.jco.pitonew.Fragments.TheoryFragment;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by jco on 12/3/2016.
 */


/*
 */
public class DisplayActivity extends AppCompatActivity{
        private MaterialSpinner spinner;
        private Toolbar actionToolBar;
        private Toolbar bottomToolBar;
        private AppCompatButton clearButton,calculateButton,returnButton;
        private String currentCalculations,currentUnits;
        public ArrayList<Double> dynamicPressureArrayList;
        private Switch unitSwitch;
        private boolean originalUnits;

    RelativeLayout ResultFragmentToolBarLayout,InputFragmentToolBarLayout;
    private Double[] previousResults;

    private FragmentManager fragmentManager;
        private String currentFragment;
    private InputFragment inputFragment;
    private ResultFragment resultFragment;

    public boolean onOptionsItemSelected(MenuItem item) {
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
        currentUnits = "SI";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);


        ResultFragmentToolBarLayout = (RelativeLayout)findViewById(R.id.ResultFragmentToolBarLayout);
        InputFragmentToolBarLayout =(RelativeLayout)findViewById(R.id.InputFragmentToolBarLayout);

        inputFragment = new InputFragment();
        resultFragment = new ResultFragment();
        currentFragment = "inputFragment";
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            inputFragment.setPreviousUnits(false);

            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            transaction.replace(R.id.fragment_container, inputFragment);
            transaction.commit();
            InputFragmentToolBarLayout.setVisibility(View.VISIBLE);
            ResultFragmentToolBarLayout.setVisibility(View.GONE);

            Log.i("FRAG TAG", "Fragment Commited");


        }


        unitSwitch = (Switch) findViewById(R.id.unitSwitch);
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(currentFragment.equals("inputFragment"))
                        inputFragment.changeUnits("US");
                    else if(currentFragment.equals("resultFragment")) {
                        resultFragment.changeUnits("US");
                    }
                    currentUnits = "US";
                } else {

                    if(currentFragment.equals("resultFragment")) {
                        resultFragment.changeUnits("SI");
                    }
                    else if(currentFragment.equals("inputFragment"))
                        inputFragment.changeUnits("SI");
                    currentUnits = "SI";
                    }

            }
        });
        //Action Toolbar Code
        actionToolBar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        setSupportActionBar(actionToolBar);
        bottomToolBar = (Toolbar) findViewById(R.id.bottom_toolbar);


        clearButton = (AppCompatButton) findViewById(R.id.toolbarClearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    inputFragment.clear();



            }
        });

        calculateButton = (AppCompatButton) findViewById(R.id.tooldbarCalculateButton);
        returnButton = (AppCompatButton) findViewById(R.id.tooldbarReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultFragmentToolBarLayout.setVisibility(View.GONE);
                InputFragmentToolBarLayout.setVisibility(View.VISIBLE);
                /*
                FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                System.out.print("UNITSS: RETURN" + unitSwitch.isChecked());
                currentFragment="inputFragment";
                transaction.replace(R.id.fragment_container,inputFragment);
                transaction.commit();

                System.out.println("UNITS! Original"+originalUnits);
                System.out.println("UNITS! unitSwitch"+unitSwitch.isChecked());
                if(originalUnits!=unitSwitch.isChecked()) {
                    if (unitSwitch.isChecked())
                        inputFragment.changeUnits("US");
                    else
                        inputFragment.changeUnits("SI");
                }
                */
                onBackPressed();
    }
});


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputFragment.validInput()) {

                    if (inputFragment.verifyDataPressureRule()) {

                        FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();

                        Gas gas = new Gas(inputFragment.getResults(), inputFragment.getDynamicPressureArray(), unitSwitch.isChecked(), inputFragment.pipeType(), inputFragment.wetBulbEnabled());
                        inputFragment.setPreviousUnits(unitSwitch.isChecked());

                        resultFragment.setResultValues(gas.getResults(), getUnits(), gas.getDynamicVelocity());


                        DisplayActivity.this.currentFragment = "resultFragment";
                        transaction.replace(R.id.fragment_container, resultFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();


                        ResultFragmentToolBarLayout.setVisibility(View.VISIBLE);
                        InputFragmentToolBarLayout.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getApplicationContext(), "75% of the velocity pressures should be greater than 10% of maximum velocity pressure.", Toast.LENGTH_LONG).show();
                        Vibrator vibrator = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(500);


                    }

                } else {
                    Toast.makeText(DisplayActivity.this, "Please fill in all input fields", Toast.LENGTH_SHORT).show();
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    vibrator.vibrate(500);
                }
            }
        });}








public String getUnits(){
        return this.currentUnits;
        }

        public Switch UnitSwitch(){
            return unitSwitch;
        }



    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

/**
    public void onTheoryClick(MenuItem mi) {
        FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
        TheoryFragment theoryFragment = TheoryFragment.newInstance();
        transaction.replace(R.id.fragment_container, theoryFragment);
        transaction.commit();
        DisplayActivity.this.currentFragment = "theoryFragment";
        unitSwitch.setVisibility(View.INVISIBLE);

        InputFragmentToolBarLayout.setVisibility(View.GONE);
        ResultFragmentToolBarLayout.setVisibility(View.VISIBLE);

    }
*/


    @Override
    public void onBackPressed() {

        System.out.println("Back Pressed");
        if(currentFragment.equals("inputFragment")){
            AlertDialog.Builder builder = new AlertDialog.Builder(DisplayActivity.this);
            // builder.setCancelable(false);
            builder.setTitle("Rate Pitonew if you like it.");
            builder.setMessage("Do you want to exit Pitonew?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.cancel();

                }
            });
            builder.setNeutralButton("Rate", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    finish();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            System.out.println("Back Pressed"+getSupportFragmentManager().getBackStackEntryCount());
            getSupportFragmentManager().popBackStack();

            currentFragment="inputFragment";
            ResultFragmentToolBarLayout.setVisibility(View.GONE);
            InputFragmentToolBarLayout.setVisibility(View.VISIBLE);

        }
    }






}






