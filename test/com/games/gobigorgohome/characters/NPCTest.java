package com.games.gobigorgohome.characters;

import com.games.gobigorgohome.characters.NPC;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NPCTest {
    NPC maintenanceGirl;
    NPC brobro;
    //    we'll need to do testing on the exceptions, for either exception
    @Before
    public void setUp() throws IOException, ParseException {
        maintenanceGirl = new NPC("maintenance lady");
        brobro = new NPC("gymbro");

    }

    @Test
    public void getInventory_shouldReturnNone_ifTheNPCHasNoITem() {
        assertEquals(brobro.getInventory().get(0), "none");
    }


    //one test for true when the name is right and one test for false
    @Test
    public void getNPCName_shouldReturnStringOfName_whenCalled() {
        assertEquals(maintenanceGirl.getNpcName(), "Kate the maintenance lady");
    }
    @Test
    public void getNPCName_shouldReturnFail_whenIncorrectName_isGiven() {
        assertNotEquals(maintenanceGirl.getNpcName(), "Katiepoops");
    }

    @Test
    public void getInventory_shouldReturnInventory_whenCalled() {
        assertEquals(maintenanceGirl.getInventory().get(0), "wrench");
    }

    @Test
    public void getInventory_shouldFailWhen_ifThatItemIsNotThere() {
        assertNotEquals(maintenanceGirl.getInventory().get(0), "marth");
    }

    @Test
    public void getPhrases_shouldReturnPhrase_whenCalled() {
        assertEquals(maintenanceGirl.getPhrases().get(0), "I'm so sick of fixing these old machines. You do it!");
    }

    @Test
    public void getPhrases_shouldFailIF_theValuesDoNotMatch() {
        assertNotEquals(maintenanceGirl.getPhrases().get(0), "You do it!");
    }



}