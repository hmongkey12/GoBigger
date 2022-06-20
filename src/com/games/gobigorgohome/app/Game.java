package com.games.gobigorgohome.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.games.gobigorgohome.*;
import com.games.gobigorgohome.characters.Player;
import com.games.gobigorgohome.parsers.ParseJSON;
import com.games.gobigorgohome.parsers.ParseTxt;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Game {

    boolean isGameOver = false;
    private final Gym gym = Gym.getInstance();
    private final Player player = new Player();
    private final int energy = player.getEnergy();
    private final int currentEnergy = player.getEnergy();
    private final String playerName = player.getName();
    private String currentRoomName = gym.getStarterRoomName();
    private Room currentRoom = gym.getStarterRoom();
    private final JSONObject rooms = gym.getRooms();
    private final Prompter prompter;
    private final ParseTxt page = new ParseTxt();
    private final ParseJSON jsonParser = new ParseJSON();

    public Game(Prompter prompter) throws IOException, ParseException {
        this.prompter = prompter;
    }

    //    collects current input from user to update their avatar
    private void getNewPlayerInfo(){
//        TODO: validate user input
        String playerName = prompter.prompt("What is your name? ");
        double playerHeight = Double.parseDouble(prompter.prompt("What is your height? "));
        double playerWeight = Double.parseDouble(prompter.prompt("What is your weight? "));
        int playerAge = Integer.parseInt(prompter.prompt("What is your age? "));
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
        System.out.println("You are in the " + currentRoomName + " room.");
        System.out.println(player.toString());
        System.out.println("------------------------------");
    }

    //    main function running the game, here we call all other functions necessary to run the game
    public void playGame() throws IOException, ParseException {
        page.instructions();
        getNewPlayerInfo();
        // runs a while loop
        while (!isGameOver()) {
            gameStatus();
            Console.clear();
            promptForPlayerInput();
            if (checkGameStatus()){
                break;
            }
            updateGame();
        }
        gameResult();
    }

    private void updateGame() {
    }

    private boolean checkGameStatus() {
        return player.isWorkoutComplete();
    }
    
    private void gameResult() {
        Console.clear();
        System.out.println("Game over");
    }

    public void promptForPlayerInput() throws IOException, ParseException {
        String command = prompter.prompt("(Hit Q to quit) What is your move? ");
        String[] commandArr = command.split(" ");
        parseThroughPlayerInput(commandArr);
    }

    public void parseThroughPlayerInput(String[] action) throws IOException, ParseException {

        List<String> actionList = Arrays.asList(action);

        String actionPrefix = "";
        String playerAction = "";

        if(actionList.size() >= 1) {
            actionPrefix = actionList.get(0);
        }
        if(actionList.size() == 2){
            playerAction = actionList.get(1);
        } else if(actionList.size() == 3){
            playerAction = (actionList.get(1) + " " + actionList.get(2)) ;
        }

        validatePlayerCommands(actionPrefix.toLowerCase(), playerAction.toLowerCase());
    }

    private void validatePlayerCommands(String actionPrefix, String playerAction) throws IOException, ParseException {
        try {
            if (actionPrefix.equals("get")){
                grabItem(playerAction);

            }else if(actionPrefix.equals("go")){
                System.out.println("you're going here: "+ playerAction);
                currentRoomName = playerAction;
                setCurrentRoom(rooms.get(playerAction));

            }else if(actionPrefix.equals("workout")){
                playerUseMachine(playerAction);

            }else if(actionPrefix.equals("use")) {
                boolean isItemRequired = isItemRequired(playerAction, currentRoom);
                if (player.useItem(playerAction, isItemRequired)) {
                    player.removeItemFromInventory(playerAction);
                }

            } else if(actionPrefix.equals("consume")){
                if (player.consumeItem(playerAction)) {
                    player.removeItemFromInventory(playerAction);
                }

            } else if(actionPrefix.equals("inspect")){
                inspectRoom();
            } else if(actionPrefix.equals("talk")){
                talkToNPC();
            } else if(actionPrefix.equals("see")){
                getRoomMap();
            } else if(actionPrefix.equals("q")) {
                quit();
            }
        } catch (Exception exception) {
//            TODO: add array with possible values for commands
            System.out.println(actionPrefix + " was sadly and invalid answer. \n please ensure you are using a valid and complete command. " );
//            TODO: fix bug caused by pressing enter where prompt for player does not work and calls inspect
            promptForPlayerInput();
        }
    }

    public boolean isItemRequired(String item, Room room) {
        boolean result = false;
        JSONArray required_items = room.getRequiredItems();

        if (required_items.contains(item)) {
            result = true;
        }
        return result;
    }
    
    private void getRoomMap() throws IOException {
        currentRoom.getRoomMap(currentRoomName);
    }

    private void talkToNPC() {
        String dialog = currentRoom.getNpc().generateDialog();
        System.out.println(dialog);

        String npcItem = (String) currentRoom.npc.getInventory().get(0);

        player.getInventory().add(npcItem);
        System.out.println("You addded " + npcItem + " to your gym bag.");
    }

    private void inspectRoom() {
        System.out.println(currentRoom.toString());
    }

    private void playerUseMachine(String playerExcerciseInput) throws FileNotFoundException {
        System.out.println("you're using the: "+ playerExcerciseInput);
        JSONObject exercises = getCurrentRoom().getExercises();

        Exercise exercise = new Exercise(exercises, playerExcerciseInput);
        JSONArray targetMuscle = exercise.getTargetMuscles();
        String exerciseStatus = exercise.getExerciseStatus();
        Long energyCost = exercise.getEnergyCost();

        if ("fixed".equals(exerciseStatus)) {
            player.workout(targetMuscle, energyCost);
            player.subtractFromPlayerEnergy(Math.toIntExact(energyCost));
        } else {
            if(player.getInventory().contains("wrench")){
                String playerResponse = prompter.prompt("This machine is broken. Would you like to use your wrench to fix it? (y/n) \n >" );
                if("y".equalsIgnoreCase(playerResponse)) {
                    player.getInventory().remove("wrench");
                    player.workout(targetMuscle, energyCost);
                    player.subtractFromPlayerEnergy(Math.toIntExact(energyCost));
                } else {
                    System.out.println("When you are ready to workout, come back with the wrench and get to it.");
                }
            } else {
                System.out.println("This machine is broken, please come back with a wrench to fix it.");
            }
        }
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
    private void quit(){
        System.out.println("--------------------------------------\n"
                + " YOU ARE A QUITTER!! GAME OVER" + "" +
                "------------------------------------");
        System.exit(0);
    }

    private void setCurrentRoom(Object currentRoom) throws IOException, ParseException {

        this.currentRoom = new Room((JSONObject) currentRoom) ;
    }

    public Room getCurrentRoom() {
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

}


