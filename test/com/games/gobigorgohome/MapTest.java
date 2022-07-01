package com.games.gobigorgohome;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MapTest {

    @Test (expected = IllegalArgumentException.class)
    public void testDataFromFile_shouldThrowIllegalArgumentException_whenFileIsNotFound() throws IOException {
        Map map = new Map();
        map.dataFromFile("Don't Exist");
    }

    @Test
    public void testDataFromFile_shouldReadFromFile_WhenFileExists() throws IOException {
        Map map = new Map();
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        map.dataFromFile("map/free_weights_map.txt");
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

        Assert.assertEquals( expectedMap, outputStreamCaptor.toString().trim());
        System.setOut(System.out);
    }

    @Test
    public void testStringEditor (){}
}