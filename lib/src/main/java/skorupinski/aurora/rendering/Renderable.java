package skorupinski.aurora.rendering;

import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.geometry.positions.Display;
import skorupinski.aurora.geometry.positions.Global;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.geometry.positions.Position;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;

public abstract class Renderable<T extends Position> {
    protected T position;
    protected Vector2 boxSize;

    public Renderable(T position, Vector2 boxSize, Camera camera) {
        this.position = position;
        this.boxSize = boxSize;
    }

    public abstract void draw(Painter painter, Vector2 position);

    public void display(Camera camera, Painter painter) {
        Vector2 displayPosition = null;

        if(position instanceof Isometric) {
            Isometric i = (Isometric) position;
            displayPosition = i.toDisplayPosition(camera).vector();

        } else if(position instanceof Global) {
            Global g = (Global) position;
            displayPosition = g.toDisplayPosition(camera).vector();

        } else {
            Display d = (Display) position;
            displayPosition = d.vector();
        }

        draw(painter, displayPosition);
    }

    public Rectangle getRectangle() {
        Vector2 pos = ((Display) position).vector();
        return new Rectangle(pos, boxSize);
    }
    
}
