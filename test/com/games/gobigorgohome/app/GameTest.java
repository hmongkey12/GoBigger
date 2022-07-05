package com.games.gobigorgohome.app;

import com.apps.util.Prompter;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static org.junit.Assert.*;

public class GameTest {
    List requiredItems = new ArrayList();
    List noRequiredItems = new ArrayList();
    Scanner in = new Scanner(System.in);
    Prompter prompter=new Prompter(in);
    Game game= new Game(prompter);

    public GameTest() throws IOException, ParseException {
    }


    @Before
    public void createRequiredItemList() {
        requiredItems.add("wrench");
    }

    @Before
    public void createNoRequiredItemList() {
        noRequiredItems.add("none");
    }

    @Test
    public void isItemRequiredShouldReturnFalseIfNoItemsAreRequired() {
        assertFalse(Game.isItemRequired(noRequiredItems));
    }

    @Test
    public void isItemRequiredShouldReturnTrueIfItemsAreRequired() {
        assertTrue(Game.isItemRequired(requiredItems));
    }
    @Test
    public void TravelingToNotValidLocationThrowsProperString() throws IOException, ParseException {
        String[] test = {"go","west"};
        game.parseThroughPlayerInput(test);
        //Assert.assertEquals(game.getCurrentRoom(), null);
    }


}