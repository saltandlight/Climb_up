package com.example.lenovo.climb_up;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 2018-02-13.
 */

public class MountAdapter extends AppCompatActivity {
    Toolbar toolbar1;
    String myJSON;
    String ID, location, URL;
    private static final String TAG_RESPONSE="response";
    private static final String TAG_MOUNTAIN="mountain";
    private static final String TAG_NUMBER="Mount_num";
    JSONArray comment=null;
    ArrayList<HashMap<String, String>> contents_list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mount_list);

        toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.mount_list);


        setSupportActionBar(toolbar1);
        setTitle("명산");

        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        location=intent.getStringExtra("location");
        //contents_list = new ArrayList<>();
        contents_list = new ArrayList<HashMap<String, String>>();
        listView.setOnItemClickListener(itemClickListener);
        URL="http://hyebin95.cafe24.com/MountList.php"+"?location="+location;
        getData(URL);
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

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            comment = jsonObj.getJSONArray(TAG_RESPONSE);

            for (int i = 0; i < comment.length(); i++) {
                JSONObject c = comment.getJSONObject(i);
                String mountain = c.getString(TAG_MOUNTAIN);
                String Mt_num=c.getString(TAG_NUMBER);
                HashMap<String, String> comment = new HashMap<String, String>();
                comment.put(TAG_MOUNTAIN, mountain);
                comment.put(TAG_NUMBER, Mt_num);
                contents_list.add(comment);

            }

            ListAdapter adapter = new SimpleAdapter(
                    MountAdapter.this, contents_list, R.layout.mount_item,
                    new String[]{TAG_MOUNTAIN},
                    new int[]{R.id.tv_mountain}
            );

            listView.setAdapter(adapter);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id)
        {
            Intent it1 = new Intent(MountAdapter.this, MtCourse.class);
            it1.putExtra("ID",ID);
            it1.putExtra("tag",contents_list.get(pos).get(TAG_NUMBER));
            startActivity(it1);
            finish();

        }
    };



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
              //  Toast.makeText(MountAdapter.this, result, Toast.LENGTH_SHORT).show();
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}