package com.games.gobigorgohome.parsers;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class SoundHandlerTest extends TestCase {

    SoundHandler musicHandler = new SoundHandler();
    SoundHandler fxHandler = new SoundHandler();
    private final String musicPath = "resources/gainz.wav";
    private float maxMusicVolume = 6.0f;
    private float minMusicVolume = -80.0f;
    private float startVolume = 0.0f;


    @Override
    @Before
    public void setUp() throws Exception {
        musicHandler.RunMusic(musicPath);
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

        musicHandler.setCurrentMusicVolume(invalidUpperVolumeValue);
        musicHandler.musicVolumeUp();
        assertEquals(maxMusicVolume, musicHandler.getCurrentMusicVolume());
        assertNotEquals(invalidUpperVolumeValue, musicHandler.getCurrentMusicVolume());
    }

    @Test
    public void testMinVolumeBoundary() {
        float invalidLowerVolumeValue = -81.0f;

        musicHandler.setCurrentMusicVolume(invalidLowerVolumeValue);
        musicHandler.musicVolumeDown();
        assertEquals(minMusicVolume, musicHandler.getCurrentMusicVolume());
        assertNotEquals(invalidLowerVolumeValue, musicHandler.getCurrentMusicVolume());
    }

    @Test
    public void testVolumeMute(){
        float testVolume = -20.0f;

        musicHandler.setCurrentMusicVolume(testVolume);

        assertFalse(musicHandler.isMusicMuted());
        assertNotEquals(minMusicVolume, musicHandler.getCurrentMusicVolume());

//        Testing mute
        musicHandler.muteMusicVolume();
        assertTrue(musicHandler.isMusicMuted());
        assertEquals(minMusicVolume, musicHandler.getCurrentMusicVolume());
        assertNotEquals(testVolume, musicHandler.getCurrentMusicVolume());
        assertEquals(testVolume, musicHandler.getPreviousMusicVolume());

//        Testing unmute
        musicHandler.muteMusicVolume();
        assertFalse(musicHandler.isMusicMuted());
        assertEquals(testVolume, musicHandler.getCurrentMusicVolume());
        assertEquals(musicHandler.getCurrentMusicVolume(), musicHandler.getPreviousMusicVolume());
        assertNotEquals(minMusicVolume, musicHandler.getCurrentMusicVolume());

    }


    @Override
    @After
    public void tearDown() throws Exception {
        musicHandler.stopMusic();
    }
}