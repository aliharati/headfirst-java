import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class MiniMusicPlayer2 {
    public static void main(String[] args) {
        MiniMusicPlayer2 mini = new MiniMusicPlayer2();
        mini.play();
    }    

    public void play(){
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsIWant = {127};
            sequencer.addControllerEventListener(event -> System.out.println("la"), eventsIWant);

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for(int i = 5;i<60;i += 4){
                track.add(makeEvent(NOTE_ON, 1, i, 100, i));
                track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i));
                track.add(makeEvent(NOTE_OFF, 1, i, 100, i + 2));
            }
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

    
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public MidiEvent makeEvent(int command,int channel, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return event;
    }
}
