package com.example.lenovo.climb_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 2018-02-12.
 */

public class LocationList extends BaseAdapter{
    android.content.Context context;
    ArrayList<Locationitem> list_itemArrayList;
    TextView location;
    public LocationList(Context context, ArrayList<Locationitem> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }


    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.zone_item,null);
            location=(TextView)convertView.findViewById(R.id.location);
            location.setText(list_itemArrayList.get(position).getLocation());

        }
        return convertView;
    }
}
