package com.example.bloodbank;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
public class Status extends Fragment {
    String name,Age,City,Phone,Password,Gender,donar,bloodgroup;

    public Status() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        String email=getArguments().getString("email");

        View v;
        v= inflater.inflate(R.layout.fragment_status, container, false);
        TextView t1=v.findViewById(R.id.t1);
        Button update=v.findViewById(R.id.update);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pankaj12345.000webhostapp.com/fetch3.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject oo = new JSONObject(response.toString());
                            JSONArray jsonArray = oo.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                t1.setText("Name: "+jsonObject.getString("Name")+"\n"
                                +"Age: "+jsonObject.getString("Age")+"\n"
                                +"City: "+jsonObject.getString("City")+"\n"
                                +"Contact: "+jsonObject.getString("Phone")+"\n"
                                +"Gender: "+jsonObject.getString("Gender")+"\n"
                                +"Donar: "+jsonObject.getString("Donar")+"\n"
                                +"Bloodgroup: "+jsonObject.getString("Bloodgroup"));
                                name=jsonObject.getString("Name");
                                Age=jsonObject.getString("Age");
                                City=jsonObject.getString("City");
                                Phone=jsonObject.getString("Phone");
                                Password=jsonObject.getString("Password");
                                Gender=jsonObject.getString("Gender");
                                donar=jsonObject.getString("Donar");
                                bloodgroup=jsonObject.getString("Bloodgroup");
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
                params.put("email", email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        requestQueue.add(stringRequest);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(v.getContext(),Update.class);
                i.putExtra("email",email);
                i.putExtra("name",name);
                i.putExtra("age",Age);
                i.putExtra("city",City);
                i.putExtra("phone",Phone);
                i.putExtra("password",Password);
                i.putExtra("gender",Gender);
                i.putExtra("donar",donar);
                i.putExtra("bloodgroup",bloodgroup);
                startActivity(i);
            }
        });
        return v;
    }

}
