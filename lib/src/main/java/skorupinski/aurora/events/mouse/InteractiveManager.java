package skorupinski.aurora.events.mouse;

import java.util.ArrayList;
import java.util.List;

import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.math.Vector2;

public class InteractiveManager {

    private List<MouseSensitive> interactives;

    private Mouse mouse;
    
    InteractiveManager(Mouse mouse) {
        interactives = new ArrayList<>();

        this.mouse = mouse;
    }

    void checkInteractivesHovered() {
        for(MouseSensitive ms : interactives) {
            Interactive i  = (Interactive) ms.shape;
            if(ms.isHovered(mouse) && !ms.alreadyHovered) {
                i.onHover();
                ms.alreadyHovered = true;
            } else if(!ms.isHovered(mouse) && ms.alreadyHovered) {
                i.onUnhover();
                ms.alreadyHovered = false;
            }
        }
    }

    boolean containsInteractive(Shape shape) {
        for(MouseSensitive ms : interactives) {
            if(shape == ms.shape) {
                return true;
            }
        }
        return false;
    }

    MouseSensitive getMouseSensitiveBy(Shape shape) {
        for(MouseSensitive ms : interactives) {
            if(shape == ms.shape) {
                return ms;
            }
        }
        return null;
    }

    void registerInteractive(Shape shape) {
        if(shape instanceof Interactive) {
            if(!containsInteractive(shape)) {
                interactives.add(new MouseSensitive(shape));
            }
        } else {
            throw new IllegalStateException("Is not an interactive.");
        }
    }

    void unregisterInteractive(Shape shape) {
        if(containsInteractive(shape)) {
            interactives.remove(getMouseSensitiveBy(shape));
        }
    }

    void dragForAll(Vector2 move) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.shape;
                i.onDrag(move);
            }
        }
    }

    void clickForAll(MouseButton button) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.shape;
                i.onClick(button);
            }
        }
    }

    void pressForAll(MouseButton button) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.shape;
                i.onPress(button);
            }
        }
    }

    void releaseForAll(MouseButton button) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.shape;
                i.onRelease(button);
            }
        }
    }
}
