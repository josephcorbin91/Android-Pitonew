package com.jco.pitonew.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jco.pitonew.ButtonRectangle;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.Utility;

import org.w3c.dom.Text;

import java.util.ArrayList;

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

    private static Double[] results;
    private static String units;
    private OnFragmentInteractionListener mListener;

    public ResultFragment() {



        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param results resultArray 1.
     * @return A new instance of fragment ResultFragment.
     */


    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(Double[]results, String units) {
        ResultFragment fragment = new ResultFragment();
        ResultFragment.results=results;
        ResultFragment.units =units;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    private TextView unitsAtmosphericPressureTextView,unitsAreaTextView,unitsAverageVelocity,unitsMassAirFlow,unitsNormalAirFlow,unitsActualAirFlow,unitsDuctPressure,unitsCalculatedGasDensity,UnitsMolarWeight;
    private TextView relativeHumidityResultTextView,atmosphericPressureTextView,averageVelocityResultTextView,ductAreaGasFlowFragmentTextView,molecularResultTextView,massAirFlowResultTextView,normalAirFlowResultTextView,actualAirFlowResultTextView,ductPressureFragmentTextView,gasDesnityResultTextView;

    public void instantiateViews() {
        unitsAverageVelocity = (TextView) mView.findViewById(R.id.UnitsaverageVelocityTextView);
        unitsMassAirFlow = (TextView) mView.findViewById(R.id.UnitsmassAirFlowTextView);
        unitsNormalAirFlow = (TextView) mView.findViewById(R.id.UnitsNormalAirFlowTextView);
        unitsActualAirFlow = (TextView) mView.findViewById(R.id.UnitsactualAirFlowTextView);
        unitsDuctPressure =(TextView)  mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity =(TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);
        UnitsMolarWeight =(TextView)mView.findViewById(R.id.UnitsMolarWeight);
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
        changeUnits(ResultFragment.units);








    }

    public void setResults(){

        averageVelocityResultTextView.setText(String.format("%.4f", ResultFragment.results[0].toString()));
        massAirFlowResultTextView.setText(String.format("%.4f",ResultFragment.results[1].toString()));
        normalAirFlowResultTextView.setText(String.format("%.4f",ResultFragment.results[2].toString()));
        actualAirFlowResultTextView.setText(String.format("%.4f",ResultFragment.results[3].toString()));
        ductPressureFragmentTextView.setText(String.format("%.4f",ResultFragment.results[4].toString()));
        gasDesnityResultTextView.setText(String.format("%.4f",ResultFragment.results[5].toString()));
        molecularResultTextView.setText(String.format("%.4f",ResultFragment.results[6].toString()));
        ductAreaGasFlowFragmentTextView.setText(String.format("%.4f",ResultFragment.results[7].toString()));
        atmosphericPressureTextView.setText(String.format("%.4f",ResultFragment.results[8].toString()));
        relativeHumidityResultTextView.setText(String.format("%.4f",ResultFragment.results[9].toString()));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.result_fragment, container, false);
        instantiateViews();
        setResults();

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


        public void changeUnits(String units) {


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

                    break;
            }
        }

        public boolean verifyInput(){
            return true;
            //return !emptyEditText(dimensionWidthGasEditText)&& !emptyEditText(dimensionHeightGasEditText) && !emptyEditText(pitotTubeCoefficientEditText) && !emptyEditText( staticPressureFragmentEditText) &&!emptyEditText( temperatureGasFragmentEditText) && !emptyEditText(ElevationAboveSeaLevelFragmentEdiText) && !emptyEditText( wetBulbTemperatureGasFragmentEditText) && !emptyEditText(seaLevelPressureGasFragmentEditText);


        }

        public boolean emptyEditText(EditText editText){
            return editText.getText().equals("");
        }





}
