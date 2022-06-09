package com.games.gobigorgohome;

import com.apps.util.Console;
import com.apps.util.Prompter;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Game {

    boolean isGameOver = false;
    private Player player;
    private int energy = player.getEnergy();
    private int currentEnergy = player.getEnergy();
    private String playerName = player.getName();
    private JSONObject currentRoom;
    private JSONObject rooms = Gym.getRooms();
    private Prompter prompter;


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
//        System.out.println("You are in the " + getCurrentRoom());
        System.out.println(player.getName() + ", you have " + player.getInventory() + " in your gym bag.");
        System.out.println("Your current energy level is " + getCurrentEnergy() + " out of " + getEnergy());
        System.out.println("------------------------------");
    }

    //    main function running the game, here we call all other functions necessary to run the game
    private void playGame(){
        // runs a while loop
        while (!isGameOver()) {
            // call functions
            validateInput();
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
        // Integer[] numbers = new Integer[] { 1, 2, 3 };
        List<String> actionList = Arrays.asList(action);
        // i would need to forEach the array to lowercase them so that

        if (actionList.get(0).equals("get")){
            //this is where the information gets returned for the item
            System.out.println("you got the :"+actionList.get(1));
            player.getInventory().add(actionList.get(1));
            return actionList.get(1);
            //or a callback function is passed
        }else if(actionList.get(0).equals("go")){
            String room = actionList.get(1);
            setCurrentRoom(rooms.get(room));
            System.out.println("you're going here: "+actionList.get(1));
            return actionList.get(1);
        }else if(actionList.get(0).equals("use")){
            System.out.println("you're using the: "+actionList.get(1));
            Map<String, Object> bodyAndMachineEnergy = currentRoom.get(actionList.get(1));
            String muscleGroup = bodyAndMachineEnergy.get("target muscle")[0];
            int energy = (int)bodyAndMachineEnergy.get("energy cost");
            String status = bodyAndMachineEnergy.get("status");
            return actionList.get(1);
        }else if(actionList.get(0).equals("consume")){
            System.out.println("you're consuming the: "+actionList.get(1));
            player.useItem(actionList.get(1));
            return actionList.get(1);
        }

        return actionList.get(1);
    }

    //    returns whether game is over or not.
    private void isGameOver(boolean currentGameStatus){
            setGameOver(currentGameStatus);
    }

    //    gives player ability to quit
    private void quit(){}

    //    start a new game
    private void newGame(){}

    public String getCurrentRoom() {
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


