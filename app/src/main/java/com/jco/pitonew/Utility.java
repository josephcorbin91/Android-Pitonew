package com.jco.pitonew;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by jco on 11/25/2016.
 */
public class Utility {



    public static void clearAllFields(ViewGroup group){
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearAllFields((ViewGroup)view);
        }
    }


    public static boolean containsText(EditText editText){
        return !editText.getText().toString().equals("");
    }

}
