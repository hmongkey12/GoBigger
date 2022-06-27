package com.games.gobigorgohome.parsers;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class ParseTxt {

    public StringBuilder dataFromFile(String filePath) {

        InputStream stream = ParseTxt.class.getClassLoader().getResourceAsStream(filePath);
        StringBuilder temp = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))){
            String line = null;
            while((line=reader.readLine())!=null) {
                   temp.append(line);
                   temp.append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp;

    }

    public String instructions()  {
        return ""+ dataFromFile("instructions.txt");
    }

}


