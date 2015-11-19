package com.jmrbaram.dam_calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSP = (Button)findViewById(R.id.bt_sp);
        Button btTT = (Button)findViewById(R.id.bt_timetable);
        Button btCalc = (Button)findViewById(R.id.bt_calc);

        btSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityMain.this, ActivitySP.class);
                startActivity(i);
            }
        });

        btTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityMain.this, ActivityTimetable.class);
                startActivity(i);
            }
        });

        btCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityMain.this, ActivityCalc.class);
                startActivity(i);
            }
        });

    }
}
