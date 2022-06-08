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



    public static void main(String[] args) throws Exception {
        // parsing file "JSONExample.json"
        JSONParser parser = new JSONParser();
        try {
        Object obj = parser.parse(new FileReader("C:\\AmazonSDE\\Practical\\prac1\\GoBigGoHome\\gymRooms.json"));
            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;
            // getting every room
            JSONObject rooms = (JSONObject) jo.get("rooms");
            Object frontDesk = rooms.get("front desk");
            System.out.println(frontDesk);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getCurrentRoom(String room){
        rooms.get("room");






    }
}








