package com.example.lenovo.climb_up;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
 * Created by lenovo on 2018-01-29.
 */

public class ContentsList extends AppCompatActivity {
    Toolbar toolbar1;
    String myJSON;
    String ID;
    String Post_id, Post_regtime;
    Integer flag=0;
    //ArrayList<String> items;
    private static final String TAG_RESPONSE="response";
    private static final String TAG_ID="ID";
    private static final String TAG_TITLE="Post_title";
    private static final String TAG_CONTENT="Post_content";
    private static final String TAG_DATE="Post_regtime";

    JSONArray comment=null;
    ArrayList<HashMap<String, String>> contents_list;
    ListView listView;
    private EditText editSearch;
    Button Bt_search;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents_list);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.my_listView);
        editSearch = (EditText) findViewById(R.id.editSearch);
        Bt_search=(Button)findViewById(R.id.Bt_search);
        setSupportActionBar(toolbar1);
        setTitle("후기 보기");

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        content=intent.getStringExtra("content");
        flag=intent.getIntExtra("flag",0);
        //contents_list = new ArrayList<>();
        contents_list = new ArrayList<HashMap<String, String>>();
        listView.setOnItemClickListener(itemClickListener);

        if(flag==0)
            getData("http://hyebin95.cafe24.com/BoardList.php");
        else if(flag==1)
            getData("http://hyebin95.cafe24.com/Search.php?Post_content="+content);
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
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            comment = jsonObj.getJSONArray(TAG_RESPONSE);

            for (int i = 0; i < comment.length(); i++) {
                JSONObject c = comment.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String title = c.getString(TAG_TITLE);
                String content=c.getString(TAG_CONTENT);
                String regtime = c.getString(TAG_DATE);

                HashMap<String, String> comment = new HashMap<String, String>();

                comment.put(TAG_ID, id);
                comment.put(TAG_TITLE, title);
                comment.put(TAG_CONTENT, content);
                comment.put(TAG_DATE, regtime);

                contents_list.add(comment);

            }

            ListAdapter adapter = new SimpleAdapter(
                    ContentsList.this, contents_list, R.layout.contents_item,
                    new String[]{TAG_TITLE, TAG_ID, TAG_DATE},
                    new int[]{R.id.Post_title, R.id.ID_nick, R.id.writeDate}
            );

            listView.setAdapter(adapter);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {

            Intent it1 = new Intent(ContentsList.this, ContentsShow.class);
            it1.putExtra("ID", ID);
            it1.putExtra("Post_title", contents_list.get(pos).get(TAG_TITLE));
            it1.putExtra("Post_id", contents_list.get(pos).get(TAG_ID));
            it1.putExtra("Post_content", contents_list.get(pos).get(TAG_CONTENT));
            it1.putExtra("Date", contents_list.get(pos).get(TAG_DATE));
            startActivity(it1);
            finish();

            Post_id = contents_list.get(pos).get(TAG_ID);
            Post_regtime = contents_list.get(pos).get(TAG_DATE);
        }
    };


    public void onClick(View view) {
        final String content=editSearch.getText().toString();
        Intent it= new Intent(ContentsList.this, ContentsList.class);
        flag=1;
        it.putExtra("content",content);
        it.putExtra("ID", ID);
        it.putExtra("flag",flag);
        startActivity(it);
        finish();
    }


    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String>{

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
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}