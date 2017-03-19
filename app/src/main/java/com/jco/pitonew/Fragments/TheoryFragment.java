package com.jco.pitonew.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.jco.pitonew.ButtonRectangle;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.DecimalDigitsInputFilter;
import com.jco.pitonew.Utilities.Utility;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TheoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheoryFragment extends Fragment  {
    public static TheoryFragment newInstance() {
        return new TheoryFragment();
    }




    public TheoryFragment() {


    }
    private EditText dimension2EditText;





    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theory_fragment, container, false);
  return view;
    }


}
