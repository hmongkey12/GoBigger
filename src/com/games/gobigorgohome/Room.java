package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Room {

    String name;
    JSONObject room;
    JSONArray items;
    JSONObject exercises;
    JSONObject npcsJSON;
    String npc_type = (String) npcsJSON.get(0);
    NPC npc = new NPC(npc_type);
    ParseJSON aParser = new ParseJSON();

    public Room(JSONObject room) throws IOException, ParseException {
        this.name = aParser.getObjectNameFromJSONObject(room);
        this.items = aParser.getJSONArrayFromJSONObject(room,"items");
        this.exercises = (JSONObject) room.get("exercises");
        this.npcsJSON = aParser.getJSONObjectFromJSONObject( room, "NPCS");
    }

}