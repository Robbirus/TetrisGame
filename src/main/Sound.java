package main;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {

    private Clip musicClip;
    private File[] soundURL = new File[10];

    public Sound(){

        soundURL[0] = new File("res/white-labyrinth-active.wav");
        soundURL[1] = new File("res/delete line.wav");
        soundURL[2] = new File("res/gameover.wav");
        soundURL[3] = new File("res/rotation.wav");
        soundURL[4] = new File("res/touch floor.wav");
    }

    public void play(int i, boolean music){

        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            Clip clip = AudioSystem.getClip();

            if(music){
                musicClip = clip;
            }

            clip.open(audioInputStream);
            clip.addLineListener(new LineListener() {

                @Override
                public void update(LineEvent event){
                    if(event.getType() == LineEvent.Type.STOP){
                        clip.close();
                    }
                }

            });

            audioInputStream.close();
            clip.start();

        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void loop(){
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        musicClip.stop();
        musicClip.close();
    }
}
