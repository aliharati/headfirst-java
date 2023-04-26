import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

import static javax.sound.midi.ShortMessage.*;


public class BeatBox2 {
    private ArrayList<JCheckBox> checkBoxList;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    private JFrame frame;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
    "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
    "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
    "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
    "Open Hi Conga"};
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox2().setUpGui();
    }

    public void setUpGui(){
        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        
        JButton startB = new JButton("Start");
        startB.addActionListener(e-> buildTrackAndStart());
        buttonBox.add(startB);
        
        JButton stopB = new JButton("Stop");
        stopB.addActionListener(e-> sequencer.stop());
        buttonBox.add(stopB);
        
        JButton tempoUp = new JButton("Tempo Up");
        tempoUp.addActionListener(e -> changeTempo(1.03f));
        buttonBox.add(tempoUp);
        
        JButton tempoDown = new JButton("Tempo Down");
        tempoDown.addActionListener(e-> changeTempo(0.97f));
        buttonBox.add(tempoDown);
        
        JButton serialize = new JButton("Serialize It");
        serialize.addActionListener(e-> writeFile());
        buttonBox.add(serialize);

        JButton restore = new JButton("Restore");
        restore.addActionListener(e-> restoreFile());
        buttonBox.add(restore);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (String instrumentName : instrumentNames){
            JLabel instrumentLabel = new JLabel(instrumentName);
            instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            nameBox.add(instrumentLabel);
        }
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST,nameBox);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);

        JPanel mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER,mainPanel);

        checkBoxList = new ArrayList<>();
        for (int i = 0; i< 256; i++){
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(false);
            checkBoxList.add(checkBox);
            mainPanel.add(checkBox);
        }

        setUpMidi();

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }
    private void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void buildTrackAndStart(){
        int[] trackList;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++){
            trackList = new int[16];
            int key = instruments[i];
        
            for (int j=0; j < 16;j++){
                JCheckBox jc = checkBoxList.get(j+16*i);
                if (jc.isSelected()){
                    trackList[j] = key;
                } else{
                    trackList[j] = 0;
                }
            }

            makeTracks(trackList);
            track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, 16));
        }

        track.add(makeEvent(PROGRAM_CHANGE, 9, 1, 0, 15));

        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void changeTempo(float tempoMultiplier){
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempoFactor*tempoMultiplier);
    }

    private void makeTracks(int[] list) {
        for (int i = 0; i < 16; i++){
            int key = list [i];

            if(key != 0 ){
                track.add(makeEvent(NOTE_ON, 9, key, 100, i));
                track.add(makeEvent(NOTE_OFF, 9, key, 100, i+1));
            }
        }
    }


    public MidiEvent makeEvent(int cmd,int chnl, int one, int two,int tick){
        MidiEvent event = null;
        try{
        ShortMessage msg = new ShortMessage();
        msg.setMessage(cmd, chnl, one, two);
        event = new MidiEvent(msg, tick);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return event;
    }
    private void writeFile(){
        boolean[] checkboxState = new boolean[256];
        JFileChooser fileSave = new JFileChooser();
        fileSave.showSaveDialog(frame);
        for (int i = 0; i < 256; i++){
            JCheckBox check = checkBoxList.get(i);
            if (check.isSelected()){
                checkboxState[i] = true;
            }
        }
        try(ObjectOutputStream os = 
            new ObjectOutputStream(new FileOutputStream(fileSave.getSelectedFile()))){
                os.writeObject(checkboxState);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private void restoreFile(){
        boolean[] checkboxState = null;
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.showOpenDialog(frame);
        try (ObjectInputStream is = 
            new ObjectInputStream(new FileInputStream(fileOpen.getSelectedFile()))){
                checkboxState = (boolean[]) is.readObject();
        }catch (Exception e){
                e.printStackTrace();
        } 
        for (int i = 0; i < 256; i++){
            JCheckBox check = checkBoxList.get(i);
            check.setSelected(checkboxState[i]);
        }
        sequencer.stop();
        buildTrackAndStart();
    }
}
