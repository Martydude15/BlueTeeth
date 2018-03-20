package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Student_Confirmation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__confirmation);
    }

    /**
     *
     * @param view
     *  This method gives intent to a button to
     *  switch pages
     */
    public void testMethod(View view) {
        // Sets intent switchPage to go from Student_Confirm layout to StudentHome
        Intent switchPage = new Intent(Student_Confirmation.this, StudentHomeActivity.class);
        // Activates page switch
        startActivity(switchPage);
    }
}
