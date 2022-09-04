package GameMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientUI {
    private static String start = "This is a test";
    private static int cursor = 0;
    private static JTextArea textArea;
    private static JTextField textField;

    public static void main(String[] args) {
        
        List<String> words = Arrays.asList(start.split("\\s+"));

        JFrame frame = new JFrame("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panel = new JPanel();
        textField = new JTextField(30);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("KEY TYPED");
                compareKey(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println("KEY PRESSED");
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        panel.add(textField);

        JPanel panelText = new JPanel();
        textArea = new JTextArea(3, 30);
        textArea.setEditable(false);
        textArea.setText(String.join(" ", words));
        textArea.getCaret().setVisible(true);
        textArea.setCaretColor(Color.BLUE);
        textArea.setCaretPosition(0);
        // textArea.addKeyListener(new KeyAdapter() {
        //     @Override
        //     public void keyTyped(KeyEvent e) {
        //         compareKey(e);
        //     }

        //     @Override
        //     public void keyPressed(KeyEvent e) {}

        //     @Override
        //     public void keyReleased(KeyEvent e) {}
        // });
        panelText.add(textArea);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panelText);
        frame.setVisible(true);
    }

    private static void compareKey(KeyEvent e) {
        if (e.getKeyChar() == start.charAt(cursor)) {
            if (start.charAt(cursor) == ' ') {
                textField.setText("");
            }
            cursor++;
            Integer current_pos = textArea.getCaretPosition();
            textArea.setCaretPosition(current_pos + 1);
            System.out.println("CURSOR " + cursor);
        } else {
            e.consume();
            textArea.setCaretPosition(textArea.getCaretPosition());
        }
    }
}
