package com.games.gobigorgohome.parsers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

        private Clip clip;
        private float previousVolume = 0;
        private float currentVolume = 0;
        FloatControl fc;
        boolean mute = false;

        public void RunMusic(String path) {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
                clip = AudioSystem.getClip();
                clip.open(inputStream);
                clip.start();
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
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

        public void volumeUp() {
            currentVolume += 1.0f;
            if( currentVolume > 6.0f) {
                currentVolume = 6.0f;
            }
            fc.setValue(currentVolume);
        }

        public void volumeDown() {
            currentVolume -= 1.0f;
            if( currentVolume < -80.0f); {
                currentVolume =  -80.0f;
            }
            fc.setValue(currentVolume);
        }

        public void muteVolume() {
            if( mute == false) {
                previousVolume = currentVolume;
                currentVolume = -80.0f;
                fc.setValue(currentVolume);
                mute = true;
            } else if (mute == true) {
                currentVolume = previousVolume;
                fc.setValue(currentVolume);
            }
        }

        public void stopMusic() {
            clip.stop();
            clip.close();
        }
}