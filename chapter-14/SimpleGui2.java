import javax.swing.*;
import java.awt.event.*;

public class SimpleGui2 implements ActionListener {
    private JButton button;
    private int clicks = 0;
    public static void main(String[] args) {
        
        SimpleGui2 gui = new SimpleGui2();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("CLick me!");
        
        button.addActionListener(this);

        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if (clicks < 1){
            button.setText("I've been clicked!!");
            clicks += 1;
        } else{
            button.setText("I've been clicked again!!");
        }
        
    }

    
}
