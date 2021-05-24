package com.example.bloodbank;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoDonar extends Fragment {

    public NoDonar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v= inflater.inflate(R.layout.fragment_no_donar, container, false);
        TextView t1=v.findViewById(R.id.t1);
        TextView t2=v.findViewById(R.id.t2);
        TextView t3=v.findViewById(R.id.t3);
        TextView t4=v.findViewById(R.id.t4);
        TextView t5=v.findViewById(R.id.t5);
        TextView t6=v.findViewById(R.id.t6);
        TextView t7=v.findViewById(R.id.t7);
        TextView t8=v.findViewById(R.id.t8);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pankaj12345.000webhostapp.com/fetch1.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject oo = new JSONObject(response.toString());
                            JSONArray jsonArray = oo.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                t1.setText(jsonObject.getString("result1"));
                                t2.setText(jsonObject.getString("result2"));
                                t3.setText(jsonObject.getString("result3"));
                                t4.setText(jsonObject.getString("result4"));
                                t5.setText(jsonObject.getString("result5"));
                                t6.setText(jsonObject.getString("result6"));
                                t7.setText(jsonObject.getString("result7"));
                                t8.setText(jsonObject.getString("result8"));
                            }



                            // .trim to remove the trailing space.
                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(v.getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        requestQueue.add(stringRequest);
        return v;
    }

}
