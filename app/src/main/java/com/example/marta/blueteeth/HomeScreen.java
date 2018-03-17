package com.example.marta.blueteeth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.marta.domain.*;
import java.io.IOException;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


    public void login(View view) throws IOException {
        try {
            EditText textBox = findViewById(R.id.editText);
            Intent intent = verifyLogin(textBox.getText().toString());
            if (intent != null) startActivity(intent);
            else {
                textBox.getText().clear();
                AlertDialog dialog = loginError();
                dialog.show();
            }
        }
        catch (IOException ioe) {
            AlertDialog dialog = loginError();
            dialog.setMessage("File not found.");
            dialog.show();
        }
    }

    public Intent verifyLogin(String jagNumber) throws IOException {
        JSONDriver json = new JSONDriver(getAssets().open("login.json"));
        json.getPeople(json.getJson());
        for (Teacher teacher : json.getTeachers()) {
            if (teacher.getJagNumber().equals(jagNumber)) {
                return new Intent(HomeScreen.this, TeacherHomeActivity.class);
            }
        }
        for (Student student : json.getStudents()) {
             if (student.getJagNumber().equals(jagNumber)) {
                 return new Intent(HomeScreen.this, StudentHomeActivity.class);
             }
        }
        return null;
    }

    public AlertDialog loginError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Jag Number does not exist.");
        AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

}
