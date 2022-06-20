package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class NPC {
    private final ParseJSON jsonParser = new ParseJSON();

    private final JSONObject theObject = jsonParser.getJSONObjectFromFile("NPCwName.json");

    private String npc_type;

    private final String npcName ;
    private final String npcLocation ;
    private final JSONArray inventory ; //convert this to an Array
    private final JSONArray phrases;


    public NPC(String npc_type) throws IOException, ParseException {

        JSONObject npcs = jsonParser.getJSONObjectFromJSONObject(theObject, "characters");
        JSONObject npc = (JSONObject) npcs.get(npc_type);
        this.npc_type = npc_type;
        this.npcName = (String) npc.get("name");
        this.npcLocation = (String) npc.get("location");
        this.inventory = (JSONArray) npc.get("inventory");
        this.phrases = (JSONArray) npc.get("dialog");
    }

    public String generateDialog() {
        int  index = (int) (Math.random() * getPhrases().size());
        return (String) getPhrases().get(index);
    }

    public String getNpcName() {
        return npcName;
    }

    public JSONArray getInventory() {
        return inventory;
    }

    public JSONArray getPhrases() {
        return phrases;
    }

}