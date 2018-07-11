package com.example.lenovo.climb_up;

import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 2018-01-16.
 */

public class Info_act extends AppCompatActivity {
    Toolbar toolbar;
    String ID, location;
    ListView listView;
    LocationList myListAdapter;
    ArrayList<Locationitem> list_itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_zone);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("지역 선택");

        listView=(ListView)findViewById(R.id.zone_list);
        list_itemArrayList = new ArrayList<Locationitem>();

        list_itemArrayList.add(new Locationitem("경기도"));
        list_itemArrayList.add(new Locationitem("강원도"));
        list_itemArrayList.add(new Locationitem("충청도"));
        list_itemArrayList.add(new Locationitem("전라도"));
        list_itemArrayList.add(new Locationitem("경상도"));
        list_itemArrayList.add(new Locationitem("제주도"));


        myListAdapter = new LocationList(Info_act.this,list_itemArrayList);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(itemClickListener);
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

//이거 수정 많이 해줘야 함
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
    public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {

        location = list_itemArrayList.get(pos).getLocation();
        Intent it1 = new Intent(Info_act.this, MountAdapter.class);
        it1.putExtra("ID", ID);
        it1.putExtra("location", location);
        Info_act.this.startActivity(it1);
       }
    };
}
