package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText Name, Age, City, Phone, Email, Password;
    CheckBox Male, Female;
    CheckBox Donar;
    Spinner Bloodgroup;
    Button Register;


    String name, age, city, phone, email, password, gender, donar, bloodgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = findViewById(R.id.Name);
        Age = findViewById(R.id.Age);
        City = findViewById(R.id.City);
        Phone = findViewById(R.id.Phone);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Male = findViewById(R.id.Male);
        Female = findViewById(R.id.Female);
        Donar = findViewById(R.id.Donar);
        Bloodgroup = findViewById(R.id.Bloodgroup);
        Register = findViewById(R.id.Register);
        final String[] bloodgroupstr = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bloodgroupstr);
        Bloodgroup.setAdapter(adapter);
        Bloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodgroup = bloodgroupstr[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = Name.getText().toString();
                age = Age.getText().toString();
                city = City.getText().toString();
                phone = Phone.getText().toString();
                email = Email.getText().toString();
                password = Password.getText().toString();
                if (Male.isChecked() && !Female.isChecked())
                    gender = "Male";
                if (Female.isChecked() && !Male.isChecked())
                    gender = "Female";
                if (Donar.isChecked())
                    donar = "Yes";
                else donar = "No";
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pankaj12345.000webhostapp.com/get_data.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("Successfully Registered")) {
                                    Toast.makeText(Register.this, "successfully registered", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Register.this, Navi.class);
                                    i.putExtra("email", email);
                                    startActivity(i);
                                    finish();
                                }

                                try {
                                    JSONObject oo = new JSONObject(response.toString());
                                } catch (Exception e) {

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Please fill all detail", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("password", password);
                        params.put("name", name);
                        params.put("age", age);
                        params.put("city", city);
                        params.put("phone", phone);
                        params.put("gender", gender);
                        params.put("donar", donar);
                        params.put("bloodgroup", bloodgroup);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }
}
