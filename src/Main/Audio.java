package Main;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/*
AUDIO
Handles the music that plays

 */
public class Audio {

    Clip sound;
    File music;

    public Audio(boolean isBoss){

        if(isBoss){
            music = new File("resources/audio/desert.wav");
        }else{
            music = new File("resources/audio/music.wav");
        }

       setAudio();

       setVolume((float) 0.3);
    }

    // Sets the audio
    public void setAudio(){
        try{
            AudioInputStream a = AudioSystem.getAudioInputStream(music);
            sound = AudioSystem.getClip();
            sound.open(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Basic functions
    public void play(){
        sound.start();
    }
    public void loop(){
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        sound.stop();
    }


    public float getVolume() {
        FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    // Set volume
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }


}
