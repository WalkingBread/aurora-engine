package skorupinski.aurora.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Keyboard implements KeyListener {

    private List<Integer> pressedKeys;

    public Keyboard() {
        pressedKeys = new ArrayList<>();
    }

    public boolean isPressed(int code) {
        if(pressedKeys.contains(code)) {
            return true;
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(!pressedKeys.contains(code)) {
            pressedKeys.add(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        pressedKeys.remove(Integer.valueOf(code));
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
}