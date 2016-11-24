package com.jco.pitonew;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
private Spinner spinner;
    private GasDensityFragment gasDensityFragment;
    private GasFlowFragment gasFlowFragment;
    private FragmentTransaction fragmentTransaction;
    private Toolbar actionToolBar;
    private Button clearButton, calculateButton;
    private String currentCalculations;
    private Switch unitSwitch,rectangularOrCircularSwitch;
    private TextView unitsGasDensityTemperatureDB,unitsGasDensityTemperatureWB, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel,unitsGasDensityAtmosphericPressure, unitsStaticPressure,unitsDuctPressure,dimensionHeader,dimension1TextView, dimension2TextView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rectangularOrCircularSwitch = (Switch)findViewById(R.id.rectangularCircularGasFlowFragmentSwitch);
        dimensionHeader = (TextView)findViewById(R.id.rectangularCircularGasFlowFragmentTextView);
        dimension1TextView = (TextView)findViewById(R.id.dimensionHeightOrDiameterGasFlowFragmentTextView);
        dimension2TextView = (TextView)findViewById(R.id.dimensionWidthGasFlowFragmentTextView);
        gasDensityFragment = new GasDensityFragment();
        gasFlowFragment = new GasFlowFragment();
        actionToolBar = (Toolbar)findViewById(R.id.action_bar_toolbar);
        unitSwitch = (Switch)findViewById(R.id.unitSwitch);
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeUnits("Metric");
                    // The toggle is enabled
                } else {
                    changeUnits("Imperial");
                    // The toggle is disabled
                }
            }
        });

        rectangularOrCircularSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDimensions("Rectangular");
                    // The toggle is enabled
                } else {
                    setDimensions("Circular");
                    // The toggle is disabled
                }
            }
        });



        setSupportActionBar(actionToolBar);
        //actionToolBar.setNavigationIcon(R.drawable.pitonew_logo);
       // actionToolBar.setNavigationContentDescription("This App");
      //  actionToolBar.setLogo(R.drawable.pitonew_logo);
      //  actionToolBar.setLogoDescription("LOGO DESCRIPTION");

        actionToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "TEST",Toast.LENGTH_LONG).show();
            return true;
            }
        });
        actionToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Navigation Icon Pressed",Toast.LENGTH_LONG).show();

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
            gasDensityFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, gasDensityFragment).commit();
        }
        currentCalculations="gasFlow";
        clearButton = (Button)findViewById(R.id.toolbarClearButton);
        calculateButton = (Button)findViewById(R.id.tooldbarCalculateButton);
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (currentCalculations){
                    case "gasFlow":
                        clearAllFields((ViewGroup)findViewById(R.id.gasFlowFragment));
                    case "gasDensity":
                        clearAllFields((ViewGroup)findViewById(R.id.gasDensityFragment));

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
            GasFlow gasFlow = new GasFlow(this);
            gasFlow.displayResult();
            break;
        case "gasDensity" :
            GasDensity gasDensity = new GasDensity(this);
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

    public void changeUnits(String units) {
        unitsGasDensityTemperatureDB = (TextView) findViewById(R.id.UnitstemperatureGasDensityFragmentTextView);
        unitsGasDensityTemperatureWB = (TextView) findViewById(R.id.UnitsWetBulbtemperatureGasDensityFragmentTextView);
        unitsGasDensitySeaLevelPressure = (TextView) findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityElevationAboveSeaLevel = (TextView) findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityAtmosphericPressure = (TextView) findViewById(R.id.UnitsAtmosphericPressureFragmentTextView);
        unitsDuctPressure = (TextView) findViewById(R.id.UnitsDuctPressureFragmentTextView);
        TextView unitsAverageVelocity = (TextView)findViewById(R.id.UnitsDimensionHeightGasFlowFragmentTextView);
        TextView unitsMassAirFlow =  (TextView)findViewById(R.id.UnitsDimensionWidthGasFlowFragmentTextView);
        TextView unitsActualAirFlow=   (TextView)  findViewById(R.id.UnitspitotTubeCoefficientTextView);
        TextView unitsNormalAirFlow=(TextView)findViewById(R.id.UnitsnormalAirFlowTextView);
        TextView UnitsDimensionHeightGasFlowFragmentTextView = (TextView)findViewById(R.id.UnitsDimensionHeightGasFlowFragmentTextView);
        TextView UnitsDimensionWidthGasFlowFragmentTextView =  (TextView)findViewById(R.id.UnitsDimensionWidthGasFlowFragmentTextView);
        TextView UnitspitotTubeCoefficientTextView=   (TextView)  findViewById(R.id.UnitspitotTubeCoefficientTextView);

        TextView UnitsCalculatedGasDensity = (TextView)findViewById(R.id.UnitsCalculatedGasDensityTextView);
        switch (units) {
            case "Metric":
                unitsGasDensityTemperatureDB.setText("°C");
                unitsGasDensityTemperatureWB.setText("°C");
                unitsGasDensitySeaLevelPressure.setText("kPa");
                unitsGasDensityElevationAboveSeaLevel.setText("m");
                unitsGasDensityAtmosphericPressure.setText("kPa");
                unitsDuctPressure.setText("kPa");
                unitsAverageVelocity.setText("m/s");
                unitsMassAirFlow.setText("kg/s");
                unitsNormalAirFlow.setText("Nm³/h");
                unitsActualAirFlow.setText("m³/h");
                UnitsCalculatedGasDensity.setText("kg/m³");
                break;
            case "Imperial":
                unitsGasDensityTemperatureDB.setText("°F");
                unitsGasDensityTemperatureWB.setText("°F");
                unitsGasDensitySeaLevelPressure.setText("in. Hg");
                unitsGasDensityElevationAboveSeaLevel.setText("ft");
                unitsGasDensityAtmosphericPressure.setText("in. Hg");
                unitsDuctPressure.setText("in. Hg");
                unitsAverageVelocity.setText("ft/s");
                unitsMassAirFlow.setText("lb/min");
                unitsNormalAirFlow.setText("ACFM");
                unitsActualAirFlow.setText("SCFM");
                UnitsCalculatedGasDensity.setText("lb/ft³");
                break;
        }
    }

    public void setDimensions(String dimensions){
        switch (dimensions) {
            case "Rectangular":
                dimensionHeader.setText("Rectangular");
                dimension1TextView.setText("Height");
                dimension2TextView.setText("Width");
                break;
            case "Circular":
                dimensionHeader.setText("Circular");
                dimension1TextView.setText("Diameter");
                break;
        }
        }



    }





