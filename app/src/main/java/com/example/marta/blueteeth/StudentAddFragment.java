package com.example.marta.blueteeth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marta.domain.*;



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

        View view = inflater.inflate(R.layout.fragment_student_add, container, false);


        String[] students = {"Test 0", "Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9"
        };

        ListView listView = (ListView) view.findViewById(R.id.listView1);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                students
        );

        listView.setAdapter(listViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
