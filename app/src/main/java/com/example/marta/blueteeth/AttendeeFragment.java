package com.example.marta.blueteeth;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendeeFragment extends Fragment {


    public AttendeeFragment() {
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
        return inflater.inflate(R.layout.fragment_attendee, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView) view.findViewById(R.id.listView3);
        CustomListView customListView = new CustomListView(getActivity(),studentName,jNumber,imgId);
        list.setAdapter(customListView);

    }

}
