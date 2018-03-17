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

    /**
     *
     * @param view
     *      Takes in the current "view" which is just the current screen that is built
     *      as defined in activity_home_screen.xml in the layouts folder
     *      to modify via a button click.
     */
    public void login(View view) {
        try {
            // Pulls the textBox defined in the layout
            EditText textBox = findViewById(R.id.editText);
            // Sets the Intent returned from verifyLogin
            Intent intent = verifyLogin(textBox.getText().toString());
            // If the intent is not null, start the activity
            if (intent != null) startActivity(intent);
            // If it was null, it means the JagNumber was not found
            else {
                // Clears to allow for re-entry
                textBox.getText().clear();
                AlertDialog dialog = loginError();
                dialog.show();
            }
        }
        catch (IOException ioe) {
            // Just here to handle the case of the file not being found.
            AlertDialog dialog = loginError();
            // Changes the message to say File not found.
            dialog.setMessage("File not found.");
            dialog.show();
        }
    }

    /**
     *
     * @param jagNumber
     *      taking in the text from the text field called editText
     * @return
     *      returns the intent depending on if the jagNumber matches a teacher or a student
     *      otherwise return null to catch later
     * @throws IOException
     */
    public Intent verifyLogin(String jagNumber) throws IOException {
        // Create new JSONDriver to handle the json file.
        JSONDriver json = new JSONDriver(getAssets().open("login.json"));
        // Processes the json file and puts into corresponding lists.
        json.getPeople(json.getJson());
        // Checks if the jagNumber returned from the textBox is a teacher
        for (Teacher teacher : json.getTeachers()) {
            if (teacher.getJagNumber().equals(jagNumber)) {
                // If teacher, go to next part for teacher
                return new Intent(HomeScreen.this, TeacherHomeActivity.class);
            }
        }
        // Checks if the jagNumber returned from the textBox is a student
        for (Student student : json.getStudents()) {
             if (student.getJagNumber().equals(jagNumber)) {
                 // If student, go to next part for student
                 return new Intent(HomeScreen.this, StudentHomeActivity.class);
             }
        }
        return null;
    }

    /**
     *
     * @return
     *      returns an alert dialog box to handle errors for File not found, and JagNumber
     *      not found.
     */
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
