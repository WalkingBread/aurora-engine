package skorupinski.aurora.audio;

import java.io.IOException;
import java.nio.file.Path;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import skorupinski.aurora.loading.ResourceManager;

public class Audio {
    private Clip clip;

    public Audio(Clip clip) {
        this.clip = clip;
    }

    public Audio(Path path) {
        try {
            clip = ResourceManager.load(Clip.class, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public void play() {
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void resume() {
        clip.start();
    }

    public void restart() {
        clip.setMicrosecondPosition(0);
    }

    public Clip raw() {
        return clip;
    }
}
