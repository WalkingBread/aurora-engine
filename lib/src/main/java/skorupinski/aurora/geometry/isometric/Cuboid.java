package skorupinski.aurora.geometry.isometric;

import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.geometry.shapes.Rectangle;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;
import skorupinski.aurora.rendering.Camera;

public class Cuboid extends Object3D {
    
    public Vector3 size;

    public Cuboid(Vector3 position, Vector3 size) {
        super(
            new Isometric(position), 
            new Isometric(position.add(new Vector3(0, 0, size.z / 2)))
        );

        this.size = size;
    }

    @Override
    public Isometric[] getInitialVertexPositions() {
        Vector3 m = position.vector();

        return new Isometric[] {
            new Isometric(m.x - size.x / 2, m.y + size.y / 2, m.z + size.z),
            new Isometric(m.x + size.x / 2, m.y + size.y / 2, m.z + size.z),
            new Isometric(m.x + size.x / 2, m.y - size.y / 2, m.z + size.z),
            new Isometric(m.x - size.x / 2, m.y - size.y / 2, m.z + size.z),
            new Isometric(m.x - size.x / 2, m.y + size.y / 2, m.z),
            new Isometric(m.x + size.x / 2, m.y + size.y / 2, m.z),
            new Isometric(m.x + size.x / 2, m.y - size.y / 2, m.z),
            new Isometric(m.x - size.x / 2, m.y - size.y / 2, m.z)
        };
    }

    @Override
    protected void drawObject(Painter painter, Vector2 position, Camera camera) {
        for(int i = 0; i < 4; i++) {
            Vector2 a = getVertices()[i].toDisplayPosition(camera).vector();
            Vector2 b = getVertices()[(i + 1) % 4].toDisplayPosition(camera).vector();
            Vector2 c = getVertices()[i + 4].toDisplayPosition(camera).vector();
            Vector2 d = getVertices()[((i + 1) % 4) + 4].toDisplayPosition(camera).vector();

            painter.line(a.toVector2i(), b.toVector2i());
            painter.line(c.toVector2i(), d.toVector2i());
            painter.line(a.toVector2i(), c.toVector2i());
        }
    }

    @Override
    public Rectangle getRectangle() {
        Vector2 top = null;
        Vector2 bottom = null;
        Vector2 left = null;
        Vector2 right = null;
        
        for(Isometric vertex : getVertices()) {
            Vector2 v = vertex.toGlobalPosition().vector();
            
            if(top == null || v.y < top.y) {
                top = v;
            } 
            if(bottom == null || v.y > bottom.y) {
                bottom = v;
            } 
            if(left == null || v.x < left.x) {
                left = v;
            } 
            if(right == null || v.x > right.x) {
                right = v;
            } 
        }
        
        return new Rectangle(
            new Vector2(left.x, top.y),
            new Vector2(right.x - left.x, bottom.y - top.y)
        );
    }
    
}
