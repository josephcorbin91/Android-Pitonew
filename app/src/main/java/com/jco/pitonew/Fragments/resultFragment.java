package com.jco.pitonew.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.jco.pitonew.ButtonRectangle;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.Utility;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RESULTS = "resultsArray";
    private static final String UNITS = "units";
    private View mView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Double[] results;
    private String units;
    private Double[] dynamicVelocity;
    private OnFragmentInteractionListener mListener;

    public ResultFragment() {



        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResultFragment.
     */


    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    private TextView unitsAtmosphericPressureTextView,unitsAreaTextView,unitsAverageVelocity,unitsMassAirFlow,unitsNormalAirFlow,unitsActualAirFlow,unitsDuctPressure,unitsCalculatedGasDensity,UnitsMolarWeight,UnitsDynamicVelocityTextView;
    private TextView relativeHumidityResultTextView,atmosphericPressureTextView,averageVelocityResultTextView,ductAreaGasFlowFragmentTextView,molecularResultTextView,massAirFlowResultTextView,normalAirFlowResultTextView,actualAirFlowResultTextView,ductPressureFragmentTextView,gasDesnityResultTextView;
    private TableRow relativeHumidityTableRow;
    private TextView dynamicVelocityResultTextView;
    private View relativeHumidityResultDivider;
    public void instantiateViews() {
        relativeHumidityTableRow = (TableRow)mView.findViewById(R.id.relativeHumidityTableRow);
        relativeHumidityResultDivider = (View)mView.findViewById(R.id.relativeHumidityResultDivider);
        unitsAverageVelocity = (TextView) mView.findViewById(R.id.UnitsaverageVelocityTextView);
        unitsMassAirFlow = (TextView) mView.findViewById(R.id.UnitsmassAirFlowTextView);
        unitsNormalAirFlow = (TextView) mView.findViewById(R.id.UnitsNormalAirFlowTextView);
        unitsActualAirFlow = (TextView) mView.findViewById(R.id.UnitsactualAirFlowTextView);
        unitsDuctPressure =(TextView)  mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity =(TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);
        UnitsMolarWeight =(TextView)mView.findViewById(R.id.UnitsMolarWeight);
        UnitsDynamicVelocityTextView = (TextView)mView.findViewById(R.id.UnitsDynamicVelocityTextView);
        unitsAtmosphericPressureTextView =(TextView)mView.findViewById(R.id.UnitsAtmosphericPressureFragmentTextViewUnits);
                unitsAreaTextView =(TextView)mView.findViewById(R.id.ductAreaGasFlowFragmentTextViewUnits);

        atmosphericPressureTextView = (TextView) mView.findViewById(R.id.gasAtmosphericPressureResultTextView);
        averageVelocityResultTextView = (TextView) mView.findViewById(R.id.averageVelocityResultTextView);
        ductAreaGasFlowFragmentTextView=(TextView) mView.findViewById(R.id.ductAreaGasFlowFragmentTextView);
        relativeHumidityResultTextView = (TextView)mView.findViewById(R.id.relativeHumidityResultTextView);
        massAirFlowResultTextView = (TextView) mView.findViewById(R.id.massAirFlowResultTextView);
        normalAirFlowResultTextView = (TextView) mView.findViewById(R.id.normalAirFlowResultTextView);
        actualAirFlowResultTextView = (TextView) mView.findViewById(R.id.actualAirFlowResultTextView);
        ductPressureFragmentTextView =(TextView)  mView.findViewById(R.id.ductPressureFragmentTextView);
        gasDesnityResultTextView =(TextView) mView.findViewById(R.id.gasDesnityResultTextView);
        molecularResultTextView =(TextView) mView.findViewById(R.id.molecularWeightResultTextView);
        dynamicVelocityResultTextView = (TextView) mView.findViewById(R.id.dynamicVelocityResultTextView);








    }

    public void setResultValues(Double[]results, String units,Double[] dynamicVelocity){

        this.results=results;
        this.units =units;
        this.dynamicVelocity = dynamicVelocity;
    }
    public void setResults(){


        DecimalFormat doubleTwoDigitsDecimalFormat = new DecimalFormat("#.00");
        DecimalFormat doubleSixDigitsDecimalFormat = new DecimalFormat("#.000000");
        DecimalFormat doubleThreeDigitsDecimalFormat = new DecimalFormat("#.000");
        DecimalFormat doubleFourDigitsDecimalFormat = new DecimalFormat("#.0000");



        averageVelocityResultTextView.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(results[0])));
            massAirFlowResultTextView.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(results[1])));
            normalAirFlowResultTextView.setText(String.valueOf(results[2].intValue()));
            actualAirFlowResultTextView.setText(String.valueOf(results[3].intValue()));
            ductPressureFragmentTextView.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(results[4])));
            gasDesnityResultTextView.setText(String.valueOf(doubleFourDigitsDecimalFormat.format(Math.floor(results[5] * 1000000) / 1000000)));
            molecularResultTextView.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(results[6])));
            ductAreaGasFlowFragmentTextView.setText(String.valueOf(doubleThreeDigitsDecimalFormat.format(Math.ceil(results[7] * 100) / 100)));
            atmosphericPressureTextView.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(results[8])));

             String dynamicVelocityString = "";
            for (int i = 0; i < dynamicVelocity.length; i++)
                dynamicVelocityString += String.valueOf(doubleTwoDigitsDecimalFormat.format(dynamicVelocity[i]) + "\n");
            dynamicVelocityResultTextView.setText(dynamicVelocityString);


            if(results[9].equals(.000)) {
            relativeHumidityResultDivider.setVisibility(View.GONE);
            relativeHumidityTableRow.setVisibility(View.GONE);
            relativeHumidityResultTextView.setVisibility(View.GONE);

        }
        else {
            relativeHumidityResultTextView.setVisibility(View.VISIBLE);
            relativeHumidityResultDivider.setVisibility(View.VISIBLE);
            relativeHumidityTableRow.setVisibility(View.VISIBLE);
            relativeHumidityResultTextView.setText(String.valueOf(doubleThreeDigitsDecimalFormat.format(results[9])));

        }

        changeStringUnits(this.units);



    }

    @Override
    public void onStart() {
        setResults();
        super.onStart();
        System.out.println("UNS ON start called");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.result_fragment, container, false);
        instantiateViews();
        System.out.print("On create view");

        return  mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



        private Gas gas;

        private ButtonRectangle dynamicVelocityButton;
        private ArrayList<String > stringInputValues = new ArrayList<String>();


        private double[] standardAirResult;
        public ArrayList<Double> dynamicVelocityArrayList;

        public void clear() {
            Utility.clearAllFields((ViewGroup) this.mView);
        }

        public void changeStringUnits(String units){
            DecimalFormat doubleTwoDigitsDecimalFormat = new DecimalFormat("#.0000");


            switch (units) {
                case "SI":
                    unitsAverageVelocity.setText("m/s");
                    unitsMassAirFlow.setText("kg/s");
                    unitsNormalAirFlow.setText("Nm³/h");
                    unitsActualAirFlow.setText("m³/h");
                    unitsDuctPressure.setText("kPa");
                    UnitsMolarWeight.setText("g/mol");
                    unitsCalculatedGasDensity.setText("kg/m³");
                    unitsAtmosphericPressureTextView.setText("kPa");
                    unitsAreaTextView.setText("m²");
                    UnitsDynamicVelocityTextView.setText("m/s");
                    break;
                case "US":
                    unitsAverageVelocity.setText("ft/s");
                    UnitsDynamicVelocityTextView.setText("ft/s");
                    unitsMassAirFlow.setText("lb/min");
                    unitsNormalAirFlow.setText("ACFM");
                    unitsActualAirFlow.setText("SCFM");
                    unitsDuctPressure.setText("in. Hg");
                    UnitsMolarWeight.setText("g/mol");
                    unitsCalculatedGasDensity.setText("lb/ft³");
                    unitsAreaTextView.setText("in²");
                    unitsAtmosphericPressureTextView.setText("in. Hg");
                    UnitsDynamicVelocityTextView.setText("ft/s");
                    break;
            }
            }



        public void changeUnits(String units) {

            System.out.println("UNS :changing results to"+units);
            DecimalFormat doubleTwoDigitsDecimalFormat = new DecimalFormat("#.00");
            DecimalFormat noDigitDecimalFormat = new DecimalFormat("#.");
            DecimalFormat doubleThreeDigitsDecimalFormat = new DecimalFormat("#.000");
            DecimalFormat doubleFourDigitsDecimalFormat = new DecimalFormat("#.0000");



            switch (units) {
                case "SI":

                    unitsAverageVelocity.setText("m/s");
                    unitsMassAirFlow.setText("kg/s");
                    unitsNormalAirFlow.setText("Nm³/h");
                    unitsActualAirFlow.setText("m³/h");
                    unitsDuctPressure.setText("kPa");
                    UnitsMolarWeight.setText("g/mol");
                    unitsCalculatedGasDensity.setText("kg/m³");
                    unitsAtmosphericPressureTextView.setText("kPa");
                    unitsAreaTextView.setText("m²");
                    UnitsDynamicVelocityTextView.setText("m/s");
                    String dynamicVelocityString="";
                    for(int i=0;i<dynamicVelocity.length;i++){
                        dynamicVelocity[i]=dynamicVelocity[i]/3.28084;
                        dynamicVelocityString+= String.valueOf(doubleTwoDigitsDecimalFormat.format(dynamicVelocity[i]))+ "\n";

                    }
                    dynamicVelocityResultTextView.setText(dynamicVelocityString);


                    averageVelocityResultTextView.setText(String.valueOf(Double.valueOf(averageVelocityResultTextView.getText().toString())*12/39.3701));
                    massAirFlowResultTextView.setText(String.valueOf(Double.valueOf(massAirFlowResultTextView.getText().toString())/(2.2046*60)));

                    normalAirFlowResultTextView.setText(String.valueOf((Double.valueOf(normalAirFlowResultTextView.getText().toString())*60/((Math.pow(39.3701/12,3)*(294.26/273.15))))));
                    actualAirFlowResultTextView.setText(String.valueOf((Double.valueOf(actualAirFlowResultTextView.getText().toString())*60/(Math.pow(39.3701/12,3)))));
                    ductPressureFragmentTextView.setText(String.valueOf(Double.valueOf(ductPressureFragmentTextView.getText().toString())*3.38639));

                    gasDesnityResultTextView.setText(String.valueOf(Double.valueOf(gasDesnityResultTextView.getText().toString())*16.018463));
                    atmosphericPressureTextView.setText(String.valueOf(Double.valueOf(atmosphericPressureTextView.getText().toString())*3.38639));
                    ductAreaGasFlowFragmentTextView.setText(String.valueOf(Double.valueOf(ductAreaGasFlowFragmentTextView.getText().toString())*0.00064516));



                    break;
                case "US":
                    unitsAverageVelocity.setText("ft/s");

                    unitsMassAirFlow.setText("lb/min");
                    unitsNormalAirFlow.setText("ACFM");
                    unitsActualAirFlow.setText("SCFM");
                    unitsDuctPressure.setText("in. Hg");
                    UnitsMolarWeight.setText("g/mol");
                    unitsCalculatedGasDensity.setText("lb/ft³");
                    unitsAreaTextView.setText("in²");
                    unitsAtmosphericPressureTextView.setText("in. Hg");
                    UnitsDynamicVelocityTextView.setText("ft/s");

                    dynamicVelocityString="";
                    for(int i=0;i<dynamicVelocity.length;i++){
                        dynamicVelocity[i]=dynamicVelocity[i]*3.28084;
                        dynamicVelocityString+= String.valueOf(doubleTwoDigitsDecimalFormat.format(dynamicVelocity[i]))+ "\n";

                    }
                    dynamicVelocityResultTextView.setText(dynamicVelocityString);


                    averageVelocityResultTextView.setText(String.valueOf(Double.valueOf(averageVelocityResultTextView.getText().toString())*39.3701/12));
                    massAirFlowResultTextView.setText(String.valueOf(Double.valueOf(massAirFlowResultTextView.getText().toString())*(2.2046*60)));


                    normalAirFlowResultTextView.setText(String.valueOf((Double.valueOf(normalAirFlowResultTextView.getText().toString())*((Math.pow(39.3701/12,3)*(294.26/273.15)))/60)));
                    actualAirFlowResultTextView.setText(String.valueOf((Double.valueOf(actualAirFlowResultTextView.getText().toString())/60*(Math.pow(39.3701/12,3)))));
                    ductPressureFragmentTextView.setText(String.valueOf(Double.valueOf(ductPressureFragmentTextView.getText().toString())/3.38639));

                    gasDesnityResultTextView.setText(String.valueOf(Double.valueOf(gasDesnityResultTextView.getText().toString())/16.018463));
                    atmosphericPressureTextView.setText(String.valueOf(Double.valueOf(atmosphericPressureTextView.getText().toString())/3.38639));
                    ductAreaGasFlowFragmentTextView.setText(String.valueOf(Double.valueOf(ductAreaGasFlowFragmentTextView.getText().toString())/0.00064516));

                    break;
            }
        }







}
