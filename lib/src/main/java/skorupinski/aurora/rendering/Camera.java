package skorupinski.aurora.rendering;

import skorupinski.aurora.game.Game;
import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.math.Constants;
import skorupinski.aurora.math.Matrix;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;

public class Camera {
    public Vector2 globalPosition;

    public Camera() {
        globalPosition = new Vector2(0, 0);
    }

    public void focusOn(Vector2 globalPosition) {
        this.globalPosition = globalPosition.substract(Game.window().getSize().toVector2().divide(2));
    }

    public Rectangle getRectangle() {
        return new Rectangle(globalPosition, Game.window().getSize().toVector2());
    }

    public Vector2 getDisplayPosition(Vector2 globalPosition) {
        Vector2 displayPosition = globalPosition.substract(this.globalPosition);
        displayPosition = displayPosition.divide(Game.window().getResizeFactor());

        return displayPosition;
    }

    public static Vector3 mapPositionToIsometric(Vector3 mapPosition) {
        Vector3 fixedMapPosition = new Vector3(mapPosition.x, -mapPosition.z, mapPosition.y);
        Matrix mapPositionMx = fixedMapPosition.toMatrix();
        Matrix isometricRotationMx = Constants.getIsometricRotationMatrix();
        Matrix rotated = isometricRotationMx.multiply(mapPositionMx);

        return new Vector3(
            rotated.getValueAt(0, 0), 
            rotated.getValueAt(1, 0), 
            rotated.getValueAt(2, 0)
        );
    }

    public static Vector3 isometricToMapPosition(Vector3 isometricPosition) {
        Matrix isometricPositionMx = isometricPosition.toMatrix();
        Matrix isometricRotationMx = Constants.getIsometricRotationMatrix();
        Matrix inverted = isometricRotationMx.inverse();
        Matrix mapPositionMx = inverted.multiply(isometricPositionMx);

        return new Vector3(
            mapPositionMx.getValueAt(0, 0), 
            mapPositionMx.getValueAt(2, 0), 
            -mapPositionMx.getValueAt(1, 0)
        );
    }

    public static Vector2 isometricTo2D(Vector3 isometricPosition) {
        Matrix orthoProjection = Constants.orthographicProjectionMatrix;
        Matrix isometricPositionMx = isometricPosition.toMatrix();
        Matrix projection = orthoProjection.multiply(isometricPositionMx);

        return new Vector2(
            projection.getValueAt(0, 0), 
            projection.getValueAt(1, 0)
         );
    }

}

