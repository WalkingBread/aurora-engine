package skorupinski.aurora.game;

public class GameConfig {
    int fps;
    int tps;

    boolean consoleEnabled;

    public GameConfig() {
        fps = 60;
        tps = 60;
        consoleEnabled = true;
    }

    public void fps(int fps) {
        this.fps = fps;
    }

    public void tps(int tps) {
        this.tps = tps;
    }

    public void enableConsole(boolean consoleEnabled) {
        this.consoleEnabled = consoleEnabled;
    }
}
