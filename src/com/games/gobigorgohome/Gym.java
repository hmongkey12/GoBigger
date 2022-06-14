package com.games.gobigorgohome;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;


public class Gym {
    private JSONParser parser = new JSONParser();
    private Object objJSON = parser.parse(new FileReader("GoBigGoHome/resources/gym_rooms.json"));
    private JSONObject rooms = (JSONObject) objJSON;
    private String starterRoomName = "front desk";
    private JSONObject starterRoom = (JSONObject) getRooms().get("front desk");


    public Gym() throws IOException, ParseException {

    }

    public static Gym getInstance() throws IOException, ParseException {
        return new Gym();
    }

    public void setRooms(JSONObject rooms) {
        this.rooms = rooms;
    }


    private void getCurrentRoom(String room){
        rooms.get("room");
    }

    public JSONObject getRooms(){
        return (JSONObject) this.rooms.get("rooms");
    }

    public JSONObject getStarterRoom() {
        return starterRoom;
    }

    public String getStarterRoomName() {
        return starterRoomName;
    }
}








