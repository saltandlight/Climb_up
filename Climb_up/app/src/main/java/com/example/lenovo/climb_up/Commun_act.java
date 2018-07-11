package com.example.lenovo.climb_up;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by lenovo on 2018-01-19.
 */

public class Commun_act extends AppCompatActivity {
    Toolbar toolbar1;
    String ID;
    Button Bt_write;
    Button Bt_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        toolbar1 =(Toolbar) findViewById(R.id.toolbar1);
        Bt_write = (Button)findViewById(R.id.Bt_write);
        Bt_show = (Button)findViewById(R.id.Bt_show);
        setSupportActionBar(toolbar1);
        setTitle("커뮤니티");

        Intent intent=getIntent();
        ID=intent.getStringExtra("ID");

        Bt_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it3= new Intent(Commun_act.this, ContentsReg.class);
                it3.putExtra("ID",ID);
                startActivity(it3);
                finish();
            }
        });

        Bt_show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(Commun_act.this, ContentsList.class);
                it2.putExtra("ID",ID);
                startActivity(it2);
                finish();
            }
        });
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
            startActivity(it1);
            finish();
            return true;
        }

        if(id==R.id.action_info2){
            Intent it1= new Intent(this, Info_act.class);
            startActivity(it1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
