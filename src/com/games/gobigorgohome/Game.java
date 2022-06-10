package com.games.gobigorgohome;

import com.apps.util.Console;
import com.apps.util.Prompter;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game {

    boolean isGameOver = false;
    private final Gym gym = Gym.getInstance();
    private Player player;
    private int energy = player.getEnergy();
    private int currentEnergy = player.getEnergy();
    private String playerName = player.getName();
    private JSONObject currentRoom;
    private JSONObject rooms = gym.getRooms();
    private Prompter prompter;

    public Game(Prompter prompter) throws IOException, ParseException {
        this.prompter = prompter;
    }


    //    collects current input from user to update their avatar
    private void getNewPlayerInfo(){
//        TODO: validate user input
        String playerName = prompter.prompt("What is your name?");
        double playerHeight = Double.parseDouble(prompter.prompt("What is your height?"));
        double playerWeight = Double.parseDouble(prompter.prompt("What is your weight?"));
        int playerAge = Integer.parseInt(prompter.prompt("What is your age?"));
        createPlayer(playerName, playerAge, playerHeight, playerWeight);
    }

    private void createPlayer(String playerName, int playerAge, double playerHeight, double playerWeight) {
        player.setName(playerName);
        player.setAge(playerAge);
        player.setHeight(playerHeight);
        player.setWeight(playerWeight);
    }

    //    updates player with current game status e.g. player inventory, current room etc.
    private void gameStatus(){
        System.out.println("------------------------------");
        System.out.println("Player: " + getPlayerName());
        System.out.println("You are in the " + getCurrentRoom());
        System.out.println(player.getName() + ", you have " + player.getInventory() + " in your gym bag.");
        System.out.println("Your current energy level is " + getCurrentEnergy() + " out of " + getEnergy());
        System.out.println("------------------------------");
    }

    //    main function running the game, here we call all other functions necessary to run the game
    public void playGame(){
        getNewPlayerInfo();
        gameStatus();
        // runs a while loop
        while (!isGameOver()) {
            // call functions
            Console.clear();
            String[] command = promptForPlayerInput();
            parsingThroughStringValues(command);
            checkGameStatus();
            updateGame();
        }
        gameResult();
        playAgain();
    }

    private void checkGameStatus() {
    }

    private void playAgain() {
    }

    private void gameResult() {
        Console.clear();

    }
    //this method will handle the user's input for the action
//    and the according methods called for whatever action
    private void updateGame() {
    }


    private void validateInput() {
    }

    public String[] returningInputFromStringAsSplitArray(String command){

        String[] sArr = command.split(" ");
        // String s2 = in.nextLine();

        return sArr;

    }

    public String[] promptForPlayerInput(){
        String command = prompter.prompt("What is your move?");
        String[] commandArr = returningInputFromStringAsSplitArray(command);
        return commandArr;
    }

    public String parsingThroughStringValues(String[] action){

        List<String> actionList = Arrays.asList(action);
        String actionPrefix = actionList.get(0);
        String playerAction = actionList.get(1);

        if (actionPrefix.equals("get")){
            grabItem(playerAction);

        }else if(actionPrefix.equals("go")){
            System.out.println("you're going here: "+ playerAction);
            setCurrentRoom(rooms.get(playerAction));

        }else if(actionPrefix.equals("use")){
            playerUseMachine(playerAction);

            return actionList.get(1);
        }else if(actionList.get(0).equals("consume")){
            System.out.println("you're consuming the: "+actionList.get(1));
            player.useItem(actionList.get(1));
            return actionList.get(1);
        }

        return actionList.get(1);
    }

    private void playerUseMachine(String machine) {
        System.out.println("you're using the: "+ machine);

        Map<String, Object> bodyAndMachineEnergy = currentRoom.get(machine);

        String muscleGroup = bodyAndMachineEnergy.get("target muscle")[0];
        int energyCost = Integer.parseInt(bodyAndMachineEnergy.get("energy cost"));
        String machineStatus = bodyAndMachineEnergy.get("status");

        player.useMachine(muscleGroup, machineStatus, energyCost);
    }

    private void grabItem(String playerAction) {
        System.out.println("you got the :"+ playerAction);
        player.getInventory().add(playerAction);
    }

    //    returns whether game is over or not.
    private void isGameOver(boolean currentGameStatus){
            setGameOver(currentGameStatus);
    }

    //    gives player ability to quit
    private void quit(){}

    //    start a new game
    private void newGame(){}

    public JSONObject getCurrentRoom() {
        return currentRoom;
    }



    // accessor methods
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }


    public boolean isGameOver() {
        return isGameOver;
    }

    public int getEnergy() {
        return energy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Prompter getPrompter() {
        return prompter;
    }
}


