import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class MiniMusicPlayer1{
    public static void main(String[] args) {
        MiniMusicPlayer1 mini = new MiniMusicPlayer1();
        mini.play();
        

    }
    public void play(){
        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            for (int i = 5; i < 61; i += 4) {
                track.add(makeEvent(NOTE_ON, 1, i, 100, i));
                track.add(makeEvent(NOTE_OFF, 1, i, 100, i+2));
            }
            player.setSequence(seq);
            player.setTempoInBPM(220);
            player.start();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("Finished");
            
        }
        
    }

    public static MidiEvent makeEvent(int command, int channel, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception e){
            e.printStackTrace();
        }
        return event;   
    }   
    
}