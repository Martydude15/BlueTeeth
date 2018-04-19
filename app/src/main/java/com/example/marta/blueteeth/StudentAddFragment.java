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
import android.widget.Toast;

import com.example.marta.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentAddFragment extends Fragment {

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
        JSONDriver jsonDriver = new JSONDriver("login.json", context);
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
                View mView4 = getLayoutInflater().inflate(R.layout.dialog_student_add, null);
                final EditText mClass = mView4.findViewById(R.id.editClass);
                final EditText mFname = mView4.findViewById(R.id.editFname);
                final EditText mMname = mView4.findViewById(R.id.editMname);
                final EditText mLname = mView4.findViewById(R.id.editLname);
                final EditText mJnumber = mView4.findViewById(R.id.editJnumber);
                Button mAdd = mView4.findViewById(R.id.addBtn);
                Button mCancel = mView4.findViewById(R.id.cancelbtn4);
                //Makes it to where a button has to be pressed to close the dialog box
                mBuilder4.setCancelable(false);

                //Show the dialog box
                mBuilder4.setView(mView4);
                final AlertDialog dialog = mBuilder4.create();
                dialog.show();
                //If a text field is empty, show an error message, else send the message and close the dialog box
                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mClass.getText().toString().isEmpty() && !mFname.getText().toString().isEmpty() && !mMname.getText().toString().isEmpty()
                                && !mLname.getText().toString().isEmpty() && !mJnumber.getText().toString().isEmpty())
                        {
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
        });
    }

}
