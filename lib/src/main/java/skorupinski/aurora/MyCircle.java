package skorupinski.aurora;

import skorupinski.aurora.events.mouse.Interactive;
import skorupinski.aurora.events.mouse.MouseButton;
import skorupinski.aurora.game.Game;
import skorupinski.aurora.geometry.shapes.Circle;
import skorupinski.aurora.math.Vector2;

public class MyCircle extends Circle implements Interactive {

    public MyCircle(float x, float y, float radius) {
        super(x, y, radius);
        Game.mouse().registerInteractive(this);
    }

    @Override
    public void onHover() {
        
    }

    @Override
    public void onUnhover() {

    }

    @Override
    public void onClick(MouseButton button) {
        System.out.println("Clicked");
    }

    @Override
    public void onPress(MouseButton button) {

    }

    @Override
    public void onRelease(MouseButton button) {

    }

    @Override
    public void onDrag(Vector2 move) {

    }


    
}
