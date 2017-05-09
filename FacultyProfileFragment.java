package com.sanskar.campus.campus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacultyProfileFragment extends android.app.Fragment {

    public FacultyProfileFragment() {
        // Required empty public constructor
    }
    TextView fname,fdept,fcontact,femail;
    View view;
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_faculty_profile, container, false);
       fname=(TextView)view.findViewById(R.id.f_profile_name);
       fdept=(TextView)view.findViewById(R.id.f_profile_dept);
       femail=(TextView)view.findViewById(R.id.f_profile_email);
       fcontact=(TextView)view.findViewById(R.id.f_profile_contact);


       fname.setText(Faculty_LoginActivity.USERNAME);
        fdept.setText(Faculty_LoginActivity.DEPT);
        femail.setText(Faculty_LoginActivity.USEREMAIL);
        fcontact.setText(Faculty_LoginActivity.CONTACT);


       return view;
   }
}
