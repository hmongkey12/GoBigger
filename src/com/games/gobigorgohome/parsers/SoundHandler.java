package com.games.gobigorgohome.parsers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

        private Clip musicClip;
        private Clip FxClip;
        private final float volumeStep = 2.0f;
        private float previousMusicVolume = 0.0f;
        private float currentMusicVolume = 0.0f;
        private float previousFxVolume = 0.0f;
        private float currentFxVolume = 0.0f;
        FloatControl musicFc;
        FloatControl FxFc;
        boolean musicMuted = false;
        boolean fxMuted = false;


        public void RunMusic(String path) {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
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

        public void playFx(String path) {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
                FxClip = AudioSystem.getClip();
                FxClip.open(inputStream);
                FxClip.start();
                FxFc = (FloatControl)FxClip.getControl(FloatControl.Type.MASTER_GAIN);
                FxClip.loop(0);
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
            if( musicMuted == false) {
                previousMusicVolume = currentMusicVolume;
                currentMusicVolume = -80.0f;
                musicFc.setValue(currentMusicVolume);
                musicMuted = true;
            } else if (musicMuted) {
                currentMusicVolume = previousMusicVolume;
                musicFc.setValue(currentMusicVolume);
                musicMuted = false;
            }
        }

        public void stopMusic() {
            musicClip.stop();
            musicClip.close();
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

}