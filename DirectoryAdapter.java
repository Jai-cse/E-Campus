package com.sanskar.campus.campus;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jai on 2017-04-05.
 */

public class DirectoryAdapter extends ArrayAdapter{

    private List list= new ArrayList();



    public DirectoryAdapter(Context context,int resource) {
        super(context, resource);
    }


    public void add( Directory_Contacts object) {

        list.add(object);
    }


    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View row;
        row=convertView;
        DataHolder dataHolder;
        if (row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.directory_row,parent,false);
            dataHolder=new DataHolder();
            dataHolder.name=(TextView)row.findViewById(R.id.tv_name);
            dataHolder.designation=(TextView)row.findViewById(R.id.tv_designation);
            dataHolder.phone=(TextView)row.findViewById(R.id.tv_phone);
            dataHolder.email=(TextView)row.findViewById(R.id.tv_email);
            row.setTag(dataHolder);

        }
        else{
            dataHolder=(DataHolder)row.getTag();
        }
            Directory_Contacts contacts=(Directory_Contacts)getItem(position);
        dataHolder.name.setText(contacts.getName());
        dataHolder.designation.setText(contacts.getDesignation());
        dataHolder.phone.setText(contacts.getPhone());
        dataHolder.email.setText(contacts.getEmail());

        return row;


    }

    static class DataHolder{

        TextView name,designation,phone,email;


    }






}
