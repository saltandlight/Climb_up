package com.example.lenovo.climb_up;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by lenovo on 2018-01-15.
 */

public class Home_act extends AppCompatActivity {
    String ID;
    Button Bt_info, Bt_commun, Bt_weather, Bt_gujo;
    Toolbar toolbar;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Intent intent= getIntent();
        ID=intent.getStringExtra("ID");
        Password=intent.getStringExtra("Password");
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");
        Bt_info = (Button) findViewById(R.id.Bt_info);
        Bt_commun = (Button) this.findViewById(R.id.Bt_commun);
        Bt_weather = (Button) findViewById(R.id.Bt_weather);
        Bt_gujo=(Button)findViewById(R.id.Bt_gujo);

        Toast.makeText(this,"축복합니다!",Toast.LENGTH_SHORT).show();

        Bt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Home_act.this, Info_act.class);
                it.putExtra("ID",ID);
                startActivity(it);
                finish();
            }
        });

        Bt_commun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Home_act.this, Commun_act.class);
                it.putExtra("ID",ID);
                startActivity(it);
                finish();
            }
        });


        Bt_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.kma.go.kr/m/index.jsp")));
            }
        });

        Bt_gujo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
                startActivity(myIntent);

          }
        });


   }

}
