package GameMain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

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
    textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
    textArea.append(c_str);
    f.getContentPane().add(BorderLayout.CENTER, textArea);
    
    // Set up key listener for user input
    KeyListener listen = new KeyListener() {
        int cursor = 0;

        @Override 
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE && cursor != 0){
                cursor--;
            }
        }
        @Override
        public void keyReleased(KeyEvent event) {
            // printEventInfo("Key Released", event);
        }
        @Override
        public void keyTyped(KeyEvent event) {
            printEventInfo("Key Typed", event);
            compareKey(event);
        }

       
        public void compareKey(KeyEvent key){

           textArea.setText("" + c_str.charAt(cursor) + "\n");
           char compare_against = c_str.charAt(cursor);
           char pressed_key = key.getKeyChar();
           if (compare_against == pressed_key){
            cursor++;
            
            if (cursor == c_str.length() - 1){
                textArea.setText("You gud at typing");
                textArea.removeKeyListener(this);
            }

            textArea.setText("" + c_str.charAt(cursor) + "\n");
           }
           else{
            textArea.append(" WRONG press " + c_str.charAt(cursor) + " correctly to continue \n");
           }

        }

        public void printEventInfo(String message, KeyEvent key) {
            textArea.setText("");
            textArea.append(message + "\n");
            String pressed_key = "" + key.getKeyChar();
            textArea.append(pressed_key + "\n");
        }
    };

    // Apply key listener to text area in gui
    textArea.addKeyListener(listen);

    f.setVisible(true);

  }

}