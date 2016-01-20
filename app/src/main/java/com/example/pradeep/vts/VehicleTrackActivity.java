package com.example.pradeep.vts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pradeep.vts.Adapters.VehicleTrackAdapter;
import com.example.pradeep.vts.UseFullMethod.Const;
import com.example.pradeep.vts.UseFullMethod.Parser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleTrackActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private String TAG = VehicleTrackActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req";
    private ListView vehicleListView;
    VehicleTrackAdapter adapter;
    ArrayList<HashMap<String,String>> listResponse;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_track);


        toolbar= (Toolbar) findViewById(R.id.toolbar);
        //TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().show();

        vehicleListView= (ListView) findViewById(R.id.vehicle_list);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        makeJsonObjReq();


        vehicleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String,String> rowObject=listResponse.get(position);
                //String latitude=rowObject.get("lLatiTude");
                //String longitude=rowObject.get("lLongiTude");
                String lVehicleId=rowObject.get("lVehicleId");
               // String lCustomerId=rowObject.get("lCustomerId");

                int flag=Integer.parseInt(lVehicleId);

                if(flag==1)
                {
                    StringBuilder ListTitle=new StringBuilder("http://117.218.229.177:8084/vtsWeb/GpsService/");
                    ListTitle.append(lVehicleId);
                    ListTitle.append("/");

                    String subTitleUrl=ListTitle.toString();


                    Intent title=new Intent(VehicleTrackActivity.this,VehicleListActivity.class);
                    title.putExtra("subTitleUrl", subTitleUrl);
                    startActivity(title);



                }
                else
                {



                    Toast.makeText(VehicleTrackActivity.this, "No Data", Toast.LENGTH_LONG).show();
                }
            }


        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vehicle_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    private void makeJsonObjReq() {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, Const.URL_JSON_VEHICLE_TRACK, null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG,response.toString());
                Parser parseData=new Parser(VehicleTrackActivity.this);
                listResponse= parseData.getVehicleObject(response);
                adapter=new VehicleTrackAdapter(VehicleTrackActivity.this,listResponse);
                vehicleListView.setAdapter(adapter);

                hideProgressDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }
}
