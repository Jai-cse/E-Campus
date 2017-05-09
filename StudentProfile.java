package com.sanskar.campus.campus;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentProfile extends android.app.Fragment {


    public StudentProfile() {
        // Required empty public constructor
    }

    TextView name,rollno,dept,year,sem,contact,email,address;
    View view;
    Button updateBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_student_profile, container, false);

        name=(TextView)view.findViewById(R.id.s_profile_name);
        rollno=(TextView)view.findViewById(R.id.s_profile_rollno);
        dept=(TextView)view.findViewById(R.id.s_profile_dept);
        year=(TextView)view.findViewById(R.id.s_profile_year);
        sem=(TextView)view.findViewById(R.id.s_profile_sem);
        email=(TextView)view.findViewById(R.id.s_profile_email);
        contact=(TextView)view.findViewById(R.id.s_profile_contact);
        address=(TextView)view.findViewById(R.id.s_profile_address);
        updateBtn=(Button)view.findViewById(R.id.s_uddate_btn);

        name.setText(LoginActivity.USERNAME);
        rollno.setText(LoginActivity.ROLLNO);
        dept.setText(LoginActivity.DEPT);
        year.setText(LoginActivity.YEAR);
        sem.setText(LoginActivity.SEM);
        email.setText(LoginActivity.USEREMAIL);
        contact.setText(LoginActivity.CONTACT);
        address.setText(LoginActivity.ADDRESS);


       updateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                UpdateStudentFragment f=new UpdateStudentFragment();

               FragmentManager fragmentManager=getFragmentManager();
               fragmentManager.beginTransaction().replace(R.id.flContent,f).commit();
           }
       });


    return view;
    }


}
