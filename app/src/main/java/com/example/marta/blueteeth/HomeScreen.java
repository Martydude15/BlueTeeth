package com.example.marta.blueteeth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.marta.domain.*;

import java.io.File;
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
                new DialogBox("Jag Number does not exist.", this);
            }
        }
        catch (IOException ioe) {
            // Just here to handle the case of the file not being found.
            new DialogBox(ioe.getMessage(), this);
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
        JSONDriver json2 = new JSONDriver(getAssets().open("login.json"), this);
        // Create new JSONDriver to handle the json file.
        JSONDriver json = new JSONDriver("login.json", this);

        // Processes the json file and puts into corresponding lists.
        // This happens in the background json.getPeople();
        // Checks if the jagNumber returned from the textBox is a teacher
        for (Teacher teacher : json.getTeachers()) {
            if (teacher.getJagNumber().toLowerCase().equals(jagNumber.toLowerCase())) {
                // If teacher, go to next part for teacher
                Intent intent = new
                        Intent(HomeScreen.this, TeacherHomeActivity.class);
                // This sends the teacher or student object associated with the jagNumber
                // to the next activity. Neat right?
                intent.putExtra("user", teacher);
                return intent;
            }
        }
        // Checks if the jagNumber returned from the textBox is a student
        for (Student student : json.getStudents()) {
             if (student.getJagNumber().toLowerCase().equals(jagNumber.toLowerCase())) {
                 // If student, go to next part for student
                 Intent intent = new
                         Intent(HomeScreen.this, StudentHomeActivity.class);
                 intent.putExtra("user", student);
                 return intent;
             }
        }
        return null;
    }
}
