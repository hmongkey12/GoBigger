package com.games.gobigorgohome;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class Gym {
    private ParseJSON jsonParser = new ParseJSON();

    private JSONObject rooms = jsonParser.getJSONObjectFromFile("/Users/marucard/StudentWork/practical_java/project/resources/gym_rooms.json");
//    private String starterRoomName = "front desk";
    private JSONObject starterRoom = jsonParser.getJSONObjectFromJSONObject(getRooms(), "front desk");
    private String starterRoomName = jsonParser.getObjectNameFromJSONObject(starterRoom);

    public Gym() throws IOException, ParseException {

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








