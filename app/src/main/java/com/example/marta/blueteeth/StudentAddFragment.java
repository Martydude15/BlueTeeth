package com.example.marta.blueteeth;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marta.domain.*;

import java.io.IOException;
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
            setUp(getContext(), view);
        } catch (Exception e) {
            new DialogBox(e.toString(), getContext());
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

}
