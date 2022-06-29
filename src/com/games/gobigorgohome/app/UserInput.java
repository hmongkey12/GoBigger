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

        // Start of our caretDemo class
        public  UserInput(Game game) {
            this.game = game;
            // Let's create a border layout to make positioning of items easy and quick.
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
//                        try {
                            // This call should receive a response from game to print to the user
                            answer = instruction.trim();
                            entered = true;
//                            game.handleInput(instruction);
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        } catch (ParseException ex) {
//                            ex.printStackTrace();
//                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Enter was pressed. Your code goes here.
//                        editor.append("> ");
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

        // Call the editor to print message along with arrow sign
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


    class MyCaretListener implements CaretListener{
        private UserInput caretDemo;

        public MyCaretListener(UserInput caretDemo){
            this.caretDemo = caretDemo;
        }
        @Override
        public void caretUpdate(CaretEvent e) {
            JTextArea editArea = (JTextArea)e.getSource();
            String lineText = "";
            // Let's start with some default values for the line and column.
            int linenum = 1;
            int columnnum = 1;
            // lineText = "";

            // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
            try {
                // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                // what position that line starts on.
                int caretpos = editArea.getCaretPosition();
                linenum = editArea.getLineOfOffset(caretpos);

                // We subtract the offset of where our line starts from the overall caret position.
                // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                // we know that we must be on column 6 of line 5.
                columnnum = caretpos - editArea.getLineStartOffset(linenum);

                //int position = editArea.viewToModel(editArea.getMousePosition());
                String[] text = editArea.getText().split("\n");
                lineText = text[linenum];

                // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                linenum += 1;
            }
            catch(Exception ex) { }

            // Once we know the position of the line and the column, pass it to a helper function for updating the status bar.
            caretDemo.updateStatus(linenum, columnnum, lineText);
        }

}
