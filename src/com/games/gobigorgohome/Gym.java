package com.games.gobigorgohome;

import java.io.IOException;

import com.games.gobigorgohome.parsers.ParseJSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class Gym {
    private final Object rooms;
    private final String starterRoomName = "front desk";
    private final Room starterRoom;
    ParseJSON jsonParser = new ParseJSON();

    public Gym() {
        String gymRoomFilePath = "JSON/gym_rooms.json";
        ParseJSON jsonParser = new ParseJSON();
        rooms = jsonParser.getJSONObjectFromFile(gymRoomFilePath);
        starterRoom = new Room(jsonParser.getObjectFromJSONObject(getRooms(), getStarterRoomName()));

    }

    public static Gym getInstance() throws IOException, ParseException {
        return new Gym();
    }


    public Object getRooms(){
        return jsonParser.getObjectFromJSONObject(this.rooms, "rooms");
    }

    public Room getStarterRoom() {
        return starterRoom;
    }

    public String getStarterRoomName() {
        return starterRoomName;
    }
}








