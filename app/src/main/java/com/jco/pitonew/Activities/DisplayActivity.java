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
public class DisplayActivity extends AppCompatActivity{
        private MaterialSpinner spinner;
        private Toolbar actionToolBar;
        private Toolbar bottomToolBar;
        private AppCompatButton clearButton,calculateButton,returnButton;
        private String currentCalculations,currentUnits;
        public ArrayList<Double> dynamicPressureArrayList;

        private Switch unitSwitch;

    RelativeLayout ResultFragmentToolBarLayout,InputFragmentToolBarLayout;

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
        currentUnits = "Metric";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);


        ResultFragmentToolBarLayout = (RelativeLayout)findViewById(R.id.ResultFragmentToolBarLayout);
        InputFragmentToolBarLayout =(RelativeLayout)findViewById(R.id.InputFragmentToolBarLayout);

        inputFragment = new InputFragment();
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
                    else if(currentFragment.equals("resultFragment"))
                        resultFragment.changeUnits("US");
                    currentUnits = "US";
                } else {
                    if(currentFragment.equals("resultFragment"))
                        resultFragment.changeUnits("SI");
                    else
                        inputFragment.changeUnits("SI");
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


                FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                transaction.replace(R.id.fragment_container, inputFragment);
                transaction.commit();
                ResultFragmentToolBarLayout.setVisibility(View.GONE);
                InputFragmentToolBarLayout.setVisibility(View.VISIBLE);
                currentFragment="inputFragment";

            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputFragment.validInput()) {
                    showDyanamicVelocityInputDialog();


                }
                else {
                    Toast.makeText(DisplayActivity.this, "Please fill in all input fields", Toast.LENGTH_SHORT).show();
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    vibrator.vibrate(500);
                }
            }


        });
    }



        public String getUnits(){
        return this.currentUnits;
    }

    private android.support.v7.app.AlertDialog dialog;
    public void showDyanamicVelocityInputDialog(){

        dynamicPressureArrayList = new ArrayList<Double>();
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Dynamic Velocity Input");
        builder.setView(R.layout.dialog_dynamic_velocity);
        builder.setPositiveButton("Done", null); //Set to null. We override the onclick
        builder.setNegativeButton("Cancel", null);
        builder.setNeutralButton("Next",null);

        dialog = builder.create();



        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Dialog alertDialog = DisplayActivity.this.dialog;

                EditText dynamicVelocityInputEditText = (EditText) alertDialog.findViewById(R.id.dynamicVelocityInput);

                dynamicVelocityInputEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_NEXT) {
                            Dialog alertDialog = DisplayActivity.this.dialog;

                            TextView dynamicVelocityTextView = (TextView) alertDialog.findViewById(R.id.listOfDynamicVelocities);

                            EditText dynamicVelocityInputEditText = (EditText) alertDialog.findViewById(R.id.dynamicVelocityInput);

                            dynamicPressureArrayList.add(Double.valueOf(dynamicVelocityInputEditText.getText().toString()));
                            dynamicVelocityInputEditText.setText("");
                            String currentString="";
                            for (Double dynamicVelocity : dynamicPressureArrayList) {
                                currentString+= String.valueOf(dynamicVelocity)+" , ";
                            }
                            dynamicVelocityTextView.setText(currentString);
                            return false;
                        }
                        else
                            return true;
                    }
                });

                Button buttonPositive = ((android.support.v7.app.AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog alertDialog = DisplayActivity.this.dialog;


                        if(dynamicPressureArrayList.size()==0){

                            Vibrator vibrator = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                            EditText dynamicVelocityInputEditText = (EditText) alertDialog.findViewById(R.id.dynamicVelocityInput);
                            dynamicVelocityInputEditText.setError(getString(R.string.dynamic_velocity_required));
                            dynamicVelocityInputEditText.requestFocus();

                        }

                        else if(verifyDataPressureRule()) {
                            FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
                            Gas gas = new Gas(inputFragment.getResults(), dynamicPressureArrayList, unitSwitch.isChecked(), inputFragment.pipeType(), inputFragment.wetBulbEnabled());
                            transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                            resultFragment = ResultFragment.newInstance(gas.getResults(), getUnits(), gas.getDynamicVelocity());
                            transaction.replace(R.id.fragment_container, resultFragment);
                            transaction.commit();
                            DisplayActivity.this.currentFragment = "resultFragment";

                            for(double value : dynamicPressureArrayList)
                                System.out.println("Dynamic Pressure:"+value);
                            ResultFragmentToolBarLayout.setVisibility(View.VISIBLE);
                            InputFragmentToolBarLayout.setVisibility(View.GONE);
                            alertDialog.hide();
                        }
                        else
                        {Toast.makeText(getApplicationContext(), "75% of the velocity pressures should be greater than 10% of maximum velocity pressure.", Toast.LENGTH_LONG).show();
                            Vibrator vibrator = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                            TextView dynamicVelocityTextView = (TextView) alertDialog.findViewById(R.id.listOfDynamicVelocities);
                            EditText dynamicVelocityInputEditText = (EditText) alertDialog.findViewById(R.id.dynamicVelocityInput);
                            dynamicVelocityInputEditText.setText("");
                            dynamicVelocityTextView.setText("");
                            dynamicPressureArrayList.clear();

                        }
                    }
                });

                Button negativeButton = ((android.support.v7.app.AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog alertDialog = DisplayActivity.this.dialog;
                        TextView dynamicVelocityTextView = (TextView) alertDialog.findViewById(R.id.listOfDynamicVelocities);
                        EditText dynamicVelocityInputEditText = (EditText) alertDialog.findViewById(R.id.dynamicVelocityInput);
                        dynamicVelocityInputEditText.setText("");
                        dynamicVelocityTextView.setText("");
                        dynamicPressureArrayList.clear();
                        alertDialog.hide();
                    }
                });

                Button neutralButton = ((android.support.v7.app.AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEUTRAL);
                neutralButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog alertDialog = DisplayActivity.this.dialog;
                        TextView dynamicVelocityTextView = (TextView) alertDialog.findViewById(R.id.listOfDynamicVelocities);
                        EditText dynamicVelocityInputEditText = (EditText) alertDialog.findViewById(R.id.dynamicVelocityInput);
                        dynamicPressureArrayList.add(Double.valueOf(dynamicVelocityInputEditText.getText().toString()));
                        dynamicVelocityInputEditText.setText("");
                        String currentString="";
                        for (Double dynamicVelocity : dynamicPressureArrayList) {
                            currentString+= String.valueOf(dynamicVelocity)+" , ";
                        }
                        dynamicVelocityTextView.setText(currentString);


                    }
                });

            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public boolean verifyDataPressureRule(){
        double maxPressureValue;
        double currentMax=dynamicPressureArrayList.get(0);
        for(int i=0; i< dynamicPressureArrayList.size();i++)
            if(dynamicPressureArrayList.get(i)>currentMax)
                currentMax=dynamicPressureArrayList.get(i);

        System.out.println("DynamicPressure: currentMax " + currentMax);
        maxPressureValue=currentMax;
        double acceptablePressureValue=0;
        for(int i=0; i< dynamicPressureArrayList.size();i++) {
            System.out.println("DynamicPressure: " + dynamicPressureArrayList.get(i));
            if (dynamicPressureArrayList.get(i) > 0.1 * maxPressureValue)
                acceptablePressureValue++;
        }
        System.out.println("DynamicPressure: acceptablePressureValue" + acceptablePressureValue);

        System.out.println("DynamicPressure: dynamicPressureArrayList" + dynamicPressureArrayList.size());
        double percentageOfAcceptableValues = acceptablePressureValue/(double)dynamicPressureArrayList.size();
        System.out.println("DynamicPressure: percentageOfacceptableValues " + percentageOfAcceptableValues);

        return percentageOfAcceptableValues>=0.75;
    }

    public void onTheoryClick(MenuItem mi) {
        FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
        TheoryFragment theoryFragment = TheoryFragment.newInstance();
        transaction.replace(R.id.fragment_container, theoryFragment);
        transaction.commit();
        DisplayActivity.this.currentFragment = "theoryFragment";

        InputFragmentToolBarLayout.setVisibility(View.GONE);
        ResultFragmentToolBarLayout.setVisibility(View.VISIBLE);

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






