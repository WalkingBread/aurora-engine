package skorupinski.aurora.geometry.isometric;

import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.math.Vector3;
import skorupinski.aurora.rendering.Renderable;

public abstract class Object3D extends Renderable<Isometric> {

    private Vector3 rotation;

    private Isometric rotationMid;

    private Isometric mid;

    private boolean drawBoundingBox;

    public Object3D(Isometric position, Isometric mid) {
        super(position);

        this.mid = mid;

        rotation = new Vector3();
        rotationMid = mid;

        drawBoundingBox = false;
    }

    public abstract Isometric[] getInitialVertexPositions();

    public void setRotationMid(Isometric rotationMid) {
        this.rotationMid = rotationMid;

        if(rotationMid == null) {
            this.rotationMid = mid;
        } 
    }

    public void rotate(Vector3 angles) {
        rotation = rotation.add(angles);
    }

    public Isometric[] getVertices() {
        Isometric[] vertices = getInitialVertexPositions();

        for(Isometric v : vertices) {
            v.rotate(mid, rotation);
        }

        return vertices;
    }


}
