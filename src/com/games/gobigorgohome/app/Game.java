package com.games.gobigorgohome.app;

import com.apps.util.Prompter;
import com.games.gobigorgohome.*;
import com.games.gobigorgohome.characters.Player;
import com.games.gobigorgohome.parsers.ParseJSON;
import com.games.gobigorgohome.parsers.ParseTxt;
import com.games.gobigorgohome.parsers.SoundHandler;
import org.json.simple.JSONArray;
import com.apps.util.Console;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Game {

    boolean isGameOver = false;
    private final Gym gym = Gym.getInstance();
    private Player player = new Player();
    private final int energy = player.getEnergy();
    private final int currentEnergy = player.getEnergy();
    private final String playerName = player.getName();
    private String currentRoomName = gym.getStarterRoomName();
    private final String musicPath = "resources/gainz.wav";
    private Room currentRoom = gym.getStarterRoom();
    private final Object rooms = gym.getRooms();
    private final Prompter prompter;
    private final ParseTxt page = new ParseTxt();
    private final ParseJSON jsonParser = new ParseJSON();
    private  JFrame frame;
    private GameMap gamemap = new GameMap(gym.getStarterRoomName());
    private SoundHandler soundHandler = new SoundHandler();
    PlayerBody playerBody;
    Container container;
    JPanel gameTextArea;
    JPanel mapPanel;
    JPanel imagePanel;
    JPanel userInput;
    JTextField textInput = new JTextField(20);
    JLabel instructionText;


    public Game(Prompter prompter) throws IOException, ParseException {
        this.prompter = prompter;
    }

    //    collects current input from user to update their avatar
    private void getNewPlayerInfo() {
//        TODO: validate user input
        String playerName = validName();
        double playerHeight = validDouble("What is your height? ", "height", "inches");
        double playerWeight = validDouble("What is your weight? ", "weight", "lbs");
        int playerAge = validInt("What is your age ", "age", "years");
        createPlayer(playerName, playerAge, playerHeight, playerWeight);
    }

    // validates name requesting one and rejecting empty space(s).
    private String validName() {
        String playerName = prompter.prompt("What is your name? ");
        if (playerName.isBlank() || playerName.isEmpty() || playerName.length() > 16) {
            try {
                System.out.println("You need to type your name or it exceeds 16 characters: ");
                validName();
            } catch (NullPointerException e) {
                System.out.println("You need to type your name: ");
                validName();
            }
        } else {
            System.out.println("Hello " + playerName + " let's get more about you...");
        }
        return playerName;
    }

    // validates height and weight taking integers or doubles only
    private double validDouble(String msg, String measureName, String unit) {
        String measurementString = prompter.prompt(msg);
        double measurement = 0;
        try {
            measurement = Double.parseDouble(measurementString);
            //validDouble(measure, "you need to type your " + measureName + " in " + unit + ": ", measureName, unit);
        } catch (NumberFormatException | NullPointerException e) {
            return validDouble("You need to type your " + measureName + " using numbers (" + unit + "): ", measureName, unit);
        }
        return measurement;
    }

    // validates age taking only an integer
    private int validInt(String msg, String measureName, String unit) {
        String measurement = prompter.prompt(msg);
        int measureNum = 0;
        try {
            measureNum = Integer.parseInt(measurement);
            //validInt(measure, "you need to type your "+ measureName+" in " + unit + " or you aren't an adult: ", measureName, unit);
        } catch (NumberFormatException e) {
            return validInt("You need to type your " + measureName + " using numbers integers (" + unit + "): ", measureName, unit);
        }
        return measureNum;
    }

    private void createPlayer(String playerName, int playerAge, double playerHeight, double playerWeight) {
        player.setName(playerName);
        player.setAge(playerAge);
        player.setHeight(playerHeight);
        player.setWeight(playerWeight);
    }

    //    updates player with current game status e.g. player inventory, current room etc.
    private void gameStatus() {
        System.out.println("------------------------------");
        System.out.println("Available commands: GO <room name>, GET <item>, CONSUME <item>, SEE MAP, WORKOUT <workout name>, INSPECT ROOM");
        System.out.println("You are in the " + currentRoomName + " room.");
        System.out.println(player.toString());
        System.out.println("------------------------------");
    }

    //    main function running the game, here we call all other functions necessary to run the game
    public void playGame() throws IOException, ParseException {
        isGameOver=false;

        MainFrame();
//        TODO: UNCOMMENT LINE BELOW BEFORE RELEASE!
        soundHandler.RunMusic(musicPath);

        System.out.println(page.instructions());
        getNewPlayerInfo();
        // runs a while loop
        while (!isGameOver()) {
//            gameStatus();
//            promptForPlayerInput();
            if (checkGameStatus()) {
                break;
            }
        }
        gameResult();
        frame.dispose();
    }


    private boolean checkGameStatus() {
        return player.isWorkoutComplete() || player.isSteroidsUsed() || player.isExhausted();
    }

    private void gameResult() {
        Console.clear();
        String result = "";
        if (player.isSteroidsUsed()) {
            result = "YOU ARE A LOSER AND A CHEATER!";
        } else if (player.isExhausted()) {
            result = "You're too tired, go home dude";
        } else if (player.isWorkoutComplete()) {
            result = "CONGRATULATIONS! YOU WORKED OUT!";
        }
        System.out.println(result);

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

        if (actionList.size() >= 1) {
            actionPrefix = actionList.get(0);
        }
        if (actionList.size() == 2) {
            playerAction = actionList.get(1);
        } else if (actionList.size() == 3) {
            playerAction = (actionList.get(1) + " " + actionList.get(2));
        }

        validatePlayerCommands(actionPrefix.toLowerCase(), playerAction.toLowerCase());
    }

    private void validatePlayerCommands(String actionPrefix, String playerAction) throws IOException, ParseException {
        try {
            switch (actionPrefix) {
                case "get":
                    grabItem(playerAction);
                    break;
                case "go":
                    Console.clear();
                    System.out.println("you're going here: " + playerAction);
                    currentRoomName = playerAction;
                    setCurrentRoom(jsonParser.getObjectFromJSONObject(rooms, playerAction));
                    repaintMap();
                    break;
                case "workout":
                    playerUseMachine(playerAction);
                    break;
                case "consume":
                    if (player.consumeItem(playerAction)) {
                        player.removeItemFromInventory(playerAction);
                    }
                    break;
                case "inspect":
                    inspectRoom();
                    break;
                case "talk":
                    talkToNPC();
                    break;
                case "see":
                    getRoomMap();
                    break;
                case "q":
                    quit();
                    break;
                case "new":
                    newGame();
                    break;
                case "up":
                    soundHandler.volumeUp();
                    break;
                case "down":
                    soundHandler.volumeDown();
                    break;
            }
        } catch (Exception exception) {
//            TODO: add array with possible values for commands
            System.out.println(actionPrefix + " was sadly and invalid answer. \n please ensure you are using a valid and complete command. ");
//            TODO: fix bug caused by pressing enter where prompt for player does not work and calls inspect
            promptForPlayerInput();
        }
    }

    private void newGame() throws IOException, ParseException {
        frame.setVisible(false);
        //reset the map
        currentRoomName = "front desk";
        //used to reset the body
        player=null;
        repaintPlayerBody();
        repaintMap();
        isGameOver=true;
        playGame();

    }

    public static boolean isItemRequired(List items) {
        return !"none".equals(items.get(0));
    }

    private void getRoomMap() throws IOException {
        currentRoom.getRoomMap(currentRoomName);
    }

    private void handleInput(String input) throws IOException, ParseException {
            gameStatus();
            String[] commandArr = input.split(" ");
            parseThroughPlayerInput(commandArr);

    }

    private void talkToNPC() {
        String dialog = currentRoom.getNpc().generateDialog();
        System.out.println(dialog);

        String npcItem = (String) currentRoom.npc.getInventory().get(0);

        player.getInventory().add(npcItem);
        System.out.println("You added " + npcItem + " to your gym bag.");
    }

    private void inspectRoom() {
        System.out.println(currentRoom.toString());
    }

    private void playerUseMachine(String playerExcerciseInput) {
        System.out.println("you're using the: " + playerExcerciseInput);
        Object exercises = getCurrentRoom().getExercises();

        Exercise exercise = new Exercise(exercises, playerExcerciseInput);
        Object targetMuscle = exercise.getTargetMuscles();
        String exerciseStatus = exercise.getExerciseStatus();
        Long energyCost = exercise.getEnergyCost();

        if ("fixed".equals(exerciseStatus)) {
            player.workout(targetMuscle, energyCost);
            player.subtractFromPlayerEnergy(Math.toIntExact(energyCost));
            repaintPlayerBody();
        } else {
            fixBrokenMachine(targetMuscle, energyCost);

        }
    }

    private void fixBrokenMachine(Object targetMuscle, Long energyCost) {
        if (player.getInventory().contains("wrench")) {
            String playerResponse = prompter.prompt("This machine is broken. Would you like to use your wrench to fix it? (y/n) \n >");
            if ("y".equalsIgnoreCase(playerResponse)) {
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

    private void grabItem(String playerAction) {
        final String[] currentItem = new String[1];
        JSONArray roomItemsObjectArray = (JSONArray) currentRoom.getItems();
        roomItemsObjectArray.forEach(item -> {
            if ( item.equals(playerAction)) {
                System.out.println("Item equals playerAction");
                currentItem[0] = (String) item;
            }
        });

        try {
            if (currentItem[0].equals(playerAction)) {
                System.out.println("\nYou got the :" + playerAction);
                player.getInventory().add(playerAction);
            }
        }
        catch (Exception e) {
            System.out.println("\nSorry, you cant can't GET " + playerAction.toUpperCase() + ". Try again!");
        }
    }

    private void repaintPlayerBody(){
        frame.remove(playerBody);
        playerBody = new PlayerBody(getMuscleGroups(player));
        frame.add(playerBody, 2);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    private void repaintMap(){
        frame.remove(gamemap);
        gamemap = new GameMap(currentRoomName);
        frame.add(gamemap, 1);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    public void MainFrame(){
        //frame Setting
        frame = new JFrame("Go Big Or Go Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLayout(new GridLayout(2,2));
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        container = frame.getContentPane();

        //three panels
        gameTextArea = new JPanel();
        mapPanel=new JPanel();
        imagePanel=new JPanel();
        playerBody = new PlayerBody(getMuscleGroups(player));
        playerBody.setPanelSize(frame.getWidth()/2, frame.getHeight()/2);

        userInput = new JPanel();
        userInput.add(textInput);
        //set map
        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            try {
                handleInput(textInput.getText());
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        });
        userInput.add(submitBtn);

        mapPanel.setBackground(Color.RED);
        mapPanel.setBounds(500,0,200,200);
        mapPanel.add(gamemap);


        //set image
        imagePanel.setBackground(Color.YELLOW);

        //setTextArea
        gameTextArea.setBackground(Color.WHITE);

        //set userInput
        userInput.setBackground(Color.GREEN);

        //Set text within text area
        JTextArea wrapperText =new JTextArea(page.instructions(),16,50);
        wrapperText.setWrapStyleWord(true);
        wrapperText.setLineWrap(true);
        wrapperText.setOpaque(false);
        wrapperText.setEditable(false);
        wrapperText.setFocusable(false);
        wrapperText.setBackground(UIManager.getColor("Label.background"));
        wrapperText.setFont(UIManager.getFont("Label.font"));
        wrapperText.setBorder(UIManager.getBorder("Label.border"));
        wrapperText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scroll = new JScrollPane(wrapperText);


        //add text to
        gameTextArea.add(scroll);

        //add components to container
        container.add(gameTextArea);
        container.add(gamemap);

        container.add(playerBody);
        container.add(userInput);

        frame.setResizable(false);
        frame.invalidate();
        frame.validate();
        frame.repaint();

    }

    //    gives player ability to quit
    private void quit() {
        System.out.println("--------------------------------------\n"
                + " YOU ARE A QUITTER!! GAME OVER" + "" +
                "------------------------------------");
        soundHandler.stopMusic();
        System.exit(0);
    }

    private void setCurrentRoom(Object currentRoom) {

        this.currentRoom = new Room(currentRoom);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    // accessor methods

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

    public boolean[] getMuscleGroups(Player player){
        boolean[] muscleGroup = new boolean[6];
        if(player == null){
            return muscleGroup;
        }
        if(player.isLegsWorked()) {
            muscleGroup[0]  = true;
        }
        if(player.isBackWorked()){
            muscleGroup[1] = true;
        }
        if(player.isChestWorked()){
            muscleGroup[2] = true;
        }
        if(player.isCoreWorked()){
            muscleGroup[3] = true;
        }
        if(player.isShoulderWorked()){
            muscleGroup[4] = true;
        }
        if(player.isTricepsWorked()){
            muscleGroup[5] = true;
        }
        return muscleGroup;
    }

}


