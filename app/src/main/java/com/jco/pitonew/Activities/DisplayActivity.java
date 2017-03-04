package com.jco.pitonew.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.Switch;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jco.pitonew.Fragments.InputFragment;
import com.jco.pitonew.Fragments.PagerFragment;
import com.jco.pitonew.Fragments.ResultFragment;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;

/**
 * Created by jco on 12/3/2016.
 */
public class DisplayActivity extends AppCompatActivity{
        private MaterialSpinner spinner;
        private Toolbar actionToolBar;
        private AppCompatButton clearButton,calculateButton;
        private String currentCalculations,currentUnits;
        private MaterialMenuDrawable materialMenu;
        private Switch unitSwitch;
        private PagerFragment pagerFragment;
        private FragmentManager fragmentManager;
    private InputFragment inputFragment;
    private ResultFragment resultFragment;
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


        inputFragment = new InputFragment();
        resultFragment= new ResultFragment();
        pagerFragment = new PagerFragment();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }


            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.setCustomAnimations(R.anim.fadein,R.anim.fadeout);
            transaction.add(R.id.fragment_container, inputFragment);
            transaction.commit();
            Log.i("FRAG TAG", "Fragment Commited");


        }


        unitSwitch = (Switch) findViewById(R.id.unitSwitch);
      /*  unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    pagerFragment.getViewPagerAdapter().getItem(pagerFragment.getViewPagerAdapter().getCurrentPosition());
                            changeUnits("Imperial");
                    currentUnits = "Imperial";
                }
                else
                {
                    pagerFragment.changeUnits("Metric");
                    pagerFragment.clear();
                    currentUnits = "Metric";
                }
                }
            });

*/
        //Action Toolbar Code
        actionToolBar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        setSupportActionBar(actionToolBar);


        clearButton = (AppCompatButton)findViewById(R.id.toolbarClearButton);
        calculateButton = (AppCompatButton)findViewById(R.id.tooldbarCalculateButton);
        /*clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                        pagerFragment.clear();
                }

        });
*/
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = DisplayActivity.this.fragmentManager.beginTransaction();

                Gas gas = new Gas(inputFragment.getResults(),inputFragment.getDynamicResults(),false /* unitSwitch.isChecked()*/, false/*circularOrRectangularSwitch.isChecked()*/);
                transaction.setCustomAnimations(R.anim.fadein,R.anim.fadeout);
                transaction.replace(R.id.fragment_container, resultFragment);
                transaction.commit();
                Double[] results = gas.getResults();
                for(int i=0; i< results.length;i++)
                    System.out.println("RESULTS " + results[i]);
                //resultFragment.setResults(gas.getResults());


            }


        });






    }









}






