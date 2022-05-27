import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class Music extends JFrame {
	private static int count = 0;
   public Music() {

      try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource("Music.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         clip.loop(1000000);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
      count++;
   }

   public static void play() {
      if(count != 1) {
    	  new Music(); 
      }
   }
}
