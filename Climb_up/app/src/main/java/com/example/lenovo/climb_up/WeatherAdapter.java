package com.example.lenovo.climb_up;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018-03-13.
 */

public class WeatherAdapter extends BaseAdapter{
    Context context;
    ArrayList<Weather> list_weather;

    TextView tv_hour;
    TextView tv_weather;
    TextView tv_temp;
    ImageView imageView;

    public WeatherAdapter(Context context, ArrayList<Weather> list_weather) {
        this.context = context;
        this.list_weather = list_weather;
    }

    @Override
    public int getCount() {
        return this.list_weather.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_weather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.weather_item, null);
            tv_hour = (TextView)convertView.findViewById(R.id.tv_hour);
            tv_weather  =(TextView)convertView.findViewById(R.id.tv_weather);
            tv_temp=(TextView)convertView.findViewById(R.id.tv_temp);
            imageView = (ImageView)convertView.findViewById(R.id.image);

        }

        tv_hour.setText(list_weather.get(position).getHour());
        tv_weather.setText(list_weather.get(position).getWeather());
        tv_temp.setText(list_weather.get(position).getTemp());
        imageView.setImageResource(list_weather.get(position).getImage());

        return convertView;
    }
}
