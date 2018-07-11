package com.example.lenovo.climb_up;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

/**
 * Created by lenovo on 2018-01-26.
 */

public class Admin_user extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        TextView idText=(TextView) findViewById(R.id.userID);
        TextView passwordText=(TextView)findViewById(R.id.userPassword);
        TextView nameText = (TextView)findViewById(R.id.userName);
        Button delete_b = (Button) findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("ID");
        String userPassword=intent.getStringExtra("Password");
        String userName=intent.getStringExtra("Name");
        idText.setText(userID);
        passwordText.setText(userPassword);
        nameText.setText(userName);
        if(!userID.equals("admin")){
            delete_b.setVisibility(View.GONE);
        }

        delete_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask().execute();
            }
        });
    }
    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            target="http://hyebin95.cafe24.com/List.php";
        }

        @Override
        protected String doInBackground(Void... voids){
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder= new StringBuilder();
                while((temp=bufferedReader.readLine()) !=null){
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void...values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent= new Intent(Admin_user.this, ManagementActivity.class);
            intent.putExtra("userList",result);
            Admin_user.this.startActivity(intent);
        }
    }
}
