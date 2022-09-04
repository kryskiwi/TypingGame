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

    final JTextArea fileArea = new JTextArea("Click the bottommost box to begin typing.");

    final JTextArea typeArea = new JTextArea(10,40);

    f.getContentPane().add(BorderLayout.PAGE_START, fileArea);
    f.getContentPane().add(BorderLayout.CENTER, textArea);
    f.getContentPane().add(BorderLayout.PAGE_END, typeArea);

    // Set up key listener for user input
    KeyListener listen = new KeyListener() {
        int cursor = 0;

        @Override 
        public void keyPressed(KeyEvent event) {
            // printEventInfo("Key Pressed", event);
            if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE && cursor != 0) {
                try{
                    // If we delete a character, unhighlight the character
                    Highlighter highlighter = textArea.getHighlighter();
                    highlighter.removeHighlight(highlighter.getHighlights()[cursor-1]);
                }
                catch(Exception e){};
                cursor--;
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
            }
            catch (Exception e){}
        }

        public void compareKey(KeyEvent key) throws BadLocationException{
            char compare_against = c_str.charAt(cursor);
            char pressed_key = key.getKeyChar();
            if (compare_against == pressed_key){
                // highlight correct text with green
                textArea.getHighlighter().addHighlight(cursor, cursor+1, new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));
                cursor++;
            }
            else{
                // highlight incorrect text with red
                textArea.getHighlighter().addHighlight(cursor, cursor+1, new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                cursor++;
            }

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

    textArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
    f.setVisible(true);

  }

}