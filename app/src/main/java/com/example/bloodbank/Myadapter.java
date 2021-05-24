package com.example.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends BaseAdapter {

    Context context;
    int flags;
    ArrayList<String> s;
    LayoutInflater inflater;
    public Myadapter(Context ApplicationContext,ArrayList<String> s,int flags)
    {
        this.context=ApplicationContext;
        this.flags=flags;
        this.s=s;
        inflater=(LayoutInflater.from(ApplicationContext));
    }
    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.listd,null);

        ImageView i1=view.findViewById(R.id.i1);
        TextView t1=view.findViewById(R.id.t1);
        i1.setImageResource(flags);
        t1.setText(s.get(i).toString());
        return view;

    }
}
