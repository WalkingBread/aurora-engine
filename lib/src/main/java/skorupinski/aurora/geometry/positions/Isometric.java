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

    /*public void rotateX(MapPosition rotationMid, float angle) {
        Vector3 rotated = vector().rotateX(rotationMid.vector(), angle);
        set(rotated);
    }

    public void rotateY(MapPosition rotationMid, float angle) {
        Vector3 rotated = vector().rotateY(rotationMid.vector(), angle);
        set(rotated);
    }

    public void rotateZ(MapPosition rotationMid, float angle) {
        Vector3 rotated = vector().rotateZ(rotationMid.vector(), angle);
        set(rotated);
    }*/

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
