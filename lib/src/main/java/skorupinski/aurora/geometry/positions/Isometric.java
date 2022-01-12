package skorupinski.aurora.geometry.positions;

import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.math.Vector3;

public class Isometric extends Position {
    private Vector3 position;

    public Isometric(Vector3 position) {
        this.position = position;
    }

    public Isometric(float x, float y, float z) {
        position = new Vector3(x, y, z);
    }

    public void rotate(Isometric rotationMid, Vector3 angles) {
        set(position.rotate(rotationMid.vector(), angles));
    }

    @Override
    public Vector3 vector() {
        return position;
    }

    public void set(Vector3 position) {
        this.position = position;
    }

    public Global toGlobalPosition() {
        return new Global(Camera.isometricTo2D(position));
    }

    public Display toDisplayPosition(Camera camera) {
        return toGlobalPosition().toDisplayPosition(camera);
    }

}
