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

    public JSONDriver(InputStream pathToJson) throws IOException {
         json = new JsonReader(new InputStreamReader(pathToJson, "UTF-8"));
         teachers = new ArrayList<>();
         students = new ArrayList<>();
    }

    public void getPeople(JsonReader json) throws IOException {
        json.beginArray();
        while (json.hasNext()) {
            readPerson(json);
        }
        json.endArray();
    }

    private void readPerson(JsonReader json) throws IOException {
        json.beginObject();
        String type = "", fname = "", mname = "", lname = "", jagnumber = "", password = "";
        while (json.hasNext()) {
            String key = json.nextName();
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
                    json.skipValue();
                    break;
            }
            if (type.toLowerCase().equals("teacher")) {
                this.teachers
                        .add(new Teacher(fname, mname, lname, jagnumber, password));
            }
            else if (type.toLowerCase().equals("student")) {
                this.students
                        .add(new Student(fname, mname, lname, jagnumber));
            }
        }
        json.endObject();
    }


    public JsonReader getJson() {
        return this.json;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }
}
