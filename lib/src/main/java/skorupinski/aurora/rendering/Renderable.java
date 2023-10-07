package skorupinski.aurora.rendering;

import skorupinski.aurora.geometry.positions.Display;
import skorupinski.aurora.geometry.positions.Global;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.geometry.positions.Position;
import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;

public abstract class Renderable<T extends Position> {
    protected T position;

    public Renderable(T position) {
        this.position = position;
    }

    protected abstract void draw(Painter painter, Vector2 position, Camera camera);

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

        draw(painter, displayPosition, camera);
    }

    public abstract Shape getShape();

    public T getPosition() {
        return position;
    }
    
}
