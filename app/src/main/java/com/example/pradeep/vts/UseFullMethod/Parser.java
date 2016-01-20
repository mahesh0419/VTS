package com.example.pradeep.vts.UseFullMethod;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRADEEP on 12/11/2015.
 */
public class Parser {

    Context context;

   public  Parser(Context context)
    {
        this.context=context;
    }



    private static final String VEHICLE_ARRAY="Vehiclees";

    private static final String VEHICLE_LIST_ARRAY="Vehiclees";

   //KEY'S of  http://192.168.0.25:8080/gpsdata/sendData

    private static final String VEHICLE_ID="vehicleID";
    private static final String lATITUDE="latitude";
   // private static final String CUSTOMER_ID="CustomerID";
    private static final String LONGITUDE="longitude";
    private static final String SPEED="speed";

    ArrayList<HashMap<String,String>>  vehicleList=new ArrayList<HashMap<String,String>>();
    ArrayList<HashMap<String,String>>  List=new ArrayList<HashMap<String,String>>();


    public ArrayList<HashMap<String,String>> getVehicleObject(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray(VEHICLE_ARRAY);
                int ArrayLength = jsonArray.length();

                for (int i = 0; i < ArrayLength; i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    String lVehicleId = jsonObject2.getString(VEHICLE_ID);
                    //String lLatiTude = jsonObject2.getString(lATITUDE);
                   // String lCustomerId = jsonObject2.getString(CUSTOMER_ID);
                   // String lLongiTude = jsonObject2.getString(LONGITUDE);
                   // String lSpeed = jsonObject2.getString(SPEED);

                    HashMap<String, String> vehicleDetails = new HashMap<String, String>();

                    vehicleDetails.put("lVehicleId", lVehicleId);
                   // vehicleDetails.put("lLatiTude", lLatiTude);
                    //vehicleDetails.put("lCustomerId", lCustomerId);
                    //vehicleDetails.put("lLongiTude", lLongiTude);
                   // vehicleDetails.put("lSpeed", lSpeed);

                    vehicleList.add(vehicleDetails);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else
        {
            Toast.makeText(context,"Some problem in parse Class",Toast.LENGTH_LONG).show();;
        }
        return vehicleList;
    }

    public ArrayList<HashMap<String,String>> getVehicleListObject(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray(VEHICLE_LIST_ARRAY);
                int ArrayLength = jsonArray.length();

                for (int i = 0; i < ArrayLength; i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    String lVehicleId = jsonObject2.getString(VEHICLE_ID);
                    //String lLatiTude = jsonObject2.getString(lATITUDE);
                    // String lCustomerId = jsonObject2.getString(CUSTOMER_ID);
                    // String lLongiTude = jsonObject2.getString(LONGITUDE);
                    // String lSpeed = jsonObject2.getString(SPEED);

                    HashMap<String, String> vehicleDetails = new HashMap<String, String>();

                    vehicleDetails.put("lVehicleId", lVehicleId);
                    // vehicleDetails.put("lLatiTude", lLatiTude);
                    //vehicleDetails.put("lCustomerId", lCustomerId);
                    //vehicleDetails.put("lLongiTude", lLongiTude);
                    // vehicleDetails.put("lSpeed", lSpeed);

                    List.add(vehicleDetails);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else
        {
            Toast.makeText(context,"Some problem in parse Class",Toast.LENGTH_LONG).show();;
        }
        return vehicleList;
    }
}