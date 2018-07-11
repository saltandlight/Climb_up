package com.example.lenovo.climb_up;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
  Toolbar toolbar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Button login_b=(Button)findViewById(R.id.login);
        Button join_b=(Button)findViewById(R.id.join);
        toolbar_main =(Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar_main);
        setTitle("Climb up, up, up!");


       login_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login_act.class);
                startActivity(intent);
            }
        });

        join_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Join_act.class);
                startActivity(intent);
            }
        });

    }
}
