package com.games.gobigorgohome;

import com.games.gobigorgohome.characters.NPC;
import com.games.gobigorgohome.parsers.ParseJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Room {
    private ParseJSON jsonParser = new ParseJSON();

    private String roomName;
    private JSONArray items;
    private JSONObject exercises;

    private JSONArray directions;
    private String npc_type;
    private JSONArray requiredItems;
    public NPC npc;
    private Map roomMap = new Map();

    public Room(JSONObject room) throws IOException, ParseException {
        this.roomName = jsonParser.getObjectNameFromJSONObject(room);
        this.items = jsonParser.getJSONArrayFromJSONObject(room,"items");
        this.exercises = (JSONObject) room.get("exercises");
        this.directions = jsonParser.getJSONArrayFromJSONObject(room,"directions");
        this.npc_type = (String) jsonParser.getJSONArrayFromJSONObject( room, "NPCS").get(0);
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

    private String getValidNpc(){
        return getNpc() == null ? "No one" : getNpc().getNpcName();
    }

    @Override
    public String toString() {
        return "You are in " + getRoomName() + "\n" +
                "Exercises available are: " + getExercises().keySet() + "\n" +
                "You see: " + getItems() + "\n" +
                 getValidNpc() + " is standing there with you.\n";
//        TODO: if adding direction limitations, call getDirections
    }
}