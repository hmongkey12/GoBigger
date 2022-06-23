package com.games.gobigorgohome.parsers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ParseTxt {

    public void dataFromFile(String filePath) throws IOException {

        InputStream stream = ParseTxt.class.getClassLoader().getResourceAsStream(filePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"))){
            String line = null;
            while((line=reader.readLine())!=null) {
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public void instructions() throws IOException {
        //String bannerPath = "banner.txt";
        dataFromFile("banner.txt");
        System.out.println("\033[33;1;2mWELCOME TO 'GO BIG OR GO HOME! v.1.1'\033[0m\nA game by \033[33;1;2mGAINZZZ Productions Refactored by Pizza Pals\033[0m where you maybe learn " +
                "to use your gym time wisely and get BIG!");
        System.out.println("\033[31;4;1mINSTRUCTIONS:\033[0m");
        dataFromFile("instructions.txt");
        System.out.println("\033[31;4;1m!!!IMPORTANT!!!:\033[0m");
        dataFromFile("instructions2.txt");

    }

}


