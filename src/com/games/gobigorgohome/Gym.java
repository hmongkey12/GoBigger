package com.games.gobigorgohome;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class Gym {

    public Gym() throws IOException, ParseException {
    }

    public static Gym getInstance() throws IOException, ParseException {
        return new Gym();
    }

    public void setRooms(JSONObject rooms) {
        this.rooms = rooms;
    }

    private JSONObject rooms;
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader("resources/gym_rooms.json"));


    private void getCurrentRoom(String room){
        rooms.get("room");
    }
    private JSONObject getRooms(){
        JSONObject jo = (JSONObject) obj;
        // getting every room
        return (JSONObject) jo.get("rooms");
    }
}








