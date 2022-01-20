package skorupinski.aurora;

import java.awt.Color;

import skorupinski.aurora.game.Game;
import skorupinski.aurora.game.GameConfig;
import skorupinski.aurora.game.GameEventHandler;
import skorupinski.aurora.game.Window;
import skorupinski.aurora.geometry.isometric.Cuboid;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.map.IsometricChunkMap;
import skorupinski.aurora.math.Vector2i;
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

        Cuboid c = new Cuboid(new Vector3(100, 0, 0), new Vector3(100, 100, 200));
        c.drawBoundingBox(true);

        Camera camera = new Camera();
        //camera.focusOn(new Isometric(0, 0, 0).toGlobalPosition().vector());

        IsometricChunkMap map = new IsometricChunkMap(new Vector2i(2, 2), 300);

        Vector3 v = new Vector3(50, 50, 0);
        System.out.println(v.rotate(new Vector3(100, 100, 0), new Vector3()));

        GameEventHandler handler = new GameEventHandler() {

            @Override
            public void onTick() {
                //System.out.println(Game.loop().getFps());
                c.rotate(new Vector3(0, 0, 0.01f));

                camera.focusOn(new Isometric(0, 0, 0).toGlobalPosition().vector());
            }

            @Override
            public void onFrame(Painter painter) {
                painter.color(Color.CYAN);

                map.display(camera, painter);
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
