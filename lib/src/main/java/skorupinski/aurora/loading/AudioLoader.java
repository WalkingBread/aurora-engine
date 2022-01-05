package skorupinski.aurora.loading;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioLoader implements Loader {

    @Override
    public boolean canLoad(Class<?> type) {
        return type.isAssignableFrom(Clip.class);
    }

    @Override
    public Object load(InputStream is) throws IOException {
        InputStream bis = new BufferedInputStream(is);
        AudioInputStream ais = null;
        
        try {
            ais = AudioSystem.getAudioInputStream(bis);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        return clip;
    }
    
}
