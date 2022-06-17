package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Room {
    ParseJSON aParser = new ParseJSON();

    String roomName;
    JSONArray items;
    JSONObject exercises;

    JSONArray directions;
    String npc_type;
    JSONArray requiredItems;
    NPC npc;
    Map roomMap = new Map();

    public Room(JSONObject room) throws IOException, ParseException {
        this.roomName = aParser.getObjectNameFromJSONObject(room);
        this.items = aParser.getJSONArrayFromJSONObject(room,"items");
        this.exercises = (JSONObject) room.get("exercises");
        this.directions = aParser.getJSONArrayFromJSONObject(room,"directions");
        this.npc_type = (String) aParser.getJSONArrayFromJSONObject( room, "NPCS").get(0);
        if(!"none".equals(npc_type)) {
            this.npc = new NPC(npc_type);
        }
    }

    public void getRoomMap(String roomName) throws IOException {
        roomMap.stringEditor(roomName);
    }

    public JSONArray getItems() {
        return items;
    }

    public String getRoomName() {
        return roomName;
    }

    public JSONObject getExercises() {
        return exercises;
    }

    public JSONArray getRequiredItems() {
        return requiredItems;
    }

    public JSONArray getDirections() {
        return directions;
    }

    public String getNpc_type() {
        return npc_type;
    }

    public NPC getNpc() {
        return npc;
    }
}