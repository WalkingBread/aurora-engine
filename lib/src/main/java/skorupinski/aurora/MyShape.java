package skorupinski.aurora;

import skorupinski.aurora.events.Interactive;
import skorupinski.aurora.events.MouseButton;
import skorupinski.aurora.game.Game;
import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.math.Vector2;

public class MyShape extends Rectangle implements Interactive {

    public MyShape() {
        super(100, 100, 100, 100);

        Game.mouse().registerInteractive(this);
    }

    @Override
    public void onHover() {
        System.out.println("welcome");
    }

    @Override
    public void onUnhover() {
        System.out.println("naura");
        
    }

    @Override
    public void onClick(MouseButton button) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPress(MouseButton button) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onRelease(MouseButton button) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDrag(Vector2 move) {
        // TODO Auto-generated method stub
        
    }
    
}
