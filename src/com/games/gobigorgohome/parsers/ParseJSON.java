package com.games.gobigorgohome.parsers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

public class ParseJSON {

    //    takes a file path string and returns a JSONObject
    public Object getJSONObjectFromFile(String filePath) {
//        return (JSONObject) parser.parse(new FileReader(filePath));
        InputStream inputTestJSON = getFileFromResourceAsStream(filePath);
        JSONParser jsonParser = new JSONParser();
        //JSONObject jsonObject = null;
        Object result = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputTestJSON, "UTF-8"))){
            result = jsonParser.parse(reader);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static InputStream getFileFromResourceAsStream(String filePath) {
        ClassLoader classLoader = ParseJSON.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        } else {
            return inputStream;
        }
    }

    public Object getObjectFromJSONObject(Object object, String objectName) {
        JSONObject jsonObject = (JSONObject) object;
        if (jsonObject == null){
            return null;
        }
        return jsonObject.get(objectName);
    }

    //    takes a jsonObject and returns a string with the object name
    public String getObjectStringFromJSONObject(Object objectName, String string) {
        JSONObject jsonObjectName = (JSONObject) objectName;
        return (String) jsonObjectName.get(string);
    }

    public Long getLongFromJSONObject(Object objectName, String string) {
        JSONObject jsonObjectName = (JSONObject) objectName;
        return (Long) jsonObjectName.get(string);
    }

    public Object getObjectFromJSON(Object object, String objectString) {
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject.get(objectString);
    }

    public Set<Object> getKeySetFromJSONObject(Object object) {
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject.keySet();
    }

    public List<Object> getKeySetFromJSONArray(Object object) {
        return (JSONArray) object;
    }

    public String getStringValueFromIndexInJSONArray(Object object, int index) {
        JSONArray jsonArray = (JSONArray) object;
        return (String) jsonArray.get(index);
    }
}