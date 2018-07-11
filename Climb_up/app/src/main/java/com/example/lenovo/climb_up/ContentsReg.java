package com.example.lenovo.climb_up;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class ContentsReg extends AppCompatActivity {

    Button Bt_register;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents_reg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Write");

        final EditText Post_title = (EditText)findViewById(R.id.title);
        final EditText Post_content=(EditText)findViewById(R.id.content);
        final TextView tv_id=(TextView)findViewById(R.id.id2);

        Bt_register = (Button)findViewById(R.id.Bt_register);
        Intent it= getIntent();
        ID=it.getStringExtra("ID");
        tv_id.setText("");
        tv_id.setText(ID);


        Bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = Post_title.getText().toString();
                String Content = Post_content.getText().toString();
                String ID2=tv_id.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String ID3=jsonResponse.getString("ID");
                                Intent it2=new Intent(ContentsReg.this, ContentsList.class);
                                it2.putExtra("ID",ID3);
                                Toast.makeText(getApplicationContext(),"글쓰기에 성공했습니다!",Toast.LENGTH_SHORT).show();
                                ContentsReg.this.startActivity(it2);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(ContentsReg.this);
                                builder.setMessage("글쓰기에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Commun_WriteRequest writeRequest = new Commun_WriteRequest(Title, Content, ID2, responseListener);
                RequestQueue queue= Volley.newRequestQueue(ContentsReg.this);
                queue.add(writeRequest);
               }

            });
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

