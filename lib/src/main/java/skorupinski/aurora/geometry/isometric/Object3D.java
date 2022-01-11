package skorupinski.aurora.geometry.isometric;

import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.rendering.Renderable;

public abstract class Object3D extends Renderable<Isometric> {

    public Object3D(Isometric position) {
        super(position);
    }

    public abstract Isometric[] getVertices();
    
    
}
