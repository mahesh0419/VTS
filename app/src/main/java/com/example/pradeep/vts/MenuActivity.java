package com.example.pradeep.vts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;

/**
 * Created by MAHESH on 12/29/2015.
 */
public class MenuActivity extends AppCompatActivity{
Button my_vehicles,share,track_vehicle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackmenu);
        my_vehicles=(Button)findViewById(R.id.my_vehicles);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        //TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().show();



        share=(Button)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Vehicle Tracking System");
                share.putExtra(Intent.EXTRA_TEXT, "http://www.fi-5.com/");
                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });

        my_vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,VehicleTrackActivity.class);
                startActivity(i);
            }
        });
    }
}
