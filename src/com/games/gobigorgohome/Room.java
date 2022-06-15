package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;

public class Room {

    String name;
    JSONArray items;
    JSONObject exercises;
    ParseJSON aParser = new ParseJSON();


    public Room(JSONObject room) throws FileNotFoundException {
        this.name = aParser.getObjectNameFromJSONObject(room);
        this.items = aParser.getJSONArrayFromJSONObject(room,"items");
        this.exercises = (JSONObject) room.get("exercises");
    }

}