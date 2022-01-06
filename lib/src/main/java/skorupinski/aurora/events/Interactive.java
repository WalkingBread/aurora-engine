package skorupinski.aurora.events;

import skorupinski.aurora.math.Vector2;

public interface Interactive {
    
    public void onHover();

    public void onUnhover();
    
    public void onClick(MouseButton button);

    public void onPress(MouseButton button);

    public void onRelease(MouseButton button);

    public void onDrag(Vector2 move);
}