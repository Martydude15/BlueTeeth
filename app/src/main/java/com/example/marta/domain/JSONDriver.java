package com.example.marta.domain;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONDriver {

    private InputStream istream;
    private OutputStream ostream;
    private Context context;
    private String filepath;
    private List<Teacher> teachers;
    private List<Student> students;

    public JSONDriver(String filepath, Context context) throws IOException {
        File file = new File(context.getFilesDir() + "/" + filepath);
        this.context = context;
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        if (file.exists()) {
            this.istream = context.openFileInput(filepath);
            getPeople();
        }
        this.filepath = filepath;
    }

    /**
     *
     * Constructor:
     *
     * @param istream
     *      take the path to the json file that contains the information of the class
     * @throws IOException
     *      handled in HomeScreen.java
     */
    public JSONDriver(InputStream istream, Context context) throws IOException {
         /*
         Pulls in the path as InputStream because it is a super class of InputStreamReader
         and allows for a string to be pass as the filename.
         */
         // Makes sure for each new instance of JSONDriver it has a fresh list
         this.istream = istream;
         teachers = new ArrayList<>();
         students = new ArrayList<>();
         getPeople();
         this.ostream = context.openFileOutput("login.json", Context.MODE_PRIVATE);
         addAll();
    }

    /**
     *
     * @param student
     *      Made to write to the json file a little easier.
     * @param writer
     *      JsonWriter to write the object to the file.
     * @throws IOException
     *      Passed to calling class.
     */
    public void addStudent(Student student, JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("class").value("Student");
        writer.name("firstName").value(student.getFirstName());
        writer.name("middleName").value(student.getMiddleName());
        writer.name("lastName").value(student.getLastName());
        writer.name("jagNumber").value(student.getJagNumber());
        writer.endObject();
    }

    /**
     *
     * @param teacher
     *      Made to write to the json file a little easier.
     * @param writer
     *      JsonWriter to write the object to the file.
     * @throws IOException
     *       Passed to the calling class.
     */
    public void addTeacher(Teacher teacher, JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("class").value("Teacher");
        writer.name("firstName").value(teacher.getFirstName());
        writer.name("middleName").value(teacher.getMiddleName());
        writer.name("lastName").value(teacher.getLastName());
        writer.name("jagNumber").value(teacher.getJagNumber());
        writer.name("passCode").value(teacher.getPassCode());
        writer.endObject();
    }

    /**
     *
     * @param newStudent
     *      Push the new student onto the file.
     * @throws IOException
     */
    public void addOneStudent(Student newStudent) throws IOException {
        students.add(newStudent);
        addAll();
    }

    /**
     *
     * @param newTeacher
     *      Pushes the new teacher onto the file.
     * @throws IOException
     */
    public void addOneTeacher(Teacher newTeacher) throws IOException {
        teachers.add(newTeacher);
        addAll();
    }

    /**
     * Added to rewrite file whenever intialized.
     *
     * @throws IOException
     */
    public void addAll() throws IOException {
        if (ostream == null) {
            this.ostream = context.openFileOutput(filepath, Context.MODE_PRIVATE);
        }
        // FileWriter writer = new FileWriter(path);
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(ostream, "UTF-8"));
        writer.beginArray();
        for (Teacher teacher : teachers) {
            addTeacher(teacher, writer);
        }
        for (Student student : students) {
            addStudent(student, writer);
        }
        writer.endArray();
        writer.close();
    }

    /**
     * Creates the json reader and passes through to getPeople(JsonReader)
     * @throws IOException
     *         Handle in the other classes
     */
    public void getPeople() throws IOException {
        JsonReader json = new JsonReader(new InputStreamReader(istream, "UTF-8"));
        getPeople(json);
    }

    /**
     *
     * @param json
     *      receives the open Json file to be read
     * @throws IOException
     *      handled in HomeScreen.java
     */
    public void getPeople(JsonReader json) throws IOException {
        /*
        Points to everything in json file that is surrounded by []
         */
        json.beginArray();
        // While it still has things encapsulated by []
        while (json.hasNext()) {
            // Just to keep the method shorter, passed work off to readPerson
            readPerson(json);
        }
        // After it has nothing left, close the file reader
        json.endArray();
        json.close();
    }

    /**
     *
     * @param json
     *      receives the open Json file to be read
     * @throws IOException
     *      handled in HomeScreen.java
     */
    private void readPerson(JsonReader json) throws IOException {
        // Looks at everything encapsulated by {}
        json.beginObject();
        while (json.hasNext()) {
            // Skips the next key, but pulls in value
            json.nextName(); String type = json.nextString();
            if (type.equals("Teacher")) {
                readTeacher(json);
            } else {
                readStudent(json);
            }
        }
        json.endObject();
    }

    /**
     *
     * @param json
     *      receives the open Json file to be read
     * @throws IOException
     *      handled in HomeScreen.java
     */
    private void readStudent(JsonReader json) throws IOException {
        json.nextName(); String fname = json.nextString();
        json.nextName(); String mname = json.nextString();
        json.nextName(); String lname = json.nextString();
        json.nextName(); String jagnumber = json.nextString();
        students.add(new Student(fname, mname, lname, jagnumber));
    }

    /**
     *
     * @param json
     *      receives the open Json file to be read
     * @throws IOException
     *      handled in HomeScreen.java
     */
    private void readTeacher(JsonReader json) throws IOException{
        json.nextName(); String fname = json.nextString();
        json.nextName(); String mname = json.nextString();
        json.nextName(); String lname = json.nextString();
        json.nextName(); String jagnumber = json.nextString();
        json.nextName(); String password = json.nextString();
        teachers.add(new Teacher(fname,mname, lname, jagnumber, password));
    }

    /**
     *
     * @return
     *      returns the list of teachers found in the Json file
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     *
     * @return
     *      returns the list of students found in the Json file
     */
    public List<Student> getStudents() {
        return students;
    }
}
