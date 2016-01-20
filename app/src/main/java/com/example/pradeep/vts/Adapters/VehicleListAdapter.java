package com.example.pradeep.vts.Adapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pradeep.vts.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRADEEP on 12/11/2015.
 */
public class VehicleListAdapter extends BaseAdapter {

    ArrayList<HashMap<String,String>> vehicleData;
    Context context;
    LayoutInflater inflater;
    public VehicleListAdapter(Context context,ArrayList<HashMap<String,String>> vehicleData)
    {
        this.context=context;
        this.vehicleData=vehicleData;
    }

    @Override
    public int getCount() {
        return vehicleData.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null)
        {
            inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.for_row_vehicle_list,parent,false);
            holder=new ViewHolder();
            holder.vehicleId= (TextView) convertView.findViewById(R.id.vehicle_id);
            // holder.customerId= (TextView) convertView.findViewById(R.id.customer_id);
            HashMap<String,String> vehicleObject = vehicleData.get(position);
            String vehicleSId=vehicleObject.get("lVehicleId");
            //String customerId=vehicleObject.get("lCustomerId");
            holder.vehicleId.setText(vehicleSId);
            // holder.customerId.setText(customerId);
            /*final   int temp=position;
            if (temp % 2 == 0) {
                convertView.setBackgroundResource(R.color.list_bg_1);
            } else {
                convertView.setBackgroundResource(R.color.list_bg_2);
            }*/
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position % 2 == 1) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.list_item_2));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.list_item_1));
        }


        return convertView;
    }

    static class ViewHolder
    {
        TextView vehicleId;
        //TextView customerId;
    }
}
