package skorupinski.aurora.map;

import java.awt.Color;

import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.rendering.Renderable;

public class Chunk extends Renderable<Isometric> {

    private IsometricChunkMap map;

    private Rectangle territory;

    Chunk(Vector2 position, IsometricChunkMap map) {
        super(new Isometric(position.toVector3()));
        
        this.map = map;

        int size = map.getChunkSize();
        territory = new Rectangle(position, new Vector2(size, size));
    }

    private Vector2 getDisplaySize() {
        Vector2[] isometricVertices = territory.verticesToIsometry();
        Vector2 p1 = isometricVertices[1];
        Vector2 p2 = isometricVertices[2];
        Vector2 p3 = isometricVertices[3];

        return new Vector2(p1.distanceFrom(p3), p2.getLength());
    }

    @Override
    public Rectangle getRectangle() {
        Vector2 globalPosition = position.toGlobalPosition().vector();
        Vector2 displaySize = getDisplaySize();

        return new Rectangle(
            globalPosition.substract(new Vector2(displaySize.x, 0)),
            displaySize
        );
    }

    public Rectangle getTerritory() {
        return territory;
    }

    public boolean inCamera(Camera camera) {
        Rectangle cameraRectangle = camera.getRectangle();

        if(getRectangle().collidesWith(cameraRectangle)) {
            return true;
        }
    
        return false;
    }

    private void defaultFill(Painter painter, Camera camera) {
        painter.color(Color.BLACK);

        Vector2[] isometricVertices = territory.verticesToIsometry();

        for(int i = 0; i < isometricVertices.length; i++) {
            Vector2 p1 = camera.getDisplayPosition(isometricVertices[i]);
            Vector2 p2 = camera.getDisplayPosition(isometricVertices[0]);

            if(i < isometricVertices.length - 1) {
                p2 = camera.getDisplayPosition(isometricVertices[i + 1]);
            }

            painter.line(p1.toVector2i(), p2.toVector2i());
        }
    }

    @Override
    protected void draw(Painter painter, Vector2 position, Camera camera) {
        defaultFill(painter, camera); 
    }

    public IsometricChunkMap getMap() {
        return map;
    }
    
}
