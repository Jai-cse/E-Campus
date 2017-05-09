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
 * Created by Jai on 2017-04-10.
 */

public class FacultyAdapterCS extends ArrayAdapter {

    private List list= new ArrayList();
    public FacultyAdapterCS(Context context,int resource) {
        super(context, resource);
    }


    public void add(FacultyContactCS object) {

        list.add(object);
    }


    public Object getItem(int position) {
        return list.get(position);
    }


    public int getCount() {
        return list.size();
    }


    public View getView(int position,View convertView,ViewGroup parent) {
        View row;
        row=convertView;
        DataHolder dataHolder;
        if (row==null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.faculty_row,parent,false);
             dataHolder=new DataHolder();
            dataHolder.name=(TextView)row.findViewById(R.id.tv_faculty);
            dataHolder.id=(TextView) row.findViewById(R.id.tv_id);
            row.setTag(dataHolder);

        }
        else{
            dataHolder=(DataHolder)row.getTag();
        }
        FacultyContactCS contactCS=(FacultyContactCS)getItem(position);
        dataHolder.name.setText(contactCS.getName());
        dataHolder.id.setText(contactCS.getId());
        return row;




    }
    static class DataHolder{

        TextView name,id;


    }

}
