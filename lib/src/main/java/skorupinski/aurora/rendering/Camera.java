package skorupinski.aurora.rendering;

import skorupinski.aurora.game.Game;
import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.math.Constants;
import skorupinski.aurora.math.Matrix;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;

public class Camera {
    public Vector2 position;

    public Camera() {
        position = new Vector2(0, 0);
    }

    public void focusOn(Vector2 globalPosition) {
        this.position = globalPosition.substract(Game.window().getSize().toVector2().divide(2));
    }

    public Rectangle getRectangle() {
        return new Rectangle(position, Game.window().getSize().toVector2());
    }

    public Vector2 getDisplayPosition(Vector2 globalPosition) {
        Vector2 displayPosition = globalPosition.substract(this.position);
        displayPosition = displayPosition.divide(Game.window().getResizeFactor());

        return displayPosition;
    }

    public static Vector2 isometricTo2D(Vector3 isometric) {
        Vector3 translatedMapPosition = new Vector3(isometric.x, -isometric.z, isometric.y);
        Matrix mapPositionMx = translatedMapPosition.toMatrix();
        Matrix isometricRotationMx = Constants.getIsometricRotationMatrix();
        Matrix rotated = isometricRotationMx.multiply(mapPositionMx);

        Matrix orthoProjection = Constants.orthographicProjectionMatrix;
        Matrix projection = orthoProjection.multiply(rotated);

        return new Vector2(
            projection.getValueAt(0, 0), 
            projection.getValueAt(1, 0)
         );
    }
}

