package com.games.gobigorgohome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

class Map {
    // read .txt files in resource folder
    public void dataFromFile(String filePath) throws IOException {

        InputStream stream = SplashPage.class.getClassLoader().getResourceAsStream(filePath);
        if (stream == null) {
            throw new IllegalArgumentException("File Not Found");
        }
        List<String> lines = new ArrayList<String>();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while((line=streamReader.readLine())!=null) {
            lines.add(line);
            System.out.println(line);
        }
    }

    public void stringEditor(String currentRoomName) throws IOException {
        String[] stringArray = currentRoomName.split(" ");
        StringJoiner joiner = new StringJoiner("_");
        for (String s : stringArray) {
            joiner.add(s);
        }
        String roomName = joiner.toString();
        String filePath = "map/" + roomName + "_map.txt";

        dataFromFile(filePath);
    }

}