package com.games.gobigorgohome.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {



    @Override
    public void keyTyped(KeyEvent e) {
        // Check if key enter was the enter key
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Enter was pressed. Your code goes here.
                // If so, we want to get the line of text where the cursor currently is.

            }
        }

        // Process the text to filter out valid game instruction.
        // If text is valid game instruction, carry it out.


    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
