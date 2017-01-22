package com.jco.pitonew;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;

/**
 * Created by jco on 12/3/2016.
 */
public class Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        private MaterialSpinner spinner;
        private GasDensityFragment gasDensityFragment;
        private GasFlowFragment gasFlowFragment;
        private BlankContainer blankContainer;
        private FragmentTransaction fragmentTransaction;
        private Toolbar actionToolBar;
        private AppCompatButton clearButton,calculateButton;
        private String currentCalculations,currentUnits;
        private TextView unitsGasDensityTemperatureDB,unitsGasDensityTemperatureWB, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel,unitsGasDensityAtmosphericPressure, unitsStaticPressure,unitsDuctPressure,dimensionHeader,dimension1TextView, dimension2TextView;
        private View gasDensityFragmentView, gasFlowFragmentView;
        private MaterialMenuDrawable materialMenu;
        private Switch unitSwitch;
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

        gasDensityFragment = new GasDensityFragment();
        gasFlowFragment = new GasFlowFragment();


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.setCustomAnimations(R.anim.fadein,R.anim.fadeout);
            transaction.add(R.id.fragment_container, gasDensityFragment);
            transaction.commit();


        }
        currentCalculations="gasDensity";


        unitSwitch = (Switch) findViewById(R.id.unitSwitch);
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (currentCalculations) {
                        case "gasFlow":
                            gasFlowFragment.changeUnits("Imperial");
                            gasDensityFragment.clear();
                            currentUnits = "Imperial";
                            break;
                        case "gasDensity":
                            gasDensityFragment.changeUnits("Imperial");
                            currentUnits = "Imperial";
                            gasDensityFragment.clear();
                            break;
                    }
                } else {
                    switch (currentCalculations) {
                        case "gasFlow":
                            gasFlowFragment.changeUnits("Metric");
                            currentUnits = "Metric";
                            break;
                        case "gasDensity":
                            gasDensityFragment.changeUnits("Metric");
                            currentUnits = "Metric";
                            break;
                    }
                }
            }
        });
        dimension1TextView = (TextView) findViewById(R.id.dimensionHeightOrDiameterGasFlowFragmentTextView);
        dimension2TextView = (TextView) findViewById(R.id.dimensionWidthGasFlowFragmentTextView);


        //Action Toolbar Code
        actionToolBar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        setSupportActionBar(actionToolBar);

        //Spinner Code
        spinner = (MaterialSpinner) findViewById(R.id.functionsSpinner);

        spinner.setItems("Gas Density", "Gas Flow");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                                              @Override
                                              public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                                                  switch (position) {

                                                      case 0:
                                                          getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout).replace(R.id.fragment_container, gasDensityFragment).commit();
                                                          currentCalculations = "gasDensity";
                                                          break;
                                                      case 1:
                                                          getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout).replace(R.id.fragment_container, gasFlowFragment).commit();
                                                          currentCalculations = "gasFlow";
                                                          break;

                                                  }
                                              }
                                          });

        clearButton = (AppCompatButton)findViewById(R.id.toolbarClearButton);
        calculateButton = (AppCompatButton)findViewById(R.id.tooldbarCalculateButton);
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                System.out.println(currentCalculations);
                switch (currentCalculations){
                    case "gasFlow":
                        gasFlowFragment.clear();
                    case "gasDensity":
                        gasDensityFragment.clear();
                }
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult(currentCalculations);}
        });






    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        switch (pos) {
            case 0:
                //android.support.v4.app.FragmentManager fm = getSupportFragmentManager();https://github.com/DesarrolloAntonio/FragmentTransactionExtended/blob/master/fragmentTransactionExample/src/main/java/com/desarrollodroide/fragmenttrasitionextendedexample/MainActivity.java
                // FragmentTransaction fragmentTransaction = fm.beginTransaction();
                // FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(this, fragmentTransaction, gasDensityFragment, gasFlowFragment, R.id.fragment_container);
                //  fragmentTransactionExtended.addTransition(FragmentTransactionExtended.GLIDE);
                //  fragmentTransactionExtended.commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.fragment_container, gasFlowFragment).commit();
                currentCalculations="gasFlow";
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.fragment_container, gasDensityFragment).commit();
                currentCalculations="gasDensity";
                break;
        }
        Toast.makeText(getApplicationContext(),String.valueOf(parent.getItemIdAtPosition(pos)), Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

        Toast.makeText(getApplicationContext(),"NOTHING SELECTED", Toast.LENGTH_LONG).show();

    }


    public void calculateResult(String currentCalculations){
        switch (currentCalculations){
            case "gasFlow" :
                /*
                circularOrRectangularSwitch = (Switch)findViewById(R.id.rectangularCircularGasFlowFragmentSwitch);
                String pipeType= circularOrRectangularSwitch.isChecked()? "circular" : "rectangular";
                Toast.makeText(getApplicationContext(), pipeType,Toast.LENGTH_LONG).show();
                GasFlow gasFlow = new GasFlow(this,pipeType);
                if(!gasFlow.verifyDataIntegrity()){
                    Toast.makeText(getApplicationContext(), "Invalid or missing entry \n Please try again.",Toast.LENGTH_LONG).show();
                    gasFlowFragment.clear();
                    gasFlowFragment.showResultView();
                }
                else{

                    gasFlowFragment.clear();
                    //gasFlow.calculateArea();

                }
                break;
                */
                gasFlowFragment.showResultView();

                Toast.makeText(getApplicationContext(), "ToBeDetermined",Toast.LENGTH_LONG).show();

            case "gasDensity" :
                /*
                GasDensity gasDensity = new GasDensity(this,gasDensityFragment,currentUnits,gasDensityFragment.getStandardAirBoolean());
                if(!gasDensity.verifyDataIntegrity()){
                    Toast.makeText(getApplicationContext(), "Invalid or missing entry \n Please try again.",Toast.LENGTH_LONG).show();
                    gasDensityFragment.clear();
                }
                else
                    gasDensity.calculatePressureAtCurrentElevation();
                break;
                */
                gasDensityFragment.showResultView();

                Toast.makeText(getApplicationContext(), "ToBeDetermined",Toast.LENGTH_LONG).show();

        }

    }


    public void clearAllFields(ViewGroup group){

        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearAllFields((ViewGroup)view);
        }
    }




}






