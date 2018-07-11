package com.example.lenovo.climb_up;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import java.util.zip.Inflater;

/**
 * Created by lenovo on 2018-02-16.
 */

public class MtCourse extends AppCompatActivity {
   Toolbar toolbar;
   LinearLayout layout;
   String ID, tag, URL_fin, URL_key, URL, URL_weather, Mt_name;
   TextView tv_rg, tv_data, tv_mount, tv_tour;
   Button Bt_weather2;
   String mt, course, tour;
   ImageView img;
   Integer gridX, gridY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mount_course);

        Intent it=getIntent();
        ID=it.getStringExtra("ID");
        tag=it.getStringExtra("tag");
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("등산로");

        tv_mount=(TextView)findViewById(R.id.mountain1);
        tv_rg=(TextView)findViewById(R.id.region);
        tv_data=(TextView)findViewById(R.id.data);
        tv_tour=(TextView)findViewById(R.id.data2);
        img=(ImageView)findViewById(R.id.img);
        Bt_weather2=(Button)findViewById(R.id.Bt_weather2);
        URL="http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI?serviceKey=";
       // Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
        URL_key="5Zy2U9EhOVw5NQL97N7joM9Zpgc5lfAhXcSvz9q9SMF900vor5AFixpbMXK%2F1aASTwmsRGxyHDfO3DCYL3ZCZw%3D%3D";
        URL_fin=URL+URL_key;
        switch (tag){
            case "1": URL_fin+="&searchMtNm=%EA%B0%90%EC%95%85%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gamak);
                      gridY=135;
                      gridX=59;
                      Mt_name="감악산";
                      break;
                      //감악산
            case "2": URL_fin+="&searchMtNm=%EA%B4%80%EC%95%85%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gwanak);
                      gridY=124;
                      gridX=59;
                      Mt_name="관악산";
                      break;
                      //관악산
            case "3": URL_fin+="&searchMtNm=%EB%8F%84%EB%B4%89%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.dobong);
                      gridY=130;
                      gridX=60;
                      Mt_name="도봉산(자운봉)";
                      break;
                      //도봉산(자운봉)
            case "4": URL_fin+="&searchMtNm=%EB%AA%85%EC%84%B1%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.myungsung2);
                      gridY=139;
                      gridX=66;
                      Mt_name="명성산";
                      break;
                      //명성산
            case "5": URL_fin+="&searchMtNm=%EB%AA%85%EC%A7%80%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.myungji);
                      gridY=135;
                      gridX=67;
                      Mt_name="명지산";
                      break;
                      //명지산
            case "6": URL_fin+="&searchMtNm=%EB%B6%81%ED%95%9C%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.bookhan);
                      gridY=129;
                      gridX=60;
                      Mt_name="북한산";
                      break;
                      //북한산
            case "7": URL_fin+="&searchMtNm=%EA%B0%80%EB%A6%AC%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.garisan);
                      gridY=134;
                      gridX=76;
                      Mt_name="가리산";
                      break;
                      //가리산
            case "8": URL_fin+="&searchMtNm=%EA%B0%80%EB%A6%AC%EC%99%95%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gariwangsan);
                      gridY=125;
                      gridX=87;
                      Mt_name="가리왕산";
                      break;
                      //가리왕산
            case "9": URL_fin+="&searchMtNm=%EA%B3%84%EB%B0%A9%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gyebang);
                      gridY=131;
                      gridX=85;
                      Mt_name="계방산";
                      break;
                      //계방산
            case "10":URL_fin+="&searchMtNm=%EA%B3%B5%EC%9E%91%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gongjack);
                      gridY=130;
                      gridX=77;
                      Mt_name="공작산";
                      break;
                      //공작산
            case "11":URL_fin+="&searchMtNm=%EB%8D%95%ED%95%AD%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.dukhang);
                      gridY=122;
                      gridX=95;
                      Mt_name="덕항산";
                      break;
                      //덕항산
            case "12":URL_fin+="&searchMtNm=%EB%91%90%ED%83%80%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.duta);
                      gridY=121;
                      gridX=96;
                      Mt_name="두타산";
                      break;
                      //두타산
            case "13":URL_fin+="&searchMtNm=%EA%B5%AC%EB%B3%91%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gubyung);
                      gridY=103;
                      gridX=75;
                      Mt_name="구병산";
                      break;
                      //구병산
            case "14":URL_fin+="&searchMtNm=%EA%B8%88%EC%88%98%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gmsusan);
                      gridY=115;
                      gridX=82;
                      Mt_name="금수산";
                      break;
                      //금수산
            case "15":URL_fin+="&searchMtNm=%EB%8C%80%EB%91%94%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.daedun);
                      gridY=95;
                      gridX=66;
                      Mt_name="대둔산";
                      break;
                      //대둔산
            case "16":URL_fin+="&searchMtNm=%EB%8C%80%EC%95%BC%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.daeyaa);
                      gridY=108;
                      gridX=77;
                      Mt_name="대야산";
                      break;
                      //대야산
            case "17":URL_fin+="&searchMtNm=%EB%8D%95%EC%88%AD%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.duksung);
                      gridY=107;
                      gridX=54;
                      Mt_name="덕숭산";
                      break;
                      //덕숭산
            case "18":URL_fin+="&searchMtNm=%EA%B0%95%EC%B2%9C%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.gangcheon);
                      gridY=80;
                      gridX=61;
                      Mt_name="강천산";
                      break;
                     //강천산
            case "19":URL_fin+="&searchMtNm=%EB%A7%88%EC%9D%B4%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.mai);
                      gridY=88;
                      gridX=68;
                      Mt_name="마이산";
                      break;
                      //마이산
            case "20":URL_fin+="&searchMtNm=%EB%82%B4%EC%9E%A5%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.naejang);
                      gridY=81;
                      gridX=59;
                      Mt_name="내장산";
                      break;
                      //내장산
            case "21":URL_fin+="&searchMtNm=%EB%8C%80%EB%91%94%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.daedun);
                      gridY=95;
                      gridX=66;
                      Mt_name="대둔산";
                      break;
                      //대둔산
            case "22":URL_fin+="&searchMtNm=%EB%8D%95%EC%9C%A0%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.deokyu);
                      gridY=90;
                      gridX=74;
                      Mt_name="덕유산";
                      break;
                      //덕유산
            case "23":URL_fin+="&searchMtNm=%EB%91%90%EB%A5%9C%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.duryun);
                      gridY=59;
                      gridX=54;
                      Mt_name="두륜산";
                      break;
                      //두륜산
            case "24":URL_fin+="&searchMtNm=%EC%86%8C%EB%B0%B1%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.sobaek);
                      gridY=114;
                      gridX=85;
                      Mt_name="소백산";
                      break;
                      //소백산
            case "25":URL_fin+="&searchMtNm=%EC%A7%80%EB%A6%AC%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.jiri);
                      gridY=68;
                      gridX=82;
                      Mt_name="지리산";
                      break;
                      //지리산...그러나 지리산(통영)도 있으므로 유의할 것
            case "26":URL_fin+="&searchMtNm=%EA%B8%88%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.geum);
                      gridY=66;
                      gridX=78;
                      Mt_name="금산";
                      break;
                      //금산
            case "27":URL_fin+="&searchMtNm=%EA%B8%88%EC%98%A4%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.geumoh);
                      gridY=95;
                      gridX=83;
                      Mt_name="금오산";
                      break;
                      //금오산
            case "28":URL_fin+="&searchMtNm=%EA%B8%88%EC%A0%95%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.geumjung);
                      gridY=78;
                      gridX=97;
                      Mt_name="금정산";
                      break;
                      //금정산
            case "29":URL_fin+="&searchMtNm=%EB%8C%80%EC%95%BC%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.daeya);
                      gridY=108;
                      gridX=77;
                      Mt_name="대야산";
                      break;
                      //대야산
            case "30":URL_fin+="&searchMtNm=%ED%95%9C%EB%9D%BC%EC%82%B0&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                      img.setImageResource(R.drawable.halla);
                      gridY= 35;
                      gridX= 53;
                      Mt_name="한라산";
                      break;
                     //한라산
        }

        URL_weather="http://www.kma.go.kr/wid/queryDFS.jsp?gridx="+gridX+"&gridy="+gridY;
        Bt_weather2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //새로운 액티비티로 넘어가도록
                Intent it = new Intent(MtCourse.this, Weather_list.class);
                it.putExtra("ID",ID);
                it.putExtra("URL",URL_weather);
                it.putExtra("Mt_name",Mt_name);
                startActivity(it);
                finish();
            }
        });
        new DownloadWebpageTask().execute(URL_fin);

    }

    public String stripHtml(String html) {
        html = html.replaceAll("<(.*?)\\>","\n");
        html = html.replaceAll("<(.*?)\\\n"," ");
        html = html.replaceFirst("(.*?)\\>", "");
        html = html.replaceAll("&nbsp;"," ");
        html = html.replaceAll("&amp;"," ");
        html = html.replaceAll("<(.*?)\\>"," ");
        html = html.replaceAll("<(.*?)\\\n"," ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&nbsp;"," ");
        html = html.replaceAll("&amp;"," ");
        return html;
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


                String mntnm = ""; //산 이름
                String etccourse = ""; //등산로
                String tourisminf = ""; //숙식 및 기타정보


                boolean bSet_mntnm = false;
                boolean bSet_etccourse = false;
                boolean bSet_tourisminf = false;

                int count = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("mntnm"))
                            bSet_mntnm = true;
                        if (tag_name.equals("etccourse"))
                            bSet_etccourse = true;
                        if (tag_name.equals("tourisminf"))
                            bSet_tourisminf = true;

                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet_mntnm) {
                            mntnm = xpp.getText().toString();
                           tv_mount.setText(mntnm);
                            bSet_mntnm = false;
                        }

                        if (bSet_etccourse) {
                            count++;
                            etccourse = xpp.getText().toString();
                            course=stripHtml(etccourse);
                            tv_data.setText("등산로:\n" + course);
                            bSet_etccourse = false;
                        }
                        if (bSet_tourisminf) {
                            tourisminf = xpp.getText().toString();
                            tour=stripHtml(tourisminf);
                            tv_tour.setText("\n"+tour);
                            bSet_tourisminf = false;
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                tv_data.setText(e.getMessage());
                tv_mount.setText(e.getMessage());
                tv_rg.setText(e.getMessage());
                tv_tour.setText(e.getMessage());
            }
        }
        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                java.net.URL url = new URL(myurl);
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
        getMenuInflater().inflate(R.menu.menu_info, menu);
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

        if(id==R.id.action_community){
            Intent it1= new Intent(this, Commun_act.class);
            it1.putExtra("ID",ID);
            startActivity(it1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
