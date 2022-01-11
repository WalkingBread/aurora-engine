package skorupinski.aurora;

import java.awt.Color;

import skorupinski.aurora.game.Game;
import skorupinski.aurora.game.GameConfig;
import skorupinski.aurora.game.GameEventHandler;
import skorupinski.aurora.game.Window;
import skorupinski.aurora.geometry.Circle;
import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.geometry.isometric.Cuboid;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;
import skorupinski.aurora.rendering.Camera;

public class Driver {

    public static void main(String[] args) {
        Window window = new Window();

        window.setTitle("test");
        window.setSize(400, 400);

        GameConfig config = new GameConfig();

        config.fps(60);
        config.tps(60);

        Game.init(window, config);

        Circle circle = new Circle(new Vector2(100, 100), 200);
        Rectangle rect = new Rectangle(new Vector2(-1, -1), new Vector2(100, 100));
        Cuboid c = new Cuboid(new Vector3(0, 0, 0), new Vector3(100, 100, 100));

        Camera camera = new Camera();
        camera.focusOn(c.getPosition().toDisplayPosition(camera).vector());


        GameEventHandler handler = new GameEventHandler() {

            @Override
            public void onTick() {
                //System.out.println(Game.loop().getFps());
            }

            @Override
            public void onFrame(Painter painter) {
                painter.color(Color.CYAN);
                //painter.lineWidth(20);
                //painter.fillRect(0, 0, 50, 50);

                //painter.draw(circle);
                painter.draw(rect);

                c.display(camera, painter);
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
        Game.start();
    }
}
