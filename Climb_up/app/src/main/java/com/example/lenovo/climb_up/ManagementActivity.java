package com.example.lenovo.climb_up;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import  android.support.v7.widget.Toolbar;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sykim1268 on 2018-01-18.
 */

public class ManagementActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_user);
        Intent intent = getIntent();

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Admin_mode");


        listView = (ListView) findViewById(R.id.userlist);
        userList = new ArrayList<User>();
        //  userList.add(new User("111", "111", "111"));
        //  userList.add(new User("Lee","Lee","Lee"));

        adapter = new UserListAdapter(getApplicationContext(), userList, this);
        listView.setAdapter(adapter);
        //Db에서 데이터를 가져오는 부분 근데 이거 JSON 이다....JSON 안되니깐 걍 파싱해서 위에
        //주석처리해논걸루 해야 되나....

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String userID, userPassword, userName;
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("ID");
                userPassword = object.getString("Password");
                userName = object.getString("Name");
                User user = new User(userID, userPassword, userName);
                if(!userID.equals("admin")) {
                    userList.add(user);
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}