package com.example.lenovo.climb_up;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by lenovo on 2018-02-08.
 */

public class ContentsShow extends AppCompatActivity {
    String ID;
    String Post_id, Post_title, Post_content;
    String Date, readcount, myJSON;
    Integer rcount;
    String URL, s, mJsonString;;
    JSONArray comment=null;
    private static final String TAG_RESPONSE="response";
    private static final String TAG_READCOUNT="Post_readcount";
    // public GettingPHP gPHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents_show);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Show");

        Intent it = getIntent();
        ID = it.getStringExtra("ID");
        Post_id = it.getStringExtra("Post_id");
        Post_title = it.getStringExtra("Post_title");
        Post_content = it.getStringExtra("Post_content");
        Date = it.getStringExtra("Date");

        //URL = "http://hyebin95.cafe24.com/Readcount.php?ID="+Post_id+"&Post_regtime="+Date+"&Post_readcount="+readcount;
        //Toast.makeText(this, ""+URL, Toast.LENGTH_SHORT).show();

        TextView tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(Post_title);
        TextView tv_content = (TextView) findViewById(R.id.content);
        tv_content.setText(Post_content);
        TextView tv_id = (TextView) findViewById(R.id.ID);
        tv_id.setText(Post_id);
        TextView Date2 = (TextView) findViewById(R.id.date);
        Date2.setText(Date);

    }

    protected void show() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            comment = jsonObj.getJSONArray("Post_readcount");
            for (int i = 0; i < comment.length(); i++) {
                JSONObject c = comment.getJSONObject(i);
                String readcount2 = c.getString(TAG_READCOUNT);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String...params){

                String uri=params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json=bufferedReader.readLine())!=null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON = result;
                Toast.makeText(ContentsShow.this, ""+result, Toast.LENGTH_SHORT).show();
               // show();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list, menu);
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

        if(id==R.id.action_show){
            Intent it1= new Intent(this, ContentsList.class);
            it1.putExtra("ID",ID);
            startActivity(it1);
            finish();

        }

        if(id==R.id.action_write){
            Intent it1= new Intent(this, ContentsReg.class);
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