package skorupinski.aurora.ui;

import skorupinski.aurora.events.mouse.Interactive;
import skorupinski.aurora.game.Game;
import skorupinski.aurora.geometry.positions.Global;
import skorupinski.aurora.geometry.shapes.Rectangle;
import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.rendering.Renderable;

public abstract class Button extends Renderable<Global> implements Interactive {

    private Vector2 size;

    public Button(Vector2 position, Vector2 size) {
        super(new Global(position));
        this.size = size;
        Game.mouse().registerInteractive(this);
    }

    public Button(int x, int y, int width, int height) {
        super(new Global(new Vector2(x, y)));
        this.size = new Vector2(width, height);
        Game.mouse().registerInteractive(this);
    }


    @Override
    public Shape getShape() {
        return new Rectangle(position.vector(), size);
    }
}
