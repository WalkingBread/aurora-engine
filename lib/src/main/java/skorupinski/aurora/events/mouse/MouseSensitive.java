package skorupinski.aurora.events.mouse;

import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.rendering.Renderable;

public class MouseSensitive {

    Renderable<?> renderable;

    boolean alreadyHovered;

    MouseSensitive(Renderable<?> renderable) {
        this.renderable = renderable;
    }
    
    boolean isHovered(Mouse mouse) {
        return renderable.getShape().collidesWith(mouse.getPosition());
    }
    Shape getShape() {
        return renderable.getShape();
    }

}