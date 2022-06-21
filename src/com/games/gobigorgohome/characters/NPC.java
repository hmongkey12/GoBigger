package com.games.gobigorgohome.characters;

import com.games.gobigorgohome.parsers.ParseJSON;

import java.util.List;

public class NPC {

    private final String npcName;
    private final List inventory; //convert this to an Array
    private final List phrases;


    public NPC(String npc_type) {

        ParseJSON jsonParser = new ParseJSON();
        Object theObject = jsonParser.getJSONObjectFromFile("JSON/NPCwName.json");
        Object npcs = jsonParser.getObjectFromJSONObject(theObject, "characters");
        Object npc = jsonParser.getObjectFromJSONObject(npcs, npc_type);
        this.npcName = jsonParser.getObjectStringFromJSONObject(npc, "name");
        this.inventory = (List) jsonParser.getObjectFromJSONObject(npc, "inventory");
        this.phrases = (List) jsonParser.getObjectFromJSONObject(npc, "dialog");;
    }

    public String generateDialog() {
        int index = (int) (Math.random() * getPhrases().size());
        return (String) getPhrases().get(index);
    }

    public String getNpcName() {
        return npcName;
    }

    public List getInventory() {
        return inventory;
    }

    public List getPhrases() {
        return phrases;
    }

}