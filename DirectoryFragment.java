package com.sanskar.campus.campus;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class DirectoryFragment extends android.app.Fragment {


    public DirectoryFragment() {
        // Required empty public constructor
    }


    DirectoryAdapter directoryAdapter;
    String JsonString;
    ListView listView_directory;
    View view;
    ProgressDialog mProgressDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_directory_layout, container, false);
            listView_directory=(ListView)view.findViewById(R.id.lv_directory1);
        directoryAdapter=new DirectoryAdapter(getActivity(),R.layout.directory_row);
       /* listView_directory.setAdapter(directoryAdapter);*/
        new JSONTask().execute();


        return view;
    }



    class JSONTask extends AsyncTask<Void,Void,Void> {

        String Json_url;
        String json_String;
        JSONObject jsonObject;
        JSONArray jsonArray;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        InputStream stream;

        @Override
        protected void onPreExecute() {

            Json_url="http://10.0.2.2:800/MyCampus/json/directorylist.jsp";

            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Campus");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(Json_url);
                 httpURLConnection = (HttpURLConnection) url.openConnection();
                 stream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JsonString = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JsonString);
                }


                try {
                    json_String = stringBuilder.toString();
                    jsonObject = new JSONObject(json_String);
                    jsonArray = jsonObject.getJSONArray("Directory");
                    int count = 0;
                    String name, designation, phone, email;
                    while (count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        name = jo.getString("name");
                        designation = jo.getString("designation");
                        phone = jo.getString("phone");
                        email = jo.getString("email");
                        Directory_Contacts data = new Directory_Contacts();
                        data.setName(name);
                        data.setDesignation(designation);
                        data.setPhone(phone);
                        data.setEmail(email);

                        directoryAdapter.add(data);
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
            listView_directory.setAdapter(directoryAdapter);


        }


    }


}
