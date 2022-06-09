package com.games.gobigorgohome;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class Gym {

    public void setRooms(JSONObject rooms) {
        this.rooms = rooms;
    }

    private JSONObject rooms;
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader("C:\\AmazonSDE\\Practical\\prac1\\GoBigGoHome\\gymRooms.json"));


    private void getCurrentRoom(String room){
        rooms.get("room");
    }
    private JSONObject getRooms(){
        JSONObject jo = (JSONObject) obj;
        // getting every room
        JSONObject rooms = (JSONObject) jo.get("rooms");
        return rooms;
    }
}








