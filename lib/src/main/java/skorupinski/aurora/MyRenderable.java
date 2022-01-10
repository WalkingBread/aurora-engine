package skorupinski.aurora;

import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.rendering.Renderable;

public class MyRenderable extends Renderable<Isometric> {

    public MyRenderable(Isometric position, Vector2 size, Camera camera) {
        super(position, size, camera);
    }

    @Override
    public void draw(Painter painter, Vector2 position) {
        
    }
    
}
