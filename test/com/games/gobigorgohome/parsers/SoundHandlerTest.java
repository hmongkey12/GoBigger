package com.games.gobigorgohome.parsers;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class SoundHandlerTest extends TestCase {

    SoundHandler soundHandler = new SoundHandler();
    private final String musicPath = "resources/gainz.wav";
    private float maxVolume = 6.0f;
    private float minVolume = -80.0f;
    private float startVolume = 0.0f;


    @Override
    @Before
    public void setUp() throws Exception {
        soundHandler.RunMusic(musicPath);
    }

    @Test
    public void testStartVolume() {
        SoundHandler starterTest = new SoundHandler();
        starterTest.RunMusic(musicPath);
        System.out.println();

        assertEquals(startVolume, starterTest.getCurrentMusicVolume());

        starterTest.stopMusic();
    }

    @Test
    public void testMaxVolumeBoundary() {
        float invalidUpperVolumeValue = 7.0f;

        soundHandler.setCurrentMusicVolume(invalidUpperVolumeValue);
        soundHandler.musicVolumeUp();
        assertEquals(maxVolume, soundHandler.getCurrentMusicVolume());
        assertNotEquals(invalidUpperVolumeValue, soundHandler.getCurrentMusicVolume());
    }

    @Test
    public void testMinVolumeBoundary() {
        float invalidLowerVolumeValue = -81.0f;

        soundHandler.setCurrentMusicVolume(invalidLowerVolumeValue);
        soundHandler.musicVolumeDown();
        assertEquals(minVolume, soundHandler.getCurrentMusicVolume());
        assertNotEquals(invalidLowerVolumeValue, soundHandler.getCurrentMusicVolume());
    }

    @Test
    public void testVolumeMute(){
        float testVolume = -20.0f;

        soundHandler.setCurrentMusicVolume(testVolume);

        assertFalse(soundHandler.isMusicMuted());
        assertNotEquals(minVolume, soundHandler.getCurrentMusicVolume());

//        Testing mute
        soundHandler.muteMusicVolume();
        assertTrue(soundHandler.isMusicMuted());
        assertEquals(minVolume, soundHandler.getCurrentMusicVolume());
        assertNotEquals(testVolume, soundHandler.getCurrentMusicVolume());
        assertEquals(testVolume, soundHandler.getPreviousMusicVolume());

//        Testing unmute
        soundHandler.muteMusicVolume();
        assertFalse(soundHandler.isMusicMuted());
        assertEquals(testVolume, soundHandler.getCurrentMusicVolume());
        assertEquals(soundHandler.getCurrentMusicVolume(), soundHandler.getPreviousMusicVolume());
        assertNotEquals(minVolume, soundHandler.getCurrentMusicVolume());

    }


    @Override
    @After
    public void tearDown() throws Exception {
        soundHandler.stopMusic();
    }
}