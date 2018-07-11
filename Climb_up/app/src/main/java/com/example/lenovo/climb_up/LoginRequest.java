package com.example.lenovo.climb_up;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018-01-23.
 */

public class LoginRequest extends StringRequest{

    final static private String URL="http://hyebin95.cafe24.com/Login.php";
    private Map<String, String> parameters;

    public LoginRequest(String ID, String Password, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters= new HashMap<>();
        parameters.put("ID",ID);
        parameters.put("Password",Password);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;

    }
}
