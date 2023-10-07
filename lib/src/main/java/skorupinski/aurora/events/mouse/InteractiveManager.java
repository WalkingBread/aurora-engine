package skorupinski.aurora.events.mouse;

import java.util.ArrayList;
import java.util.List;

import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.rendering.Renderable;

public class InteractiveManager {

    private List<MouseSensitive> interactives;

    private Mouse mouse;
    
    InteractiveManager(Mouse mouse) {
        interactives = new ArrayList<>();
        this.mouse = mouse;
    }

    void checkInteractivesHovered() {
        for(MouseSensitive ms : interactives) {
            Interactive i  = (Interactive) ms.renderable;
            if(ms.isHovered(mouse) && !ms.alreadyHovered) {
                i.onHover();
                ms.alreadyHovered = true;
            } else if(!ms.isHovered(mouse) && ms.alreadyHovered) {
                i.onUnhover();
                ms.alreadyHovered = false;
            }
        }
    }

    boolean containsInteractive(Renderable<?> renderable) {
        return getMouseSensitiveBy(renderable) != null;
    }

    MouseSensitive getMouseSensitiveBy(Renderable<?> renderable) {
        for(MouseSensitive ms : interactives) {
            if(ms.renderable == renderable) {
                return ms;
            }
        }
        return null;
    }

    void registerInteractive(Renderable<?> renderable) {
        if(renderable instanceof Interactive) {
            if(!containsInteractive(renderable)) {
                interactives.add(new MouseSensitive(renderable));
            }
        } else {
            throw new IllegalStateException("Is not an interactive.");
        }
    }

    void unregisterInteractive(Renderable<?> renderable) {
        if(containsInteractive(renderable)) {
            interactives.remove(getMouseSensitiveBy(renderable));
        }
    }

    void dragForAll(Vector2 move) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.renderable;
                i.onDrag(move);
            }
        }
    }

    void clickForAll(MouseButton button) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.renderable;
                i.onClick(button);
            }
        }
    }

    void pressForAll(MouseButton button) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.renderable;
                i.onPress(button);
            }
        }
    }

    void releaseForAll(MouseButton button) {
        for(MouseSensitive ms : interactives) {
            if(ms.isHovered(mouse)) {
                Interactive i  = (Interactive) ms.renderable;
                i.onRelease(button);
            }
        }
    }
}
