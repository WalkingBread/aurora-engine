package skorupinski.aurora;

import java.awt.Color;
import java.nio.file.Path;

import skorupinski.aurora.audio.Audio;
import skorupinski.aurora.game.Game;
import skorupinski.aurora.game.GameConfig;
import skorupinski.aurora.game.GameEventHandler;
import skorupinski.aurora.game.Window;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.graphics.sprites.Sprite;
import skorupinski.aurora.math.Vector2i;

public class Driver {

    public static void main(String[] args) {
        Window window = new Window();

        window.setTitle("test");
        window.setSize(400, 400);
        window.setFixedSize();

        GameConfig config = new GameConfig();

        config.fps(60);
        config.tps(60);

        Sprite sprite = new Sprite(Path.of("", "close.png"));
        Audio audio = new Audio(Path.of("", "step.wav"));
        audio.setVolume(0.3f);

        GameEventHandler handler = new GameEventHandler() {

            @Override
            public void onTick() {
                System.out.println(Game.loop().getFps());
            }

            @Override
            public void onFrame(Painter painter) {
                painter.color(Color.CYAN);
                painter.fillRect(0, 0, 50, 50);

                painter.sprite(sprite, new Vector2i(0, 0), 80);
            }

            @Override
            public void onStart() {
                System.out.println("hello");
            }

            @Override
            public void onClose() {
                System.out.println("bye");
            }
            
        };
    
        Game.addGameEventHandler(handler);
        Game.init(window, config);
    }
}
