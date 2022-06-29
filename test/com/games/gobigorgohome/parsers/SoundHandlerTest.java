package com.games.gobigorgohome.parsers;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class SoundHandlerTest extends TestCase {

    SoundHandler soundHandler = new SoundHandler();
    private final String musicPath = "resources/gainz.wav";
    private float maxVolume = 6.0f;
    private float minVolume = -80.0f;


    @Override
    @Before
    public void setUp() throws Exception {
        soundHandler.RunMusic(musicPath);
    }

    @Test
    public void testMaxVolumeBoundary() {
        float invalidUpperVolumeValue = 7.0f;
        soundHandler.setCurrentVolume(invalidUpperVolumeValue);
        soundHandler.volumeUp();
        assertEquals(maxVolume, soundHandler.getCurrentVolume());
        assertNotEquals(invalidUpperVolumeValue, soundHandler.getCurrentVolume());
    }

    @Test
    public void testMinVolumeBoundary() {
        float invalidLowerVolumeValue = -81.0f;
        soundHandler.setCurrentVolume(invalidLowerVolumeValue);
        soundHandler.volumeDown();
        assertEquals(minVolume, soundHandler.getCurrentVolume());
        assertNotEquals(invalidLowerVolumeValue, soundHandler.getCurrentVolume());
    }


    @Override
    @After
    public void tearDown() throws Exception {
        soundHandler.stopMusic();
    }
}