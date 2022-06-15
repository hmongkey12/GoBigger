package com.games.gobigorgohome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NPC {
    private ParseJSON jsonParser = new ParseJSON();

    private JSONObject theObject = jsonParser.getJSONObjectFromFile("/Users/ressameo/Desktop/GoBigGoHome/resources/NPCwName.json");
    private JSONObject characters = jsonParser.getJSONObjectFromJSONObject(theObject,"characters");


//
    private String npcName;
    private String npcLocation;
    private JSONArray inventory;//convert this to an Array
    private JSONArray phrases;


    public NPC( String name) throws IOException, ParseException {

        this.npcName = returnName((JSONObject) characters.get(name));
        this.inventory = returnInventory((JSONObject) characters.get(name));
        this.npcLocation = returnLocation((JSONObject) characters.get(name));
        this.phrases = returnPhrases((JSONObject) characters.get(name));
    }

    private String returnName(JSONObject character){
        String person = (String)character.get("name");
        return person;
    }
    private String returnLocation (JSONObject character){
        String locale = (String)character.get("location");
        return locale;
    }

    private JSONArray returnInventory( JSONObject character){
        JSONArray inventory = (JSONArray)character.get("inventory");

        System.out.println(inventory.get(0));
        return inventory;
    }

    private JSONArray returnPhrases( JSONObject character){
        JSONArray phrases = (JSONArray)character.get("dialog");
        return phrases;
    }

    public static void main(String[] args) throws IOException, ParseException {
        NPC manager = new NPC("maintenance guy");
        System.out.println(manager.getNpcName());
        System.out.println(manager.getNpcLocation());
        System.out.println(manager.getInventory());
        System.out.println(manager.getPhrases());
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