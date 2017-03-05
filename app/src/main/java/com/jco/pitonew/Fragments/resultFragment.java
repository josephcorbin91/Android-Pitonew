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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private TextView unitsAverageVelocity,unitsMassAirFlow,unitsNormalAirFlow,unitsActualAirFlow,unitsDuctPressure,unitsCalculatedGasDensity;
    private TextView averageVelocityResultTextView,ductAreaGasFlowFragmentTextView,molecularResultTextView,massAirFlowResultTextView,normalAirFlowResultTextView,actualAirFlowResultTextView,ductPressureFragmentTextView,gasDesnityResultTextView;

    public void instantiateViews() {
        unitsAverageVelocity = (TextView) mView.findViewById(R.id.UnitsaverageVelocityTextView);
        unitsMassAirFlow = (TextView) mView.findViewById(R.id.UnitsmassAirFlowTextView);
        unitsNormalAirFlow = (TextView) mView.findViewById(R.id.UnitsNormalAirFlowTextView);
        unitsActualAirFlow = (TextView) mView.findViewById(R.id.UnitsactualAirFlowTextView);
        unitsDuctPressure =(TextView)  mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity =(TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);


        averageVelocityResultTextView = (TextView) mView.findViewById(R.id.averageVelocityResultTextView);
        ductAreaGasFlowFragmentTextView=(TextView) mView.findViewById(R.id.ductAreaGasFlowFragmentTextView);

        massAirFlowResultTextView = (TextView) mView.findViewById(R.id.massAirFlowResultTextView);
        normalAirFlowResultTextView = (TextView) mView.findViewById(R.id.normalAirFlowResultTextView);
        actualAirFlowResultTextView = (TextView) mView.findViewById(R.id.actualAirFlowResultTextView);
        ductPressureFragmentTextView =(TextView)  mView.findViewById(R.id.ductPressureFragmentTextView);
        gasDesnityResultTextView =(TextView) mView.findViewById(R.id.gasDesnityResultTextView);
        molecularResultTextView =(TextView) mView.findViewById(R.id.molecularWeightResultTextView);








    }

    public void setResults(Double [] results){

        averageVelocityResultTextView.setText(results[0].toString());
        massAirFlowResultTextView.setText(results[1].toString());
        normalAirFlowResultTextView.setText(results[2].toString());
        actualAirFlowResultTextView.setText(results[3].toString());
        ductPressureFragmentTextView.setText(results[4].toString());
        gasDesnityResultTextView.setText(results[5].toString());
        molecularResultTextView.setText(results[6].toString());
        ductAreaGasFlowFragmentTextView.setText(results[7].toString());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.result_fragment, container, false);
        instantiateViews();
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
                case "Metric":
                    unitsAverageVelocity.setText("m/s");
                    unitsMassAirFlow.setText("kg/s");
                    unitsNormalAirFlow.setText("Nm³/h");
                    unitsActualAirFlow.setText("m³/h");
                    unitsDuctPressure.setText("kPa");
                    unitsCalculatedGasDensity.setText("kg/m³");

                    break;
                case "Imperial":
                    unitsAverageVelocity.setText("ft/s");
                    unitsMassAirFlow.setText("lb/min");
                    unitsNormalAirFlow.setText("ACFM");
                    unitsActualAirFlow.setText("SCFM");
                    unitsDuctPressure.setText("in. Hg");
                    unitsCalculatedGasDensity.setText("lb/ft³");
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
