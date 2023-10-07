package skorupinski.aurora;
import java.awt.Color;

import skorupinski.aurora.events.mouse.MouseButton;
import skorupinski.aurora.game.Game;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.ui.Button;

public class MyButton extends Button {

    public Color color;

    public MyButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        color = Color.BLACK;
    }

    @Override
    public void onHover() {
        color = Color.WHITE;
    }

    @Override
    public void onUnhover() {
        color = Color.BLACK;
    }

    @Override
    public void onClick(MouseButton button) {
        
    }

    @Override
    public void onPress(MouseButton button) {
        color = Color.RED;
    }

    @Override
    public void onRelease(MouseButton button) {
        color = Color.WHITE;
    }

    @Override
    public void onDrag(Vector2 move) {

    }

    @Override
    protected void draw(Painter painter, Vector2 position, Camera camera) {
        painter.color(color);
        painter.fill(getShape());
    }
    
}
