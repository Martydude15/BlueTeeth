package com.example.marta.blueteeth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NonAttendeeFragment extends Fragment {


    public NonAttendeeFragment() {
        // Required empty public constructor
    }

    ListView list;
    String[] studentName={"Damian Lillard", "Karl Anthony Towns", "Kawhi Leonard", "Hassan Whiteside", "Russell Westbrook", "Kevin Love", "Frank Ocean", "Lebron James", "Jermaine Cole"};
    String[] jNumber={"J00000004", "J00000005", "J00222222", "J00232323", "J00023023", "J00000001", "J00372656", "J00623623", "J00201400"};
    Integer[] imgId={R.drawable.vacant, R.drawable.vacant, R.drawable.vacant, R.drawable.vacant, R.drawable.vacant, R.drawable.vacant, R.drawable.vacant, R.drawable.vacant, R.drawable.vacant};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_attendee, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView) view.findViewById(R.id.listView4);
        CustomListView customListView = new CustomListView(getActivity(),studentName,jNumber,imgId);
        list.setAdapter(customListView);

    }

}
