package com.sanskar.campus.campus;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacultyTnpFragment extends android.app.Fragment {

    public FacultyTnpFragment() {
        // Required empty public constructor
    }

    static String dept;
    View view;
    Button csBtn,meBtn,enBtn,ceBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_faculty_tnp, container, false);
      csBtn=(Button)view.findViewById(R.id.tnp_cs_btn);
        ceBtn=(Button)view.findViewById(R.id.tnp_ce_btn);
        meBtn=(Button)view.findViewById(R.id.tnp_me_btn);
        enBtn=(Button)view.findViewById(R.id.tnp_en_btn);

        csBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dept="CSE";
             FacultySendTnp f=new FacultySendTnp();
    FragmentManager fragmentManager=getFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.flContent1,f).commit();
            }
        });


        meBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept="ME";
                FacultySendTnp f=new FacultySendTnp();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent1,f).commit();
            }
        });



        enBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept="EN";
                FacultySendTnp f=new FacultySendTnp();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent1,f).commit();
            }
        });



        ceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept="CE";
                FacultySendTnp f=new FacultySendTnp();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent1,f).commit();
            }
        });

        return view;
    }



}
