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
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.map.IsometricChunkMap;
import skorupinski.aurora.math.Vector2i;
import skorupinski.aurora.math.Vector3;
import skorupinski.aurora.rendering.Camera;

public class Driver {

    public static void main(String[] args) {
        Window window = new Window();

        window.setTitle("test");
        window.setSize(1000, 800);

        GameConfig config = new GameConfig();

        config.fps(60);
        config.tps(20);

        Game.init(window, config);

        Camera camera = new Camera();

        Cuboid c1 = new Cuboid(new Vector3(0, 0, 20), new Vector3(300, 90, 10));
        c1.rotate(new Vector3(15, 0, 49));
        c1.setOutlineColor(Color.RED);
        Cuboid c2 = new Cuboid(new Vector3(150, 0, 20), new Vector3(300, 90, 10));
        c2.rotate(new Vector3(15, 0, 49));
        c2.setOutlineColor(Color.BLUE);

        List<Object3D> objects = new ArrayList<>();
        objects.add(c1);
        objects.add(c2);
        
        IsometricChunkMap map = new IsometricChunkMap(new Vector2i(10, 10), 100);

        //IsometricRenderingOrder iro = new IsometricRenderingOrder(objects, camera);

        //MyButton btn = new MyButton(50, 50, 100, 50);

        GameEventHandler handler = new GameEventHandler() {

            int i = 0;

            @Override
            public void onTick() {
                //System.out.println(Game.loop().getFps());

                camera.focusOn(new Isometric(0, 0, 0).toGlobalPosition().vector());
                c1.rotate(new Vector3(0, 0, 0.01f));
                c2.rotate(new Vector3(0, 0, 0.01f));

                if(AxisProjector.collide(c1, c2)) {
                    System.out.println("kolizka");
                    System.out.println(i);
                    i++;
                }
            }

            @Override
            public void onFrame(Painter painter) {
                map.display(camera, painter);
                //iro.display(camera, painter);
                painter.color(Color.CYAN);

                c1.display(camera, painter);
                c2.display(camera, painter);
                //btn.display(camera, painter);
            }

            @Override
            public void onStart() {
                //System.out.println("hello");
            }

            @Override
            public void onClose() {
                //System.out.println("bye");
            }
            
        };
    
        Game.addGameEventHandler(handler);
        Game.start();
    }
}
