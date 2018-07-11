package com.example.lenovo.climb_up;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 2018-01-12.
 */

public class Join_act extends AppCompatActivity {

    Button Bt_join;
    Toolbar toolbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        final  EditText idText2 = (EditText) findViewById(R.id.idText2);
        final  EditText Password2 = (EditText) findViewById(R.id.Password2);
        final  EditText Name_t = (EditText) findViewById(R.id.NameText);

        Bt_join = (Button) findViewById(R.id.idButton);
        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);

        setSupportActionBar(toolbar4);
        setTitle("Join");

        Bt_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String ID = idText2.getText().toString();
                String Password = Password2.getText().toString();
                String Name = Name_t.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Join_act.this);
                                builder.setMessage("회원가입에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                                finish();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Join_act.this);
                                builder.setMessage("회원가입에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(ID, Password, Name, responseListener);
                RequestQueue queue= Volley.newRequestQueue(Join_act.this);
                queue.add(registerRequest);
            }
        });
    }
}
