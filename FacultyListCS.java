package com.sanskar.campus.campus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class FacultyListCS extends AppCompatActivity {


    ListView listView;
    FacultyAdapterCS facultyAdapterCS;
    String JsonString;
    ProgressDialog mProgressDailog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cs_faculty_list_layout);
        listView=(ListView)findViewById(R.id.cs_lv);
        facultyAdapterCS=new FacultyAdapterCS(this,R.layout.faculty_row);
        new JSONTask().execute();

        listView.setAdapter(facultyAdapterCS);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent i;

               //Toast.makeText(getApplicationContext(),listView.getAdapter().getItem(position).toString(),Toast.LENGTH_LONG).show();
               i = new Intent(FacultyListCS.this,FeedbackPage.class);
               i.putExtra("name",listView.getAdapter().getItem(position).toString());
               startActivity(i);

           }
       });
        Toast.makeText(this, "Choose Faculty for Feedback", Toast.LENGTH_SHORT).show();

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

            Json_url="http://10.0.2.2:800/MyCampus/json/faculty_list.jsp?dept=CSE";
            mProgressDailog=new ProgressDialog(FacultyListCS.this);
            mProgressDailog.setTitle("ECampus");
            mProgressDailog.setMessage("Loading Faculty Data");
            mProgressDailog.setIndeterminate(false);
            mProgressDailog.show();



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
                    jsonArray = jsonObject.getJSONArray("Faculty");
                    int count = 0;
                    String name,id;

                    while (count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        name = jo.getString("name");
                        id = jo.getString("id");
                        FacultyContactCS data=new FacultyContactCS();
                        data.setName(name);
                        data.setId(id);

                        facultyAdapterCS.add(data);
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
            mProgressDailog.dismiss();
        }


    }



}
