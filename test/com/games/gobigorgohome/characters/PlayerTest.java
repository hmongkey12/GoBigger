package com.games.gobigorgohome.characters;

import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    Player roni;

    @Before
    public void setUp() {
        roni = new Player();
        roni.setName("Summer");
        roni.setAge(42);
        roni.setWeight(200);
        roni.setHeight(45);

    }

    @Test
    public void isWorkoutComplete_shouldReturnFalse_ifAllMuscleBooleansAreNotTrue(){
//        we didn't change any of the muscle values which are all initially defined at false.
        assertEquals(roni.isWorkoutComplete(), false);
    }

    @Test
    public void isWorkoutComplete_shouldReturnFalse_evenIfOneMuscleIsNotWorked(){
        roni.setChestWorked(true);
        roni.setLegsWorked(true);
        roni.setBackWorked(true);
        roni.setTricepsWorked(true);
        assertEquals(roni.isWorkoutComplete(), false);
    }

    @Test
    public void isWorkoutComplete_shouldReturnTrue_whenAllMusclesAreWorked(){
        roni.setChestWorked(true);
        roni.setLegsWorked(true);
        roni.setBackWorked(true);
        roni.setTricepsWorked(true);
        roni.setCoreWorked(true);
        assertEquals(roni.isWorkoutComplete(), true);
    }

    @Test
    public void removeItemFromInventory_shouldRemoveItemFromInventory_whenCalled() {
        List<String> andEmptyList = new ArrayList<>();
        roni.removeItemFromInventory("flail");
        assertEquals(roni.getInventory(), andEmptyList );
    }

    @Test
    public void getHeight_shouldReturnEnergyValue_whenCalled() {
        assertEquals(roni.getAge(), 42 );
    }

    @Test
    public void getHeight_shouldFailWhen_ageIsWrong() {
       // assertNotEquals(roni.getAge(), 32 );
    }
    @Test
    public void getAge_shouldReturnAgeValue_whenCalled() {
        assertEquals(roni.getAge(), 42 );
    }

    @Test
    public void getAge_shouldFailWhen_ageIsWrong() {
        //assertNotEquals(roni.getAge(), 32 );
    }
    @Test
    public void getEnergy_shouldReturnEnergyValue_whenCalled() {
        assertEquals(roni.getEnergy(), 100 );
    }

    @Test
    public void getEnergy_shouldFail_ifTheEnergyIsNotCorrect() {
       // assertNotEquals(roni.getEnergy(), 40 );
    }

}