package com.sanskar.campus.campus;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacultyNewsFragment extends android.app.Fragment {
    public FacultyNewsFragment() {
        // Required empty public constructor
    }

    String JsonString;
Button addEvent_btn;
    String json_String;
    JSONObject jsonObject;
    JSONArray jsonArray;
    newsAdapter news_adapter;
    ListView listView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_faculty_news, container, false);
        addEvent_btn=(Button)view.findViewById(R.id.AddEventBtn);
        addEvent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddEventFragment add=new AddEventFragment();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent1, add).commit();

            }
        });
        listView=(ListView)view.findViewById(R.id.f_lv_event);
        news_adapter=new newsAdapter(getActivity(),R.layout.row_layout);

        new BackgroundTask().execute();

        return view;

    }

    class BackgroundTask extends AsyncTask<Void,Void,Void> {

        String Json_url;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        InputStream stream;
        ProgressDialog mProgressDialog;
        @Override
        protected void onPreExecute() {
            Json_url="http://10.0.2.2:800/MyCampus/json/news_eventJSON.jsp";
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Campus");
            // Set progressdialog message
            mProgressDialog.setMessage("Downloading Data...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();



        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url= new URL(Json_url);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                stream=httpURLConnection.getInputStream();
                bufferedReader=new BufferedReader(new InputStreamReader(stream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JsonString= bufferedReader.readLine())!=null  ){

                    stringBuilder.append(JsonString);
                }


                try {
                    json_String = stringBuilder.toString();
                    jsonObject = new JSONObject(json_String);
                    jsonArray=jsonObject.getJSONArray("Event");

                    int count=0;
                    String subject,description;
                    while (count<jsonArray.length()){

                        JSONObject jo=  jsonArray.getJSONObject(count);
                        subject=jo.getString("subject");
                        description=jo.getString("description");
                        get_set_news_event data=new get_set_news_event();
                        data.setSubject(subject);
                        data.setDescription(description);

                        news_adapter.add(data);

                        count++;


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}

                if (stream!=null){
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}

                if (httpURLConnection!=null) {
                    httpURLConnection.disconnect();
                }
            }

            return  null;

        }

        protected void onPostExecute(Void args) {

            mProgressDialog.dismiss();
            listView.setAdapter(news_adapter);

        }


    }



}
