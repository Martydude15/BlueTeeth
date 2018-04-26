package com.example.marta.blueteeth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marta.domain.Teacher;

public class CustomListView extends ArrayAdapter<String>{


    private String[] studentName;
    private String[] jNumber;
    private Integer[] imgId;
    private Activity context;


    public CustomListView(Activity context, String[] studentName, String[] jNumber, Integer[] imgId) {
        super(context, R.layout.listview_layout, studentName);


        this.context=context;
        this.studentName=studentName;
        this.jNumber=jNumber;
        this.imgId=imgId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r==null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            //inflates the layout
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) r.getTag();

        }

        //sets these values in the order they are typed
        viewHolder.imgv.setImageResource(imgId[position]);
        viewHolder.txtv1.setText(studentName[position]);
        viewHolder.txtv2.setText(jNumber[position]);

        return r;

    }

    //class to get the views from the listview_layout xml
    class ViewHolder
    {
        TextView txtv1;
        TextView txtv2;
        ImageView imgv;
        ViewHolder(View v)
        {
            txtv1 = v.findViewById(R.id.studentName);
            txtv2 = v.findViewById(R.id.jNumber);
            imgv = v.findViewById(R.id.imageView2);
        }
    }

}

