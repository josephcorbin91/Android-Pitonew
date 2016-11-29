package com.jco.pitonew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jco on 11/24/2016.
 */
public class MainMenu extends AppCompatActivity {

    private Toolbar actionToolBar;
    private Button calculationButton, theoryButton, constantButton, developerButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        actionToolBar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        setSupportActionBar(actionToolBar);
calculationButton = (Button)findViewById(R.id.mainMenuCalculatorButton);
calculationButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
});
        //actionToolBar.setNavigationIcon(R.drawable.pitonew_logo);
        // actionToolBar.setNavigationContentDescription("This App");
        //  actionToolBar.setLogo(R.drawable.pitonew_logo);
        //  actionToolBar.setLogoDescription("LOGO DESCRIPTION");

        actionToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainMenu.this, "TEST",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        actionToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "Navigation Icon Pressed",Toast.LENGTH_LONG).show();

                actionToolBar.setVisibility(View.GONE);
            }
        });


    }
}
;
