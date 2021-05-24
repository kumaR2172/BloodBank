package com.example.bloodbank;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Searchdonar extends Fragment {
    String bloodgroup;
    String mob;

    public Searchdonar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_searchdonar, container, false);
        Spinner Bloodgroup=v.findViewById(R.id.Bloodgroup);
        String[] bloodgroupstr={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_dropdown_item,bloodgroupstr);
        Bloodgroup.setAdapter(adapter);
        ArrayList<String> a1=new ArrayList<String>();
        ArrayList<String> a2=new ArrayList<String>();
        Bloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodgroup=bloodgroupstr[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       final Button search=v.findViewById(R.id.Search);
        ListView l1=v.findViewById(R.id.list);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pankaj12345.000webhostapp.com/fetch.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject oo = new JSONObject(response.toString());
                                    JSONArray jsonArray = oo.getJSONArray("result");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        a1.add("Name:"+jsonObject.getString("Name")+"\nContact:"
                                                +jsonObject.getString("Phone")+"\nCity:"+jsonObject.getString("City"));
                                        a2.add(jsonObject.getString("Phone"));
                                    }
                                    Myadapter adapter1=new Myadapter(v.getContext(),a1,R.drawable.mob);
                                    l1.setAdapter(adapter1);
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
                        params.put("Bloodgroup", bloodgroup);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
                requestQueue.add(stringRequest);
                search.setEnabled(false);
            }
        });
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i1=new Intent(Intent.ACTION_CALL);
                mob=a2.get(i).toString();
                if(mob.trim().isEmpty()){
                    i1.setData(Uri.parse("tel:9812604622"));
                }
                else
                {
                    i1.setData(Uri.parse("tel:"+mob));
                }
                if(ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(v.getContext(), "Please grant the permission to call", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    startActivity(i1);
                }
            }
        });
        return v;
    }

}
