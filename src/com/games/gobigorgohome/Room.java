package com.games.gobigorgohome;

import com.games.gobigorgohome.characters.NPC;
import com.games.gobigorgohome.parsers.ParseJSON;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Room {
    private final ParseJSON jsonParser = new ParseJSON();

    private final String roomName;
    private final Object items;
    private final Object exercises;

    private final String npc_type;
    private final List requiredItems;
    public NPC npc;
    private final Map roomMap = new Map();

    public Room(Object room) {
        this.roomName = jsonParser.getObjectStringFromJSONObject(room, "name");


        this.items = jsonParser.getObjectFromJSONObject(room, "items");
        this.exercises = jsonParser.getObjectFromJSON(room, "exercises");

//        this.directions = jsonParser.getJSONArrayFromJSONObject(room,"directions");

        Object npcTypeObject = jsonParser.getObjectFromJSONObject(room, "NPCS");
        this.npc_type = jsonParser.getStringValueFromIndexInJSONArray(npcTypeObject, 0);

        Object requiredItemsObject = jsonParser.getObjectFromJSONObject(room, "required items");
        this.requiredItems = jsonParser.getKeySetFromJSONArray(requiredItemsObject);
        System.out.println(this.requiredItems);

        if (!"none".equals(npc_type)) {
            this.npc = new NPC(npc_type);
        }
    }

    public void getRoomMap(String roomName) throws IOException {
        roomMap.stringEditor(roomName);
    }

    public Object getItems() {
        return items;
    }

    public String getRoomName() {
        return roomName;
    }

    public Object getExercises() {
        return exercises;
    }

    //    TODO: determine the Set value types
    public Set getExerciseList() {
        return jsonParser.getKeySetFromJSONObject(getExercises());
    }

    public List getRequiredItems() {
        return requiredItems;
    }

    public String getNpc_type() {
        return npc_type;
    }

    public NPC getNpc() {
        return npc;
    }

    private String getValidNpc() {
        return getNpc() == null ? "No one" : getNpc().getNpcName();
    }

    @Override
    public String toString() {
        return "You are in " + getRoomName() + "\n" +
                "Exercises available are: " + getExerciseList() + "\n" +
                "You see: " + getItems() + "\n" +
                getValidNpc() + " is standing there with you.\n";
//        TODO: if adding direction limitations, call getDirections
    }
}