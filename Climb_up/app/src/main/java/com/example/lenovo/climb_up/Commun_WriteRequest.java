package com.example.lenovo.climb_up;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018-01-23.
 */

    public class Commun_WriteRequest extends StringRequest{

        final static private String URL="http://hyebin95.cafe24.com/WriteBoard.php";
        private Map<String, String> parameters;

        public Commun_WriteRequest(String Post_title, String Post_content, String ID, Response.Listener<String> listener){
            super(Method.POST, URL, listener, null);
            parameters= new HashMap<>();
            parameters.put("Post_title",Post_title);
            parameters.put("Post_content",Post_content);
            parameters.put("ID",ID);
        }

    
    @Override
    public Map<String, String> getParams() {
        return parameters;


    }
}
