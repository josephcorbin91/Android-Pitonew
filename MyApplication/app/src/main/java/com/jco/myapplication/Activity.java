package com.jco.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

/**
 * Created by jco on 12/3/2016.
 */
public class Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        private Spinner spinner;
        private GasDensityFragment gasDensityFragment;
        private GasFlowFragment gasFlowFragment;
        private FragmentTransaction fragmentTransaction;
        private Toolbar actionToolBar;
        private ButtonRectangle clearButton, calculateButton;
        private String currentCalculations,currentUnits;
        private TextView unitsGasDensityTemperatureDB,unitsGasDensityTemperatureWB, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel,unitsGasDensityAtmosphericPressure, unitsStaticPressure,unitsDuctPressure,dimensionHeader,dimension1TextView, dimension2TextView;
        private View gasDensityFragmentView, gasFlowFragmentView;
        private Switch unitSwitch;
        private Switch circularOrRectangularSwitch;



        private LinearLayout activityMainRootLayout;
    protected void onCreate(Bundle savedInstanceState) {




        currentUnits="Metric";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        unitSwitch = (Switch) findViewById(R.id.unitSwitch);
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (currentCalculations) {
                        case "gasFlow":
                            gasFlowFragment.changeUnits("Imperial");
                            currentUnits = "Imperial";
                            break;
                        case "gasDensity":
                            gasDensityFragment.changeUnits("Imperial");
                            currentUnits = "Imperial";
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
        dimensionHeader = (TextView)findViewById(R.id.rectangularCircularGasFlowFragmentTextView);
        dimension1TextView = (TextView)findViewById(R.id.dimensionHeightOrDiameterGasFlowFragmentTextView);
        dimension2TextView = (TextView)findViewById(R.id.dimensionWidthGasFlowFragmentTextView);
        gasDensityFragment = new GasDensityFragment();
        gasFlowFragment = new GasFlowFragment();
        actionToolBar = (Toolbar)findViewById(R.id.action_bar_toolbar);

/*


*/

        setSupportActionBar(actionToolBar);
        //actionToolBar.setNavigationIcon(R.drawable.pitonew_logo);
        // actionToolBar.setNavigationContentDescription("This App");
        //  actionToolBar.setLogo(R.drawable.pitonew_logo);
        //  actionToolBar.setLogoDescription("LOGO DESCRIPTION");

        actionToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(Activity.this, "TEST",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        actionToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity.this, "Navigation Icon Pressed",Toast.LENGTH_LONG).show();

                actionToolBar.setVisibility(View.GONE);
            }
        });
        spinner = (Spinner) findViewById(R.id.functionsSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.functionality_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout


            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            gasFlowFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, gasFlowFragment).commit();
        }
        currentCalculations="gasFlow";
        clearButton = (ButtonRectangle)findViewById(R.id.toolbarClearButton);
        calculateButton = (ButtonRectangle)findViewById(R.id.tooldbarCalculateButton);
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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

                calculateResult(currentCalculations);




            }
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, gasFlowFragment).commit();
                currentCalculations="gasFlow";
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, gasDensityFragment).commit();
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
                circularOrRectangularSwitch = (Switch)findViewById(R.id.rectangularCircularGasFlowFragmentSwitch);
                String pipeType= circularOrRectangularSwitch.isChecked()? "circular" : "rectangular";
                Toast.makeText(getApplicationContext(), pipeType,Toast.LENGTH_LONG).show();
                GasFlow gasFlow = new GasFlow(this,pipeType);
                if(!gasFlow.verifyDataIntegrity()){
                    Toast.makeText(getApplicationContext(), "Invalid or missing entry \n Please try again.",Toast.LENGTH_LONG).show();
                    gasFlowFragment.clear();
                }
                else
                    gasFlow.displayResult();
                break;
            case "gasDensity" :
                GasDensity gasDensity = new GasDensity(this);
                if(!gasDensity.verifyDataIntegrity()){
                    Toast.makeText(getApplicationContext(), "Invalid or missing entry \n Please try again.",Toast.LENGTH_LONG).show();
                    gasDensityFragment.clear();
                }
                else
                    gasDensity.displayResult();
                break;
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






