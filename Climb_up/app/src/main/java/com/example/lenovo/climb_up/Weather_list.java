package com.example.lenovo.climb_up;

/**
 * Created by lenovo on 2018-03-13.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sykim1268 on 2018-03-11.
 */

public class Weather_list extends AppCompatActivity {
    ListView listView;
    WeatherAdapter weatherAdapter;
    ArrayList<Weather> list_weather;
    String ID, URL, Mt_name;
    Toolbar toolbar;
    Integer h_count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_list);
        listView = (ListView)findViewById(R.id.weather_list);
        list_weather = new ArrayList<Weather>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        URL=intent.getStringExtra("URL");
        Mt_name=intent.getStringExtra("Mt_name");
        setTitle(Mt_name+" 날씨 정보");

        new DownloadWebpageTask().execute(URL);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String) downloadUrl((String) urls[0]);
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String wfKor = "";
                String hour="";
                String temp="";

                boolean bSet_wfKor=false;
                boolean bSet_hour=false;
                boolean bSet_temp=false;
                int count = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("wfKor"))
                            bSet_wfKor = true;
                        if (tag_name.equals("hour"))
                            bSet_hour = true;
                        if (tag_name.equals("temp"))
                            bSet_temp = true;

                    } else if(eventType == XmlPullParser.TEXT) {
                        if (bSet_hour) {
                            hour = xpp.getText()+"시";
                            h_count=1;
                            bSet_hour = false;
                        }//correct!
                        if (bSet_temp) {
                            temp = xpp.getText()+"℃";
                            bSet_temp = false;
                        }
                        if (bSet_wfKor) {
                            count++;
                            wfKor = xpp.getText();
                            bSet_wfKor = false;
                            if(h_count==1) {
                                if (wfKor.equals("맑음")) {
                                    list_weather.add(
                                            new Weather(R.drawable.sunny, hour, temp, wfKor));
                                } else if (wfKor.equals("구름 조금")){
                                    list_weather.add(
                                            new Weather(R.drawable.cloudy_little, hour,temp, wfKor));
                                }else if (wfKor.equals("구름 많음")) {
                                    list_weather.add(
                                            new Weather(R.drawable.cloudy, hour, temp, wfKor));
                                }else if (wfKor.equals("흐림")) {
                                    list_weather.add(
                                            new Weather(R.drawable.cloudy_little, hour, temp, wfKor));
                                }else if (wfKor.equals("비")) {
                                    list_weather.add(
                                            new Weather(R.drawable.rainy, hour, temp, wfKor));
                                }else if (wfKor.equals("눈/비")) {
                                        list_weather.add(
                                                new Weather(R.drawable.snowy, hour, temp, wfKor));
                                }else if (wfKor.equals("눈")) {
                                        list_weather.add(
                                                new Weather(R.drawable.snowy, hour, temp, wfKor));
                                }
                            }
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
               ;
            }
            weatherAdapter = new WeatherAdapter(Weather_list.this,list_weather);
            listView.setAdapter(weatherAdapter);
        }

        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));

                String line = null;
                String page = "";
                while ((line = bufreader.readLine()) != null) {
                    page += line;
                }

                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_commun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.action_home){
            Intent it1= new Intent(this, Home_act.class);
            it1.putExtra("ID",ID);
            startActivity(it1);
            finish();
            return true;
        }

        if(id==R.id.action_info2){
            Intent it1= new Intent(this, Info_act.class);
            it1.putExtra("ID",ID);
            startActivity(it1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
