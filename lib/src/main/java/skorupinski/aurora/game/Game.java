package skorupinski.aurora.game;

import java.awt.Color;

import skorupinski.aurora.events.Keyboard;
import skorupinski.aurora.events.Mouse;
import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;

public class Game {

    private static Window window = null;

    private static GameLoop gameLoop = null;

    private static GameConfig config = null;

    private static GameEventHandler eventHandler = null;

    private static GameRenderer renderer = null;

    private static Mouse mouse = null;

    private static Keyboard keyboard = null;

    private static final Color BACKGROUND = Color.DARK_GRAY;

    private Game() {}

    public static void init(Window gameWindow, GameConfig config) {
        window = gameWindow;
        window.setup();

        mouse = new Mouse();
        window.setMouse(mouse);

        keyboard = new Keyboard();
        window.setKeyboard(keyboard);

        gameLoop = new GameLoop(config.fps, config.tps);
        
        renderer = new GameRenderer(window.getCanvas());
    }

    public static void start() {
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
        Rectangle background = new Rectangle(
            new Vector2(0, 0), 
            window.getSize().toVector2()
        );
        painter.color(BACKGROUND);
        painter.fill(background);
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

    private static void notInitialized() {
        throw new IllegalStateException("Game not initialized.");
    }

    public static Window window() {
        if(window == null) {
            notInitialized();
        }
        return window;
    }

    public static GameLoop loop() {
        if(gameLoop == null) {
            notInitialized();
        }
        return gameLoop;
    }

    public static GameRenderer renderer() {
        if(renderer == null) {
            notInitialized();
        }
        return renderer;
    }

    public static Mouse mouse() {
        if(mouse == null) {
            notInitialized();
        }
        return mouse;
    }

    public static Keyboard keyboard() {
        if(keyboard == null) {
            notInitialized();
        }
        return keyboard;
    }

}
