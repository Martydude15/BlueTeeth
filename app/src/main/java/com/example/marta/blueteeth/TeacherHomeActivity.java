package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.marta.domain.Teacher;


public class TeacherHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_password);
    }

    public void verifyPassword(View view) {
        // Pulls in the object from the HomeScreen activity.
        Teacher teacher = getIntent().getParcelableExtra("user");
        // Just pulling in the textbox for this activity.
        EditText textBox = findViewById(R.id.editText2);
        String password = teacher.getPassCode();
        // Checks the password
        if (password.equals(String.valueOf(textBox.getText()))) {
            // Sets up to move to the next activity
            Intent switchPage = new
                    Intent(TeacherHomeActivity.this, TeacherConnectScreen.class);
            // Adds the object to send to next activity.
            switchPage.putExtra("user", teacher);
            startActivity(switchPage);
        }
        else {
            new DialogBox(String.format("The password is incorrect.\n" +
                    "Please try again. \n%s\n\n%s", password, textBox.getText())
                    , this);
            textBox.getText().clear();
        }
    }
}
