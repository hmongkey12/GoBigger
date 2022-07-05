package com.games.gobigorgohome.app;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

public class UserInputTest extends TestCase {
    private UserInput prompter;
    private JTextArea statusArea;
    private JLabel promptSpace;

    @Before
    public void initialize() {
        prompter = new UserInput(null);
        statusArea = prompter.getEditor();
        promptSpace = prompter.getLabel();
    }

    @Test
    public void testPromptShouldOutputGameQuestionInJLabel() {
        initialize();
        // Generate a game question.
        String gameQuestion = "What's your height?";
        // Create a thread that sets the prompter entered value to true
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000); // Simulating the time a user type to get an answer
                    prompter.setEntered(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        // call the prompt method of prompter and pass it the game question.
        prompter.prompt(gameQuestion);
        // Assert that the text in the prompt space is the same as the game question.
        assertEquals(promptSpace.getText(), gameQuestion);

    }

    @Test
    public void testDisplayShouldShowGameMessageInJTextArea() {
        initialize(); // Initializes the prompter and the swing component
        // Generate a status message from the server
        String welcome = "Hello Herman let's get to know more about you";
        // Call the display method of prompter passing it the status message.
        prompter.display(welcome);
        // Assert that the text in the status area is the same as the status message.
        assertEquals(statusArea.getText(), welcome);

    }

}