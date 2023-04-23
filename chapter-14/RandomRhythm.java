// Import the necessary classes
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// Define a class that implements ActionListener
class StopListener implements ActionListener {
    // Declare a sequencer field
    private Sequencer sequencer;

    // Define a constructor that takes a sequencer as an argument
    public StopListener(Sequencer sequencer) {
        this.sequencer = sequencer;
    }

    // Define the actionPerformed method that stops the sequencer
    public void actionPerformed(ActionEvent e) {
        sequencer.stop();
    }
}

// Define the main class
public class RandomRhythm {
    // Declare some constants for the note numbers and durations
    public static final int[] NOTES = {60, 62, 64, 65, 67, 69, 71, 72};
    public static final int[] DURATIONS = {4, 8, 16};

    // Declare a random object
    public static final Random random = new Random();

    // Define the main method
    public static void main(String[] args) throws Exception {
        // Create a sequencer and open it
        Sequencer sequencer = MidiSystem.getSequencer();
sequencer.open();

// Create a sequence with PPQ division type and 24 ticks per quarter note
Sequence sequence = new Sequence(Sequence.PPQ, 24);

// Create a track
Track track = sequence.createTrack();

// Create a variable to store the current tick
int tick = 0;

// Create an infinite loop to generate random notes and durations
while (true) {
    // Choose a random note from the NOTES array
    int note = NOTES[random.nextInt(NOTES.length)];

    // Use a fixed duration of 8, which means an eighth note
    int duration = 8;

    // Calculate the number of ticks for the duration
    int ticks = duration * 24 / 4;

    // Create a note-on event for the note at the current tick with velocity 100
    ShortMessage noteOn = new ShortMessage();
    noteOn.setMessage(0x90, note, 100);
    MidiEvent eventOn = new MidiEvent(noteOn, tick);
    track.add(eventOn);

    // Create a note-off event for the note at the current tick plus the number of ticks with velocity 0
    ShortMessage noteOff = new ShortMessage();
    noteOff.setMessage(0x80, note, 0);
    MidiEvent eventOff = new MidiEvent(noteOff, tick + ticks);
    track.add(eventOff);

    // Update the current tick by adding the number of ticks
    tick += ticks;

    // Break the loop if the sequencer is stopped
    if (!sequencer.isRunning()) {
        break;
    }
}

// Set the sequence for the sequencer
sequencer.setSequence(sequence);

// Set the loop count to LOOP_CONTINUOUSLY to repeat the sequence indefinitely
sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

// Start playing the sequence
sequencer.start();

// Create a JFrame to display a button to stop the program
JFrame frame = new JFrame("Random Rhythm");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(300, 100);
frame.setLocationRelativeTo(null);

// Create a JButton to stop the program
JButton button = new JButton("Stop");

// Add an action listener to the button that stops the sequencer
button.addActionListener(new StopListener(sequencer));

// Add the button to the frame's content pane
frame.getContentPane().add(button);

// Make the frame visible
frame.setVisible(true);
    }
}