import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SimpleGui3 implements ActionListener {
    private JFrame frame;
    public static void main(String[] args) {
        
        SimpleGui3 gui = new SimpleGui3();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change colors");
        button.addActionListener(this);

        JButton button2 = new JButton("Change Label");
        button2.addActionListener(this);

        MyDrawPanel drawPanel = new MyDrawPanel();
        MyDrawPanel drawPanel2 = new MyDrawPanel();
        drawPanel2.setPreferredSize(new Dimension(200, 200));


        frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
        frame.getContentPane().add(BorderLayout.WEST,drawPanel2);
        frame.getContentPane().add(BorderLayout.SOUTH ,button);


        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event){
        frame.repaint();
        
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

        GradientPaint gradient = new GradientPaint(70, 70, startColor, 170, 170, endColor);
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 100);
       

    }
}
