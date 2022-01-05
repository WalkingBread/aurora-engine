package skorupinski.aurora.game;

public class GameConfig {
    int fps;
    int tps;

    public GameConfig() {
        fps = 60;
        tps = 60;
    }

    public void fps(int fps) {
        this.fps = fps;
    }

    public void tps(int tps) {
        this.tps = tps;
    }
}
