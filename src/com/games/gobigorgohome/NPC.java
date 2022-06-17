package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class NPC {
    private ParseJSON jsonParser = new ParseJSON();

    private JSONObject theObject = jsonParser.getJSONObjectFromFile("NPCwName.json");
    private JSONObject npcs = jsonParser.getJSONObjectFromJSONObject(theObject,"characters");

    private String npc_type;

    private JSONObject npc;
    private String npcName = (String) npc.get("name");
    private String npcLocation = (String) npc.get("location");
    private JSONArray inventory = (JSONArray) npc.get("inventory"); //convert this to an Array
    private JSONArray phrases = (JSONArray) npc.get("dialog");


    public NPC(String npc_type) throws IOException, ParseException {
        this.npc_type = npc_type;
        this.npc = (JSONObject) npcs.get(npc_type);
    }

//    private String returnName(JSONObject character){
//        String person = (String)character.get("name");
//        return person;
//    }
//
//    private String returnLocation (JSONObject character){
//        String locale = (String)character.get("location");
//        return locale;
//    }
//
//    private JSONArray returnInventory( JSONObject character){
//        JSONArray inventory = (JSONArray)character.get("inventory");
//
//        System.out.println(inventory.get(0));
//        return inventory;
//    }
//
//    private JSONArray returnPhrases( JSONObject character){
//        JSONArray phrases = (JSONArray)character.get("dialog");
//        return phrases;
//    }

    public JSONObject getNpc() {
        return npc;
    }

    public String getNpcName() {
        return npcName;
    }

    public String getNpcLocation() {
        return npcLocation;
    }

    public JSONArray getInventory() {
        return inventory;
    }

    public JSONArray getPhrases() {
        return phrases;
    }
}