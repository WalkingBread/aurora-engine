package skorupinski.aurora.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import skorupinski.aurora.geometry.Shape;
import skorupinski.aurora.math.Vector2;

public class Mouse implements MouseListener, MouseMotionListener {
    
    private List<MouseButton> pressedButtons;

    private InteractiveManager interactiveManager;

    private Vector2 mousePosition;

    private Vector2 pressPosition;

    public Mouse() {
        pressedButtons = new ArrayList<>();
        pressPosition = null;

        interactiveManager = new InteractiveManager(this);
    }

    public void registerInteractive(Shape shape) {
        interactiveManager.registerInteractive(shape);
    }

    public void unregisterInteractive(Shape shape) {
        interactiveManager.unregisterInteractive(shape);
    }

    public boolean isPressed(MouseButton button) {
        if(pressedButtons.contains(button)) {
            return true;
        }
        return false;
    }

    public boolean mousePressed() {
        if(pressedButtons.size() > 0) {
            return true;
        }
        return false;
    }


    private void setPosition(float x, float y) {
        mousePosition = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return mousePosition;
    }

    public Vector2 getPositionOnScreen() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        return new Vector2(p.x, p.y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        float x = e.getX();
        float y = e.getY();

        Vector2 move = new Vector2(x, y).substract(pressPosition);

        setPosition(x, y);

        interactiveManager.checkInteractivesHovered();
        interactiveManager.dragForAll(move);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        float x = e.getX();
        float y = e.getY();

        setPosition(x, y);

        interactiveManager.checkInteractivesHovered();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        MouseButton button = MouseButton.getButton(code);

        pressedButtons.add(button);
        pressPosition = new Vector2(e.getX(), e.getY());

        interactiveManager.pressForAll(button);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        MouseButton button = MouseButton.getButton(code);

        pressedButtons.remove(MouseButton.getButton(code));
        pressPosition = null;

        interactiveManager.releaseForAll(button);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int code = e.getButton();
        MouseButton button = MouseButton.getButton(code);

        interactiveManager.clickForAll(button);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


}
