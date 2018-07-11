package com.example.lenovo.climb_up;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018-01-23.
 */

public class RegisterRequest extends StringRequest{

    final static private String URL="http://hyebin95.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String ID, String Password, String Name, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters= new HashMap<>();
        parameters.put("ID",ID);
        parameters.put("Password",Password);
        parameters.put("Name", Name);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;

    }
}
