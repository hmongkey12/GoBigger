package com.games.gobigorgohome;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MapTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Map map = new Map();
    String expectedMap = "123123123123123123123123\n" +
            "                 12                    23\n" +
            "                 12  \"machines\"        23\n" +
            "                 12                    23\n" +
            "                 123123123123123123123123 <><><><><><12312312312312123123123123123\n" +
            "                 ||            ||      ||            12                         23\n" +
            "                 ||            ||      ||            12   \"managers office\"     23\n" +
            "                 ||            ||      ||            12                         23\n" +
            "                 ||            ||      ||            12                         23\n" +
            "       123123123123123123123<><><><<><><><><><><><><>23123123121231231231231231223\n" +
            "       12                 23   ||      ||                ||     ||\n" +
            "       12   ******        23<><><><><123123123123123123123123   ||\n" +
            "       12 \"free weights\"  23   ||    12                    12   ||\n" +
            "       12    Y.A.H        23   ||    12   yoga studio      12   ||\n" +
            "       12   ******        23   ||    12                    12   ||\n" +
            "       123123123123123123123   ||    123123123123123123123123   ||\n" +
            "                 ||            ||        ||                     ||\n" +
            "                 ||            ||        ||                     ||\n" +
            "                 ||            ||        ||                     ||\n" +
            "                 ||            ||        ||                     ||\n" +
            "                 123123123123123123123123123123123123123123123123123\n" +
            "                 12                                               12\n" +
            "                 12            \"front desk\"                       12\n" +
            "                 12                                               12\n" +
            "                 121231231231231231231231231231231231231231231231231";

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown(){
        System.setOut(System.out);
    }


    @Test (expected = IllegalArgumentException.class)
    public void testDataFromFile_shouldThrowIllegalArgumentException_whenFileIsNotFound() throws IOException {
        map.dataFromFile("Don't Exist");
    }

    @Test
    public void testDataFromFile_shouldReadFromFile_WhenFileExists() throws IOException {
        map.dataFromFile("map/free_weights_map.txt");
        Assert.assertEquals( expectedMap, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testStringEditor_shouldCreateMap_whenCalledWithCorrectMapName () throws IOException {
        map.stringEditor("free weights");
        Assert.assertEquals(expectedMap, outputStreamCaptor.toString().trim());
    }
}