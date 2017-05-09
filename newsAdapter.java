package com.sanskar.campus.campus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jai on 2017-04-04.
 */

public class newsAdapter extends ArrayAdapter {
        List list=new ArrayList();

    public newsAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(get_set_news_event object) {

        list.add(object);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        DataHolder dataHolder;
        if(row == null){

            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
                dataHolder=new DataHolder();
            dataHolder.subject= (TextView) row.findViewById(R.id.tv_subject);
            dataHolder.description=(TextView)row.findViewById(R.id.tv_description);
            row.setTag(dataHolder);

        }
            else{
            dataHolder= (DataHolder)row.getTag();
        }

        get_set_news_event  setPositon=(get_set_news_event)this.getItem(position);
        dataHolder.subject.setText(setPositon.getSubject());
        dataHolder.description.setText(setPositon.getDescription());

        return row;


    }



    static class DataHolder{

        TextView subject,description;


    }



}
