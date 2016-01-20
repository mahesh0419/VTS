package com.example.pradeep.vts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pradeep.vts.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRADEEP on 12/16/2015.
 */
public class SpinnerAdapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String,String>> list;
    LayoutInflater inflater;
   public  SpinnerAdapter(Context context,ArrayList<HashMap<String,String>> list)
    {
        this.context=context;
        this.list=list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView=inflater.inflate(R.layout.row_for_spinner,parent,false);
            holder=new ViewHolder();
            holder.vehicleNumber= (TextView) convertView.findViewById(R.id.spinner_text);
            HashMap<String,String> dataObject=list.get(position);
            String vehiclNum=dataObject.get("lVehicleId");
            holder.vehicleNumber.setText(vehiclNum);

        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    static class ViewHolder
    {
        TextView vehicleNumber;
    }
}
