package com.example.marta.domain;

import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        String type = "", fname = "", mname = "", lname = "", jagnumber = "", password = "";
        // While there is anything left to read in {}
        while (json.hasNext()) {
            // Pulls the next key or string defined on left side
            String key = json.nextName();
            // Lowers the casing for better checking
            switch (key.toLowerCase()) {
                case "class":
                    type = json.nextString();
                    break;
                case "firstname":
                    fname = json.nextString();
                    break;
                case "middlename":
                    mname = json.nextString();
                    break;
                case "lastname":
                    lname = json.nextString();
                    break;
                case "jagnumber":
                    jagnumber = json.nextString();
                    break;
                case "passcode":
                    password = json.nextString();
                    break;
                default:
                    // Just here in case
                    json.skipValue();
                    break;
            }
            // If teacher, add to teachers
            if (type.toLowerCase().equals("teacher")) {
                this.teachers
                        .add(new Teacher(fname, mname, lname, jagnumber, password));
            }
            // If student, add to students
            else if (type.toLowerCase().equals("student")) {
                this.students
                        .add(new Student(fname, mname, lname, jagnumber));
            }
        }
        json.endObject();
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
