package com.games.gobigorgohome.app;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class UserInput extends JPanel implements GamePrompter {
        // Two controls, one is the editor and the other is our little status bar at the bottom.
        // When we update the editor, the change in caret will update the status text field.
        private JTextArea editor;
        private JTextArea status;
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
//            editor.append(prompt + " ");

            status = new JTextArea();
            add(status, BorderLayout.NORTH);
            status.append(prompt + " ");

            status.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Enter was pressed. Your code goes here.
//                        System.out.println(currentLineText + "1");
                        String temp = prompt + " ";
                        setCurrentLineText(temp);


                        String instruction = currentLineText.substring(prompt.length());
                        System.out.println(instruction + "2");

                            // This call should receive a response from game to print to the user
                            answer = instruction.trim();
                        System.out.println(answer + "3");
                            entered = true;
                        ;
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
            status.addCaretListener(new MyCaretListener(this));

            // Add the fields to the layout, the editor in the middle and the status at the bottom.
            add(editor, BorderLayout.CENTER);



            // Give the status update value
//            updateStatus( "");
        }

        public String getCurrentLineText() {
            return currentLineText;
        }

        public void setCurrentLineText(String currentLineText) {
            this.currentLineText = currentLineText;
        }

        // This helper function updates the status bar with the line number and column number.
//        void updateStatus(String lineText) {
//            setCurrentLineText(lineText);
//        }

        // Entry point to the program. It kicks off by creating an instance of our class and making it visible.

    public String prompt(String message) {

        // Call the editor to print message
        // Start a while loop that wait until entered is true
        // Pickup the user's command and return it
        prompt = message;
        entered = false;
        status.append(message);
        while (entered == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return answer;

    }

    @Override
    public void display(String x) {
        editor.setText("");
        editor.append(x);

    }

}

