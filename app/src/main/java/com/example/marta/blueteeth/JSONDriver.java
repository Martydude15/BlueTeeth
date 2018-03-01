package com.example.marta.blueteeth;

import android.os.Message;
import android.util.JsonReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class JSONDriver {

    private final JsonReader json;

    JSONDriver(InputStream pathToJson) throws IOException {
         json = new JsonReader(new InputStreamReader(pathToJson, "UTF-8"));
    }

    List<Person> getPeople(JsonReader json) throws IOException{
        List<Person> messages = new ArrayList<>();
        json.beginArray();
        while (json.hasNext()) {
            messages.add(readPerson(json));
        }
        json.endArray();
        return messages;
    }

    private Person readPerson(JsonReader json) throws IOException {
        String jagNumber = "";
        Boolean teacher = false;

        json.beginObject();
        while (json.hasNext()) {
            String key = json.nextName();
            switch (key) {
                case "JagNumber":
                    jagNumber = json.nextString();
                    break;
                case "Teacher":
                    teacher = (1 == json.nextInt());
                    break;
                default:
                    json.skipValue();
                    break;
            }
        }
        json.endObject();
        return new Person(jagNumber, teacher);
    }

    JsonReader getJson() {
        return this.json;
    }
}
