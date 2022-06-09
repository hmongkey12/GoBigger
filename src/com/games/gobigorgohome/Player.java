package com.games.gobigorgohome;

import java.util.ArrayList;
import java.util.List;

public class Player {
//    idea- creating a class that's parsing through the json? and putting certain information into lists mainly lists of the muscles and the exercises that work them out.
//    static variables
    public final static int BASE_ENERGY = 50; //example not set in stone


//    fields
    int age;
    String name;
    int energy;
    double weight;
    List<String> inventory = new ArrayList<>();
//    just realize that the boolean values are named the same for the getters, idk why but they did it when I did it with the intellij autgenerated ones
    boolean isChestWorked = false;
    boolean isTricepsWorked = false;
    boolean isLegsWorked = false;
    boolean isBackWorked = false;
    boolean isCoreWorked = false;
    boolean isShoulderWorked = false;

//    constructors

    public Player(){
    }

    public Player(int age, String name, int weight) {
        this.age = age;
        this.name = name;
        this.weight = weight;
        this.energy = BASE_ENERGY;
    }

    public Player(int age, String name, int weight, int energyInput) {
        this(age, name, weight);
        this.energy = energyInput;
    }

    //    business methods

//    we need to think about what values are being played around with or manipulated that can be tested?
    public void playerWorksOut(String machine, String bodyPart){
//        if(bodyPart.equals("chest")){
//
//        }
    }
//    i think this needs to be in the game class or the gym class because it relates to the item being returned in response to the rooms too
    public String moveRooms(String action){
        String newRoom = "default";
        return newRoom;
    }

    public String getItem(String item) {
        String itemToGet = "item not here";
//        mayybe this contains method could be a method itself.
        if(isItemInInventory(item)){
            itemToGet = inventory.get(inventory.indexOf(item));
        }
        return itemToGet;
    }

    private boolean isItemInInventory(String item){
        return getInventory().contains(item);
    }

    public String useItem(String item) {
        String response = " a default item";
        if(item.equals("energy drink")){
            consumeItem(item);
            response = item + " has been consumed";
//            item = getItem(item); one way of doing it
//            consumeItem(item);
            //get the item, return it
            //then pass it to the consum item
//            is it possible to combine these in an or statement, that way they both get returned either way?
        }else if(item.equals("key")){
            response = item;
        }else if(item.equals("wrench")){
            response = item;
        }
        return response;
    }

    public void talkTo() {
        // talk to npc
    }
//    there's a bit of code redundancy here so there could possible be a way to morph these together
    public int addToPlayerEnergy(int energyToGive) {
        int num = getEnergy() + energyToGive;
        setEnergy(num);
        return getEnergy();
    }

    public int subtractFromPLayerEnergy(int energyToTake) {
        int num = getEnergy() + energyToTake;
        setEnergy(num);
        return getEnergy();
    }

    public void consumeItem(String item ) {
//        this could be replaced with a try catch but it would have to have an exception in it.
        if(isItemInInventory(item)){
            if(item.equals("energy drink")){
                System.out.println("Ahh yeah man more energy to work out!!");
                addToPlayerEnergy(5); // hard coded value that we can talk about later
            }else if (item.equals("energy drink")){
                System.out.println("GAME OVER");
            }
        }else{
            System.out.println("not in inventory mate ;/");
        }

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Player player = (Player) o;
//
//        if (age != player.age) return false;
//        if (energy != player.energy) return false;
//        if (Double.compare(player.weight, weight) != 0) return false;
//        if (isChestWorked != player.isChestWorked) return false;
//        if (isTricepsWorked != player.isTricepsWorked) return false;
//        if (isLegsWorked != player.isLegsWorked) return false;
//        if (isBackWorked != player.isBackWorked) return false;
//        if (isCoreWorked != player.isCoreWorked) return false;
//        if (isShoulderWorked != player.isShoulderWorked) return false;
//        if (!name.equals(player.name)) return false;
//        return inventory.equals(player.inventory);
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = age;
//        result = 31 * result + name.hashCode();
//        result = 31 * result + energy;
//        temp = Double.doubleToLongBits(weight);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + inventory.hashCode();
//        result = 31 * result + (isChestWorked ? 1 : 0);
//        result = 31 * result + (isTricepsWorked ? 1 : 0);
//        result = 31 * result + (isLegsWorked ? 1 : 0);
//        result = 31 * result + (isBackWorked ? 1 : 0);
//        result = 31 * result + (isCoreWorked ? 1 : 0);
//        result = 31 * result + (isShoulderWorked ? 1 : 0);
//        return result;
//    }
}