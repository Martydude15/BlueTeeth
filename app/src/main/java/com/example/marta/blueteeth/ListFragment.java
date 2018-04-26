package com.example.marta.blueteeth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.marta.domain.JSONDriver;
import com.example.marta.domain.Student;
import com.example.marta.domain.Teacher;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //when the list button is clicked
        view.findViewById(R.id.listBtn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            //inflate the layout
            final View mView = getLayoutInflater().inflate(R.layout.dialog_list_date, null);
            RadioGroup  mRgroup = mView.findViewById(R.id.rgroup3);
            final RadioButton mDay = mView.findViewById(R.id.rbDay);
            final RadioButton mWeek = mView.findViewById(R.id.rbWeek);
            final RadioButton mMonth = mView.findViewById(R.id.rbMonth);
            Button mOk = mView.findViewById(R.id.okbtn3);
            Button mCancel = mView.findViewById(R.id.cancelbtn7);
            //Makes it to where a button has to be pressed to close the dialog box
            mBuilder.setCancelable(false);

            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            //show the dialog
            dialog.show();

            //when the ok button is clicked
            mOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if a button is checked
                    if(mDay.isChecked() || mWeek.isChecked() || mMonth.isChecked())
                    {
                        //dismiss the current dialog
                        dialog.dismiss();

                        AlertDialog.Builder mBuilder2 = new AlertDialog.Builder(getActivity());
                        //inflate the new dialog
                        final View mView2 = getLayoutInflater().inflate(R.layout.dialog_list_attendees, null);
                        RadioGroup mRgroup2 = mView2.findViewById(R.id.rgroup4);
                        final RadioButton mAttendees = mView2.findViewById(R.id.rbAttendees);
                        final RadioButton mNonAttendees = mView2.findViewById(R.id.rbNonAttendees);
                        final RadioButton mAll = mView2.findViewById(R.id.rbAll);
                        Button mOk2 = mView2.findViewById(R.id.okbtn4);
                        Button mCancel2 = mView2.findViewById(R.id.cancelbtn8);
                        //Makes it to where a button has to be pressed to close the dialog box
                        mBuilder2.setCancelable(false);

                        mBuilder2.setView(mView2);
                        final AlertDialog dialog = mBuilder2.create();
                        //show the enw dialog
                        dialog.show();

                        mOk2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //if a button is checked
                                if(mAttendees.isChecked() || mNonAttendees.isChecked() || mAll.isChecked())
                                {
                                    //dismiss the current dialog
                                     dialog.dismiss();

                                    AlertDialog.Builder mBuilder3 = new AlertDialog.Builder(getActivity());
                                    //inflate the new dialog
                                    final View mView3 = getLayoutInflater().inflate(R.layout.dialog_list_file, null);
                                    RadioGroup mRgroup3 = mView3.findViewById(R.id.rgroup5);
                                    final RadioButton mXml = mView3.findViewById(R.id.rbXml);
                                    final RadioButton mJson = mView3.findViewById(R.id.rbJson);
                                    Button mOk3 = mView3.findViewById(R.id.okbtn5);
                                    Button mCancel3 = mView3.findViewById(R.id.cancelbtn9);
                                    //Makes it to where a button has to be pressed to close the dialog box
                                    mBuilder3.setCancelable(false);

                                    mBuilder3.setView(mView3);
                                    final AlertDialog dialog = mBuilder3.create();
                                    //show the new dialog
                                    dialog.show();

                                    //if the ok button is pressed on the third dialog
                                    mOk3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //if a button is checked
                                            if(mXml.isChecked() || mJson.isChecked())
                                            {
                                                //display a toast message
                                                Toast.makeText(getActivity(),"File generated.",Toast.LENGTH_SHORT).show();
                                                //dismiss the dialog
                                                dialog.dismiss();
                                            }
                                            else{
                                                //display a toast message if an option is not selected on the third dialog
                                                Toast.makeText(getActivity(),"Select an option.",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }

                                else {
                                    //display a toast message if an option is not selected on the third dialog
                                    Toast.makeText(getActivity(),"Select an option.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        //if the cancel button is clicked on the second dialog
                        mCancel2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //display a toast message
                                Toast.makeText(getActivity(),"List cancelled.",Toast.LENGTH_SHORT).show();
                                //dismiss the dialog
                                dialog.dismiss();
                            }
                        });

                    }

                    else {
                        //display a toast message if an option is not selected
                        Toast.makeText(getActivity(),"Select an option.",Toast.LENGTH_SHORT).show();
                    }

                }
            });

            //when the cancel button is clicked
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //display a toast message
                    Toast.makeText(getActivity(),"List cancelled.",Toast.LENGTH_SHORT).show();
                    //dismiss the dialog
                    dialog.dismiss();
                }
            });

        }
    });



    }



}