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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marta.domain.Bluetooth;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    ListView list;
    String[] studentName={"Kobe Bryant", "Michael Jordan", "Lebron James", "Anthony Davis", "Chris Paul", "Shaquille O'Neal"};
    String[] jNumber={"J00240824", "J00234523", "J00623623", "J00232323", "J00333333", "J00343334"};
    Integer[] imgId={R.drawable.kobe, R.drawable.mj, R.drawable.lebron, R.drawable.ad, R.drawable.cp3, R.drawable.shaq};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Current date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = view.findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);



        //Bluetooth button function
        view.findViewById(R.id.btButton2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_attendee_list_view, null);
                list = (ListView) mView.findViewById(R.id.listView2);
                    CustomListView customListView = new CustomListView(getActivity(),studentName,jNumber,imgId);
                    list.setAdapter(customListView);
                Button mClose = mView.findViewById(R.id.closeBtn);
                //Makes it to where a button has to be pressed to close the dialog box
                mBuilder.setCancelable(false);

                //Show the dialog box
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       dialog.dismiss();
                    }
                });

            }
        });
    }
}
