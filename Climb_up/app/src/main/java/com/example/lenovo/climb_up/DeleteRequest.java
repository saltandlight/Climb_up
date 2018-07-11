package com.example.lenovo.climb_up;

/**
 * Created by lenovo on 2018-01-26.
 */
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sykim1268 on 2018-01-25.
 */

public class DeleteRequest extends StringRequest {

    final static private String  URL="http://hyebin95.cafe24.com/Delete.php";
    private Map<String, String> parameters;

    public DeleteRequest(String ID, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("ID", ID);
    }

    public Map <String , String> getParams()
    {
        return parameters;
    }

}