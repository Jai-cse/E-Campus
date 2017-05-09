package com.sanskar.campus.campus;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateStudentFragment extends android.app.Fragment {

    public UpdateStudentFragment() {
        // Required empty public constructor
    }
    TextView s_name,s_rollno,s_dept,s_sem,s_year;
    EditText address,email,contact;
    View view;
    String U_EMAIL,U_CONTACT,U_ADDRESS;
    Button submitBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_update_student_layout, container, false);

        s_name=(TextView)view.findViewById(R.id.s_profile_name1);
        s_rollno=(TextView)view.findViewById(R.id.s_profile_rollno1);
        s_dept=(TextView)view.findViewById(R.id.s_profile_dept1);
        s_sem=(TextView)view.findViewById(R.id.s_profile_sem1);
        s_year=(TextView)view.findViewById(R.id.s_profile_year1);
        address=(EditText)view.findViewById(R.id.s_profile_address1);
        email=(EditText)view.findViewById(R.id.s_profile_email1);
        contact=(EditText)view.findViewById(R.id.s_profile_contact1);
        submitBtn=(Button)view.findViewById(R.id.submit_btn);


        s_name.setText(LoginActivity.USERNAME);
        s_rollno.setText(LoginActivity.ROLLNO);
        s_dept.setText(LoginActivity.DEPT);
        s_year.setText(LoginActivity.YEAR);
        s_sem.setText(LoginActivity.SEM);
        email.setHint(LoginActivity.USEREMAIL);
        contact.setHint(LoginActivity.CONTACT);
        address.setHint(LoginActivity.ADDRESS);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                U_EMAIL= email.getText().toString();
                U_ADDRESS=address.getText().toString();
                U_CONTACT=contact.getText().toString();

                LoginActivity.USEREMAIL=U_EMAIL;
                LoginActivity.ADDRESS=U_ADDRESS;
                LoginActivity.CONTACT=U_CONTACT;


                StudentProfile f=new StudentProfile();

                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent,f).commit();

                Toast.makeText(getActivity(),"Profile Updated Sucessfully",Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }

}
