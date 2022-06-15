package com.games.gobigorgohome;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class Gym {
    private ParseJSON jsonParser = new ParseJSON();
    private JSONObject rooms= null;
    private String starterRoomName = "front desk";
    private JSONObject starterRoom = null;

//    private String starterRoomName = "front desk";
//    private JSONObject starterRoom = jsonParser.getJSONObjectFromJSONObject(getRooms(), "front desk");
//    private String starterRoomName = jsonParser.getObjectNameFromJSONObject(starterRoom);

    public Gym() throws IOException, ParseException {
        String gymRoomFilePath = "JSON/gym_rooms.json";
        rooms = jsonParser.getJSONObjectFromFile(gymRoomFilePath);

    }

    public static Gym getInstance() throws IOException, ParseException {
        return new Gym();
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








