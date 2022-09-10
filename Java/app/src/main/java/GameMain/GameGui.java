package GameMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;

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

    final JTextArea statsArea = new JTextArea("Click the bottommost box to begin typing.");
    statsArea.setEditable(false);

    final JTextArea typeArea = new JTextArea(10,40);
    String default_type_text = "Click here to type.";
    typeArea.setText(default_type_text);

    final JTextArea game_over = new JTextArea(10, 5);
    game_over.setText("Game Over");
    game_over.setEditable(false);

    
    f.getContentPane().add(BorderLayout.PAGE_START, statsArea);
    f.getContentPane().add(BorderLayout.CENTER, textArea);
    f.getContentPane().add(BorderLayout.PAGE_END, typeArea);

    textArea.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
    textArea.setPreferredSize(new Dimension(50,20) );
    f.setVisible(true);

    // Set up timer
    int delay = 1000; //milliseconds
    

    final JTextArea timeArea = new JTextArea(5,20);
    f.getContentPane().add(BorderLayout.LINE_END, timeArea);
    timeArea.setPreferredSize(new Dimension(1,1));
    

    Timer timer = new Timer(delay, new ActionListener() {
        int count = 10;
        public void actionPerformed(ActionEvent evt) {
            if (count == 0) {
                f.remove(textArea);
                f.getContentPane().add(BorderLayout.PAGE_START, game_over);
                f.getContentPane().add(BorderLayout.CENTER, statsArea);
                // f.remove(typeArea);
            }
            else{
                timeArea.setText("" + count);
                count--;
            }

            
            }
        }
    );
    timer.start();
    


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
                if (cursor == str_len){
                    f.getContentPane().removeAll();
                    f.getContentPane().add(BorderLayout.PAGE_START,game_over);
                    f.getContentPane().add(BorderLayout.CENTER, statsArea);
                }
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
            float accuracy;
            if (cursor == 0){
                accuracy = 100;
            }
            else{ accuracy = correct*100 / cursor;}
            String format = "%1$5s %2$-40s %3$-20s";
            // String stats_line = "Accuracy: " + String.valueOf(accuracy) + "%" + "Timer: " + 
            statsArea.setText("Accuracy: " + String.valueOf(accuracy) + "%");
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