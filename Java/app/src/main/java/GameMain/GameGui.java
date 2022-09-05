package GameMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GameGui{

  public static void main(String[] args) {
    // Create gui popup
    JFrame f = new JFrame("A JFrame");
    f.setSize(250, 250);
    f.setLocation(300,200);

    // Comparison array
    String c_str = "Bonkadonk"; 
    
    // Make gui a textbox
    final JTextArea textArea = new JTextArea(10, 40);
    textArea.setText(c_str);
    textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
    textArea.setEditable(false);

    final JTextArea fileArea = new JTextArea("Click the bottommost box to begin typing.");
    fileArea.setEditable(false);

    final JTextArea typeArea = new JTextArea(10,40);
    String default_type_text = "Click here to type.";
    typeArea.setText(default_type_text);

    f.getContentPane().add(BorderLayout.PAGE_START, fileArea);
    f.getContentPane().add(BorderLayout.CENTER, textArea);
    f.getContentPane().add(BorderLayout.PAGE_END, typeArea);

    textArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
    f.setVisible(true);

    // Set up key listener for user input
    KeyListener listen = new KeyListener() {
        int cursor = 0;
        int correct = 0;
        int incorrect = 0;
        int str_len = c_str.length();
        DefaultHighlighter.DefaultHighlightPainter correct_painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
        DefaultHighlighter.DefaultHighlightPainter incorrect_painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);


        @Override 
        public void keyPressed(KeyEvent event) {
            // printEventInfo("Key Pressed", event);
            if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE && cursor != 0) {
                try{
                    // If we delete a character, unhighlight the character
                    Highlighter highlighter = textArea.getHighlighter();
                    Highlight highlight = highlighter.getHighlights()[cursor-1];
                    if (highlight.getPainter().equals(correct_painter)){
                        correct--;
                    }
                    else{
                        incorrect--;
                    }
                    highlighter.removeHighlight(highlight);
                }
                catch(Exception e){};
                cursor--;
                calcAccuracy();
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {
            // printEventInfo("Key Released", event);
        }

        @Override
        public void keyTyped(KeyEvent event) {
            if (event.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                return;
            }

            try {
                compareKey(event);
                calcAccuracy();
            }
            catch (Exception e){}
        }

        public void compareKey(KeyEvent key) throws BadLocationException{
            char compare_against = c_str.charAt(cursor);
            char pressed_key = key.getKeyChar();
            if (compare_against == pressed_key){
                // highlight correct text with green
                textArea.getHighlighter().addHighlight(cursor, cursor+1, correct_painter);
                cursor++;
                correct++;
            }
            else{
                // highlight incorrect text with red
                textArea.getHighlighter().addHighlight(cursor, cursor+1, incorrect_painter);
                cursor++;
                incorrect++;
            }

        }

        public void calcAccuracy(){
            float accuracy = correct*100 / cursor;
            fileArea.setText("Accuracy: " + String.valueOf(accuracy) + "%");
        }
        // public void printEventInfo(String message, KeyEvent key) {
        //     textArea.setText(" " + key.getKeyCode());
        //     textArea.append(message + "\n");
        //     String pressed_key = "" + key.getKeyChar();
        //     textArea.append(pressed_key + "\n");
        // }
    };

    // Apply key listener to text area in gui
    typeArea.addKeyListener(listen);

    // Text prompt in field until clicked 
    FocusListener fl = new FocusListener() {
        @Override
        public void focusLost(FocusEvent e) {
            // typeArea.setText("Click here to type.");
        }

        @Override
        public void focusGained(FocusEvent e) {
            System.out.println(typeArea.getText());
            if (typeArea.getText().equals(default_type_text)){
                typeArea.setText("");
            }
        };
    };
    
    typeArea.addFocusListener(fl);
}
}