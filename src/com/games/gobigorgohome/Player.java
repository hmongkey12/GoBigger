package com.games.gobigorgohome;

import java.util.ArrayList;
import java.util.List;

public class Player {
//    static variables
//    public final static int BASE_ENERGY = 50; //example not set in stone


//    fields
    int age;
    String name;
    // in the beginning our energy is going to be set to the static variable containing the energy, but then it will be incremented or decremented overtime with the accessor methods.
    int energy;
    double weight;
    List<String> inventory = new ArrayList<>();
//    just realize that the boolean values are named the same for the getters, idk why but they did it when I did it with the intellij autgenerated ones
    boolean isChestWorked;
    boolean isTricepsWorked;
    boolean isLegsWorked;
    boolean isBackWorked;
    boolean isCoreWorked;
    boolean isShoulderWorked;


//    constructor

    public Player(){
    }

    public Player(int age, String name, int weight) {
        this.age = age;
        this.name = name;
        this.weight = weight;
    }

//    business methods

//    we need to think about what values are being played around with or manipulated that can be tested?
    public void playerWorksOut(String machine){

    }

    public String moveRooms(String action){
        String newRoom = "default";
        return newRoom;
    }

    public String getItem() {
        // add item to the inventory

        String itemToGet = "default";
        return itemToGet;
    }

    public void useItem() {
        // use item from inventory e.g. wrench or key
        //maybe throw in logic that will validate it with truthy or falsey values
    }

    public void talkTo() {
        // talk to npc
    }

    public int addToPlayerEnergy(int energyToGive) {
        return energy + energyToGive;
    }

    public int subtractFromPLayerEnergy(int energyToTake) {
        return energy - energyToTake;
    }

    public void consumeItem(String item ) {
        // using steroids, drinking energy drink
        // if using steroid -> GAME OVER
//        if item.equals("steroid")-> gameover else; energy += 2 or whatever
    }


//    accessor methods

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public boolean isChestWorked() {
        return isChestWorked;
    }

    public void setChestWorked(boolean chestWorked) {
        isChestWorked = chestWorked;
    }

    public boolean isTricepsWorked() {
        return isTricepsWorked;
    }

    public void setTricepsWorked(boolean tricepsWorked) {
        isTricepsWorked = tricepsWorked;
    }

    public boolean isLegsWorked() {
        return isLegsWorked;
    }

    public void setLegsWorked(boolean legsWorked) {
        isLegsWorked = legsWorked;
    }

    public boolean isBackWorked() {
        return isBackWorked;
    }

    public void setBackWorked(boolean backWorked) {
        isBackWorked = backWorked;
    }

    public boolean isCoreWorked() {
        return isCoreWorked;
    }

    public void setCoreWorked(boolean coreWorked) {
        isCoreWorked = coreWorked;
    }

    public boolean isShoulderWorked() {
        return isShoulderWorked;
    }

    public void setShoulderWorked(boolean shoulderWorked) {
        isShoulderWorked = shoulderWorked;
    }


//    toString


    @Override
    public String toString() {
        return "Player{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", energy=" + energy +
                ", weight=" + weight +
                ", inventory=" + inventory +
                ", isChestWorked=" + isChestWorked +
                ", isTricepsWorked=" + isTricepsWorked +
                ", isLegsWorked=" + isLegsWorked +
                ", isBackWorked=" + isBackWorked +
                ", isCoreWorked=" + isCoreWorked +
                ", isShoulderWorked=" + isShoulderWorked +
                '}';
    }
}