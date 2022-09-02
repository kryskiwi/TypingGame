package GameMain;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// public class GameGui {
//     public static void main( ){
//         JFrame frame = new JFrame();
//         frame.setSize(250,250);
//         frame.setLocation(300,200);
//         final JTextArea textArea = new JTextArea(10,40);
//         frame.getContentPane().add(BorderLayout.CENTER, textArea);


//         KeyListener listen = new KeyListener() {
//             @Override
//             public void keyPressed(KeyEvent event) {
//                 printEventInfo("Key Pressed", event);
//             }
//             @Override
//             public void keyReleased(KeyEvent event) {
//                 printEventInfo("Key Released", event);
//             }
//             @Override
//             public void keyTyped(KeyEvent event) {
//                 printEventInfo("Key Typed", event);
//             }

//             public void printEventInfo(String message, KeyEvent key) {
//                 System.out.println(message);
//                 System.out.println(key.getKeyChar());
//             }
//         };
    

//         frame.addKeyListener(listen);



//     }
// }


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GameGui{

  public static void main(String[] args) {

    JFrame f = new JFrame("A JFrame");
    f.setSize(250, 250);
    f.setLocation(300,200);
    final JTextArea textArea = new JTextArea(10, 40);
    textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(10,10,10,10)));
    
    f.getContentPane().add(BorderLayout.CENTER, textArea);
    
    KeyListener listen = new KeyListener() {
        @Override 
        public void keyPressed(KeyEvent event) {
            printEventInfo("Key Pressed", event);
        }
        @Override
        public void keyReleased(KeyEvent event) {
            printEventInfo("Key Released", event);
        }
        @Override
        public void keyTyped(KeyEvent event) {
            printEventInfo("Key Typed", event);
        }

        public void printEventInfo(String message, KeyEvent key) {
            textArea.setText("");
            textArea.append(message + "\n");
            String pressed_key = "" + key.getKeyChar();
            textArea.append(pressed_key + "\n");
        }
    };


    textArea.addKeyListener(listen);

    f.setVisible(true);

  }

}