package skorupinski.aurora.game;

import java.awt.Color;

import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2i;

public class Game {

    private static Window window = null;

    private static GameLoop gameLoop = null;

    private static GameConfig config = null;

    private static GameEventHandler eventHandler = null;

    private static GameRenderer renderer = null;

    private static final Color BACKGROUND = Color.DARK_GRAY;

    private Game() {}

    public static void init(Window gameWindow, GameConfig config) {
        window = gameWindow;
        window.setup();

        gameLoop = new GameLoop(config.fps, config.tps);
        
        renderer = new GameRenderer();

        if(eventHandler != null) {
            eventHandler.onStart();
        }

        gameLoop.start();
    }

    public static void terminate() {
        if(eventHandler != null) {
            eventHandler.onClose();
        }
        gameLoop.stop();
    }

    public static void setupBackground(Painter painter) {
        painter.color(BACKGROUND);
        painter.fillRect(new Vector2i(0, 0), window.getMaximumSize());
    }

    protected static void frame() {
        Painter painter = renderer.getPainter();
        setupBackground(painter);

        if(eventHandler != null) {
            eventHandler.onFrame(painter);
        }

        renderer.render();
    }

    protected static void tick() {
        if(eventHandler != null) {
            eventHandler.onTick();
        }
    }

    public static void addGameEventHandler(GameEventHandler gameEventHandler) {
        eventHandler = gameEventHandler;
    } 

    public static Window window() {
        if(window == null) {
            throw new IllegalStateException("Game not initialized.");
        }
        return window;
    }

    public static GameLoop loop() {
        if(gameLoop == null) {
            throw new IllegalStateException("Game not initialized.");
        }
        return gameLoop;
    }

    public static GameRenderer renderer() {
        if(renderer == null) {
            throw new IllegalStateException("Game not initialized.");
        }
        return renderer;
    }
    
}
