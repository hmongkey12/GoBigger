package com.games.gobigorgohome.app;
import org.json.simple.parser.ParseException;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

public class UserInput extends JPanel implements GamePrompter {
        // Two controls, one is the editor and the other is our little status bar at the bottom.
        // When we update the editor, the change in caret will update the status text field.
        private JTextArea editor;
        private JTextField status;
        private String currentLineText;
        private Game game;
        private String prompt = "";
        private String answer;
        private boolean entered = false;

        // Start of caretDemo class
        public  UserInput(Game game) {
            this.game = game;
            // Create a border layout to make positioning of items easy and quick.
            setLayout(new BorderLayout());

            editor = new JTextArea();
            editor.append(prompt + " ");

            editor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Enter was pressed. Your code goes here.
                        System.out.println(currentLineText);

                        String instruction = currentLineText.substring(prompt.length());
                        System.out.println(instruction);

                            // This call should receive a response from game to print to the user
                            answer = instruction.trim();
                            entered = true;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    }
                }
            });

            // Add a caretListener to the editor. This is an anonymous class because it is inline and has no specific name.
            editor.addCaretListener(new MyCaretListener(this));

            // Add the fields to the layout, the editor in the middle and the status at the bottom.
            add(editor, BorderLayout.CENTER);

            status = new JTextField();
            add(status, BorderLayout.SOUTH);

            // Give the status update value
            updateStatus(1,1, "");
        }

        public String getCurrentLineText() {
            return currentLineText;
        }

        public void setCurrentLineText(String currentLineText) {
            this.currentLineText = currentLineText;
        }

        // This helper function updates the status bar with the line number and column number.
        void updateStatus(int linenumber, int columnnumber, String lineText) {
            status.setText("Line: " + linenumber + " Column: " + columnnumber + ": " + lineText);
            setCurrentLineText(lineText);
        }

        // Entry point to the program. It kicks off by creating an instance of our class and making it visible.

    public String prompt(String message) {

        // Call the editor to print message
        // Start a while loop that wait until entered is true
        // Pickup the user's command and return it
        prompt = message;
        entered = false;
        editor.append(message);
        while (entered == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return answer;

    }
}

