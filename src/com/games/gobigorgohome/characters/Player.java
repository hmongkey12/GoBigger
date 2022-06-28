package com.games.gobigorgohome.characters;

import com.games.gobigorgohome.parsers.ParseJSON;

import java.util.*;

public class Player {
    //    idea- creating a class that's parsing through the json? and putting certain information into lists mainly lists of the muscles and the exercises that work them out.
//    static variables
    public final static int BASE_ENERGY = 100; //example not set in stone

    private final ParseJSON jsonParser = new ParseJSON();

    //    fields
    private int age;
    private String name;
    private int energy = 100;
    private double weight;
    private double height;
    private final List<String> inventory = new ArrayList<>();
    //    just realize that the boolean values are named the same for the getters, idk why but they did it when I did it with the intellij autgenerated ones
    private boolean isChestWorked = false;
    private boolean isTricepsWorked = false;
    private boolean isLegsWorked = false;
    private boolean isBackWorked = false;
    private boolean isCoreWorked = false;
    private boolean isShoulderWorked = false;
    private boolean isBicepsWorked = false;
    private boolean isSteroidsUsed = false;


    private boolean isExhausted = false;

    private final Set<String> musclesWorked = new HashSet<>();

    //    constructors
    public Player() {
    }

    public Player(String name, int age, double weight, double height) {
        this.age = age;
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    //    business methods

    //    we need to think about what values are being played around with or manipulated that can be tested?
    public void workout(Object muscleGroup, Long energyCost) {

        String targetMuscle = jsonParser.getStringValueFromIndexInJSONArray(muscleGroup, 0);

        if (Objects.equals(targetMuscle, "chest")) {
            setChestWorked(true);
            musclesWorked.add(targetMuscle);
        } else if (Objects.equals(targetMuscle, "back")) {
            setBackWorked(true);
            musclesWorked.add(targetMuscle);
        } else if (Objects.equals(targetMuscle, "core")) {
            setCoreWorked(true);
            musclesWorked.add(targetMuscle);
        } else if (Objects.equals(targetMuscle, "triceps")) {
            setTricepsWorked(true);
            musclesWorked.add(targetMuscle);
        } else if (Objects.equals(targetMuscle, "legs")) {
            setLegsWorked(true);
            musclesWorked.add(targetMuscle);
        } else if (Objects.equals(targetMuscle, "biceps")) {
            setBicepsWorked();
            musclesWorked.add(targetMuscle);
        } else if (Objects.equals(targetMuscle, "shoulders")) {
            setShoulderWorked(true);
            musclesWorked.add(targetMuscle);
        }
        subtractFromPlayerEnergy(Math.toIntExact(energyCost));
    }

    private void setBicepsWorked() {
        this.isBicepsWorked = true;
    }

    public boolean isWorkoutComplete() {
        return isChestWorked && isCoreWorked && isLegsWorked && isTricepsWorked && isBackWorked;
    }

    private boolean isItemInInventory(String item) {
        return getInventory().contains(item);
    }

//    public Boolean useItem(String item, boolean isItemRequired) {
//        boolean isItemConsumed = false;
//        if (isItemInInventory(item)) {
//            if (item.equals("key") && isItemRequired) {
//                isItemConsumed = true;
//                removeItemFromInventory(item);
//            } else if (item.equals("wrench") && isItemRequired) {
//                isItemConsumed = true;
//                removeItemFromInventory(item);
//            }
//        } else {
//            System.out.println("oi mate! that's not in your inventory");
//        }
//        //the idea is to return the string for validation purposes
//        return isItemConsumed;
//    }

    public void removeItemFromInventory(String item) {
        getInventory().remove(item);
    }

    //    there's a bit of code redundancy here so there could possibly be a way to morph these together
    public void addToPlayerEnergy(int energyToGive) {
        int num = getEnergy() + energyToGive;
        setEnergy(num);
    }

    public void subtractFromPlayerEnergy(int energyToTake) {
        int num = getEnergy() - energyToTake;
        setEnergy(num);
    }

    public boolean consumeItem(String item) {

        boolean couldYouConsume = false;
//        this could be replaced with a try catch but it would have to have an exception in it.

        if (item.equals("energy drink")) {
            System.out.println("Ahh yeah man more energy to work out!!");
            addToPlayerEnergy(5); // hard coded value that we can talk about later
            couldYouConsume = true;
        } else if (item.equals("steroids")) {
            hasPlayerUsedSteroids(true);
            couldYouConsume = true;
        } else {
            System.out.println("oi mate! that's not in your inventory");
        }

        return couldYouConsume;

    }

    public void hasPlayerUsedSteroids(boolean value) {
//        if this value is true, then we need to set the steroid to the value and then return sterod
        //if the value is true, which means player used steroids
        if (value) {
//           call the setter
            setSteroidsUsed(true);
        }
    }

    public boolean isExhausted() {
        return getEnergy() == 0;
    }


//    accessor methods

    public boolean isSteroidsUsed() {
        return isSteroidsUsed;
    }

    public void setSteroidsUsed(boolean steroidsUsed) {
        isSteroidsUsed = steroidsUsed;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

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

    public Set<String> getMusclesWorked() {
        if (musclesWorked.size() == 0) {
            musclesWorked.add("none");
        } else {
            musclesWorked.remove("none");
        }
        return musclesWorked;
    }

    public void resetBody(){
        setChestWorked(false);
        setBackWorked(false);
        setLegsWorked(false);
        setShoulderWorked(false);
        setTricepsWorked(false);
        setCoreWorked(false);




    }


//    toString

    @Override
    public String toString() {
        return "Player: " + name + "\n" +
                "Age: " + age + ", Weight: " + weight + ", Height: " + height + "\n" +
                "Current Energy: " + energy + " out of " + BASE_ENERGY + "\n" +
                "Gym Bag Contents: " + inventory + "\n" +
                "Workout Status: " + getMusclesWorked().toString();
    }


}