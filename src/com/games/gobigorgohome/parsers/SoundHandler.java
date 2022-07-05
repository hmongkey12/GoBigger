package com.games.gobigorgohome.parsers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundHandler {

        private Clip musicClip;
        private Clip fxClip;
        private final float volumeStep = 5.0f;
        private float previousMusicVolume = 0.0f;
        private float currentMusicVolume = 0.0f;
        private float previousFxVolume = 0.0f;
        private float currentFxVolume = 0.0f;
        private FloatControl musicFc;
        private FloatControl fxFc;
        boolean musicMuted = false;
        boolean fxMuted = false;


//        BACKGROUND MUSIC AUDIO CONTROLS
        public void RunMusic(String path) {
            try {
                ClassLoader classLoader = SoundHandler.class.getClassLoader();
                URL sound= classLoader.getResource(path);
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
                musicClip = AudioSystem.getClip();
                musicClip.open(inputStream);
                musicClip.start();
                musicFc = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void musicVolumeUp() {
            currentMusicVolume += volumeStep;
            if( currentMusicVolume > 6.0f) {
                currentMusicVolume = 6.0f;
            }
            musicFc.setValue(currentMusicVolume);
        }

        public void musicVolumeDown() {
            currentMusicVolume -= volumeStep;

            if( currentMusicVolume < -80.0f) {
                currentMusicVolume =  -80.0f;
            }
                musicFc.setValue(currentMusicVolume);
        }

        public void muteMusicVolume() {
            if(!musicMuted) {
                previousMusicVolume = currentMusicVolume;
                currentMusicVolume = -80.0f;
                musicFc.setValue(currentMusicVolume);
                musicMuted = true;
            } else {
                currentMusicVolume = previousMusicVolume;
                musicFc.setValue(currentMusicVolume);
                musicMuted = false;
            }
        }


//        SOUND FX VOLUME CONTROLS
        public void playFx(String path) {
            try {
                ClassLoader classLoader = SoundHandler.class.getClassLoader();
                URL sound= classLoader.getResource(path);
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
                fxClip = AudioSystem.getClip();
                fxClip.open(inputStream);
                fxFc = (FloatControl) fxClip.getControl(FloatControl.Type.MASTER_GAIN);
                fxFc.setValue(currentFxVolume);
                fxClip.start();
                fxClip.loop(0);
            }
            catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void fxVolumeUp() {
            currentFxVolume += volumeStep;
            if( currentFxVolume > 6.0f) {
                currentFxVolume = 6.0f;
            }
        }



        public void fxVolumeDown() {
            currentFxVolume -= volumeStep;
            if( currentFxVolume < -80.0f) {
                currentFxVolume =  -80.0f;
            }
        }

        public void muteFxVolume() {
            if( fxMuted == false) {
                previousFxVolume = currentFxVolume;
                currentFxVolume = -80.0f;
                fxMuted = true;
            } else if (fxMuted) {
                currentFxVolume = previousFxVolume;
                fxMuted = false;
            }

        }

        public void stopMusic() {
            musicClip.stop();
            musicClip.close();
            fxClip.close();
        }


        public float getCurrentMusicVolume() {
            return currentMusicVolume;
        }

        public void setCurrentMusicVolume(float currentMusicVolume) {
            this.currentMusicVolume = currentMusicVolume;
        }

        public float getPreviousMusicVolume() {
            return previousMusicVolume;
        }


        public boolean isMusicMuted() {
            return musicMuted;
        }

        public float getPreviousFxVolume() {
            return previousFxVolume;
        }

        public float getCurrentFxVolume() {
            return currentFxVolume;
        }

        public boolean isFxMuted() {
            return fxMuted;
        }


}