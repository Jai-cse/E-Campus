package com.sanskar.campus.campus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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


public class FeedbackPage extends AppCompatActivity {

    Spinner sp1, sp2, sp3, sp4, sp5, sp6, sp7;
    Button feedbackBtn;
    String QUE1, QUE2, QUE3, QUE4, QUE5, QUE6, QUE7, ID;
    TextView tv_faculty_name, tv_faculty_id;
    String a, b;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_page_layout);

        Intent intent = getIntent();
        String temp[];
        String name = intent.getStringExtra("name");
        temp = name.split("\\|");
        a=temp[0];
        b=temp[1];
        //Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
        tv_faculty_name = (TextView) findViewById(R.id.tv_faculty_name);
        tv_faculty_id = (TextView) findViewById(R.id.tv_faculty_id);
        tv_faculty_name.setText(a);
        tv_faculty_id.setText(b);

        sp1 = (Spinner) findViewById(R.id.spinner1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp3 = (Spinner) findViewById(R.id.spinner3);
        sp4 = (Spinner) findViewById(R.id.spinner4);
        sp5 = (Spinner) findViewById(R.id.spinner5);
        sp6 = (Spinner) findViewById(R.id.spinner6);
        sp7 = (Spinner) findViewById(R.id.spinner7);
        feedbackBtn = (Button) findViewById(R.id.feedback_btn);

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ID=tv_faculty_id.getText().toString();
                QUE1 = sp1.getSelectedItem().toString();
                QUE2 = sp2.getSelectedItem().toString();
                QUE3 = sp3.getSelectedItem().toString();
                QUE4 = sp4.getSelectedItem().toString();
                QUE5 = sp5.getSelectedItem().toString();
                QUE6 = sp6.getSelectedItem().toString();
                QUE7 = sp7.getSelectedItem().toString();

               new SetFeedback().execute();

            }
        });


    }


    class SetFeedback extends AsyncTask<Void, Void, Boolean> {

        String Json_url;
        String json_String;
        JSONObject jsonObject;
        JSONArray jsonArray;
        String JsonString;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        InputStream stream;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Json_url = "http://10.0.2.2:800/MyCampus/json/store_feedback.jsp?que1=" +QUE1+ "&que2=" +QUE2+ "&que3=" +QUE3+ "&que4=" +QUE4+ "&que5=" +QUE5+ "&que6=" +QUE6+ "&que7=" +QUE7+ "&facultyID=" +ID;
            mProgressDialog = new ProgressDialog(FeedbackPage.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Sending Feedback");
            // Set progressdialog message
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            mProgressDialog.dismiss();

            if(aBoolean)
            {
                Intent intent = new Intent(FeedbackPage.this, FacultyListCS.class);
                startActivity(intent);
                Toast.makeText(FeedbackPage.this,"Feedback Submitted Successfully",Toast.LENGTH_SHORT);
            }
            else
            {
               sp1.requestFocus();
            }

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                URL url = new URL(Json_url);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                stream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JsonString = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JsonString);
                }

                json_String = stringBuilder.toString();
                jsonObject=new JSONObject(json_String);
                jsonArray=jsonObject.getJSONArray("data");
                return !jsonArray.getJSONObject(0).getString("id").equals("0");


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
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


            return false;
        }


    }
}