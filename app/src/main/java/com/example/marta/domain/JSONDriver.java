package com.example.marta.domain;

import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JSONDriver {

    private final JsonReader json;
    private List<Teacher> teachers;
    private List<Student> students;

    /**
     *
     * @param pathToJson
     *      take the path to the json file that contains the information of the class
     * @throws IOException
     *      handled in HomeScreen.java
     */
    public JSONDriver(InputStream pathToJson) throws IOException {
        /*
        Pulls in the path as InputStream because it is a super class of InputStreamReader
        and allows for a string to be pass as the filename.
         */
         json = new JsonReader(new InputStreamReader(pathToJson, "UTF-8"));
         // Makes sure for each new instance of JSONDriver it has a fresh list
         teachers = new ArrayList<>();
         students = new ArrayList<>();
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
        json.nextName();
        String type = json.nextString();
        if (type.equals("Teacher")) {
            readTeacher(json);
        }
        else {
            readStudent(json);
        }
        json.endObject();
    }

    private void readStudent(JsonReader json) throws IOException {
        json.nextName();
        String fname = json.nextString();
        json.nextName();
        String mname = json.nextString();
        json.nextName();
        String lname = json.nextString();
        json.nextName();
        String jagnumber = json.nextString();
        students.add(new Student(fname, mname, lname, jagnumber));
    }

    private void readTeacher(JsonReader json) throws IOException{
        json.nextName();
        String fname = json.nextString();
        json.nextName();
        String mname = json.nextString();
        json.nextName();
        String lname = json.nextString();
        json.nextName();
        String jagnumber = json.nextString();
        json.nextName();
        String password = json.nextString();
        teachers.add(new Teacher(fname,mname, lname, jagnumber, password));
    }

    /**
     *
     * @return
     *      returns the json file so it can be handled in different classes
     */
    public JsonReader getJson() {
        return this.json;
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
