package skorupinski.aurora;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import skorupinski.aurora.game.Game;
import skorupinski.aurora.game.GameConfig;
import skorupinski.aurora.game.GameEventHandler;
import skorupinski.aurora.game.Window;
import skorupinski.aurora.geometry.isometric.Cuboid;
import skorupinski.aurora.geometry.isometric.Object3D;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.geometry.sat.AxisProjector;
import skorupinski.aurora.geometry.shapes.Rectangle;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.rendering.IsometricRenderingOrder;

public class Driver {

    public static void main(String[] args) {
        Window window = new Window();

        window.setTitle("test");
        window.setSize(800, 600);

        GameConfig config = new GameConfig();

        config.fps(60);
        config.tps(60);

        Game.init(window, config);

        Camera camera = new Camera();

        Cuboid c1 = new Cuboid(new Vector3(0, 0, 0), new Vector3(50, 50, 50));
        c1.setOutlineColor(Color.RED);
        Cuboid c2 = new Cuboid(new Vector3(50, 51, 0), new Vector3(50, 50, 50));
        c2.setOutlineColor(Color.BLUE);

        List<Object3D> objects = new ArrayList<>();
        objects.add(c1);
        objects.add(c2);

        IsometricRenderingOrder iro = new IsometricRenderingOrder(objects, camera);

        System.out.println(iro.getOrder().indexOf(c1));

        if(AxisProjector.collide(c1, c2)) {
            System.out.println(true);
        }

        GameEventHandler handler = new GameEventHandler() {

            @Override
            public void onTick() {
                //System.out.println(Game.loop().getFps());

                camera.focusOn(new Isometric(0, 0, 0).toGlobalPosition().vector());

            }

            @Override
            public void onFrame(Painter painter) {
                painter.color(Color.CYAN);
                iro.display(camera, painter);
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
