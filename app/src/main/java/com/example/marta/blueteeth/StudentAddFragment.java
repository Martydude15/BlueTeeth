package com.example.marta.blueteeth;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.marta.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentAddFragment extends Fragment {

    public JSONDriver jsonDriver;

    public StudentAddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // String[] students = {"Test 0", "Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9"};
        View view = inflater.inflate(R.layout.fragment_student_add, container, false);

        try {
            Log.d("BLUETEETH", "Going into setup.");
            setUp(getContext(), view);
        } catch (Exception e) {
            Log.d("BLUETEETH", e.toString());
        }

        // Inflate the layout for this fragment
        return view;
    }

    public void setUp(Context context, View view) throws IOException {
        jsonDriver = new JSONDriver("login.json", context);
        List<Student> students = jsonDriver.getStudents();
        ListView listView = view.findViewById(R.id.listView1);

        ArrayAdapter<Student> listViewAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                students
        );
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.addBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder4 = new AlertDialog.Builder(getActivity());
                final View mView4 = getLayoutInflater().inflate(R.layout.dialog_student_or_professor, null);
                RadioGroup  mRgroup = mView4.findViewById(R.id.rgroup2);
                final RadioButton mStudent = mView4.findViewById(R.id.rbStudent);
                final RadioButton mProfessor = mView4.findViewById(R.id.rbProfessor);
                Button mOk = mView4.findViewById(R.id.okbtn2);
                Button mCancel = mView4.findViewById(R.id.cancelbtn5);
                //Makes it to where a button has to be pressed to close the dialog box
                mBuilder4.setCancelable(false);


                //Show the dialog box
                mBuilder4.setView(mView4);
                final AlertDialog dialog = mBuilder4.create();
                dialog.show();
                //If a text field is empty, show an error message, else send the message and close the dialog box
                mOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //if the Student radio button is checked
                        if(mStudent.isChecked())

                        {
                                dialog.dismiss();

                                AlertDialog.Builder mBuilder5 = new AlertDialog.Builder(getActivity());
                                View mView5 = getLayoutInflater().inflate(R.layout.dialog_student_add, null);
                                final EditText mFname = mView5.findViewById(R.id.editFname);
                                final EditText mMname = mView5.findViewById(R.id.editMname);
                                final EditText mLname = mView5.findViewById(R.id.editLname);
                                final EditText mJnumber = mView5.findViewById(R.id.editJnumber);
                                Button mAdd = mView5.findViewById(R.id.addBtn);
                                Button mCancel = mView5.findViewById(R.id.cancelbtn4);
                                //Makes it to where a button has to be pressed to close the dialog box
                                mBuilder5.setCancelable(false);


                                //Show the dialog box
                                mBuilder5.setView(mView5);
                                final AlertDialog dialog = mBuilder5.create();
                                dialog.show();
                                //If a text field is empty, show an error message, else send the message and close the dialog box
                                mAdd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(!mFname.getText().toString().isEmpty() && !mMname.getText().toString().isEmpty()
                                                && !mLname.getText().toString().isEmpty() && !mJnumber.getText().toString().isEmpty())
                                        {

                                            String fName = mFname.getText().toString();
                                            String mName = mMname.getText().toString();
                                            String lName = mLname.getText().toString();
                                            String jNumber = mJnumber.getText().toString();


                                            try {
                                                jsonDriver.addOneStudent(new Student(fName, mName, lName, jNumber));
                                                jsonDriver.getPeople();
                                            } catch (Exception e) {
                                                new DialogBox(e.toString(), getContext());
                                            }





                                            Toast.makeText(getActivity(), "Add complete.", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(getActivity(), "Fill all of the text fields.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                //Cancel button closes dialog box on press
                                mCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(getActivity(), "Add cancelled.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });

                        }

                        //If the Professor radio button is clicked
                        else if (mProfessor.isChecked())

                        {

                                dialog.dismiss();

                                AlertDialog.Builder mBuilder6 = new AlertDialog.Builder(getActivity());
                                View mView6 = getLayoutInflater().inflate(R.layout.dialog_professor_add, null);
                                final EditText mFname2 = mView6.findViewById(R.id.editFname2);
                                final EditText mMname2 = mView6.findViewById(R.id.editMname2);
                                final EditText mLname2 = mView6.findViewById(R.id.editLname2);
                                final EditText mJnumber2 = mView6.findViewById(R.id.editJnumber2);
                                final EditText mPassword = mView6.findViewById(R.id.editPassword);
                                Button mAdd = mView6.findViewById(R.id.addBtn3);
                                Button mCancel = mView6.findViewById(R.id.cancelbtn6);
                                //Makes it to where a button has to be pressed to close the dialog box
                                mBuilder6.setCancelable(false);


                                //Show the dialog box
                                mBuilder6.setView(mView6);
                                final AlertDialog dialog = mBuilder6.create();
                                dialog.show();
                                //If a text field is empty, show an error message, else send the message and close the dialog box
                                mAdd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(!mFname2.getText().toString().isEmpty() && !mMname2.getText().toString().isEmpty() && !mLname2.getText().toString().isEmpty()
                                                && !mJnumber2.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty())
                                        {

                                            String fName2 = mFname2.getText().toString();
                                            String mName2 = mMname2.getText().toString();
                                            String lName2 = mLname2.getText().toString();
                                            String jNumber2 = mJnumber2.getText().toString();
                                            String password = mPassword.getText().toString();



                                            Toast.makeText(getActivity(), "Add complete.", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }

                                        else{
                                            Toast.makeText(getActivity(), "Fill all of the text fields.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                //Cancel button closes dialog box on press
                                mCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(getActivity(), "Add cancelled.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });

                        }

                        else{
                            Toast.makeText(getActivity(), "Select an option", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Cancel button closes dialog box on press
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "Add cancelled.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                
            }
        });
    }

}
