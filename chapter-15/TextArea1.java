import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextArea1 {
    public static void main(String[] args) {
        TextArea1 gui = new TextArea1();
        gui.go();
    }
    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        
        JTextArea text = new JTextArea(10,20);
        JScrollPane scroller = new JScrollPane(text);
        text.setLineWrap(true);

        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton button = new JButton("Just Click it");
        button.addActionListener(event -> text.append("button clicked\n"));
        frame.add(BorderLayout.SOUTH, button);
        panel.add(scroller);
        frame.add(BorderLayout.CENTER,panel);

        frame.setSize(500, 500);
        frame.setVisible(true);

    }
}
