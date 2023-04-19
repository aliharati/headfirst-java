import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimpleAnimation {
    private int x;
    private int y;
    private JFrame frame;
    private boolean stopAnimation = false;
    
    public static void main(String[] args) {
        SimpleAnimation simpleAnimation = new SimpleAnimation();
        simpleAnimation.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton button = new JButton("Start/Stop");
        button.addActionListener(event-> stopAnimation = !stopAnimation);
        
        MyDrawPanel drawPanel =new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.SOUTH,button);
        frame.setSize(500, 500);
        frame.setVisible(true);
        while (stopAnimation == false){
            if (x == 500){
                for(int i=500; i>0;i--){
                    x = i;
                    y = i;
                    drawPanel.repaint();
                    try{
                        Thread.sleep(1);
                    } catch (InterruptedException e){
                        System.out.println("interrupted");
                    }
                }
            }
            else{
                for(int i=1; i<501;i++){
                    x = i;
                    y = i;
                    drawPanel.repaint();
                    try{
                        Thread.sleep(1);
                    } catch (InterruptedException e){
                        System.out.println("interrupted");
                    }
                }
            }
            
        }
        
        
        
        
    }

    public class MyDrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.white);
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

            GradientPaint gradient = new GradientPaint(x, y, startColor, x*2, y*2, endColor);
            g2d.setPaint(gradient);
            g2d.fillOval(x, y, 100, 100);
        }
    }
}
