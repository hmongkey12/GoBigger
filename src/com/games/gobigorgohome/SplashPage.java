package com.games.gobigorgohome;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class SplashPage {

    private void createAscii(String fileName) {
        try {
            Files.lines(Path.of(fileName))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void instructions(){
        createAscii("resources/banner.txt");
        System.out.println("\033[33;1;2mWELCOME TO 'GO BIG OR GO HOME! v.1.1'\033[0m\nA game by \033[33;1;2mGAINZZZ Productions\033[0m where you maybe learn " +
                "to use your gym time wisely and get BIG!");
        System.out.println("\033[31;4;1mINSTRUCTIONS:\033[0m");
        createAscii("resources/instructions.txt");
        System.out.println("\033[31;4;1m!!!IMPORTANT!!!:\033[0m");
        createAscii("resources/instructions2.txt");

    }
}


