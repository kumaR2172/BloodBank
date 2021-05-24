package com.example.bloodbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Navi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    FrameLayout frameLayout;
    androidx.fragment.app.Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        tabLayout=findViewById(R.id.tablayout);
        frameLayout=findViewById(R.id.framelayout);
        fragment = new Searchdonar();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new Searchdonar();
                        break;
                    case 1:
                        fragment = new NoDonar();
                        break;
                    case 2:
                        String email=getIntent().getExtras().getString("email");
                        Bundle bundle=new Bundle();
                        bundle.putString("email",email);
                        fragment = new Status();
                        fragment.setArguments(bundle);
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.framelayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.s1) {
            Intent i=new Intent(Navi.this,MainActivity.class);
            startActivity(i);

        } else if (id == R.id.s2) {

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id");
            startActivity(i);

        } else if (id == R.id.s3)
        {
            Intent i=new Intent(Navi.this,MainActivity.class);
            startActivity(i);

        } else if (id == R.id.s5)
        {

            final   AlertDialog.Builder b=new AlertDialog.Builder(this);
            b.setCancelable(false).
                    setTitle("About").
                    setMessage("Blood Bank is power full source in your hand,that support life.\n\n" +
                            "if you are looking for 'Blood Donor' or if you want to donate blood to someone?\n\n" +
                            "Then, this 'Blood Bank' system will interact you with blood donors. \n" +
                            "\n" +
                            "   \"Donate Blood ,Give a smile to Someone\" ").setPositiveButton("OK",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Toast.makeText(Navi.this, "Ok is clicked", Toast.LENGTH_SHORT).show();
                   b.setCancelable(true);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Toast.makeText(Navi.this, " Cancel is clicked ", Toast.LENGTH_SHORT).show();

                }
            }).create().show();
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));




        } else if (id == R.id.s6) {


            AlertDialog.Builder b=new AlertDialog.Builder(this);
            b.setCancelable(true).
                    setTitle("Frequently Asked Question").
                    setMessage("1. What are the minimum requirement to become a blood donor?\n\n " +
                            "You must be at least 16 years of age, a minimum of 110 pounds, and in basic good health.\n \n"+
                            "2. How will i feel after i donate?\n \n"+
                            "Most people tolerate blood donation very well. However, some people experience fatigue. You should discuss your concerns before and after donation with a staff member at the blood donor center or blood drive.\n\n"
                            +"3. If i have a cold or flu,can i donate blood?\n \n"+
                            "No, blood blood centers require that you be in good health (symptom-free) and feeling well.\n\n"
                            +"4. How often may i donate?\n \n"+" you must wait 56 days between whole blood donations.Platelets will return to normal levels within about 72 hours of donating.\n\n"+

                            "5. What is the most common blood type\n \n"+ "people with type O blood are known as universal blood donors" ).show();
            AlertDialog a=b.create();
            a.show();

        } else if (id == R.id.s7)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("gmail"));
            String x[]={"kp12@gmail.com"};
            i.putExtra(Intent.EXTRA_EMAIL,x);
            i.putExtra(Intent.EXTRA_SUBJECT,"This is subject");
            i.putExtra(Intent.EXTRA_TEXT,"Write your Feedback");
            i.setType("message/rfc822");
            startActivity(i);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
