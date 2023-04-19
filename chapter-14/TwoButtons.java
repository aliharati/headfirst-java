import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TwoButtons{
    private JFrame frame;
    private JLabel label;
    public static void main(String[] args) {
        
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change colors");
        button.addActionListener(new ColorListener());

        JButton labelButton = new JButton("Change label");
        labelButton.addActionListener(new LabelListener());

        label = new JLabel("I'm a label");

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
        frame.getContentPane().add(BorderLayout.SOUTH ,button);
        frame.getContentPane().add(BorderLayout.EAST ,labelButton);
        frame.getContentPane().add(BorderLayout.WEST,label);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            frame.repaint();
            
        }
    }
    class LabelListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            label.setText("Ouch!");
            
        }
    }

    
}
class MyDrawPanel extends JPanel{
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        Random random = new Random();
        int red = random.nextInt(256);
        int blue = random.nextInt(256);
        int green = random.nextInt(256);
        Color startColor = new Color(red, green, blue);

        red = random.nextInt(256);
        blue = random.nextInt(256);
        green = random.nextInt(256);
        Color endColor = new Color(red, green, blue);

        GradientPaint gradient = new GradientPaint(70, 70, startColor, 140, 140, endColor);
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 100);


    }
}
