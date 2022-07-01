package com.games.gobigorgohome.app;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

    class MyCaretListener implements CaretListener {
        private UserInput caretDemo;

        public MyCaretListener(UserInput caretDemo){
            this.caretDemo = caretDemo;
        }
        @Override
        public void caretUpdate(CaretEvent e) {
            JTextArea editArea = (JTextArea)e.getSource();
            String lineText = "";
            // default values for the line and column.
            int linenum = 1;
            int columnnum = 1;
            // lineText = "";

            // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
            try {
                // First we find the position of the caret. This is the number of where the caret is in relation to the
                // start of the JTextArea
                // In the upper left corner. We use this position to find offset values (eg what line we are on for the
                // given position as well as
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

        }

    }

