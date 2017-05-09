package com.sanskar.campus.campus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FeedbackFragment extends android.app.Fragment {


    public FeedbackFragment() {
        // Required empty public constructor
    }


View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_feedback_layout, container, false);
        context=view.getContext();
        Button csBtn=(Button) view.findViewById(R.id.cs_btn);
        Button meBtn=(Button )view.findViewById(R.id.me_btn);
        Button ceBtn=(Button) view.findViewById(R.id.ce_btn);
        Button eeBtn=(Button) view.findViewById(R.id.ee_btn);


        csBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),FacultyListCS.class);
                getActivity().startActivity(i);
            }
        });


        return view;

    }



    }


