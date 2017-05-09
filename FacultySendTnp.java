package com.sanskar.campus.campus;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FacultySendTnp extends android.app.Fragment {

    public FacultySendTnp() {
        // Required empty public constructor
    }
    EditText sub,description;
    Button submit;
    View view;
    String SUBJECT,DESCRIPTION,DEPT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_faculty_send_tnp, container, false);
        sub=(EditText)view.findViewById(R.id.topic);
        description=(EditText)view.findViewById(R.id.brief);
        submit=(Button)view.findViewById(R.id.tnp_addBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DEPT=FacultyTnpFragment.dept;
                SUBJECT=sub.getText().toString();
                DESCRIPTION=description.getText().toString();
                new SetTnp().execute();

            }
        });





        return view;
    }


    class SetTnp extends AsyncTask<Void, Void, Boolean> {

        String Json_url;
        String json_String;
        JSONObject jsonObject;
        JSONArray jsonArray;
        String JsonString;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        InputStream stream;
        ProgressDialog mProgressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SUBJECT=SUBJECT.replace(" ","+");
            DESCRIPTION=DESCRIPTION.replace(" ","+");
            Log.d("JSON :","http://10.0.2.2:800/MyCampus/json/store_tnp.jsp?subject="+SUBJECT+"&description="+DESCRIPTION+"&dept="+DEPT);
            Json_url = "http://10.0.2.2:800/MyCampus/json/store_tnp.jsp?subject="+SUBJECT+"&description="+DESCRIPTION+"&dept="+DEPT;
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Sending Details");
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
                FacultyTnpFragment f=new FacultyTnpFragment();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent1, f).commit();



                Toast.makeText(getActivity()," Submitted Successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                sub.requestFocus();
            }

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.d("link",SUBJECT+"-"+ DESCRIPTION);

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
