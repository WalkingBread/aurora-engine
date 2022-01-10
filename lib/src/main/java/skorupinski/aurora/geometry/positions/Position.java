package skorupinski.aurora.geometry.positions;

import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.rendering.Camera;

public abstract class Position {

    protected Position() {}

    public abstract Object vector();

    @Override
    public String toString() {
        return vector().toString();
    }
}
