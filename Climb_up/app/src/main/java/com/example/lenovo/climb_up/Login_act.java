package com.example.lenovo.climb_up;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_act extends AppCompatActivity {

    Toolbar toolbar5;
    String absolute_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText idText1 = (EditText) findViewById(R.id.editText);
        final EditText Password1 = (EditText) findViewById(R.id.editText2);
        final Button Bt_login = (Button) findViewById(R.id.loginButton);
        toolbar5 = (Toolbar) findViewById(R.id.toolbar5);

        setSupportActionBar(toolbar5);
        setTitle("Login");
        absolute_id = "admin";

        Bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ID=idText1.getText().toString();
                final String Password=Password1.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse= new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                String ID2=jsonResponse.getString("ID");
                                String Password2=jsonResponse.getString("Password");
                                String Name2=jsonResponse.getString("Name");
                                if(ID.equals(absolute_id)) {
                                    Intent it = new Intent(Login_act.this,Admin_user.class);
                                    it.putExtra("ID",ID2);
                                    it.putExtra("Password", Password2);
                                    it.putExtra("Name",Name2);
                                    Toast.makeText(getApplicationContext(),"관리자입니다!",Toast.LENGTH_SHORT).show();
                                    Login_act.this.startActivity(it);

                                }
                                else {
                                    Intent intent = new Intent(Login_act.this, Home_act.class);
                                    intent.putExtra("ID", ID2);
                                    intent.putExtra("Password", Password2);
                                    Toast.makeText(getApplicationContext(),"일반 회원입니다!",Toast.LENGTH_SHORT).show();
                                    Login_act.this.startActivity(intent);
                                }
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login_act.this);
                                builder.setMessage("로그인에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(ID, Password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login_act.this);
                queue.add(loginRequest);
            }
        });
    }
}

