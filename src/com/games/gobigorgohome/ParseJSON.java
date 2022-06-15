package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

class ParseJSON {
    private JSONParser parser = new JSONParser();

    ParseJSON() throws FileNotFoundException {
    }

//    takes a file path string and returns a JSONObject
    public JSONObject getJSONObjectFromFile (String filePath) throws IOException, ParseException {
//        return (JSONObject) parser.parse(new FileReader(filePath));
        InputStream inputTestJSON = getFileFromResourceAsStream(filePath);
        JSONParser jsonParser = new JSONParser();
        //JSONObject jsonObject = null;
        JSONObject result = null;
        try {
            Reader jsonReader = new InputStreamReader(inputTestJSON, "UTF-8");
            Object obj = jsonParser.parse(jsonReader);
            JSONObject jsonObject = (JSONObject) obj;
            result = jsonObject;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private InputStream getFileFromResourceAsStream(String filePath) {
        ClassLoader classLoader = ParseJSON.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        } else {
            return inputStream;
        }
    }

    //    takes a Json object and a string and returns JSON object
    public JSONObject getJSONObjectFromJSONObject(JSONObject jsonObject, String objectName) {

        return (JSONObject) jsonObject.get(objectName);
    }

    public JSONArray getJSONArrayFromJSONObject(JSONObject jsonObject, String objectName) {
        return (JSONArray) jsonObject.get(objectName);
    }

//    takes a jsonObject and returns a string with the object name
    public String getObjectNameFromJSONObject(JSONObject jsonObject) {
        return (String) jsonObject.get("name");
    }
//    hard code values so that we can get certain objects from the individual.
}