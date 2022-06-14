package com.games.gobigorgohome;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ParseJSON {
    private JSONParser parser = new JSONParser();

    ParseJSON() throws FileNotFoundException {
    }

//    takes a file path string and returns a JSONObject
    public JSONObject getJSONObjectFromFile (String filePath) throws IOException, ParseException {
        return (JSONObject) parser.parse(new FileReader(filePath));
    }

//    takes a Json object and a string and returns JSON object
    public JSONObject getJSONObjectFromJSONObject(JSONObject jsonObject, String objectName) {
        System.out.println(jsonObject);
        return (JSONObject) jsonObject.get(objectName);
    }

//    takes a jsonObject and returns a string with the object name
    public String getObjectNameFromJSONObject(JSONObject jsonObject) {
        return (String) jsonObject.get("name");
    }
}