package com.example.marta.blueteeth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


    public void testMethod(View view) throws IOException {
        EditText textBox = findViewById(R.id.editText);
        try {
            Person loginPerson = verifyLogin(textBox.getText().toString());
            if (loginPerson.isTeacher()) {
                Intent switchPage = new Intent(HomeScreen.this, TeacherHomeActivity.class);
                startActivity(switchPage);
            } else if (!loginPerson.getJagNumber().equals("")) {
                Intent switchPage = new Intent(HomeScreen.this, StudentHomeActivity.class);
                startActivity(switchPage);
            } else {
                textBox.getText().clear();
                loginError();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Person verifyLogin(String jagNumber) throws IOException{
        JSONDriver json = new JSONDriver(getAssets().open("login.json"));
        List<Person> people = json.getPeople(json.getJson());
        for (Person person: people) {
            if (person.getJagNumber().equals(jagNumber)) {
                return person;
            }
        }
        return new Person();
    }

    public void loginError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Jag Number does not exist.");
        AlertDialog dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
