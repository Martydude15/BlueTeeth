package com.example.marta.blueteeth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Student_Step_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__step_2);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radioButton2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radioButton3:
                if (checked)
                    // Pirates are the best
                    break;
        }
    }

}
