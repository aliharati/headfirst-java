import javax.sound.midi.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.sound.midi.ShortMessage.*;
import java.awt.*;
import java.util.Random;
import java.util.random.*;

public class MiniMusicPlayer3 {
    private MyDrawPanel panel;
    private Random random = new Random();
    public static void main(String[] args) {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }
    public void setUpGui(){
        JFrame frame = new JFrame("My First Music Video");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MyDrawPanel();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    public void go(){
        setUpGui();
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(panel, new int[]{127});
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            int note;
            for (int i = 0; i < 60;i+=4){
                note= random.nextInt(50) + 1;
                track.add(makeEvent(NOTE_ON, 1, note, 100, i));
                track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i));
               
                
                track.add(makeEvent(NOTE_OFF, 1, note, 100, i+2));
                track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i+2));
                
            }
            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(180);
            
        }catch (Exception ex){
            ex.printStackTrace();
        } 
        
    }
    public static MidiEvent makeEvent(int cmd,int chnl, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage msg = new ShortMessage();
            msg.setMessage(cmd,chnl, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return event;
    }

    class MyDrawPanel extends JPanel implements ControllerEventListener{
        private boolean msg = false;
    
        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }
    
        public void paintComponent(Graphics g){
            if(msg){
                int red = random.nextInt(250);
                int blue = random.nextInt(256);
                int green = random.nextInt(256);

                g.setColor(new Color(red, green,  blue));

                int height = random.nextInt(120) + 100;
                int width = random.nextInt(120) + 100;

                int xPos = random.nextInt(40) + 100;
                int yPos = random.nextInt(40) + 100;

                g.fillRect(xPos, yPos, width, height);
                msg = false;
            }
        }
    }
}

